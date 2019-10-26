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

public final class SearchQueryLeafValueNotEqualsTest extends SearchQueryLeafValueTestCase<SearchQueryLeafValueNotEquals> {

    // BigDecimal......................................................................................

    @Test
    public final void testBigDecimalLess() {
        final BigDecimalSearchNode node = this.bigDecimalNode(VALUE_LT);
        final SearchQuery query = this.bigDecimalQueryValue(VALUE).notEquals();

        this.querySelectAndCheck(query, node, node.selected());
    }

    @Test
    public final void testBigDecimalEqual() {
        final BigDecimalSearchNode node = this.bigDecimalNode(VALUE);
        final SearchQuery query = this.bigDecimalQueryValue(VALUE).notEquals();

        this.querySelectAndCheck(query, node, node);
    }

    @Test
    public final void testBigDecimalGreater() {
        final BigDecimalSearchNode node = this.bigDecimalNode(VALUE_GT);
        final SearchQuery query = this.bigDecimalQueryValue(VALUE).notEquals();

        this.querySelectAndCheck(query, node, node.selected());
    }

    @Test
    public final void testBigDecimalReplaceSelection() {
        final SearchNode before = this.before();
        final SearchNode after = this.after();

        final BigDecimalSearchNode eq = this.bigDecimalNode(VALUE);
        final BigDecimalSearchNode lt = this.bigDecimalNode(VALUE_LT);
        final BigDecimalSearchNode gt = this.bigDecimalNode(VALUE_GT);

        final SearchQuery query = this.bigDecimalQueryValue(VALUE).notEquals();
        this.querySelectAndCheck(query,
                this.sequenceNode(before, lt.selected(), eq, gt.selected(), after),
                this.sequenceNode(before, lt.selected(), eq, gt.selected(), after));
    }

    @Test
    public final void testBigDecimalNewSelection() {
        final SearchNode before = this.before();
        final SearchNode after = this.after();

        final BigDecimalSearchNode eq = this.bigDecimalNode(VALUE);
        final BigDecimalSearchNode lt = this.bigDecimalNode(VALUE_LT);
        final BigDecimalSearchNode gt = this.bigDecimalNode(VALUE_GT);

        final SearchQuery query = this.bigDecimalQueryValue(VALUE).notEquals();
        this.querySelectAndCheck(query,
                this.sequenceNode(before, lt, eq, gt, after),
                this.sequenceNode(before, lt.selected(), eq, gt.selected(), after));
    }

    @Test
    public final void testBigDecimalIgnoreOtherTypes() {
        final SearchNode before = this.before();
        final SearchNode after = this.after();

        final BigDecimalSearchNode eq = this.bigDecimalNode(VALUE);
        final BigDecimalSearchNode lt = this.bigDecimalNode(VALUE_LT);
        final BigDecimalSearchNode gt = this.bigDecimalNode(VALUE_GT);

        final BigIntegerSearchNode bigIntegerNode = this.bigIntegerNode(VALUE);
        final DoubleSearchNode doubleNode = this.doubleNode(VALUE);
        final LocalDateSearchNode dateNode = this.localDateNode(DATE);
        final LocalDateTimeSearchNode dateTimeNode = this.localDateTimeNode(DATETIME);
        final LocalTimeSearchNode timeNode = this.localTimeNode(TIME);
        final LongSearchNode longNode = this.longNode(VALUE);
        final TextSearchNode textNode = this.textNode(TEXT);

        final SearchQuery query = this.bigDecimalQueryValue(VALUE).notEquals();
        this.querySelectAndCheck(query,
                this.sequenceNode(before, lt, eq, gt, after, bigIntegerNode, doubleNode, dateNode, dateTimeNode, timeNode, longNode, textNode),
                this.sequenceNode(before, lt.selected(), eq, gt.selected(), after, bigIntegerNode, doubleNode, dateNode, dateTimeNode, timeNode, longNode, textNode));
    }

    @Test
    public final void testBigDecimalLessNot() {
        final BigDecimalSearchNode node = this.bigDecimalNode(VALUE_LT);
        final SearchQuery query = this.bigDecimalQueryValue(VALUE).notEquals().not();

        this.querySelectAndCheck(query, node, node);
    }

