/*
 * Copyright 2020 Miroslav Pokorny (github.com/mP1)
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

package walkingkooka.tree.search.sample;

import walkingkooka.collect.list.Lists;
import walkingkooka.predicate.character.CharPredicates;
import walkingkooka.text.CaseSensitivity;
import walkingkooka.text.cursor.TextCursors;
import walkingkooka.text.cursor.parser.FakeParserContext;
import walkingkooka.text.cursor.parser.Parser;
import walkingkooka.text.cursor.parser.ParserContext;
import walkingkooka.text.cursor.parser.ParserReporters;
import walkingkooka.text.cursor.parser.ParserToken;
import walkingkooka.text.cursor.parser.Parsers;
import walkingkooka.text.cursor.parser.RepeatedParserToken;
import walkingkooka.tree.search.SearchNode;
import walkingkooka.tree.search.SearchQuery;
import walkingkooka.tree.search.SearchQueryValue;
import walkingkooka.tree.search.SequenceSearchNode;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class Sample {
    public static void main(final String[] args) {
        final String input = "apple banana carrot dog egg";

        // boring tokenize on space...
        final Parser<ParserContext> words = Parsers.stringCharPredicate(CharPredicates.letterOrDigit(), 1, 100).cast();
        final Parser<ParserContext> whitespace = Parsers.stringCharPredicate(CharPredicates.whitespace(), 1, 100).cast();
        final Parser<ParserContext> other = Parsers.stringCharPredicate(CharPredicates.whitespace().or(CharPredicates.letterOrDigit()).negate(), 1, 100).cast();

        final Parser<ParserContext> parser = Parsers.repeated(
                Parsers.alternatives(Lists.of(words, whitespace, other)))
                .orReport(ParserReporters.basic());

        final Optional<ParserToken> tokens = parser.parse(TextCursors.charSequence(input), new FakeParserContext());

        // convert into SearchTextNodes
        final SequenceSearchNode nodes = SearchNode.sequence(tokens.get()
                .cast(RepeatedParserToken.class)
                .flat()
                .value()
                .stream()
                .map(t -> SearchNode.text(t.text(), t.text()))
                .collect(Collectors.toList()));

        final String searchFor = "BANANA";
        final CaseSensitivity caseSensitivity = CaseSensitivity.INSENSITIVE;
        final String replaceWith = "zebra";

        final Function<SequenceSearchNode, SearchNode> queryAndReplace = (sequenceNode -> {
            final SearchQuery query = SearchQueryValue.text(searchFor)
                    .equalsQuery(caseSensitivity);

            // query and replace
            return query.select(sequenceNode)
                    .replaceSelected((s) -> SearchNode.text(replaceWith, replaceWith));
        });

        final SearchNode replaced = queryAndReplace.apply(nodes);

        // convert SearchNode back into text.
        final String expected = "apple zebra carrot dog egg";

        assertEquals(expected,
                replaced.text(),
                "search and replace failed\n" + input);
    }
}
