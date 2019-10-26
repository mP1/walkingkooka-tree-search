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
 * A {@link SearchQueryValue} that holds a {@link Double}
 */
public final class DoubleSearchQueryValue extends SearchQueryValueNumber<Double> {

    static DoubleSearchQueryValue with(final Double value) {
        check(value);
        return new DoubleSearchQueryValue(value);
    }

    private DoubleSearchQueryValue(final Double value) {
        super(value);
    }

    @Override
    DoubleSearchQueryValueSearchQueryTester tester(final SearchQueryValueSearchQueryTesterComparisonPredicate resultPredicate) {
        return DoubleSearchQueryValueSearchQueryTester.with(this.value, resultPredicate);
    }

    @Override
    boolean canBeEqual(final Object other) {
        return other instanceof DoubleSearchQueryValue;
    }
}
