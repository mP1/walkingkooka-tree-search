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

import java.math.BigDecimal;

public final class SearchBigDecimalQueryValueTest extends SearchQueryValueTestCase2<SearchBigDecimalQueryValue, BigDecimal> {
    @Override
    SearchBigDecimalQueryValue createSearchQueryValue(final BigDecimal value) {
        return SearchBigDecimalQueryValue.with(value);
    }

    @Override
    BigDecimal value() {
        return BigDecimal.valueOf(123.5);
    }

    @Override
    BigDecimal differentValue() {
        return BigDecimal.valueOf(999);
    }

    @Override
    String searchQueryValueToString() {
        return this.value().toString();
    }

    @Override
    public Class<SearchBigDecimalQueryValue> type() {
        return SearchBigDecimalQueryValue.class;
    }
}
