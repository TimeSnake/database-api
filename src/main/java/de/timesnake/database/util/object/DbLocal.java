package de.timesnake.database.util.object;

public interface DbLocal<T> {

    /**
     * Generates a local data copy
     * <\p>
     * This object shouldn't be used for a longer time, otherwise the object could contain invalid data.
     * It is recommended to use this for many data requests on the same data object in a short time.
     * Methods with the {@link NotLocal}-Annotation are not
     * </\p>
     *
     * @return a local {@link T} copy
     */
    T toLocal();

    T toDatabase();
}
