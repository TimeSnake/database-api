/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util.anticheat;

import java.util.Collection;

public interface DatabaseAntiCheat {
  Collection<String> getWordList();

  void addWord(String word);
}
