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

import java.time.LocalTime;

/**
 * A {@link SearchQueryTester} that only returns true if a {@link LocalTime} is greater than equal {@link LocalTimeSearchNode}.
 */
final class LocalTimeSearchQueryValueSearchQueryTesterGreaterThanEquals extends LocalTimeSearchQueryValueSearchQueryTester {

    static LocalTimeSearchQueryValueSearchQueryTesterGreaterThanEquals with(final LocalTime value) {
        return new LocalTimeSearchQueryValueSearchQueryTesterGreaterThanEquals(value);
    }

    private LocalTimeSearchQueryValueSearchQueryTesterGreaterThanEquals(final LocalTime value) {
        super(value);
    }

    @Override
    SearchQueryTester not() {
        return lessThan(this.value);
    }

    @Override
    boolean test(final LocalTimeSearchNode node) {
        final LocalTime otherValue = node.value();
        return this.value.equals(otherValue) || otherValue.isAfter(this.value);
    }

    @Override
    boolean canBeEqual(final Object other) {
        return other instanceof LocalTimeSearchQueryValueSearchQueryTesterGreaterThanEquals;
    }
}
