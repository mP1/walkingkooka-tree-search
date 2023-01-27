/*
 * Copyright 2019 Miroslav Pokorny (github.com/mP1)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package walkingkooka.tree.search;

import org.junit.jupiter.api.Test;
import walkingkooka.collect.list.Lists;
import walkingkooka.collect.map.Maps;
import walkingkooka.text.CaseSensitivity;
import walkingkooka.visit.Visiting;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class MetaSearchNodeTest extends SearchNodeParentTestCase<MetaSearchNode> {

    private final static String CHILD_TEXT = "child";

    @Test
    public void testWithNullFails() {
        assertThrows(NullPointerException.class, () -> MetaSearchNode.with(null, this.attributes()));
    }

    @Test
    public void testWithNullAttributesFails() {
        assertThrows(NullPointerException.class, () -> MetaSearchNode.with(this.child(), null));
    }

    @Test
    public void testWithEmptyAttributesFails() {
        assertThrows(IllegalArgumentException.class, () -> MetaSearchNode.with(this.child(), MetaSearchNode.NO_ATTRIBUTES));
    }

    @Test
    public void testWith() {
        final TextSearchNode child = this.child();
        final Map<SearchNodeAttributeName, String> attributes = this.attributes();
        final MetaSearchNode node = MetaSearchNode.with(child, attributes);
        this.checkAttributes(node, attributes);
        this.checkChildren(node, Lists.of(child));
    }

    @Test
    public void testSetChildrenIncorrectCountFails() {
        assertThrows(IllegalArgumentException.class, () -> this.createSearchNode().setChildren(Lists.of(this.text("child-1"), this.text("child-2"))));
    }

    @Test
    public void testSetDifferentChildren() {
        this.setChildrenDifferent(Lists.of(this.text("different")));
    }

    @Test
    public void testReplaceAll() {
        final MetaSearchNode node = this.createSearchNode();
        final SearchNode replace = this.replaceNode();
        this.checkEquals(replace.selected(), node.replace(0, node.text().length(), replace));
    }

    @Test
    public void testReplace() {
        final MetaSearchNode node = MetaSearchNode.with(this.text("123"), this.attributes());
        final TextSearchNode replacing = this.text("REPLACEMENT");
        final SearchNode replaced = node.replace(1, 2, replacing);
        this.checkEquals(this.sequence(this.text("1"), replacing, this.text("3")).selected(), replaced);
    }

    @Override
    public void testReplaceChild() {
    }

    @Test
    public void testAppendChild() {
        assertThrows(UnsupportedOperationException.class, () -> this.createSearchNode().appendChild(this.text("append-fails")));
    }

    @Override
    public void testAppendChild2() {
    }

    @Test
    public void testRemoveChildFirst() {
        assertThrows(UnsupportedOperationException.class, () -> this.createSearchNode().removeChild(0));
    }

    @Override
    public void testRemoveChildLast() {
    }

    @Override
    public void testParentWithoutChild() {
    }

    @Test
    public void testSetAttributesSame() {
        final SearchNode node = this.createSearchNode();
        assertSame(node, node.setAttributes(this.attributes()));
    }

    @Test
    public void testSetAttributesDifferent() {
        final MetaSearchNode node = this.createSearchNode();

        final Map<SearchNodeAttributeName, String> differentAttributes = this.differentAttributes();
        final SearchNode different = node.setAttributes(differentAttributes);
        assertNotSame(node, different);

        this.checkAttributes(different, differentAttributes);
        this.checkAttributes(node, this.attributes());
    }

    @Test
    public void testSetAttributesDifferent2() {
        final SelectSearchNode selected = this.createSearchNode().selected();
        final MetaSearchNode meta = selected.child().cast();

        final Map<SearchNodeAttributeName, String> differentAttributes = this.differentAttributes();
        final SearchNode different = meta.setAttributes(differentAttributes);
        this.checkAttributes(different, differentAttributes);
    }

    @Test
    public void testText() {
        this.checkEquals(this.child().text(), this.createSearchNode().text());
    }

    @Test
    public void testIgnored() {
        final SearchNode node = this.createSearchNode();
        assertNotSame(node, node.ignored());
    }

    @Test
    public void testMetaSameAttributes() {
        final SearchNode node = this.createSearchNode();
        assertSame(node, node.setAttributes(this.attributes()));
    }

    @Test
    public void testMetaDifferentAttributes() {
        final MetaSearchNode node = this.createSearchNode();

        final Map<SearchNodeAttributeName, String> attributes = this.differentAttributes();
        final SearchNode different = node.setAttributes(attributes);
        assertNotSame(different, attributes);

        this.checkAttributes(different, attributes);
        this.checkAttributes(node, this.attributes());
    }

    @Test
    public void testSelected() {
        final SearchNode node = this.createSearchNode();
        assertNotSame(node, node.selected());
    }

    @Test
    public void testReplaceSelectedWithout() {
        this.replaceSelectedWithoutSelectedAndCheck(this.createSearchNode());
    }

    @Test
    public void testReplaceSelectedNothingReplaced() {
        final SearchNode node = this.text("will-be-replaced")
                .selected()
                .setAttributes(this.attributes());

        this.replaceSelectedNothingAndCheck(node);
    }

    @Test
    public void testReplaceSelected() {
        final SearchNode node = this.text("will-be-replaced")
                .selected()
                .setAttributes(this.attributes());
        final SearchNode replaced = this.text("replaced");

        this.replaceSelectedAndCheck(node,
                (n) -> replaced,
                replaced.setAttributes(this.attributes()));
    }

    @Test
    public void testQuery() {
        final MetaSearchNode node = this.createSearchNode();
        final SearchQuery query = SearchQueryValue.text(CHILD_TEXT).equalsQuery(CaseSensitivity.SENSITIVE);
        this.checkEquals(this.child().selected().setAttributes(this.attributes()),
                query.select(node),
                "child selected");
    }

    @Test
    public void testAccept() {
        final StringBuilder b = new StringBuilder();
        final MetaSearchNode node = this.createSearchNode();
        final TextSearchNode child = node.children().get(0).cast();

        new FakeSearchNodeVisitor() {
            @Override
            protected Visiting startVisit(final SearchNode n) {
                b.append("1");
                return Visiting.CONTINUE;
            }

            @Override
            protected void endVisit(final SearchNode n) {
                b.append("2");
            }

            @Override
            protected Visiting startVisit(final MetaSearchNode n) {
                assertSame(node, n);
                b.append("3");
                return Visiting.CONTINUE;
            }

            @Override
            protected void endVisit(final MetaSearchNode n) {
                assertSame(node, n);
                b.append("4");
            }

            @Override
            protected void visit(final TextSearchNode n) {
                assertSame(child, n);
                b.append("5");
            }
        }.accept(node);

        this.checkEquals("1315242", b.toString());
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createSearchNode(),
                "( \"child\" {attribute-1=attribute-value-1})");
    }

    @Test
    public void testToStringWithName() {
        this.toStringAndCheck(this.createSearchNode().setName(SearchNodeName.with("Name123")),
                "Name123( \"child\" {attribute-1=attribute-value-1})");
    }

    @Override
    MetaSearchNode createSearchNode() {
        return MetaSearchNode.with(this.child(), this.attributes());
    }

    private Map<SearchNodeAttributeName, String> attributes() {
        return Maps.of(SearchNodeAttributeName.with("attribute-1"), "attribute-value-1");
    }

    private Map<SearchNodeAttributeName, String> differentAttributes() {
        return Maps.of(SearchNodeAttributeName.with("attribute-2"), "attribute-value-2");
    }

    @Override
    List<SearchNode> children() {
        return Lists.of(this.child());
    }

    private TextSearchNode child() {
        return this.text(CHILD_TEXT);
    }

    private void checkAttributes(final SearchNode node, final Map<SearchNodeAttributeName, String> attributes) {
        this.checkEquals(attributes, node.attributes(), "attributes");
    }

    @Override
    Class<MetaSearchNode> searchNodeType() {
        return MetaSearchNode.class;
    }
}
