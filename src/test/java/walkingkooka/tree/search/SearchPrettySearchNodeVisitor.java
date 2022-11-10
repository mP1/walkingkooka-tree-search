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

import walkingkooka.text.CharSequences;
import walkingkooka.text.Indentation;
import walkingkooka.text.LineEnding;
import walkingkooka.text.printer.IndentingPrinter;
import walkingkooka.text.printer.Printers;
import walkingkooka.visit.Visiting;

/**
 * Takes a {@link SearchNode} and pretty prints the nodes, making
 */
final class SearchPrettySearchNodeVisitor extends SearchNodeVisitor {

    static String toString(final SearchNode node) {
        final StringBuilder b = new StringBuilder();

        try (final IndentingPrinter printer = Printers.stringBuilder(b, LineEnding.NL).indenting(Indentation.SPACES2)) {
            new SearchPrettySearchNodeVisitor(printer).accept(node);
            printer.flush();
        }
        return b.toString();
    }

    private SearchPrettySearchNodeVisitor(final IndentingPrinter printer) {
        this.printer = printer;
    }

    @Override
    protected Visiting startVisit(final IgnoredSearchNode node) {
        return this.enter(node);
    }

    @Override
    protected void endVisit(final IgnoredSearchNode node) {
        this.exit(node);
    }

    @Override
    protected Visiting startVisit(final MetaSearchNode node) {
        return this.enter(node);
    }

    @Override
    protected void endVisit(final MetaSearchNode node) {
        this.exit(node);
    }

    @Override
    protected Visiting startVisit(final SelectSearchNode node) {
        return this.enter(node);
    }

    @Override
    protected void endVisit(final SelectSearchNode node) {
        this.exit(node);
    }

    @Override
    protected Visiting startVisit(final SequenceSearchNode node) {
        return this.enter(node);
    }

    @Override
    protected void endVisit(final SequenceSearchNode node) {
        this.exit(node);
    }

    @Override
    protected void visit(final BigDecimalSearchNode node) {
        this.leaf(node);
    }

    @Override
    protected void visit(final BigIntegerSearchNode node) {
        this.leaf(node);
    }

    @Override
    protected void visit(final DoubleSearchNode node) {
        this.leaf(node);
    }

    @Override
    protected void visit(final LocalDateSearchNode node) {
        this.leaf(node);
    }

    @Override
    protected void visit(final LocalDateTimeSearchNode node) {
        this.leaf(node);
    }

    @Override
    protected void visit(final LocalTimeSearchNode node) {
        this.leaf(node);
    }

    @Override
    protected void visit(final LongSearchNode node) {
        this.leaf(node);
    }

    @Override
    protected void visit(final TextSearchNode node) {
        this.leaf(node);
    }

    private Visiting enter(final SearchNode node) {
        this.printer.print(this.typeName(node));
        this.printer.print(this.printer.lineEnding());
        this.printer.indent();
        return Visiting.CONTINUE;
    }

    /**
     * All endVisit methods should call this.
     */
    private void exit(final SearchNode node) {
        this.printer.outdent();
    }

    /**
     * All visit methods should call this.
     */
    private void leaf(final SearchNode node) {
        this.printer.print(this.typeName(node) + "=" + node);
        this.printer.print(this.printer.lineEnding());
    }

    private String typeName(final SearchNode node) {
        return CharSequences.subSequence(node.getClass().getSimpleName(), 0, -SearchNode.class.getSimpleName().length()).toString();
    }

    private final IndentingPrinter printer;

    @Override
    public String toString() {
        return this.printer.toString();
    }
}
