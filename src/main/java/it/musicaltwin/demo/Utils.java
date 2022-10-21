package it.musicaltwin.demo;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.LinkedHashMap;
import static java.util.stream.Collectors.toMap;
import static java.util.Map.Entry.comparingByValue;

import it.musicaltwin.demo.entities.Cards;
import it.musicaltwin.demo.entities.UsersArtists;
import it.musicaltwin.demo.entities.UsersSongs;
import it.musicaltwin.demo.services.UsersArtistsService;
import it.musicaltwin.demo.services.UsersSongService;

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

    public static String randomName(Long genderId) {
        List<String> nomiMaschili = Arrays.asList("Carlo", "Mario", "Paolo", "Francesco", "Giovanni", "Mauro",
                "Stefano", "Vladi");
        List<String> nomiFemminili = Arrays.asList("Giovanna", "Francesca", "Giulia", "Marta", "Gabriella", "Maria",
                "Paola", "Camilla");

        List<String> nomi = new ArrayList<String>(nomiMaschili);
        nomi.addAll(nomiFemminili);

        List<String> listpick = genderId == 1 ? nomiMaschili : genderId == 2 ? nomiFemminili : nomi;
        return listpick.get(rnd.nextInt(listpick.size()));
    }

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

    public static Map<Cards, Double> sortByValue(Map<Cards, Double> map) {
        Map<Cards, Double> sorted = map
                .entrySet().stream().sorted(comparingByValue())
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        return sorted;
    }

    public static void randId(String path, int max, String id, UsersArtistsService usersArtistsService) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(path)) {
            Object obj = jsonParser.parse(reader);
            JSONArray list = (JSONArray) obj;

            Long num = Utils.randomGenderId(max, 1);

            for (var element : list) {
                if (element.toString().contains("\"id\":" + num.toString() + "}")) {
                    String artistsId = element.toString().substring(9, element.toString().indexOf("\"", 9));
                    UsersArtists usersArtists = new UsersArtists(id, artistsId);
                    usersArtistsService.addToDatabase(usersArtists);
                }
            }

        } catch (

        FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void randId(String path, int max, String id, UsersSongService usersSongsService) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(path)) {
            Object obj = jsonParser.parse(reader);
            JSONArray list = (JSONArray) obj;

            Long num = Utils.randomGenderId(max, 1);

            for (var element : list) {
                if (element.toString().contains("\"id\":" + num.toString() + "}")) {
                    String SongsId = element.toString().substring(9, element.toString().indexOf("\"", 9));
                    UsersSongs usersSongs = new UsersSongs(id, SongsId);
                    usersSongsService.addToDatabase(usersSongs);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
