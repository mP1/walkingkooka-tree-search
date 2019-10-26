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
import walkingkooka.visit.Visiting;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A container or parent for one or more {@link SearchNode}.
 */
public final class SearchSelectNode extends SearchParentNode2 {

    @SuppressWarnings("WeakerAccess")
    public final static SearchNodeName NAME = SearchNodeName.fromClass(SearchSelectNode.class);

    /**
     * Factory that wraps the child or casts so that a {@link SearchSelectNode}
     * is returned.
     */
    static SearchSelectNode with(final SearchNode child) {
        Objects.requireNonNull(child, "child");
        return child.isSelect() ?
                child.cast() :
                new SearchSelectNode(NO_INDEX, NAME, Lists.of(child));
    }

    /**
     * Private ctor to limit sub classing.
     */
    private SearchSelectNode(final int index, final SearchNodeName name, final List<SearchNode> children) {
        super(index, name, children);
    }

    @Override
    SearchNodeName defaultName() {
        return NAME;
    }

    @Override
    public SearchSelectNode setName(final SearchNodeName name) {
        return super.setName0(name).cast();
    }

    @Override
    public SearchSelectNode removeParent() {
        return this.removeParent0().cast();
    }

    public SearchNode child() {
        return this.children().get(0);
    }

    @Override
    public SearchSelectNode setChildren(final List<SearchNode> children) {
        return this.setChildren0(children).cast();
    }

    /**
     * While copying unwraps any {@link SearchIgnoredNode}
     */
    @Override
    final List<SearchNode> copyChildren(final List<SearchNode> children) {
        return Lists.immutable(children.stream()
                .map(SearchSelectNode::maybeUnwrap)
                .collect(Collectors.toList()));
    }

    private static SearchNode maybeUnwrap(final SearchNode node) {
        return node.isSelect() ?
                ((SearchSelectNode) node).child() :
                node;
    }

    @Override
    void replaceChildrenCheck(final List<SearchNode> children) {
        final int count = children.size();
        if (1 != count) {
            throw new IllegalArgumentException("Expected only 1 child but got " + count + "=" + children);
        }
    }

    @Override
    SearchParentNode replace0(final int index, final SearchNodeName name, final List<SearchNode> children) {
        return new SearchSelectNode(index, name, children);
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
    final SearchNode extract0(final int beginOffset, final int endOffset, final String text) {
        return this.text1(beginOffset, endOffset, text).selected();
    }

    // SearchQuery .....................................................................................................

    @Override
    void select(final SearchQuery query, final SearchQueryContext context) {
        // nop cant "search" a select.
    }

    @Override
    public SearchIgnoredNode ignored() {
        return SearchNode.ignored(this.child());
    }

    @Override
    public SearchSelectNode selected() {
        return this;
    }

    @Override
    SearchNode replaceSelected0(final Function<SearchSelectNode, SearchNode> replacer) {
        SearchNode answer = replacer.apply(this);
        return this == answer ?
                this :
                answer.replaceChild(this.parent(), this.index());
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
        return other instanceof SearchSelectNode;
    }

    @Override
    String toStringPrefix() {
        return "< ";
    }

    @Override
    String toStringSuffix() {
        return " >";
    }
}
