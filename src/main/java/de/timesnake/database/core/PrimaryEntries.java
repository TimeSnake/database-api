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

package de.timesnake.database.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrimaryEntries {

    private final List<Entry<?>> primaryEntries;

    public PrimaryEntries(Entry<?>... primaryEntries) {
        this.primaryEntries = Arrays.asList(primaryEntries);
    }

    public PrimaryEntries(List<Entry<?>> primaryEntries) {
        this.primaryEntries = primaryEntries;
    }

    public List<Entry<?>> getPrimaryEntries() {
        return primaryEntries;
    }

    public Entry<?> get(int index) {
        return this.primaryEntries.get(index);
    }

    public PrimaryEntries with(Entry<?>... entries) {
        List<Entry<?>> primaryEntries = new ArrayList<>();
        primaryEntries.addAll(this.primaryEntries);
        primaryEntries.addAll(Arrays.asList(entries));
        return new PrimaryEntries(primaryEntries);
    }

}
