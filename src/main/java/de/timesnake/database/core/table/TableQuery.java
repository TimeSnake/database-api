/*
 * workspace.database-api.main
 * Copyright (C) 2022 timesnake
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; If not, see <http://www.gnu.org/licenses/>.
 */

package de.timesnake.database.core.table;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.Entry;
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.util.object.ColumnMap;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.SyncExecute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public abstract class TableQuery extends Table {

    protected final PrimaryEntries primaryEntries;
    private final List<Entry<?>> primaryEntriesList;

    protected TableQuery(DatabaseConnector databaseConnector, String nameTable, Entry<?>... keys) {
        super(databaseConnector, nameTable);
        this.primaryEntries = new PrimaryEntries(keys);
        this.primaryEntriesList = Arrays.asList(keys);
    }

    protected TableQuery(DatabaseConnector databaseConnector, String nameTable, PrimaryEntries primaryEntries) {
        super(databaseConnector, nameTable);
        this.primaryEntries = primaryEntries;
        this.primaryEntriesList = primaryEntries.getPrimaryEntries();
    }

    private Entry<?>[] addPrimaryKeys(Entry<?>... entries) {
        List<Entry<?>> entriesList = new ArrayList<>(primaryEntriesList);
        if (entries != null && entries.length > 0) {
            entriesList.addAll(Arrays.asList(entries));
        }
        Entry<?>[] newEntries = new Entry[entriesList.size()];
        return entriesList.toArray(newEntries);
    }

    public abstract boolean exists();

    public void delete() {
        this.deleteWithKey();
    }

    protected void deleteWithKey(Entry<?>... entries) {
        this.deleteEntry(this.addPrimaryKeys(entries));
    }

    protected void deleteWithKey(SyncExecute syncExecute, Entry<?>... entries) {
        this.deleteEntry(syncExecute, this.addPrimaryKeys(entries));
    }

    public <Value> Value getFirstWithKey(Column<Value> resultColumn, Entry<?>... entries) {
        return this.getFirst(resultColumn, this.addPrimaryKeys(entries));
    }

    public ColumnMap getFirstWithKey(Set<Column<?>> resultColumn, Entry<?>... entries) {
        return super.getFirst(resultColumn, this.addPrimaryKeys(entries));
    }

    public <Value> Set<Value> getWithKey(Column<Value> resultColumn, Entry<?>... entries) {
        return super.get(resultColumn, this.addPrimaryKeys(entries));
    }

    public Set<ColumnMap> getWithKey(Set<Column<?>> resultColumn, Entry<?>... entries) {
        return super.get(resultColumn, this.addPrimaryKeys(entries));
    }

    protected Integer getHighestIntegerWithKey(Column<Integer> resultColumn, Entry<?>... entries) {
        return this.getHighestInteger(resultColumn, this.addPrimaryKeys(entries));
    }

    protected Integer getLowestIntegerWithKey(Column<Integer> resultColumn, Entry<?>... entries) {
        return this.getLowestInteger(resultColumn, this.addPrimaryKeys(entries));
    }

    protected <Value> void setWithKeySynchronized(Value value, Column<Value> valueColumn, Entry<?>... entries) {
        this.setSynchronized(value, valueColumn, this.addPrimaryKeys(entries));
    }

    protected <Value> void setWithKey(Value value, Column<Value> valueColumn, Entry<?>... entries) {
        this.set(value, valueColumn, this.addPrimaryKeys(entries));
    }

    protected <Value> void setWithKey(Value value, Column<Value> valueColumn, SyncExecute syncExecute,
                                      Entry<?>... entries) {
        this.set(value, valueColumn, syncExecute, this.addPrimaryKeys(entries));
    }

    protected void setWithKey(Set<Entry<?>> values, Entry<?>... entries) {
        this.set(values, this.addPrimaryKeys(entries));
    }

    protected void setWithKey(Set<Entry<?>> values, SyncExecute syncExecute, Entry<?>... entries) {
        this.set(values, syncExecute, this.addPrimaryKeys(entries));
    }

}
