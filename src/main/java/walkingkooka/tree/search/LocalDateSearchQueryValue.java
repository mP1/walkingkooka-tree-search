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
import java.time.format.DateTimeFormatter;

/**
 * A {@link SearchQueryValue} that holds a {@link LocalDate}
 */
public final class LocalDateSearchQueryValue extends SearchQueryValue {

    static LocalDateSearchQueryValue with(final LocalDate value) {
        check(value);
        return new LocalDateSearchQueryValue(value);
    }

    private LocalDateSearchQueryValue(final LocalDate value) {
        super();
        this.value = value;
    }

    public SearchQuery equalsQuery() {
        return SearchQuery.equalsQuery(this, LocalDateSearchQueryValueSearchQueryTester.equalsTester(this.value));
    }

    public SearchQuery greaterThan() {
        return SearchQuery.greaterThan(this, LocalDateSearchQueryValueSearchQueryTester.greaterThan(this.value));
    }

    public SearchQuery greaterThanEquals() {
        return SearchQuery.greaterThanEquals(this, LocalDateSearchQueryValueSearchQueryTester.greaterThanEquals(this.value));
    }

    public SearchQuery lessThan() {
        return SearchQuery.lessThan(this, LocalDateSearchQueryValueSearchQueryTester.lessThan(this.value));
    }

    public SearchQuery lessThanEquals() {
        return SearchQuery.lessThanEquals(this, LocalDateSearchQueryValueSearchQueryTester.lessThanEquals(this.value));
    }

    public SearchQuery notEquals() {
        return SearchQuery.notEquals(this, LocalDateSearchQueryValueSearchQueryTester.notEquals(this.value));
    }

    private final LocalDate value;

    @Override
    String text() {
        throw new UnsupportedOperationException();
    }

    @Override
    LocalDate value() {
        return this.value;
    }

    @Override
    boolean canBeEqual(final Object other) {
        return other instanceof LocalDateSearchQueryValue;
    }

    @Override
    public String toString() {
        return DateTimeFormatter.ISO_LOCAL_DATE.format(this.value);
    }
}
