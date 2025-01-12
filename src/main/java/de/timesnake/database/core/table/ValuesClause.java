/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.table;

import de.timesnake.database.core.Entry;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static de.timesnake.database.core.table.QueryTool.COLUMN_WRAPPER;

public class ValuesClause extends StatementClause {

  public ValuesClause(Collection<Entry<?>> entries) {
    super(entries);
  }

  public ValuesClause(Collection<Entry<?>>... entries) {
    super(Arrays.stream(entries).flatMap(Collection::stream).toList());
  }

  public ValuesClause(Entry<?>... entries) {
    super(entries);
  }

  @Override
  public String getText() {
    return "(" +
        entries.stream().map(c -> COLUMN_WRAPPER + c.getColumn().getName() + COLUMN_WRAPPER)
            .collect(Collectors.joining(", ")) +
        ")" + "VALUES (" +
        entries.stream().map(c -> c.getColumn().getType().getValueWrapper())
            .collect(Collectors.joining(", ")) +
        ")";
  }
}
