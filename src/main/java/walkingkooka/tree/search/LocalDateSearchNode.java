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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A {@link SearchNode} that holds a {@link LocalDate} value.
 */
public final class LocalDateSearchNode extends SearchNodeLeaf<LocalDate> {

    @SuppressWarnings("WeakerAccess")
    public final static SearchNodeName NAME = SearchNodeName.fromClass(LocalDateSearchNode.class);

    static LocalDateSearchNode with(final String text, final LocalDate value) {
        check(text, value);

        return new LocalDateSearchNode(NO_INDEX, NAME, text, value);
    }

    private LocalDateSearchNode(final int index, final SearchNodeName name, final String text, final LocalDate value) {
        super(index, name, text, value);
    }

    @Override
    SearchNodeName defaultName() {
        return NAME;
    }

    @Override
    public LocalDateSearchNode setName(final SearchNodeName name) {
        return super.setName0(name).cast();
    }

    @Override
    public LocalDateSearchNode setValue(final LocalDate value) {
        return this.setValue0(value).cast();
    }

    @Override
    LocalDateSearchNode replace0(final int index, final SearchNodeName name, final String text, final LocalDate value) {
        return new LocalDateSearchNode(index, name, text, value);
    }

    @Override
    public LocalDateSearchNode removeParent() {
        return this.removeParent0().cast();
    }

    // SearchQuery......................................................................................................

    @Override
    void select(final SearchQuery query, final SearchQueryContext context) {
        query.visit(this, context);
    }

    // Visitor ..........................................................................................................

    @Override
    public void accept(final SearchNodeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    boolean canBeEqual(final Object other) {
        return other instanceof LocalDateSearchNode;
    }

    @Override
    void toString1(final StringBuilder b) {
        b.append(DateTimeFormatter.ISO_LOCAL_DATE.format(this.value()));
    }
}
