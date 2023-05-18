/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.database.util;

import java.sql.SQLException;

public class DatabaseOperationException extends RuntimeException {

  public DatabaseOperationException() {
  }

  public DatabaseOperationException(String message) {
    super(message);
  }

  public DatabaseOperationException(SQLException sqlException) {
    super(sqlException);
  }
}