    // BigInteger......................................................................................

    @Test
    public final void testBigIntegerLess() {
        final BigIntegerSearchNode node = this.bigIntegerNode(VALUE_LT);
        final SearchQuery query = this.bigIntegerQueryValue(VALUE).notEquals();

        this.querySelectAndCheck(query, node, node.selected());
    }

    @Test
    public final void testBigIntegerEqual() {
        final BigIntegerSearchNode node = this.bigIntegerNode(VALUE);
        final SearchQuery query = this.bigIntegerQueryValue(VALUE).notEquals();

        this.querySelectAndCheck(query, node, node);
    }

    @Test
    public final void testBigIntegerGreater() {
        final BigIntegerSearchNode node = this.bigIntegerNode(VALUE_GT);
        final SearchQuery query = this.bigIntegerQueryValue(VALUE).notEquals();

        this.querySelectAndCheck(query, node, node.selected());
    }

    @Test
    public final void testBigIntegerReplaceSelection() {
        final SearchNode before = this.before();
        final SearchNode after = this.after();

        final BigIntegerSearchNode eq = this.bigIntegerNode(VALUE);
        final BigIntegerSearchNode lt = this.bigIntegerNode(VALUE_LT);
        final BigIntegerSearchNode gt = this.bigIntegerNode(VALUE_GT);

        final SearchQuery query = this.bigIntegerQueryValue(VALUE).notEquals();
        this.querySelectAndCheck(query,
                this.sequenceNode(before, lt.selected(), eq, gt.selected(), after),
                this.sequenceNode(before, lt.selected(), eq, gt.selected(), after));
    }

    @Test
    public final void testBigIntegerNewSelection() {
        final SearchNode before = this.before();
        final SearchNode after = this.after();

        final BigIntegerSearchNode eq = this.bigIntegerNode(VALUE);
        final BigIntegerSearchNode lt = this.bigIntegerNode(VALUE_LT);
        final BigIntegerSearchNode gt = this.bigIntegerNode(VALUE_GT);

        final SearchQuery query = this.bigIntegerQueryValue(VALUE).notEquals();
        this.querySelectAndCheck(query,
                this.sequenceNode(before, lt, eq, gt, after),
                this.sequenceNode(before, lt.selected(), eq, gt.selected(), after));
    }

    @Test
    public final void testBigIntegerIgnoreOtherTypes() {
        final SearchNode before = this.before();
        final SearchNode after = this.after();

        final BigIntegerSearchNode eq = this.bigIntegerNode(VALUE);
        final BigIntegerSearchNode lt = this.bigIntegerNode(VALUE_LT);
        final BigIntegerSearchNode gt = this.bigIntegerNode(VALUE_GT);

        final BigDecimalSearchNode bigDecimalNode = this.bigDecimalNode(VALUE);
        final DoubleSearchNode doubleNode = this.doubleNode(VALUE);
        final LocalDateSearchNode dateNode = this.localDateNode(DATE);
        final LocalDateTimeSearchNode dateTimeNode = this.localDateTimeNode(DATETIME);
        final LocalTimeSearchNode timeNode = this.localTimeNode(TIME);
        final LongSearchNode longNode = this.longNode(VALUE);
        final TextSearchNode textNode = this.textNode(TEXT);

        final SearchQuery query = this.bigIntegerQueryValue(VALUE).notEquals();
        this.querySelectAndCheck(query,
                this.sequenceNode(before, lt, eq, gt, after, bigDecimalNode, doubleNode, dateNode, dateTimeNode, timeNode, longNode, textNode),
                this.sequenceNode(before, lt.selected(), eq, gt.selected(), after, bigDecimalNode, doubleNode, dateNode, dateTimeNode, timeNode, longNode, textNode));
    }

    @Test
    public final void testBigIntegerLessNot() {
        final BigIntegerSearchNode node = this.bigIntegerNode(VALUE_LT);
        final SearchQuery query = this.bigIntegerQueryValue(VALUE).notEquals().not();

        this.querySelectAndCheck(query, node, node);
    }

