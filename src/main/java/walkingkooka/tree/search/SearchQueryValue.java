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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Holds a query value and provides factory methods to create {@link SearchQuery}.
 */
public abstract class SearchQueryValue {

    /**
     * {@see BigDecimalSearchQueryValue}
     */
    public static BigDecimalSearchQueryValue bigDecimal(final BigDecimal value) {
        return BigDecimalSearchQueryValue.with(value);
    }

    /**
     * {@see BigIntegerSearchQueryValue}
     */
    public static BigIntegerSearchQueryValue bigInteger(final BigInteger value) {
        return BigIntegerSearchQueryValue.with(value);
    }

    /**
     * {@see DoubleSearchQueryValue}
     */
    public static DoubleSearchQueryValue doubleValue(final double value) {
        return DoubleSearchQueryValue.with(value);
    }

    /**
     * {@see LocalDateSearchQueryValue}
     */
    public static LocalDateSearchQueryValue localDate(final LocalDate value) {
        return LocalDateSearchQueryValue.with(value);
    }

    /**
     * {@see LocalDateTimeSearchQueryValue}
     */
    public static LocalDateTimeSearchQueryValue localDateTime(final LocalDateTime value) {
        return LocalDateTimeSearchQueryValue.with(value);
    }

    /**
     * {@see LocalTimeSearchQueryValue}
     */
    public static LocalTimeSearchQueryValue localTime(final LocalTime value) {
        return LocalTimeSearchQueryValue.with(value);
    }

    /**
     * {@see LongSearchQueryValue}
     */
    public static LongSearchQueryValue longValue(final long value) {
        return LongSearchQueryValue.with(value);
    }

    /**
     * {@see SearchStringQueryValue}
     */
    public static TextSearchQueryValue text(final String value) {
        return TextSearchQueryValue.with(value);
    }

    /**
     * Used in static factory methods to verify non nullness of values.
     */
    static <T> void check(final T value) {
        Objects.requireNonNull(value, "value");
    }

    /**
     * Package private to limit sub classing.
     */
    SearchQueryValue() {
    }

    /**
     * This method is only implemented by {@link TextSearchQueryValue}
     */
    abstract String text();

    abstract Object value();

    @Override
    public final int hashCode() {
        return this.value().hashCode();
    }

    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public final boolean equals(final Object other) {
        return this == other ||
                this.canBeEqual(other) &&
                        this.equals0(Cast.to(other));
    }

    abstract boolean canBeEqual(final Object other);

    private boolean equals0(final SearchQueryValue other) {
        return this.value().equals(other.value());
    }
}
