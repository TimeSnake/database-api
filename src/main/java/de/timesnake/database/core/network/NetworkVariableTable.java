/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core.network;

import de.timesnake.database.core.Column.Network;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.table.DefinitionAndQueryTool;
import de.timesnake.database.util.object.DatabaseConnector;

public class NetworkVariableTable extends DefinitionAndQueryTool {

  protected NetworkVariableTable(DatabaseConnector databaseConnector, String tableName) {
    super(databaseConnector, tableName, Network.VARIABLE_KEY);

    super.addColumn(Network.VARIABLE_VALUE);

    this.setUpdatePolicy(UpdatePolicy.INSERT_IF_NOT_EXISTS);
  }

  @Override
  protected void create() {
    super.create();
  }

  @Override
  public void save() {
    super.save();
  }

  public void setValue(String key, String value) {
    super.set(value, Network.VARIABLE_VALUE, new Entry<>(key, Network.VARIABLE_KEY));
  }

  public String getValue(String key) {
    return super.getFirst(Network.VARIABLE_VALUE, new Entry<>(key, Network.VARIABLE_KEY));
  }

}