    // Double......................................................................................

    @Test
    public final void testDoubleLess() {
        final DoubleSearchNode node = this.doubleNode(VALUE_LT);
        final SearchQuery query = this.doubleQueryValue(VALUE).notEquals();

        this.querySelectAndCheck(query, node, node.selected());
    }

    @Test
    public final void testDoubleEqual() {
        final DoubleSearchNode node = this.doubleNode(VALUE);
        final SearchQuery query = this.doubleQueryValue(VALUE).notEquals();

        this.querySelectAndCheck(query, node, node);
    }

    @Test
    public final void testDoubleGreater() {
        final DoubleSearchNode node = this.doubleNode(VALUE_GT);
        final SearchQuery query = this.doubleQueryValue(VALUE).notEquals();

        this.querySelectAndCheck(query, node, node.selected());
    }

    @Test
    public final void testDoubleReplaceSelection() {
        final SearchNode before = this.before();
        final SearchNode after = this.after();

        final DoubleSearchNode eq = this.doubleNode(VALUE);
        final DoubleSearchNode lt = this.doubleNode(VALUE_LT);
        final DoubleSearchNode gt = this.doubleNode(VALUE_GT);

        final SearchQuery query = this.doubleQueryValue(VALUE).notEquals();
        this.querySelectAndCheck(query,
                this.sequenceNode(before, lt.selected(), eq, gt.selected(), after),
                this.sequenceNode(before, lt.selected(), eq, gt.selected(), after));
    }

    @Test
    public final void testDoubleNewSelection() {
        final SearchNode before = this.before();
        final SearchNode after = this.after();

        final DoubleSearchNode eq = this.doubleNode(VALUE);
        final DoubleSearchNode lt = this.doubleNode(VALUE_LT);
        final DoubleSearchNode gt = this.doubleNode(VALUE_GT);

        final SearchQuery query = this.doubleQueryValue(VALUE).notEquals();
        this.querySelectAndCheck(query,
                this.sequenceNode(before, lt, eq, gt, after),
                this.sequenceNode(before, lt.selected(), eq, gt.selected(), after));
    }

    @Test
    public final void testDoubleIgnoreOtherTypes() {
        final SearchNode before = this.before();
        final SearchNode after = this.after();

        final DoubleSearchNode eq = this.doubleNode(VALUE);
        final DoubleSearchNode lt = this.doubleNode(VALUE_LT);
        final DoubleSearchNode gt = this.doubleNode(VALUE_GT);

        final BigDecimalSearchNode bigDecimalNode = this.bigDecimalNode(VALUE);
        final BigIntegerSearchNode bigIntegerNode = this.bigIntegerNode(VALUE);
        final LocalDateSearchNode dateNode = this.localDateNode(DATE);
        final LocalDateTimeSearchNode dateTimeNode = this.localDateTimeNode(DATETIME);
        final LocalTimeSearchNode timeNode = this.localTimeNode(TIME);
        final LongSearchNode longNode = this.longNode(VALUE);
        final TextSearchNode textNode = this.textNode(TEXT);

        final SearchQuery query = this.doubleQueryValue(VALUE).notEquals();
        this.querySelectAndCheck(query,
                this.sequenceNode(before, lt, eq, gt, after, bigDecimalNode, bigIntegerNode, dateNode, dateTimeNode, timeNode, longNode, textNode),
                this.sequenceNode(before, lt.selected(), eq, gt.selected(), after, bigDecimalNode, bigIntegerNode, dateNode, dateTimeNode, timeNode, longNode, textNode));
    }

    @Test
    public final void testDoubleLessNot() {
        final DoubleSearchNode node = this.doubleNode(VALUE_LT);
        final SearchQuery query = this.doubleQueryValue(VALUE).notEquals().not();

        this.querySelectAndCheck(query, node, node);
    }

    // LocalDate......................................................................................

    @Test
    public final void testLocalDateLess() {
        final LocalDateSearchNode node = this.localDateNode(DATE_LT);
        final SearchQuery query = this.localDateQueryValue(DATE).notEquals();

        this.querySelectAndCheck(query, node, node.selected());
    }

