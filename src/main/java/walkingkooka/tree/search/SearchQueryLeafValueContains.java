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

import walkingkooka.Cast;
import walkingkooka.collect.list.Lists;
import walkingkooka.text.CaseSensitivity;

import java.util.List;
import java.util.Objects;

/**
 * Searches the text of a {@link SearchQueryValue} if it contains text.
 */
@SuppressWarnings("lgtm[java/inconsistent-equals-and-hashcode]")
final class SearchQueryLeafValueContains extends SearchQueryLeaf<TextSearchQueryValue> {

    static SearchQueryLeafValueContains with(final TextSearchQueryValue value, final CaseSensitivity caseSensitivity) {
        Objects.requireNonNull(caseSensitivity, "caseSensitivity");

        return new SearchQueryLeafValueContains(value, caseSensitivity);
    }

    private SearchQueryLeafValueContains(final TextSearchQueryValue value, final CaseSensitivity caseSensitivity) {
        super(value);
        this.caseSensitivity = caseSensitivity;
    }

    @Override
    public SearchQuery not() {
        throw new UnsupportedOperationException(); // SearchContainsNotQuery
    }

    @Override
    final void visit(final BigDecimalSearchNode node, final SearchQueryContext context) {
        this.visit0(node, context);
    }

    @Override
    final void visit(final BigIntegerSearchNode node, final SearchQueryContext context) {
        this.visit0(node, context);
    }

    @Override
    final void visit(final DoubleSearchNode node, final SearchQueryContext context) {
        this.visit0(node, context);
    }

    @Override
    final void visit(final LocalDateSearchNode node, final SearchQueryContext context) {
        this.visit0(node, context);
    }

    @Override
    final void visit(final LocalDateTimeSearchNode node, final SearchQueryContext context) {
        this.visit0(node, context);
    }

    @Override
    final void visit(final LocalTimeSearchNode node, final SearchQueryContext context) {
        this.visit0(node, context);
    }

    @Override
    final void visit(final LongSearchNode node, final SearchQueryContext context) {
        this.visit0(node, context);
    }

    @Override
    final void visit(final MetaSearchNode node, final SearchQueryContext context) {
        node.select(this, context);
    }

    @Override
    final void visit(final TextSearchNode node, final SearchQueryContext context) {
        this.visit0(node, context);
    }

    /**
     * Scans the text of the given {@link SearchNode} honouring the {@link CaseSensitivity}, making {@link TextSearchNode}
     * from each match.
     */
    private void visit0(final SearchNode node, final SearchQueryContext context) {
        final String nodeText = node.text();
        final String searchForText = this.value.text();
        final CaseSensitivity caseSensitivity = this.caseSensitivity;

        int replaceBegin = caseSensitivity.indexOf(nodeText, searchForText);
        if (-1 != replaceBegin) {
            // at least one part found...
            final int searchForTextLength = searchForText.length();

            SearchNode current = node;
            do {
                final int replaceEnd = replaceBegin + searchForTextLength;
                final SearchNode extracted = current.extract(replaceBegin, replaceEnd)
                        .selected();
                current = current.replace(replaceBegin,
                        replaceEnd,
                        extracted);

                if (current.isSequence()) {
                    final SequenceSearchNode sequence = current.cast();

                    final List<SearchNode> newChildren = Lists.array();
                    newChildren.addAll(sequence.children());

                    final int lastIndex = newChildren.size() - 1;
                    final SearchNode last = newChildren.get(lastIndex);
                    if (last.isSequence()) {
                        newChildren.remove(lastIndex);
                        final SequenceSearchNode lastAsSequence = last.cast();
                        newChildren.addAll(lastAsSequence.children());
                    }

                    current = sequence.setChildren(newChildren);
                }

                replaceBegin = caseSensitivity.indexOf(nodeText, searchForText, replaceEnd);
            } while (-1 != replaceBegin);

            context.success(node, current);
        }
    }

    private final CaseSensitivity caseSensitivity;

    @Override
    public int hashCode() {
        return Objects.hash(this.value, this.caseSensitivity);
    }

    @Override
    boolean canBeEqual(final Object other) {
        return other instanceof SearchQueryLeafValueContains;
    }

    @Override
    boolean equals0(final SearchQuery other) {
        return this.equals1(Cast.to(other));
    }

    private boolean equals1(final SearchQueryLeafValueContains other) {
        return this.value.equals(other.value) &&
                this.caseSensitivity.equals(other.caseSensitivity);
    }

    @Override
    void toStringPrefix(final StringBuilder b) {
        b.append('~');
    }
}
