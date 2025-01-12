/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.core;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PrimaryKeyEntries {

  private final Map<Column<?>, Entry<?>> primaryEntries;

  public PrimaryKeyEntries(Entry<?>... primaryEntries) {
    this.primaryEntries = Arrays.stream(primaryEntries).collect(Collectors.toMap(Entry::getColumn,
        Function.identity()));
  }

  public PrimaryKeyEntries(List<Entry<?>> primaryEntries) {
    this.primaryEntries = primaryEntries.stream().collect(Collectors.toMap(Entry::getColumn, Function.identity()));
  }

  public Collection<Entry<?>> getPrimaryEntries() {
    return this.primaryEntries.values();
  }

  public <T> Entry<T> get(Column<T> column) {
    return (Entry<T>) this.primaryEntries.get(column);
  }

  public PrimaryKeyEntries with(Entry<?>... entries) {
    List<Entry<?>> primaryEntries = new ArrayList<>();
    primaryEntries.addAll(this.getPrimaryEntries());
    primaryEntries.addAll(Arrays.asList(entries));
    return new PrimaryKeyEntries(primaryEntries);
  }

}