    @Test
    public final void testLocalDateEqual() {
        final LocalDateSearchNode node = this.localDateNode(DATE);
        final SearchQuery query = this.localDateQueryValue(DATE).notEquals();

        this.querySelectAndCheck(query, node, node);
    }

    @Test
    public final void testLocalDateGreater() {
        final LocalDateSearchNode node = this.localDateNode(DATE_GT);
        final SearchQuery query = this.localDateQueryValue(DATE).notEquals();

        this.querySelectAndCheck(query, node, node.selected());
    }

    @Test
    public final void testLocalDateReplaceSelection() {
        final SearchNode before = this.before();
        final SearchNode after = this.after();

        final LocalDateSearchNode eq = this.localDateNode(DATE);
        final LocalDateSearchNode lt = this.localDateNode(DATE_LT);
        final LocalDateSearchNode gt = this.localDateNode(DATE_GT);

        final SearchQuery query = this.localDateQueryValue(DATE).notEquals();
        this.querySelectAndCheck(query,
                this.sequenceNode(before, lt.selected(), eq, gt.selected(), after),
                this.sequenceNode(before, lt.selected(), eq, gt.selected(), after));
    }

    @Test
    public final void testLocalDateNewSelection() {
        final SearchNode before = this.before();
        final SearchNode after = this.after();

        final LocalDateSearchNode eq = this.localDateNode(DATE);
        final LocalDateSearchNode lt = this.localDateNode(DATE_LT);
        final LocalDateSearchNode gt = this.localDateNode(DATE_GT);

        final SearchQuery query = this.localDateQueryValue(DATE).notEquals();
        this.querySelectAndCheck(query,
                this.sequenceNode(before, lt, eq, gt, after),
                this.sequenceNode(before, lt.selected(), eq, gt.selected(), after));
    }

    @Test
    public final void testLocalDateIgnoreOtherTypes() {
        final SearchNode before = this.before();
        final SearchNode after = this.after();

        final LocalDateSearchNode eq = this.localDateNode(DATE);
        final LocalDateSearchNode lt = this.localDateNode(DATE_LT);
        final LocalDateSearchNode gt = this.localDateNode(DATE_GT);

        final BigDecimalSearchNode bigDecimalNode = this.bigDecimalNode(VALUE);
        final BigIntegerSearchNode bigIntegerNode = this.bigIntegerNode(VALUE);
        final DoubleSearchNode doubleNode = this.doubleNode(VALUE);
        final LocalDateTimeSearchNode dateTimeNode = this.localDateTimeNode(DATETIME);
        final LocalTimeSearchNode timeNode = this.localTimeNode(TIME);
        final LongSearchNode longNode = this.longNode(VALUE);
        final TextSearchNode textNode = this.textNode(TEXT);

        final SearchQuery query = this.localDateQueryValue(DATE).notEquals();
        this.querySelectAndCheck(query,
                this.sequenceNode(before, lt, eq, gt, after, bigDecimalNode, bigIntegerNode, doubleNode, dateTimeNode, timeNode, longNode, textNode),
                this.sequenceNode(before, lt.selected(), eq, gt.selected(), after, bigDecimalNode, bigIntegerNode, doubleNode, dateTimeNode, timeNode, longNode, textNode));
    }

    @Test
    public final void testLocalDateLessNot() {
        final LocalDateSearchNode node = this.localDateNode(DATE_LT);
        final SearchQuery query = this.localDateQueryValue(DATE).notEquals().not();

        this.querySelectAndCheck(query, node, node);
    }

    // LocalDateTime......................................................................................

    @Test
    public final void testLocalDateTimeLess() {
        final LocalDateTimeSearchNode node = this.localDateTimeNode(DATETIME_LT);
        final SearchQuery query = this.localDateTimeQueryValue(DATETIME).notEquals();

        this.querySelectAndCheck(query, node, node.selected());
    }

    @Test
    public final void testLocalDateTimeEqual() {
        final LocalDateTimeSearchNode node = this.localDateTimeNode(DATETIME);
        final SearchQuery query = this.localDateTimeQueryValue(DATETIME).notEquals();

        this.querySelectAndCheck(query, node, node);
    }

