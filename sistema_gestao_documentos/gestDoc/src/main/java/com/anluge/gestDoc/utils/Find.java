package com.anluge.gestDoc.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Find {

    private static final When<Object> WHEN_NOT_NULL = new When<Object>() {
        @Override
        public boolean found(Object e) {
            return e != null;
        }
    };

    /**
     * Finds and returns the first element based on When implementation. If the
     * collection is null no execution will be performed. Null elements will be
     * discarded avoiding NullPointerException.
     *
     * @param from
     *            Collection to iterate over and find the proper element
     * @param when
     *            Predicate to be executed and return the correct element
     * @return an element considered found by "when" predicate or null if none
     *         is considered found.
     */
    public static <E> E first(Collection<? extends E> from, When<E> when) {

        if (!Has.content(from)) {
            return null;
        }

        for (E e : from) {
            if (e != null && when.found(e)) {
                return e;
            }
        }
        return null;
    }

    /**
     * Finds and returns the first element based on When implementation. If the
     * array is null no execution will be performed. Null elements will be
     * discarded avoiding NullPointerException.
     *
     * @param from
     *            Array to iterate over and find the proper element
     * @param when
     *            Predicate to be executed and return the correct element
     * @return an element considered found by "when" predicate or null if none
     *         is considered found.
     */
    public static <E> E first(E[] from, When<E> when) {

        if (!Has.content(from)) {
            return null;
        }

        for (E e : from) {
            if (e != null && when.found(e)) {
                return e;
            }
        }
        return null;
    }

    /**
     * Finds and returns the first non null element from this collection. If the
     * collection is null no execution will be performed.
     *
     * @param from
     *            Collection to search for the first non null element.
     * @return the first non null element or throws a IllegalArgumentException
     */
    @SuppressWarnings("unchecked")
    public static <E> E firstNonNull(Collection<? extends E> from) {
        Object element = first(from, WHEN_NOT_NULL);
        if (element == null) {
            throw new IllegalArgumentException("There's must be at least one non null element in the collection");
        }
        return (E) element;
    }

    /**
     * Finds and returns the first non null element from this array. If the
     * array is null no execution will be performed.
     *
     * @param from
     *            Array to search for the first non null element.
     * @return the first non null element or throws a IllegalArgumentException
     */
    @SuppressWarnings("unchecked")
    public static <E> E firstNonNull(E... from) {
        Object element = first(from, WHEN_NOT_NULL);
        if (element == null) {
            throw new IllegalArgumentException("There's must be at least one non null element in the array");
        }
        return (E) element;
    }

    /**
     * Finds and returns all the elements based on When implementation. If the
     * collection is null no execution will be performed. Null elements will be
     * discarded avoiding NullPointerException.
     *
     * @param from
     *            Collection to iterate over and find the proper elements
     * @param when
     *            Predicate to be executed and return the correct elements
     * @return a List of elements considered found by "when" predicate or an
     *         empty List if none is considered found.
     */
    public static <E> List<E> all(List<E> from, When<E> when) {
        if (!Has.content(from)) {
            return Collections.emptyList();
        }

        List<E> list = new ArrayList<E>();
        for (E e : from) {
            if (e != null && when.found(e)) {
                list.add(e);
            }
        }
        return list;
    }

    /**
     * Finds and returns all the elements based on When implementation. If the
     * collection is null no execution will be performed. Null elements will be
     * discarded avoiding NullPointerException.
     *
     * @param from
     *            Collection to iterate over and find the proper elements
     * @param when
     *            Predicate to be executed and return the correct elements
     * @return a Set of elements considered found by "when" predicate or an
     *         empty List if none is considered found.
     */
    public static <E> Set<E> all(Set<E> from, When<E> when) {
        if (!Has.content(from)) {
            return Collections.emptySet();
        }

        Set<E> set = new HashSet<E>();
        for (E e : from) {
            if (e != null && when.found(e)) {
                set.add(e);
            }
        }
        return set;
    }

    /**
     * Finds and returns all the elements based on When implementation. If the
     * collection is null no execution will be performed. Null keys or null
     * values elements will be discarded avoiding NullPointerException.
     *
     * @param from
     *            Collection to iterate over and find the proper elements
     * @param when
     *            Predicate to be executed and return the correct elements
     * @return a Map of elements considered found by "when" predicate or an
     *         empty List if none is considered found.
     */
    public static <K, V> Map<K, V> all(Map<K, V> from, When<Entry<K, V>> when) {
        if (!Has.content(from)) {
            return Collections.emptyMap();
        }

        Map<K, V> set = new HashMap<K, V>();
        for (Entry<K, V> e : from.entrySet()) {
            if (e != null && e.getKey() != null && e.getValue() != null && when.found(e)) {
                set.put(e.getKey(), e.getValue());
            }
        }
        return set;
    }

}
