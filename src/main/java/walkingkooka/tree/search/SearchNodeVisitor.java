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

import walkingkooka.visit.Visiting;
import walkingkooka.visit.Visitor;

import java.util.Objects;

/**
 * A {@link Visitor} for a graph of {@link SearchNode}.
 */
public abstract class SearchNodeVisitor extends Visitor<SearchNode> {

    protected SearchNodeVisitor() {
        super();
    }

    // SearchNode.......................................................................

    public final void accept(final SearchNode node) {
        Objects.requireNonNull(node, "node");

        if (Visiting.CONTINUE == this.startVisit(node)) {
            node.accept(this);
        }
        this.endVisit(node);
    }

    protected Visiting startVisit(final SearchNode node) {
        return Visiting.CONTINUE;
    }

    protected void endVisit(final SearchNode node) {
        // nop
    }

    protected Visiting startVisit(final IgnoredSearchNode node) {
        return Visiting.CONTINUE;
    }

    protected void endVisit(final IgnoredSearchNode node) {
        // nop
    }

    protected Visiting startVisit(final MetaSearchNode node) {
        return Visiting.CONTINUE;
    }

    protected void endVisit(final MetaSearchNode node) {
        // nop
    }

    protected Visiting startVisit(final SelectSearchNode node) {
        return Visiting.CONTINUE;
    }

    protected void endVisit(final SelectSearchNode node) {
        // nop
    }

    protected Visiting startVisit(final SequenceSearchNode node) {
        return Visiting.CONTINUE;
    }

    protected void endVisit(final SequenceSearchNode node) {
        // nop
    }

    protected void visit(final BigDecimalSearchNode node) {
        // nop
    }

    protected void visit(final BigIntegerSearchNode node) {
        // nop
    }

    protected void visit(final DoubleSearchNode node) {
        // nop
    }

    protected void visit(final LocalDateSearchNode node) {
        // nop
    }

    protected void visit(final LocalDateTimeSearchNode node) {
        // nop
    }

    protected void visit(final LocalTimeSearchNode node) {
        // nop
    }

    protected void visit(final LongSearchNode node) {
        // nop
    }

    protected void visit(final TextSearchNode node) {
        // nop
    }
}
