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

import walkingkooka.collect.list.Lists;
import walkingkooka.collect.map.Maps;
import walkingkooka.visit.Visiting;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * A container or parent for one or more {@link SearchNode}, adding tags or meta attributes which can then be queried
 * and matched.
 */
public final class MetaSearchNode extends SearchNodeParent {

    @SuppressWarnings("WeakerAccess")
    public final static SearchNodeName NAME = SearchNodeName.fromClass(MetaSearchNode.class);

    final static Map<SearchNodeAttributeName, String> NO_ATTRIBUTES = Maps.empty();

    static MetaSearchNode with(final SearchNode child, final Map<SearchNodeAttributeName, String> attributes) {
        Objects.requireNonNull(child, "child");

        return with0(child, copyAttributes(attributes));
    }

    /**
     * Creates a new {@link MetaSearchNode} without taking a defensive copy.
     */
    static MetaSearchNode with0(final SearchNode child, final Map<SearchNodeAttributeName, String> attributes) {
        return new MetaSearchNode(NO_INDEX, NAME, Lists.of(child), Maps.readOnly(attributes));
    }

    /**
     * Private ctor use either factory.
     */
    private MetaSearchNode(final int index,
                           final SearchNodeName name,
                           final List<SearchNode> children,
                           final Map<SearchNodeAttributeName, String> attributes) {
        super(index, name, children);
        if (attributes.isEmpty()) {
            throw new IllegalArgumentException("Attributes must not be empty");
        }
        this.attributes = attributes;
    }

    @Override
    SearchNodeName defaultName() {
        return NAME;
    }

    @Override
    public MetaSearchNode setName(final SearchNodeName name) {
        return super.setName0(name).cast();
    }

    @Override
    public MetaSearchNode removeParent() {
        return this.removeParent0().cast();
    }

    @SuppressWarnings("WeakerAccess")
    public SearchNode child() {
        return this.children().get(0);
    }

    @Override
    public MetaSearchNode setChildren(final List<SearchNode> children) {
        return this.setChildren0(children).cast();
    }

    /**
     * While copying unwraps any {@link IgnoredSearchNode}
     */
    @Override
    List<SearchNode> copyChildren(final List<SearchNode> children) {
        return Lists.immutable(children);
    }

    @Override
    void replaceChildrenCheck(final List<SearchNode> children) {
        final int count = children.size();
        if (1 != count) {
            throw new IllegalArgumentException("Expected only 1 child but got " + count + "=" + children);
        }
    }

    @Override
    SearchNodeParent replace0(final int index, final SearchNodeName name, final List<SearchNode> children) {
        return new MetaSearchNode(index, name, children, this.attributes);
    }

    @Override
    public SearchNode appendChild(final SearchNode child) {
        throw new UnsupportedOperationException();
    }

    @Override
    public SearchNode removeChild(final int child) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<SearchNodeAttributeName, String> attributes() {
        return this.attributes;
    }

    private final Map<SearchNodeAttributeName, String> attributes;

    /**
     * Would be setter that returns a {@link MetaSearchNode} with the given attributes, or this if attributes also match.
     */
    @Override
    MetaSearchNode setAttributes0(final Map<SearchNodeAttributeName, String> attributes) {
        return this.attributes.equals(attributes) ?
                this :
                new MetaSearchNode(this.index, this.name, this.children, attributes)
                        .replaceChild(this.parent(), this.index)
                        .cast();
    }

    @Override
    SearchNode replaceAll(final SearchNode replace) {
        return this.replaceAll0(replace).selected();
    }

    @Override
    SearchNode replace0(final int beginOffset, final int endOffset, final SearchNode replace, final String text) {
        return this.child()
                .replace0(beginOffset, endOffset, replace, text)
                .selected();
    }

    @Override
    SearchNode extract0(final int beginOffset, final int endOffset, final String text) {
        return this.text1(beginOffset, endOffset, text).selected();
    }

    // HasText ........................................................................................................

    @Override
    public String text() {
        return this.child().text();
    }

    @Override
    void appendText(final StringBuilder b) {
        this.child().appendText(b);
    }

    // HasTextLength...............................................................................................

    @Override
    public int textLength() {
        return this.child().textLength();
    }

    // SearchQuery ...............................................................................................

    @Override
    void select(final SearchQuery query, final SearchQueryContext context) {
        query.visit(this, context);
        this.child().select(query, context);
    }

    @Override
    public IgnoredSearchNode ignored() {
        return SearchNode.ignored(this.child());
    }

    @Override
    public SelectSearchNode selected() {
        return SearchNode.select(this);
    }

    /**
     * Loop over all children and perform {@link #replaceSelected(Function)} and then calling setChildren.
     */
    @Override
    SearchNode replaceSelected0(final Function<SelectSearchNode, SearchNode> replacer) {
        return this.setChildren(Lists.of(this.child().replaceSelected0(replacer)));
    }

    // Visitor.........................................................................................................

    @Override
    public void accept(final SearchNodeVisitor visitor) {
        if (Visiting.CONTINUE == visitor.startVisit(this)) {
            this.acceptValues(visitor);
        }
        visitor.endVisit(this);
    }

    // Object.........................................................................................................

    @Override
    boolean canBeEqual(final Object other) {
        return other instanceof MetaSearchNode;
    }

    @Override
    boolean equalsIgnoringParentAndChildren0(final SearchNode other) {
        return this.canBeEqual(other) && this.equalsIgnoringParentAndChildren1(other.cast());
    }

    private boolean equalsIgnoringParentAndChildren1(final MetaSearchNode other) {
        return this.attributes.equals(other.attributes);
    }

    @Override
    String toStringPrefix() {
        return "( ";
    }

    @Override
    void toStringSuffix(final StringBuilder b) {
        b.append(" ");
        b.append(this.attributes());
        b.append(')');
    }
}
