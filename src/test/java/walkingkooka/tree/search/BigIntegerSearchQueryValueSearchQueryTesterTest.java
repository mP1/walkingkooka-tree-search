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

public final class BigIntegerSearchQueryValueSearchQueryTesterTest extends SearchQueryValueSearchQueryTesterComparableTestCase<BigIntegerSearchQueryValueSearchQueryTester, BigInteger> {

    @Override
    BigIntegerSearchQueryValueSearchQueryTester createSearchQueryTester(final BigInteger value,
                                                                        final SearchQueryValueSearchQueryTesterComparisonPredicate predicate) {
        return BigIntegerSearchQueryValueSearchQueryTester.with(value, predicate);
    }

    @Override
    public Class<BigIntegerSearchQueryValueSearchQueryTester> type() {
        return BigIntegerSearchQueryValueSearchQueryTester.class;
    }

    @Override
    BigInteger value() {
        return BigInteger.ONE;
    }

    @Override
    BigInteger differentValue() {
        return BigInteger.valueOf(99);
    }
}
