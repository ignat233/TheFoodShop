/*
 * Copyright
 */

package com.netcraker.services.method;

import java.util.LinkedList;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * The class provides an implementation of the findAll () method for services.
 *
 * @param <T> Type of service
 * @param <E> Repository
 * @since 0.0.1
 */
@SuppressWarnings("PMD.AbstractClassWithoutAbstractMethod")
public abstract class FindAll<T, E extends CrudRepository<T, Long>> {

    /**
     * Initialization of the repository variable.
     */
    private final E repository;

    /**
     * Public constructor.
     *
     * @param repository Repository
     */
    public FindAll(final E repository) {
        this.repository = repository;
    }

    /**
     * The method finds all records in the database.
     *
     * @return All
     */
    public List<T> findAll() {
        final Iterable<T> iter = this.repository.findAll();
        final List<T> all = new LinkedList<>();
        iter.forEach(all::add);
        return all;
    }
}