    @Test
    public final void testLocalDateTimeGreater() {
        final LocalDateTimeSearchNode node = this.localDateTimeNode(DATETIME_GT);
        final SearchQuery query = this.localDateTimeQueryValue(DATETIME).notEquals();

        this.querySelectAndCheck(query, node, node.selected());
    }

    @Test
    public final void testLocalDateTimeReplaceSelection() {
        final SearchNode before = this.before();
        final SearchNode after = this.after();

        final LocalDateTimeSearchNode eq = this.localDateTimeNode(DATETIME);
        final LocalDateTimeSearchNode lt = this.localDateTimeNode(DATETIME_LT);
        final LocalDateTimeSearchNode gt = this.localDateTimeNode(DATETIME_GT);

        final SearchQuery query = this.localDateTimeQueryValue(DATETIME).notEquals();
        this.querySelectAndCheck(query,
                this.sequenceNode(before, lt.selected(), eq, gt.selected(), after),
                this.sequenceNode(before, lt.selected(), eq, gt.selected(), after));
    }

    @Test
    public final void testLocalDateTimeNewSelection() {
        final SearchNode before = this.before();
        final SearchNode after = this.after();

        final LocalDateTimeSearchNode eq = this.localDateTimeNode(DATETIME);
        final LocalDateTimeSearchNode lt = this.localDateTimeNode(DATETIME_LT);
        final LocalDateTimeSearchNode gt = this.localDateTimeNode(DATETIME_GT);

        final SearchQuery query = this.localDateTimeQueryValue(DATETIME).notEquals();
        this.querySelectAndCheck(query,
                this.sequenceNode(before, lt, eq, gt, after),
                this.sequenceNode(before, lt.selected(), eq, gt.selected(), after));
    }

    @Test
    public final void testLocalDateTimeIgnoresOtherTypes() {
        final SearchNode before = this.before();
        final SearchNode after = this.after();

        final LocalDateTimeSearchNode eq = this.localDateTimeNode(DATETIME);
        final LocalDateTimeSearchNode lt = this.localDateTimeNode(DATETIME_LT);
        final LocalDateTimeSearchNode gt = this.localDateTimeNode(DATETIME_GT);

        final BigDecimalSearchNode bigDecimalNode = this.bigDecimalNode(VALUE);
        final BigIntegerSearchNode bigIntegerNode = this.bigIntegerNode(VALUE);
        final DoubleSearchNode doubleNode = this.doubleNode(VALUE);
        final LocalDateSearchNode dateNode = this.localDateNode(DATE);
        final LocalTimeSearchNode timeNode = this.localTimeNode(TIME);
        final LongSearchNode longNode = this.longNode(VALUE);
        final TextSearchNode textNode = this.textNode(TEXT);

        final SearchQuery query = this.localDateTimeQueryValue(DATETIME).notEquals();
        this.querySelectAndCheck(query,
                this.sequenceNode(before, lt, eq, gt, after, bigDecimalNode, bigIntegerNode, doubleNode, dateNode, timeNode, longNode, textNode),
                this.sequenceNode(before, lt.selected(), eq, gt.selected(), after, bigDecimalNode, bigIntegerNode, doubleNode, dateNode, timeNode, longNode, textNode));
    }

    @Test
    public final void testLocalDateTimeLessNot() {
        final LocalDateTimeSearchNode node = this.localDateTimeNode(DATETIME_LT);
        final SearchQuery query = this.localDateTimeQueryValue(DATETIME).notEquals().not();

        this.querySelectAndCheck(query, node, node);
    }

    // LocalTime......................................................................................

    @Test
    public final void testLocalTimeLess() {
        final LocalTimeSearchNode node = this.localTimeNode(TIME_LT);
        final SearchQuery query = this.localTimeQueryValue(TIME).notEquals();

        this.querySelectAndCheck(query, node, node.selected());
    }

    @Test
    public final void testLocalTimeEqual() {
        final LocalTimeSearchNode node = this.localTimeNode(TIME);
        final SearchQuery query = this.localTimeQueryValue(TIME).notEquals();

        this.querySelectAndCheck(query, node, node);
    }

