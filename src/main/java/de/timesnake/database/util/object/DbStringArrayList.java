/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.object;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class DbStringArrayList extends ArrayList<String> {

    public DbStringArrayList() {
        super();
    }

    public DbStringArrayList(Collection<String> collection) {
        if (collection != null) {
            super.addAll(collection);
        }
    }

    public DbStringArrayList(String[] array) {
        if (array != null) {
            super.addAll(Arrays.asList(array));
        }
    }
}
