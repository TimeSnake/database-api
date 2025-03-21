/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.table;

import de.timesnake.database.core.Entry;

import java.util.Collection;
import java.util.stream.Collectors;

import static de.timesnake.database.core.table.QueryTool.COLUMN_WRAPPER;

public class EquationClause extends StatementClause {

  public EquationClause(Collection<Entry<?>> entries) {
    super(entries);
  }

  public EquationClause(Entry<?>... entries) {
    super(entries);
  }

  @Override
  public String getText() {
    return entries.stream()
        .map(e -> COLUMN_WRAPPER + e.getColumn().getName() + COLUMN_WRAPPER + " = " +
            e.getColumn().getType().getValueWrapper())
        .collect(Collectors.joining(", "));
  }
}
