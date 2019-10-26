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
import walkingkooka.visit.Visiting;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public final class SearchBigDecimalNodeTest extends SearchLeafNodeTestCase<SearchBigDecimalNode, BigDecimal> {

    @Test
    public void testAccept() {
        final StringBuilder b = new StringBuilder();
        final SearchBigDecimalNode node = this.createSearchNode();

        new FakeSearchNodeVisitor() {
            @Override
            protected Visiting startVisit(final SearchNode n) {
                assertSame(node, n);
                b.append("1");
                return Visiting.CONTINUE;
            }

            @Override
            protected void endVisit(final SearchNode n) {
                assertSame(node, n);
                b.append("2");
            }

            @Override
            protected void visit(final SearchBigDecimalNode n) {
                assertSame(node, n);
                b.append("3");
            }
        }.accept(node);

        assertEquals("132", b.toString());
    }

    // ToString ...................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createSearchNode("123", BigDecimal.valueOf(123)),
                "123");
    }

    @Test
    public void testToStringWithName() {
        this.toStringAndCheck(this.createSearchNode("123", BigDecimal.valueOf(123)).setName(SearchNodeName.with("Name123")),
                "Name123=123");
    }

    @Override
    SearchBigDecimalNode createSearchNode(final String text, final BigDecimal value) {
        return SearchBigDecimalNode.with(text, value);
    }

    @Override
    String text() {
        return this.value().toString();
    }

    @Override
    BigDecimal value() {
        return BigDecimal.valueOf(123.5);
    }

    @Override
    String differentText() {
        return this.differentValue().toString();
    }

    @Override
    BigDecimal differentValue() {
        return BigDecimal.valueOf(999);
    }

    @Override
    Class<SearchBigDecimalNode> searchNodeType() {
        return SearchBigDecimalNode.class;
    }
}