    @Test
    public final void testLocalTimeGreater() {
        final LocalTimeSearchNode node = this.localTimeNode(TIME_GT);
        final SearchQuery query = this.localTimeQueryValue(TIME).notEquals();

        this.querySelectAndCheck(query, node, node.selected());
    }

    @Test
    public final void testLocalTimeReplaceSelection() {
        final SearchNode before = this.before();
        final SearchNode after = this.after();

        final LocalTimeSearchNode eq = this.localTimeNode(TIME);
        final LocalTimeSearchNode lt = this.localTimeNode(TIME_LT);
        final LocalTimeSearchNode gt = this.localTimeNode(TIME_GT);

        final SearchQuery query = this.localTimeQueryValue(TIME).notEquals();
        this.querySelectAndCheck(query,
                this.sequenceNode(before, lt.selected(), eq, gt.selected(), after),
                this.sequenceNode(before, lt.selected(), eq, gt.selected(), after));
    }

    @Test
    public final void testLocalTimeNewSelection() {
        final SearchNode before = this.before();
        final SearchNode after = this.after();

        final LocalTimeSearchNode eq = this.localTimeNode(TIME);
        final LocalTimeSearchNode lt = this.localTimeNode(TIME_LT);
        final LocalTimeSearchNode gt = this.localTimeNode(TIME_GT);

        final SearchQuery query = this.localTimeQueryValue(TIME).notEquals();
        this.querySelectAndCheck(query,
                this.sequenceNode(before, lt, eq, gt, after),
                this.sequenceNode(before, lt.selected(), eq, gt.selected(), after));
    }

    @Test
    public final void testLocalTimeIgnoreOtherTypes() {
        final SearchNode before = this.before();
        final SearchNode after = this.after();

        final LocalTimeSearchNode eq = this.localTimeNode(TIME);
        final LocalTimeSearchNode lt = this.localTimeNode(TIME_LT);
        final LocalTimeSearchNode gt = this.localTimeNode(TIME_GT);

        final BigDecimalSearchNode bigDecimalNode = this.bigDecimalNode(VALUE);
        final BigIntegerSearchNode bigIntegerNode = this.bigIntegerNode(VALUE);
        final DoubleSearchNode doubleNode = this.doubleNode(VALUE);
        final LocalDateSearchNode dateNode = this.localDateNode(DATE);
        final LocalDateTimeSearchNode dateTimeNode = this.localDateTimeNode(DATETIME);
        final LongSearchNode longNode = this.longNode(VALUE);
        final TextSearchNode textNode = this.textNode(TEXT);

        final SearchQuery query = this.localTimeQueryValue(TIME).notEquals();
        this.querySelectAndCheck(query,
                this.sequenceNode(before, lt, eq, gt, after, bigDecimalNode, bigIntegerNode, doubleNode, dateNode, dateTimeNode, longNode, textNode),
                this.sequenceNode(before, lt.selected(), eq, gt.selected(), after, bigDecimalNode, bigIntegerNode, doubleNode, dateNode, dateTimeNode, longNode, textNode));
    }

    @Test
    public final void testLocalTimeLessNot() {
        final LocalTimeSearchNode node = this.localTimeNode(TIME_LT);
        final SearchQuery query = this.localTimeQueryValue(TIME).notEquals().not();

        this.querySelectAndCheck(query, node, node);
    }

    // Long......................................................................................

    @Test
    public final void testLongLess() {
        final LongSearchNode node = this.longNode(VALUE_LT);
        final SearchQuery query = this.longQueryValue(VALUE).notEquals();

        this.querySelectAndCheck(query, node, node.selected());
    }

    @Test
    public final void testLongEqual() {
        final LongSearchNode node = this.longNode(VALUE);
        final SearchQuery query = this.longQueryValue(VALUE).notEquals();

        this.querySelectAndCheck(query, node, node);
    }

    @Test
    public final void testLongGreater() {
        final LongSearchNode node = this.longNode(VALUE_GT);
        final SearchQuery query = this.longQueryValue(VALUE).notEquals();

        this.querySelectAndCheck(query, node, node.selected());
    }

