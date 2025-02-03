
package com.tasi.tdd;

import com.tasi.tdd.exception.EmptyInputException;
import com.tasi.tdd.exception.SpecialCharacterException;
import com.tasi.tdd.exception.WrongFormatException;
import java.util.ArrayList;
import java.util.List;

public class CamelCase {

    CamelCase() {
        // Utility class must not be instantiated
    }

    public static List<String> convertCamelCase(String original) {
        CamelCase.checkInput(original);
        List<String> result = new ArrayList<>();
        int lastUpperIndex = 0;
        boolean lastWasUpper = true;
        for (int currIndex = 0; currIndex < original.length(); currIndex++) {
            char c = original.charAt(currIndex);
            boolean currentIsUpper = (c >= 65 && c <= 90) || (c >= 48 && c <= 57);
            // Checks if it's uppercase or number
            if (currentIsUpper) {
                if (!lastWasUpper) {
                    // Recover the last item
                    lastUpperIndex = CamelCase.getItem(original, result, lastUpperIndex, currIndex);
                }
                lastWasUpper = true;
            } // For acronym or numbers
            else if (currIndex != 0 && lastWasUpper) {
                lastUpperIndex = CamelCase.getItem(original, result, lastUpperIndex, currIndex - 1);
                lastWasUpper = false;
            }
        }
        CamelCase.getItem(original, result, lastUpperIndex, original.length());
        return CamelCase.toLowercase(result);
    }

    /**
     * Performs input validations. Throws a RuntimeException if an error
     * is encountered.
     * @param input Input String.
     */
    private static void checkInput(String input) {
        // Checks for null or empty - Invalid input
        if (null == input || input.isEmpty()) {
            throw new EmptyInputException();
        }
        // Checks starting with a number
        if ((input.charAt(0) >= 48 && input.charAt(0) <= 57)) {
            throw new WrongFormatException();
        }
        // Checks for any character that is not a number or a letter
        if (!input.matches("[A-Za-z0-9]*")) {
            throw new SpecialCharacterException();
        }
    }

    private static int getItem(String original, List<String> destination, int begin, int end){
        destination.add(original.substring(begin, end));
        return end;
    }

    private static List<String> toLowercase(List<String> toLower){
        List<String> result = new ArrayList<>();
        toLower.forEach(item -> {
            if(!item.isEmpty()) {
                if(!CamelCase.isAllUpper(item)){
                    item = item.toLowerCase();
                }
                result.add(item);
            }
        });
        return result;
    }

    private static boolean isAllUpper(String validate){
        return validate.equals(validate.toUpperCase());
    }

}