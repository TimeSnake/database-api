/*
 * database-api.main
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

package de.timesnake.database.core;

import de.timesnake.database.core.table.TableDDL;

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
            sb.append(TableDDL.parseTypeToDatabaseString(entry.getColumn(), entry.getValue()));

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
            sb.append(TableDDL.parseTypeToDatabaseString(entry.getColumn(), entry.getValue()));
            sb.append(" AND ");
        }
        sb.delete(sb.length() - 5, sb.length());
        return sb.toString();
    }

}
