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

import java.time.LocalDate;

/**
 * A {@link SearchQueryTester} that only returns true if a {@link LocalDate} is less than equal {@link LocalDateSearchNode}.
 */
final class LocalDateSearchQueryValueSearchQueryTesterLessThanEquals extends LocalDateSearchQueryValueSearchQueryTester {

    static LocalDateSearchQueryValueSearchQueryTesterLessThanEquals with(final LocalDate value) {
        return new LocalDateSearchQueryValueSearchQueryTesterLessThanEquals(value);
    }

    private LocalDateSearchQueryValueSearchQueryTesterLessThanEquals(final LocalDate value) {
        super(value);
    }

    @Override
    SearchQueryTester not() {
        return greaterThan(this.value);
    }

    @Override
    boolean test(final LocalDateSearchNode node) {
        final LocalDate otherValue = node.value();
        return this.value.equals(otherValue) || otherValue.isBefore(this.value);
    }

    @Override
    boolean canBeEqual(final Object other) {
        return other instanceof LocalDateSearchQueryValueSearchQueryTesterLessThanEquals;
    }
}
