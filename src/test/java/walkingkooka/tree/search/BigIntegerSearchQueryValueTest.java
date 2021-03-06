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

import java.math.BigInteger;

public final class BigIntegerSearchQueryValueTest extends SearchQueryValueTestCase2<BigIntegerSearchQueryValue, BigInteger> {
    @Override
    BigIntegerSearchQueryValue createSearchQueryValue(final BigInteger value) {
        return BigIntegerSearchQueryValue.with(value);
    }

    @Override
    BigInteger value() {
        return BigInteger.valueOf(123);
    }

    @Override
    BigInteger differentValue() {
        return BigInteger.valueOf(999);
    }

    @Override
    String searchQueryValueToString() {
        return this.value().toString();
    }

    @Override
    public Class<BigIntegerSearchQueryValue> type() {
        return BigIntegerSearchQueryValue.class;
    }
}
