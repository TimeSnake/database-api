package de.timesnake.database.util.object;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Marks methods, which must request data from the database
 */
@Target(ElementType.METHOD)
public @interface NotLocal {
}
