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

import java.util.Objects;

/**
 * Base class for all queries that wrap another query.
 */
@SuppressWarnings("lgtm[java/inconsistent-equals-and-hashcode]")
abstract class SearchQueryParentUnary extends SearchQueryParent {

    static void check(final SearchQuery query) {
        Objects.requireNonNull(query, "query");
    }

    SearchQueryParentUnary(final SearchQuery query) {
        this.query = query;
    }

    @Override
    final void visit(final BigDecimalSearchNode node, final SearchQueryContext context) {
        this.query.visit(node, this.context(context));
    }

    @Override
    final void visit(final BigIntegerSearchNode node, final SearchQueryContext context) {
        this.query.visit(node, this.context(context));
    }

    @Override
    final void visit(final DoubleSearchNode node, final SearchQueryContext context) {
        this.query.visit(node, this.context(context));
    }

    @Override
    final void visit(final LocalDateSearchNode node, final SearchQueryContext context) {
        this.query.visit(node, this.context(context));
    }

    @Override
    final void visit(final LocalDateTimeSearchNode node, final SearchQueryContext context) {
        this.query.visit(node, this.context(context));
    }

    @Override
    final void visit(final LocalTimeSearchNode node, final SearchQueryContext context) {
        this.query.visit(node, this.context(context));
    }

    @Override
    final void visit(final LongSearchNode node, final SearchQueryContext context) {
        this.query.visit(node, this.context(context));
    }

    @Override
    final void visit(final TextSearchNode node, final SearchQueryContext context) {
        this.query.visit(node, this.context(context));
    }

    final SearchQuery query;

    /**
     * Factory that creates the unary query custom {@link SearchQueryParentUnarySearchQueryContext}.
     */
    abstract SearchQueryParentUnarySearchQueryContext context(final SearchQueryContext context);

    @Override
    public final int hashCode() {
        return Objects.hash(this.query);
    }

    @Override
    final boolean equals0(final SearchQuery other) {
        return this.equals1(Cast.to(other));
    }

    private boolean equals1(final SearchQueryParentUnary other) {
        return this.query.equals(other.query);
    }
}
