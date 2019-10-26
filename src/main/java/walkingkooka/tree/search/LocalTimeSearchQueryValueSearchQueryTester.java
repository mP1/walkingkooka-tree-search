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
 * Base class for all {@link SearchQueryTester} used by {@link LocalTimeSearchQueryValue}
 */
abstract class LocalTimeSearchQueryValueSearchQueryTester extends SearchQueryTester<LocalTime> {

    static LocalTimeSearchQueryValueSearchQueryTesterEquals equalsTester(final LocalTime value) {
        return LocalTimeSearchQueryValueSearchQueryTesterEquals.with(value);
    }

    static LocalTimeSearchQueryValueSearchQueryTesterGreaterThan greaterThan(final LocalTime value) {
        return LocalTimeSearchQueryValueSearchQueryTesterGreaterThan.with(value);
    }

    static LocalTimeSearchQueryValueSearchQueryTesterGreaterThanEquals greaterThanEquals(final LocalTime value) {
        return LocalTimeSearchQueryValueSearchQueryTesterGreaterThanEquals.with(value);
    }

    static LocalTimeSearchQueryValueSearchQueryTesterLessThan lessThan(final LocalTime value) {
        return LocalTimeSearchQueryValueSearchQueryTesterLessThan.with(value);
    }

    static LocalTimeSearchQueryValueSearchQueryTesterLessThanEquals lessThanEquals(final LocalTime value) {
        return LocalTimeSearchQueryValueSearchQueryTesterLessThanEquals.with(value);
    }

    static LocalTimeSearchQueryValueSearchQueryTesterNotEquals notEquals(final LocalTime value) {
        return LocalTimeSearchQueryValueSearchQueryTesterNotEquals.with(value);
    }

    LocalTimeSearchQueryValueSearchQueryTester(final LocalTime value) {
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
    final boolean test(final LocalDateSearchNode node) {
        return false;
    }

    @Override
    final boolean test(final LocalDateTimeSearchNode node) {
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
