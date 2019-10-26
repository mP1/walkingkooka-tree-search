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
import walkingkooka.collect.map.Maps;
import walkingkooka.text.CaseSensitivity;

public final class SearchQueryLeafAttributeValueNotEqualsTest extends SearchQueryLeafAttributeTestCase<SearchQueryLeafAttributeValueNotEquals> {

    @Test
    public void testSelect() {
        final SearchNode first = this.textNode("text-1")
                .setAttributes(Maps.of(this.attributeName(), this.text()));
        final SearchNode second = this.textNode("text-2")
                .setAttributes(Maps.of(this.attributeName(), "different"));

        final SearchNode node = this.sequenceNode(first, second);

        this.querySelectAndCheck(node,
                this.sequenceNode(first, second.selected()));
    }

    @Test
    public void testSelectDifferentCase() {
        final SearchNode first = this.textNode("text-1")
                .setAttributes(Maps.of(this.attributeName(), this.text().toUpperCase()));
        final SearchNode second = this.textNode("text-2")
                .setAttributes(Maps.of(this.attributeName(), "different"));

        final SearchNode node = this.sequenceNode(first, second);

        this.querySelectAndCheck(
                this.createSearchQuery(this.queryValue(), this.attributeName(), CaseSensitivity.INSENSITIVE),
                node,
                this.sequenceNode(first, second.selected()));
    }

    @Test
    public void testSelectIgnoresNodesWithout() {
        final SearchNode first = this.textNode("text-1")
                .setAttributes(Maps.of(this.attributeName(), this.text()));
        final SearchNode second = this.textNode("text-2")
                .setAttributes(Maps.of(this.attributeName(), "different"));
        final SearchNode third = this.textNode("text-3");

        final SearchNode node = this.sequenceNode(first, second, third);

        this.querySelectAndCheck(node,
                this.sequenceNode(first, second.selected(), third));
    }

    @Override
    SearchQueryLeafAttributeValueNotEquals createSearchQuery(final SearchTextQueryValue queryValue,
                                                             final SearchNodeAttributeName attributeName,
                                                             final CaseSensitivity sensitivity) {
        return SearchQueryLeafAttributeValueNotEquals.with(queryValue, attributeName, sensitivity);
    }

    @Override
    public Class<SearchQueryLeafAttributeValueNotEquals> type() {
        return SearchQueryLeafAttributeValueNotEquals.class;
    }
}
