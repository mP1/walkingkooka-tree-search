/*
 * Copyright © 2020 Miroslav Pokorny
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
 */
package test;


import com.google.j2cl.junit.apt.J2clTestInput;
import org.junit.Assert;
import org.junit.Test;
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

@J2clTestInput(JunitTest.class)
public class JunitTest {

    @Test
    public void testQueryText() {
        final String input = "apple banana carrot dog egg";

        // boring tokenize on space...
        final Parser<ParserContext> words = Parsers.charPredicateString(
            CharPredicates.letterOrDigit(),
            1,
            100
        );
        final Parser<ParserContext> whitespace = Parsers.charPredicateString(
            CharPredicates.whitespace(),
            1,
            100
        );
        final Parser<ParserContext> other = Parsers.charPredicateString(
            CharPredicates.whitespace()
                .or(CharPredicates.letterOrDigit())
                .negate(), 1, 100
        );

        final Parser<ParserContext> parser = Parsers.alternatives(
                Lists.of(
                    words,
                    whitespace,
                    other
                )
            ).repeating()
            .orReport(ParserReporters.basic());

        final Optional<ParserToken> tokens = parser.parse(
            TextCursors.charSequence(input),
            new FakeParserContext()
        );

        // convert into SearchTextNodes
        final SequenceSearchNode nodes = SearchNode.sequence(
            tokens.get()
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

        Assert.assertEquals(
            "search and replace failed\n" + input,
            expected,
            replaced.text()
        );
    }
}
