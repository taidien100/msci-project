package ic.msciproject.minoritygame;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Collection;
import java.util.Iterator;
import java.util.HashSet;

/**
 * The Strategy class represents a strategy employed by an agent in predicting
 * the next outcome in the minority game. A Strategy instance is initialised
 * with a map of history strings to predicted outcomes and when an agent has
 * to choose an outcome, the history string is used to retrieve the predicted
 * outcome from the Strategy.
 * @author tobyclemson
 */
public class Strategy extends AbstractMap<String,String>{

    /**
     * A String to String Map containing the mappings of history string to
     * predicted outcome comprising the Strategy.
     */
    private Map<String, String> mappings;

    /**
     * An integer representing the key length in the mapping of the
     * Strategy. This is returned by the {@link #getKeyLength} method.
     */
    private int keyLength;

    /**
     * An integer representing the score of the Strategy. The score of a
     * Strategy is incremented if in a given turn it would have resulted in
     * a correct prediction for the minority choice. The score is incremented
     * using the {@link #incrementScore} method and returned by the
     * {@link #getScore} method.
     */
    private int score = 0;

    private Set<String> acceptedKeys;

    /**
     * Constructs a Strategy instance using the supplied history string to
     * outcome mappings.
     * <p>
     * The supplied mappings must all have the same key length, must represent
     * every possible mapping for that key length and must have only the
     * strings "0" or "1" as values. If this is not true, an
     * IllegalArgumentException is thrown.
     * 
     * @param mappings A Map of Strings to Strings representing the mappings of
     * all possible history strings of a particular length to predicted
     * outcomes.
     */
    public Strategy(Map<String, String> mappings) {
        // throw an IllegalArgumentException if the supplied map contains no
        // entries
        if(mappings.isEmpty()) {
            throw new IllegalArgumentException();
        }

        // declare required variables
        Set<String> keySet, validValueSet;
        Collection<String> valueCollection;
        Iterator<String> keyIterator, valueIterator;
        String currentKey, currentValue;
        int previousKeyLength, currentKeyLength;

        // get an iterator for the set of keys contained in the supplied
        // mapping
        keySet = mappings.keySet();
        keyIterator = keySet.iterator();

        // initialise variables
        currentKey = "";
        currentKeyLength = 0;
        previousKeyLength = 0;

        // check that all keys in the mapping are the same length, throwing
        // an IllegalArgumentException if any differ.
        do {
            currentKey = keyIterator.next();
            currentKeyLength = currentKey.length();

            if(previousKeyLength == 0) {
                previousKeyLength = currentKeyLength;
            } else if(currentKeyLength != previousKeyLength) {
                throw new IllegalArgumentException();
            }
        } while(keyIterator.hasNext());

        // set the key length to that found in the supplied mappings
        keyLength = currentKeyLength;

        // if the supplied mapping does not contain all of the required keys
        // throw an IllegalArgumentException.
        acceptedKeys = StringPermutator.generateAll(keyLength);

        if(!keySet.equals(acceptedKeys)) {
            throw new IllegalArgumentException();
        }

        // get an iterator for the values contained in the supplied mapping.
        valueCollection = mappings.values();
        valueIterator = valueCollection.iterator();

        // create a set containing all valid values for the mappings.
        validValueSet = new HashSet<String>();
        validValueSet.add("0");
        validValueSet.add("1");

        // check each value in this mapping is in the set of valid values, if
        // not throw an IllegalArgumentException.
        do {
            currentValue = valueIterator.next();

            if(!validValueSet.contains(currentValue)) {
                throw new IllegalArgumentException();
            }
        } while(valueIterator.hasNext());

        // if all is well, set the mappings to the supplied mappings object.
        this.mappings = mappings;
    }

    /**
     * Returns the length of the keys contained in the mappings in this
     * strategy.
     * @return An integer representing the key length.
     */
    public int getKeyLength() {
        return keyLength;
    }

    /**
     * Returns a Set view of the mappings contained in the strategy.
     *
     * @return The mappings representing this strategy.
     */
    public Set<Map.Entry<String, String>> entrySet() {
        return mappings.entrySet();
    }

    /**
     * Returns the outcome corresponding to the supplied history
     * string predicted by the Strategy.
     * @param key The current minority game history string.
     * @return The predicted outcome as a string, either "0" or "1".
     */
    public String get(String key) {
        if(!acceptedKeys.contains(key)) {
            throw new IllegalArgumentException();
        }
        return mappings.get(key);
    }

    /**
     * Adds 1 to the Strategy's score.
     */
    public void incrementScore() {
        score++;
    }

    /**
     * Returns the score of the Strategy. The score represents the number of
     * steps in the game so far for which the strategy has made correct
     * predictions.
     * @return The score of the strategy.
     */
    public int getScore() {
        return score;
    }
}
