/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.object;

import java.util.ArrayList;
import java.util.Collection;

public class DbIntegerArrayList extends ArrayList<Integer> {

  public DbIntegerArrayList() {
    super();
  }

  public DbIntegerArrayList(Collection<Integer> arrayList) {
    if (arrayList != null) {
      super.addAll(arrayList);
    }
  }

}
