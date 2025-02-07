
package com.tasi.tdd.translator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Translator {

    private final Map<String, String> dictionary = new HashMap<>();

    public void addTranslation(String original, String translation) {
        if (this.dictionary.containsKey(original)) {
            translation = this.dictionary.get(original) + ", " + translation;
        }
        this.dictionary.put(original, translation);
    }

    public String translate(String original) {
        return this.dictionary.get(original);
    }

    public String translatePhrase(String phrase) {
        String[] words = phrase.split(" ");
        List<String> result = new ArrayList<>();
        for (String word : words) {
            result.add(getFirstTranslation(word));
        }
        return result.stream().collect(Collectors.joining(" "));
    }

    private String getFirstTranslation(String original) {
        String translation = this.translate(original);
        // Checks if the word translation has more than one word
        if (translation.contains(",")) {
            translation = translation.substring(0, translation.indexOf(","));
        }
        return translation;
    }

    public boolean isEmpty() {
        return this.dictionary.isEmpty();
    }
}
