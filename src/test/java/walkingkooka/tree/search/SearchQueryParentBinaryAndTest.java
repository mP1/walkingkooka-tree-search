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

public final class SearchQueryParentBinaryAndTest extends SearchQueryParentBinaryTestCase<SearchQueryParentBinaryAnd> {

    @Test
    public void testNeither() {
        final BigDecimalSearchNode node = this.bigDecimalNode(VALUE);

        final SearchQuery query = this.bigDecimalQueryValue(VALUE_LT).equalsQuery()
                .and(this.bigDecimalQueryValue(VALUE_GT).equalsQuery());

        this.querySelectAndCheck(query, node, node);
    }

    @Test
    public void testBoth() {
        final BigIntegerSearchNode node = this.bigIntegerNode(VALUE);

        final SearchQuery query = this.bigIntegerQueryValue(VALUE_LT).greaterThan()
                .and(this.bigIntegerQueryValue(VALUE_GT).lessThan());

        this.querySelectAndCheck(query, node, node.selected());
    }

    @Test
    public void testOnlyLeft() {
        final DoubleSearchNode node = this.doubleNode(VALUE);

        final SearchQuery query = this.doubleQueryValue(VALUE).equalsQuery()
                .and(this.doubleQueryValue(VALUE_LT).equalsQuery());

        this.querySelectAndCheck(query, node, node);
    }

    @Test
    public void testOnlyRight() {
        final LongSearchNode node = this.longNode(VALUE);

        final SearchQuery query = this.longQueryValue(VALUE_LT).equalsQuery()
                .and(this.longQueryValue(VALUE).equalsQuery());

        this.querySelectAndCheck(query, node, node);
    }

    @Test
    public void testBothLocalDate() {
        final LocalDateSearchNode node = this.localDateNode(DATE);

        final SearchQuery query = this.localDateQueryValue(DATE_LT).greaterThan()
                .and(this.localDateQueryValue(DATE_GT).lessThan());

        this.querySelectAndCheck(query, node, node.selected());
    }

    @Test
    public void testBothLocalDateTime() {
        final LocalDateTimeSearchNode node = this.localDateTimeNode(DATETIME);

        final SearchQuery query = this.localDateTimeQueryValue(DATETIME_LT).greaterThan()
                .and(this.localDateTimeQueryValue(DATETIME_GT).lessThan());

        this.querySelectAndCheck(query, node, node.selected());
    }

    @Test
    public void testBothLocalTime() {
        final LocalTimeSearchNode node = this.localTimeNode(TIME);

        final SearchQuery query = this.localTimeQueryValue(TIME_LT).greaterThan()
                .and(this.localTimeQueryValue(TIME_GT).lessThan());

        this.querySelectAndCheck(query, node, node.selected());
    }

    @Override
    SearchQueryParentBinaryAnd createSearchQuery(final SearchQuery left, final SearchQuery right) {
        return SearchQueryParentBinaryAnd.with(left, right);
    }

    @Override
    public Class<SearchQueryParentBinaryAnd> type() {
        return SearchQueryParentBinaryAnd.class;
    }
}