    @Test
    public final void testLongReplaceSelection() {
        final SearchNode before = this.before();
        final SearchNode after = this.after();

        final LongSearchNode eq = this.longNode(VALUE);
        final LongSearchNode lt = this.longNode(VALUE_LT);
        final LongSearchNode gt = this.longNode(VALUE_GT);

        final SearchQuery query = this.longQueryValue(VALUE).notEquals();
        this.querySelectAndCheck(query,
                this.sequenceNode(before, lt.selected(), eq, gt.selected(), after),
                this.sequenceNode(before, lt.selected(), eq, gt.selected(), after));
    }

    @Test
    public final void testLongNewSelection() {
        final SearchNode before = this.before();
        final SearchNode after = this.after();

        final LongSearchNode eq = this.longNode(VALUE);
        final LongSearchNode lt = this.longNode(VALUE_LT);
        final LongSearchNode gt = this.longNode(VALUE_GT);

        final SearchQuery query = this.longQueryValue(VALUE).notEquals();
        this.querySelectAndCheck(query,
                this.sequenceNode(before, lt, eq, gt, after),
                this.sequenceNode(before, lt.selected(), eq, gt.selected(), after));
    }

    @Test
    public final void testLongIgnoreOtherTypes() {
        final SearchNode before = this.before();
        final SearchNode after = this.after();

        final LongSearchNode eq = this.longNode(VALUE);
        final LongSearchNode lt = this.longNode(VALUE_LT);
        final LongSearchNode gt = this.longNode(VALUE_GT);

        final BigDecimalSearchNode bigDecimalNode = this.bigDecimalNode(VALUE);
        final BigIntegerSearchNode bigIntegerNode = this.bigIntegerNode(VALUE);
        final DoubleSearchNode doubleNode = this.doubleNode(VALUE);
        final LocalDateSearchNode dateNode = this.localDateNode(DATE);
        final LocalDateTimeSearchNode dateTimeNode = this.localDateTimeNode(DATETIME);
        final LocalTimeSearchNode timeNode = this.localTimeNode(TIME);
        final TextSearchNode textNode = this.textNode(TEXT);

        final SearchQuery query = this.longQueryValue(VALUE).notEquals();
        this.querySelectAndCheck(query,
                this.sequenceNode(before, lt, eq, gt, after, bigDecimalNode, bigIntegerNode, doubleNode, dateNode, dateTimeNode, timeNode, textNode),
                this.sequenceNode(before, lt.selected(), eq, gt.selected(), after, bigDecimalNode, bigIntegerNode, doubleNode, dateNode, dateTimeNode, timeNode, textNode));
    }

    @Test
    public final void testLongLessNot() {
        final LongSearchNode node = this.longNode(VALUE_LT);
        final SearchQuery query = this.longQueryValue(VALUE).notEquals().not();

        this.querySelectAndCheck(query, node, node);
    }

    // Text......................................................................................

    @Test
    public final void testTextLess() {
        final TextSearchNode node = this.textNode(TEXT_LT);
        final SearchQuery query = this.textQueryValue(TEXT).notEquals(CaseSensitivity.SENSITIVE);

        this.querySelectAndCheck(query, node, node.selected());
    }

    @Test
    public final void testTextEqual() {
        final TextSearchNode node = this.textNode(TEXT);
        final SearchQuery query = this.textQueryValue(TEXT).notEquals(CaseSensitivity.SENSITIVE);

        this.querySelectAndCheck(query, node, node);
    }

    @Test
    public final void testTextGreater() {
        final TextSearchNode node = this.textNode(TEXT_GT);
        final SearchQuery query = this.textQueryValue(TEXT).notEquals(CaseSensitivity.SENSITIVE);

        this.querySelectAndCheck(query, node, node.selected());
    }

    @Test
    public final void testTextReplaceSelection() {
        final SearchNode before = this.before();
        final SearchNode after = this.after();

        final TextSearchNode eq = this.textNode(TEXT);
        final TextSearchNode lt = this.textNode(TEXT_LT);
        final TextSearchNode gt = this.textNode(TEXT_GT);

        final SearchQuery query = this.textQueryValue(TEXT).notEquals(CaseSensitivity.SENSITIVE);
        this.querySelectAndCheck(query,
                this.sequenceNode(before, lt.selected(), eq, gt.selected(), after),
                this.sequenceNode(before.selected(), lt.selected(), eq, gt.selected(), after.selected()));
    }

