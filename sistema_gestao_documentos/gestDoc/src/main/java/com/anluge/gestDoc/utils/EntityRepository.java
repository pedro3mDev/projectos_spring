package com.anluge.gestDoc.utils;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface EntityRepository<T extends Identifiable<ID>, ID extends Serializable> extends Repository<T, ID>  {
    /**
     * Finds and returns an entity given his id
     *
     * @param id
     *            to look for
     * @return entity found or null
     */
    T findById(ID id);

    /**
     * Creates an entity
     *
     * @param entity
     *            to be created
     * @return the entity created with its id filled in
     */
    T create(T entity);

    /**
     * Creates entities in bulk execution
     *
     * @param entities
     * @return
     */
    <C extends Collection<? extends T>> C create(C entities);

    /**
     * Updates the entity values if there are any updates to be performed
     *
     * @param entity
     * @return
     */
    T update(T entity);

    /**
     * Updates the entities in bulk execution
     *
     * @param entities
     * @return
     */
    <C extends Collection<? extends T>> C update(C entities);

    /**
     * Deletes the entity values if there are any updates to be performed
     *
     * @param entity
     * @return
     */
    T delete(T entity);

    /**
     * Deletes the entities in bulk execution
     *
     * @param entities
     * @return
     */
    Collection<? extends T> delete(Collection<? extends T> entities);
}
