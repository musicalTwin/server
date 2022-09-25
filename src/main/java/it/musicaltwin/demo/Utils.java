package it.musicaltwin.demo;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import java.util.List;

/* 
    IO TI GIURO CHE NON L'HO COPIATO QUESTO CODICE, L'HO FATTO IO 
    (https://stackoverflow.com/questions/46508634/sorting-an-arraylist-according-to-number-of-occurences-of-similar-elements)
*/

public class Utils {
    public static <V extends Comparable<V>> ArrayList<V> sortL(List<V> input) {
        Map<V, ValueCount<V>> map = new HashMap<>();
        for (V value : input)
            map.computeIfAbsent(value, ValueCount<V>::new).count++;
        @SuppressWarnings("unchecked")
        ValueCount<V>[] arr = map.values().toArray(new ValueCount[map.size()]);
        Arrays.sort(arr);
        ArrayList<V> sorted = new ArrayList<>(input.size());
        for (ValueCount<V> vc : arr)
            for (int i = 0; i < vc.count; i++)
                sorted.add(vc.value);
        return sorted;
    }

    private static final class ValueCount<V extends Comparable<V>> implements Comparable<ValueCount<V>> {
        final V value;
        int count;

        ValueCount(V value) {
            this.value = value;
        }

        @Override
        public int compareTo(ValueCount<V> that) {
            int cmp = Integer.compare(that.count, this.count); // descending
            if (cmp == 0)
                cmp = that.value.compareTo(this.value); // descending
            return cmp;
        }
    }

    // https://www.geeksforgeeks.org/how-to-remove-duplicates-from-arraylist-in-java/
    public static <T> ArrayList<T> removeDuplicates(List<T> list) {

        // Create a new ArrayList
        ArrayList<T> newList = new ArrayList<T>();

        // Traverse through the first list
        for (T element : list) {

            // If this element is not present in newList
            // then add it
            if (!newList.contains(element)) {

                newList.add(element);
            }
        }

        // return the new list
        return newList;
    }

    static final String AB = "0123456789abcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    public static String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    public static Long randomGenderId(Integer max, Integer min) {
        Long random_int = (long) Math.floor(Math.random() * (max - min + 1) + min);
        return random_int;
    }

}
