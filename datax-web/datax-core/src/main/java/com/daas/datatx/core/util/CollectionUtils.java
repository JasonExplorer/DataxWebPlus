package com.daas.datatx.core.util;

import org.apache.commons.lang3.StringUtils;

import java.util.*;


/**
 * Provides utility methods and decorators for {@link Collection} instances.
 * <p>
 * Various utility methods might put the input objects into a Set/Map/Bag. In case
 * the input objects override {@link Object#equals(Object)}, it is mandatory that
 * the general contract of the {@link Object#hashCode()} method is maintained.
 * <p>
 * NOTE: From 4.0, method parameters will take {@link Iterable} objects when possible.
 *
 * @version $Id: CollectionUtils.java 1686855 2015-06-22 13:00:27Z tn $
 * @since 1.0
 */
public class CollectionUtils {

    private CollectionUtils() {
        throw new IllegalStateException("CollectionUtils class");
    }

    public static boolean isNotEmpty(Collection coll) {
        return !isEmpty(coll);
    }

    public static boolean isEmpty(Collection coll) {
        return coll == null || coll.isEmpty();
    }

    /**
     * String to map
     *
     * @param str       string
     * @param separator separator
     * @return string to map
     */
    public static Map<String, String> stringToMap(String str, String separator) {
        return stringToMap(str, separator, "");
    }

    /**
     * String to map
     *
     * @param str       string
     * @param separator separator
     * @param keyPrefix prefix
     * @return string to map
     */
    public static Map<String, String> stringToMap(String str, String separator, String keyPrefix) {
        Map<String, String> emptyMap = new HashMap<>(0);
        if (StringUtils.isEmpty(str)) {
            return emptyMap;
        }
        if (StringUtils.isEmpty(separator)) {
            return emptyMap;
        }
        String[] strings = str.split(separator);
        Map<String, String> map = new HashMap<>(strings.length);
        for (int i = 0; i < strings.length; i++) {
            String[] strArray = strings[i].split("=");
            if (strArray.length != 2) {
                return emptyMap;
            }
            //strArray[0] KEY  strArray[1] VALUE
            if (StringUtils.isEmpty(keyPrefix)) {
                map.put(strArray[0], strArray[1]);
            } else {
                map.put(keyPrefix + strArray[0], strArray[1]);
            }
        }
        return map;
    }


    /**
     * Helper class to easily access cardinality properties of two collections.
     *
     * @param <O> the element type
     */
    private static class CardinalityHelper<O> {

        /**
         * Contains the cardinality for each object in collection A.
         */
        final Map<O, Integer> cardinalityA;

        /**
         * Contains the cardinality for each object in collection B.
         */
        final Map<O, Integer> cardinalityB;

        /**
         * Create a new CardinalityHelper for two collections.
         *
         * @param a the first collection
         * @param b the second collection
         */
        public CardinalityHelper(final Iterable<? extends O> a, final Iterable<? extends O> b) {
            cardinalityA = CollectionUtils.<O>getCardinalityMap(a);
            cardinalityB = CollectionUtils.<O>getCardinalityMap(b);
        }

        /**
         * Returns the frequency of this object in collection A.
         *
         * @param obj the object
         * @return the frequency of the object in collection A
         */
        public int freqA(final Object obj) {
            return getFreq(obj, cardinalityA);
        }

        /**
         * Returns the frequency of this object in collection B.
         *
         * @param obj the object
         * @return the frequency of the object in collection B
         */
        public int freqB(final Object obj) {
            return getFreq(obj, cardinalityB);
        }

        private int getFreq(final Object obj, final Map<?, Integer> freqMap) {
            final Integer count = freqMap.get(obj);
            if (count != null) {
                return count;
            }
            return 0;
        }
    }

    /**
     * returns {@code true} iff the given {@link Collection}s contain
     * exactly the same elements with exactly the same cardinalities.
     *
     * @param a the first collection
     * @param b the second collection
     * @return Returns true iff the given Collections contain exactly the same elements with exactly the same cardinalities.
     * That is, iff the cardinality of e in a is equal to the cardinality of e in b, for each element e in a or b.
     */
    public static boolean equalLists(Collection<?> a, Collection<?> b) {
        if (a == null && b == null) {
            return true;
        }

        if (a == null || b == null) {
            return false;
        }

        return isEqualCollection(a, b);
    }

    /**
     * Returns {@code true} iff the given {@link Collection}s contain
     * exactly the same elements with exactly the same cardinalities.
     * <p>
     * That is, iff the cardinality of <i>e</i> in <i>a</i> is
     * equal to the cardinality of <i>e</i> in <i>b</i>,
     * for each element <i>e</i> in <i>a</i> or <i>b</i>.
     *
     * @param a the first collection, must not be null
     * @param b the second collection, must not be null
     * @return <code>true</code> iff the collections contain the same elements with the same cardinalities.
     */
    public static boolean isEqualCollection(final Collection<?> a, final Collection<?> b) {
        if (a.size() != b.size()) {
            return false;
        }
        final CardinalityHelper<Object> helper = new CardinalityHelper<>(a, b);
        if (helper.cardinalityA.size() != helper.cardinalityB.size()) {
            return false;
        }
        for (final Object obj : helper.cardinalityA.keySet()) {
            if (helper.freqA(obj) != helper.freqB(obj)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a {@link Map} mapping each unique element in the given
     * {@link Collection} to an {@link Integer} representing the number
     * of occurrences of that element in the {@link Collection}.
     * <p>
     * Only those elements present in the collection will appear as
     * keys in the map.
     *
     * @param <O>  the type of object in the returned {@link Map}. This is a super type of O
     * @param coll the collection to get the cardinality map for, must not be null
     * @return the populated cardinality map
     */
    public static <O> Map<O, Integer> getCardinalityMap(final Iterable<? extends O> coll) {
        final Map<O, Integer> count = new HashMap<>();
        for (final O obj : coll) {
            count.put(obj, count.getOrDefault(obj, 0) + 1);
        }
        return count;
    }

}
