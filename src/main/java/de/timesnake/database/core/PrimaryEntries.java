/*
 * Copyright (C) 2023 timesnake
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
