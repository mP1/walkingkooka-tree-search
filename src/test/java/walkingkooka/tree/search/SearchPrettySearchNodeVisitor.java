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

import walkingkooka.text.Indentation;
import walkingkooka.text.LineEnding;
import walkingkooka.text.printer.IndentingPrinters;
import walkingkooka.text.printer.Printers;
import walkingkooka.tree.Node;
import walkingkooka.visit.Visiting;
import walkingkooka.visit.VisitorPrettyPrinter;

/**
 * Takes a {@link SearchNode} and pretty prints the nodes, making
 */
final class SearchPrettySearchNodeVisitor extends SearchNodeVisitor {

    static String toString(final SearchNode node) {
        final StringBuilder b = new StringBuilder();

        new SearchPrettySearchNodeVisitor(VisitorPrettyPrinter.with(
                IndentingPrinters.printer(Printers.stringBuilder(b, LineEnding.NL), Indentation.with(' ', 2)),
                SearchPrettySearchNodeVisitor::tokenName)).accept(node);
        return b.toString();
    }

    private static String tokenName(final SearchNode token) {
        return VisitorPrettyPrinter.computeFromClassSimpleName(token, "Search", Node.class.getSimpleName());
    }

    private SearchPrettySearchNodeVisitor(final VisitorPrettyPrinter<SearchNode> printer) {
        this.printer = printer;
    }

    @Override
    protected Visiting startVisit(final IgnoredSearchNode node) {
        this.printer.enter(node);
        return super.startVisit(node);
    }

    @Override
    protected void endVisit(final IgnoredSearchNode node) {
        this.printer.exit(node);
        super.endVisit(node);
    }

    @Override
    protected Visiting startVisit(final MetaSearchNode node) {
        this.printer.enter(node);
        return super.startVisit(node);
    }

    @Override
    protected void endVisit(final MetaSearchNode node) {
        this.printer.exit(node);
        super.endVisit(node);
    }

    @Override
    protected Visiting startVisit(final SelectSearchNode node) {
        this.printer.enter(node);
        return super.startVisit(node);
    }

    @Override
    protected void endVisit(final SelectSearchNode node) {
        this.printer.exit(node);
        super.endVisit(node);
    }

    @Override
    protected Visiting startVisit(final SequenceSearchNode node) {
        this.printer.enter(node);
        return super.startVisit(node);
    }

    @Override
    protected void endVisit(final SequenceSearchNode node) {
        this.printer.exit(node);
        super.endVisit(node);
    }

    @Override
    protected void visit(final BigDecimalSearchNode node) {
        this.printer.leaf(node);
    }

    @Override
    protected void visit(final BigIntegerSearchNode node) {
        this.printer.leaf(node);
    }

    @Override
    protected void visit(final DoubleSearchNode node) {
        this.printer.leaf(node);
    }

    @Override
    protected void visit(final LocalDateSearchNode node) {
        this.printer.leaf(node);
    }

    @Override
    protected void visit(final LocalDateTimeSearchNode node) {
        this.printer.leaf(node);
    }

    @Override
    protected void visit(final LocalTimeSearchNode node) {
        this.printer.leaf(node);
    }

    @Override
    protected void visit(final LongSearchNode node) {
        this.printer.leaf(node);
    }

    @Override
    protected void visit(final TextSearchNode node) {
        this.printer.leaf(node);
    }

    private final VisitorPrettyPrinter<SearchNode> printer;

    @Override
    public String toString() {
        return this.printer.toString();
    }
}
