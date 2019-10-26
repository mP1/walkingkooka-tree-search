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

/**
 * A {@link SearchNode} that holds a {@link long} value.
 */
public final class SearchLongNode extends SearchLeafNode<Long> {

    @SuppressWarnings("WeakerAccess")
    public final static SearchNodeName NAME = SearchNodeName.fromClass(SearchLongNode.class);

    static SearchLongNode with(final String text, final Long value) {
        check(text, value);

        return new SearchLongNode(NO_INDEX, NAME, text, value);
    }

    private SearchLongNode(final int index, final SearchNodeName name, final String text, final Long value) {
        super(index, name, text, value);
    }

    @Override
    SearchNodeName defaultName() {
        return NAME;
    }

    @Override
    public SearchLongNode setName(final SearchNodeName name) {
        return super.setName0(name).cast();
    }

    @Override
    public SearchLongNode setValue(final Long value) {
        return this.setValue0(value).cast();
    }

    @Override
    SearchLongNode replace0(final int index, final SearchNodeName name, final String text, final Long value) {
        return new SearchLongNode(index, name, text, value);
    }

    @Override
    public SearchLongNode removeParent() {
        return this.removeParent0().cast();
    }

    // SearchQuery......................................................................................................

    @Override
    void select(final SearchQuery query, final SearchQueryContext context) {
        query.visit(this, context);
    }

    // Visitor ..........................................................................................................

    @Override
    public void accept(final SearchNodeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    final boolean canBeEqual(final Object other) {
        return other instanceof SearchLongNode;
    }

    @Override
    final void toString1(final StringBuilder b) {
        b.append(this.value());
    }
}
