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

import org.junit.jupiter.api.Test;
import walkingkooka.text.CaseSensitivity;

public final class SearchQueryParentUnaryNotTest extends SearchQueryParentTestCase<SearchQueryParentUnaryNot> {

    @Test
    public final void testBigDecimal() {
        final BigDecimalSearchNode node = this.bigDecimalNode(VALUE);
        final SearchQuery query = this.bigDecimalQueryValue(VALUE_LT).greaterThan()
                .and(this.bigDecimalQueryValue(VALUE_GT).lessThan())
                .not();

        this.querySelectAndCheck(query, node, node);
    }

    @Test
    public final void testBigInteger() {
        final BigIntegerSearchNode node = this.bigIntegerNode(VALUE);
        final SearchQuery query = this.bigIntegerQueryValue(VALUE_LT).greaterThan()
                .and(this.bigIntegerQueryValue(VALUE_GT).lessThan())
                .not();

        this.querySelectAndCheck(query, node, node);
    }

    @Test
    public final void testDouble() {
        final DoubleSearchNode node = this.doubleNode(VALUE);
        final SearchQuery query = this.doubleQueryValue(VALUE_LT).greaterThan()
                .and(this.doubleQueryValue(VALUE_GT).lessThan())
                .not();

        this.querySelectAndCheck(query, node, node);
    }

    @Test
    public final void testLocalDate() {
        final LocalDateSearchNode node = this.localDateNode(DATE);
        final SearchQuery query = this.localDateQueryValue(DATE_LT).greaterThan()
                .and(this.localDateQueryValue(DATE_GT).lessThan())
                .not();

        this.querySelectAndCheck(query, node, node);
    }

    @Test
    public final void testLocalDateTime() {
        final LocalDateTimeSearchNode node = this.localDateTimeNode(DATETIME);
        final SearchQuery query = this.localDateTimeQueryValue(DATETIME_LT).greaterThan()
                .and(this.localDateTimeQueryValue(DATETIME_GT).lessThan())
                .not();

        this.querySelectAndCheck(query, node, node);
    }

    @Test
    public final void testLocalTime() {
        final LocalTimeSearchNode node = this.localTimeNode(TIME);
        final SearchQuery query = this.localTimeQueryValue(TIME_LT).greaterThan()
                .and(this.localTimeQueryValue(TIME_GT).lessThan())
                .not();

        this.querySelectAndCheck(query, node, node);
    }

    @Test
    public final void testLong() {
        final LongSearchNode node = this.longNode(VALUE);
        final SearchQuery query = this.longQueryValue(VALUE_LT).greaterThan()
                .and(this.longQueryValue(VALUE_GT).lessThan())
                .not();

        this.querySelectAndCheck(query, node, node);
    }

    @Test
    public void testEqualsDifferentQuery() {
        this.checkNotEquals(SearchQueryParentUnaryNot.with(this.textQueryValue("different").equalsQuery(CaseSensitivity.SENSITIVE)));
    }

    @Override
    public void testNotTwiceGivesOriginalQuery() {
    }

    @Override
    SearchQueryParentUnaryNot createSearchQuery() {
        return SearchQueryParentUnaryNot.with(this.textQueryValue("query").equalsQuery(CaseSensitivity.SENSITIVE));
    }

    @Override
    public Class<SearchQueryParentUnaryNot> type() {
        return SearchQueryParentUnaryNot.class;
    }
}
