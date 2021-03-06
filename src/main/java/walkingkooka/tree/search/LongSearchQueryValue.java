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
 * A {@link SearchQueryValue} that holds a {@link Long}
 */
public final class LongSearchQueryValue extends SearchQueryValueNumber<Long> {

    static LongSearchQueryValue with(final Long value) {
        check(value);
        return new LongSearchQueryValue(value);
    }

    private LongSearchQueryValue(final Long value) {
        super(value);
    }

    @Override
    LongSearchQueryValueSearchQueryTester tester(final SearchQueryValueSearchQueryTesterComparisonPredicate resultPredicate) {
        return LongSearchQueryValueSearchQueryTester.with(this.value, resultPredicate);
    }

    @Override
    boolean canBeEqual(final Object other) {
        return other instanceof LongSearchQueryValue;
    }
}
