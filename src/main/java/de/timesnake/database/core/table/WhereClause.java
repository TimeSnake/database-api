/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.table;

import de.timesnake.database.core.Entry;

import java.util.Collection;

public class WhereClause extends StatementClause {

  public WhereClause(Collection<Entry<?>> entries) {
    super(entries);
  }

  public WhereClause(Entry<?>... entries) {
    super(entries);
  }

  @Override
  public String getText() {
    return !entries.isEmpty() ? " WHERE " + parseToEquation(entries, " AND ") : "";
  }

}
