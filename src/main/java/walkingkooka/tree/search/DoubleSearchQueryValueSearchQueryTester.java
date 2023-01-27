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

final class DoubleSearchQueryValueSearchQueryTester extends SearchQueryValueSearchQueryTesterComparable<Double> {

    static DoubleSearchQueryValueSearchQueryTester with(final Double value, final SearchQueryValueSearchQueryTesterComparisonPredicate predicate) {
        return new DoubleSearchQueryValueSearchQueryTester(value, predicate);
    }

    private DoubleSearchQueryValueSearchQueryTester(final Double value, final SearchQueryValueSearchQueryTesterComparisonPredicate predicate) {
        super(value, predicate);
    }

    @Override
    DoubleSearchQueryValueSearchQueryTester not() {
        return new DoubleSearchQueryValueSearchQueryTester(this.value, this.predicate.not());
    }

    @Override
    boolean test(final BigDecimalSearchNode node) {
        return false;
    }

    @Override
    boolean test(final BigIntegerSearchNode node) {
        return false;
    }

    @Override
    boolean test(final DoubleSearchNode node) {
        return this.predicate.test(this.value, node.value());
    }

    @Override
    boolean test(final LongSearchNode node) {
        return false;
    }

    @Override
    boolean test(final TextSearchNode node) {
        return false;
    }

    @Override
    boolean canBeEqual(final Object other) {
        return other instanceof DoubleSearchQueryValueSearchQueryTester;
    }

    @Override
    boolean equals3(final SearchQueryValueSearchQueryTesterComparable other) {
        return true; // no extra properties
    }
}