    @Test
    public final void testTextNewSelection() {
        final SearchNode before = this.before();
        final SearchNode after = this.after();

        final TextSearchNode eq = this.textNode(TEXT);
        final TextSearchNode lt = this.textNode(TEXT_LT);
        final TextSearchNode gt = this.textNode(TEXT_GT);

        final SearchQuery query = this.textQueryValue(TEXT).notEquals(CaseSensitivity.SENSITIVE);
        this.querySelectAndCheck(query,
                this.sequenceNode(before, lt, eq, gt, after),
                this.sequenceNode(before.selected(), lt.selected(), eq, gt.selected(), after.selected()));
    }

    @Test
    public final void testTextLessNot() {
        final TextSearchNode node = this.textNode(TEXT_LT);
        final SearchQuery query = this.textQueryValue(TEXT).notEquals(CaseSensitivity.SENSITIVE).not();

        this.querySelectAndCheck(query, node, node);
    }

    // Text Insensitive.................................................................................................

    @Test
    public final void testTextLessInsensitive() {
        final TextSearchNode node = this.textNode(TEXT_LT);
        final SearchQuery query = this.textQueryValue(TEXT2).notEquals(CaseSensitivity.INSENSITIVE);

        this.querySelectAndCheck(query, node, node.selected());
    }

    @Test
    public final void testTextEqualInsensitive() {
        final TextSearchNode node = this.textNode(TEXT);
        final SearchQuery query = this.textQueryValue(TEXT2).notEquals(CaseSensitivity.INSENSITIVE);

        this.querySelectAndCheck(query, node, node);
    }

    @Test
    public final void testTextGreaterInsensitive() {
        final TextSearchNode node = this.textNode(TEXT_GT);
        final SearchQuery query = this.textQueryValue(TEXT2).notEquals(CaseSensitivity.INSENSITIVE);

        this.querySelectAndCheck(query, node, node.selected());
    }

    @Test
    public final void testTextReplaceSelectionInsensitive() {
        final SearchNode before = this.before();
        final SearchNode after = this.after();

        final TextSearchNode eq = this.textNode(TEXT);
        final TextSearchNode lt = this.textNode(TEXT_LT);
        final TextSearchNode gt = this.textNode(TEXT_GT);

        final SearchQuery query = this.textQueryValue(TEXT2).notEquals(CaseSensitivity.INSENSITIVE);
        this.querySelectAndCheck(query,
                this.sequenceNode(before, lt.selected(), eq, gt.selected(), after),
                this.sequenceNode(before.selected(), lt.selected(), eq, gt.selected(), after.selected()));
    }

    @Test
    public final void testTextNewSelectionInsensitive() {
        final SearchNode before = this.before();
        final SearchNode after = this.after();

        final TextSearchNode eq = this.textNode(TEXT2);
        final TextSearchNode lt = this.textNode(TEXT2_LT);
        final TextSearchNode gt = this.textNode(TEXT2_GT);

        final SearchQuery query = this.textQueryValue(TEXT2).notEquals(CaseSensitivity.INSENSITIVE);
        this.querySelectAndCheck(query,
                this.sequenceNode(before, lt, eq, gt, after),
                this.sequenceNode(before.selected(), lt.selected(), eq, gt.selected(), after.selected()));
    }

    @Override
    SearchQueryLeafValueNotEquals createSearchQuery(final TextSearchQueryValue value, final SearchQueryTester tester) {
        return SearchQueryLeafValueNotEquals.with(value, tester);
    }

    @Override
    SearchQueryValueSearchQueryTesterComparisonPredicate predicate() {
        return SearchQueryValueSearchQueryTesterComparisonPredicate.NOT_EQUALS;
    }

    @Override
    public Class<SearchQueryLeafValueNotEquals> type() {
        return SearchQueryLeafValueNotEquals.class;
    }
}
