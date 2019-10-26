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

public final class LocalTimeSearchQueryValueTest extends SearchQueryValueTestCase<LocalTimeSearchQueryValue, LocalTime> {

    @Override
    LocalTimeSearchQueryValue createSearchQueryValue(final LocalTime value) {
        return LocalTimeSearchQueryValue.with(value);
    }

    @Override
    LocalTime value() {
        return LocalTime.of(12, 58, 59);
    }

    @Override
    LocalTime differentValue() {
        return LocalTime.of(6, 28, 29);
    }

    @Override
    String searchQueryValueToString() {
        return "12:58:59";
    }

    @Override
    public Class<LocalTimeSearchQueryValue> type() {
        return LocalTimeSearchQueryValue.class;
    }
}
