package de.timesnake.database.core.table;

import de.timesnake.database.core.Column;
import de.timesnake.database.core.PrimaryEntries;
import de.timesnake.database.core.TableEntry;
import de.timesnake.database.util.object.ColumnMap;
import de.timesnake.database.util.object.DatabaseConnector;
import de.timesnake.database.util.object.SyncExecute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public abstract class TableQuery extends BasicTable {

    protected final PrimaryEntries primaryEntries;
    private final List<TableEntry<?>> primaryEntriesList;

    protected TableQuery(DatabaseConnector databaseConnector, String nameTable, TableEntry<?>... keys) {
        super(databaseConnector, nameTable);
        this.primaryEntries = new PrimaryEntries(keys);
        this.primaryEntriesList = Arrays.asList(keys);
    }

    protected TableQuery(DatabaseConnector databaseConnector, String nameTable, PrimaryEntries primaryEntries) {
        super(databaseConnector, nameTable);
        this.primaryEntries = primaryEntries;
        this.primaryEntriesList = primaryEntries.getPrimaryEntries();
    }

    private TableEntry<?>[] addPrimaryKeys(TableEntry<?>... entries) {
        List<TableEntry<?>> entriesList = new ArrayList<>(primaryEntriesList);
        if (entries != null && entries.length > 0) {
            entriesList.addAll(Arrays.asList(entries));
        }
        TableEntry<?>[] newEntries = new TableEntry[entriesList.size()];
        return entriesList.toArray(newEntries);
    }

    public abstract boolean exists();

    public void delete() {
        this.deleteWithKey();
    }

    protected void deleteWithKey(TableEntry<?>... entries) {
        this.deleteEntry(this.addPrimaryKeys(entries));
    }

    protected void deleteWithKey(SyncExecute syncExecute, TableEntry<?>... entries) {
        this.deleteEntry(syncExecute, this.addPrimaryKeys(entries));
    }

    public <Value> Value getFirstWithKey(Column<Value> resultColumn, TableEntry<?>... entries) {
        return this.getFirst(resultColumn, this.addPrimaryKeys(entries));
    }

    public ColumnMap getFirstWithKey(Set<Column<?>> resultColumn, TableEntry<?>... entries) {
        return super.getFirst(resultColumn, this.addPrimaryKeys(entries));
    }

    public <Value> Set<Value> getWithKey(Column<Value> resultColumn, TableEntry<?>... entries) {
        return super.get(resultColumn, this.addPrimaryKeys(entries));
    }

    public Set<ColumnMap> getWithKey(Set<Column<?>> resultColumn, TableEntry<?>... entries) {
        return super.get(resultColumn, this.addPrimaryKeys(entries));
    }

    protected Integer getHighestIntegerWithKey(Column<Integer> resultColumn, TableEntry<?>... entries) {
        return this.getHighestInteger(resultColumn, this.addPrimaryKeys(entries));
    }

    protected Integer getLowestIntegerWithKey(Column<Integer> resultColumn, TableEntry<?>... entries) {
        return this.getLowestInteger(resultColumn, this.addPrimaryKeys(entries));
    }

    protected <Value> void setWithKeySynchronized(Value value, Column<Value> valueColumn, TableEntry<?>... entries) {
        this.setSynchronized(value, valueColumn, this.addPrimaryKeys(entries));
    }

    protected <Value> void setWithKey(Value value, Column<Value> valueColumn, TableEntry<?>... entries) {
        this.set(value, valueColumn, this.addPrimaryKeys(entries));
    }

    protected <Value> void setWithKey(Value value, Column<Value> valueColumn, SyncExecute syncExecute, TableEntry<?>... entries) {
        this.set(value, valueColumn, syncExecute, this.addPrimaryKeys(entries));
    }

}
