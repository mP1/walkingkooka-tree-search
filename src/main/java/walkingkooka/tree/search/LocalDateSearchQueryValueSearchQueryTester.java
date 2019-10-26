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
 * Base class for all {@link SearchQueryTester} used by {@link LocalDateSearchQueryValue}
 */
abstract class LocalDateSearchQueryValueSearchQueryTester extends SearchQueryTester<LocalDate> {

    static LocalDateSearchQueryValueSearchQueryTesterEquals equalsTester(final LocalDate value) {
        return LocalDateSearchQueryValueSearchQueryTesterEquals.with(value);
    }

    static LocalDateSearchQueryValueSearchQueryTesterGreaterThan greaterThan(final LocalDate value) {
        return LocalDateSearchQueryValueSearchQueryTesterGreaterThan.with(value);
    }

    static LocalDateSearchQueryValueSearchQueryTesterGreaterThanEquals greaterThanEquals(final LocalDate value) {
        return LocalDateSearchQueryValueSearchQueryTesterGreaterThanEquals.with(value);
    }

    static LocalDateSearchQueryValueSearchQueryTesterLessThan lessThan(final LocalDate value) {
        return LocalDateSearchQueryValueSearchQueryTesterLessThan.with(value);
    }

    static LocalDateSearchQueryValueSearchQueryTesterLessThanEquals lessThanEquals(final LocalDate value) {
        return LocalDateSearchQueryValueSearchQueryTesterLessThanEquals.with(value);
    }

    static LocalDateSearchQueryValueSearchQueryTesterNotEquals notEquals(final LocalDate value) {
        return LocalDateSearchQueryValueSearchQueryTesterNotEquals.with(value);
    }

    LocalDateSearchQueryValueSearchQueryTester(final LocalDate value) {
        super(value);
    }

    @Override
    final boolean test(final BigDecimalSearchNode node) {
        return false;
    }

    @Override
    final boolean test(final BigIntegerSearchNode node) {
        return false;
    }

    @Override
    final boolean test(final DoubleSearchNode node) {
        return false;
    }

    @Override
    final boolean test(final LocalDateTimeSearchNode node) {
        return false;
    }

    @Override
    final boolean test(final LocalTimeSearchNode node) {
        return false;
    }

    @Override
    final boolean test(final LongSearchNode node) {
        return false;
    }

    @Override
    final boolean test(final TextSearchNode node) {
        return false;
    }

    @Override
    boolean equals1(final SearchQueryTester other) {
        return true; // no extra properties to test.
    }
}
