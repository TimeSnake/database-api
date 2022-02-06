package de.timesnake.database.core;

import de.timesnake.database.core.table.Table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrimaryEntries {

    private final List<TableEntry<?>> primaryEntries;

    public PrimaryEntries(TableEntry<?>... primaryEntries) {
        this.primaryEntries = Arrays.asList(primaryEntries);
    }

    public PrimaryEntries(List<TableEntry<?>> primaryEntries) {
        this.primaryEntries = primaryEntries;
    }

    public List<TableEntry<?>> getPrimaryEntries() {
        return primaryEntries;
    }

    public TableEntry<?> get(int index) {
        return this.primaryEntries.get(index);
    }

    public PrimaryEntries with(TableEntry<?>... entries) {
        List<TableEntry<?>> primaryEntries = new ArrayList<>();
        primaryEntries.addAll(this.primaryEntries);
        primaryEntries.addAll(Arrays.asList(entries));
        return new PrimaryEntries(primaryEntries);
    }

    public String getColumnsAsEntry() {
        StringBuilder sb = new StringBuilder();
        for (TableEntry<?> entry : this.primaryEntries) {
            sb.append(entry.getColumn().getName());
            sb.append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

    public String getValuesAsEntry() {
        StringBuilder sb = new StringBuilder();
        for (TableEntry<?> entry : this.primaryEntries) {
            sb.append("'").append(Table.parseType(entry.getValue())).append("'");
            sb.append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

    public String getAsKeys() {
        StringBuilder sb = new StringBuilder();
        for (TableEntry<?> entry : this.primaryEntries) {
            sb.append(entry.getColumn().getName());
            sb.append("=");
            sb.append("'").append(entry.getValue()).append("'");
            sb.append(" AND ");
        }
        sb.delete(sb.length() - 5, sb.length());
        return sb.toString();
    }

}
