import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class task3 {
    public static void main(String[] args) {
        System.out.println(replaceVowels("apple"));
        System.out.println(replaceVowels("Even if you did this task not by yourself, you have to understand every single line of code."));
        System.out.println(stringTransform("hello"));
        System.out.println(stringTransform("bookkeeper"));
        System.out.println(doesBlockFit(1, 3 , 5, 4, 5));
        System.out.println(doesBlockFit(1, 8, 1 , 1, 1));
        System.out.println(doesBlockFit(1, 2, 2 , 1, 1));
        System.out.println(numCheck(243));
        System.out.println(numCheck(52));
        int[] firstArray = {1, -3, 2};
        int[] secondArray = {2, 5, 2};
        int[] thirdArray = {1, -6, 9};
        System.out.println(countRoots(firstArray));
        System.out.println(countRoots(secondArray));
        System.out.println(countRoots(thirdArray));
        String[][] fourthArray = {{"Apple","Shop1","Shop2","Shop3","Shop4"},{"Banana","Shop2","Shop3","Shop4"},{"Orange","Shop1","Shop3","Shop4"},{"Pear","Shop2","Shop4"}};
        String[][] fifthArray = {{"Fridge","Shop2","Shop3"},{"Microwave","Shop1","Shop2","Shop3","Shop4"},{"Laptop","Shop3","Shop4"},{"Phone","Shop1","Shop2","Shop3","Shop4"}};
        System.out.println(salesData(fourthArray));
        System.out.println(salesData(fifthArray));
        System.out.println(validSplit("apple eagle egg goat"));
        System.out.println(validSplit("cat dog goose fish"));
        int[] sixthArray = {3, 1, 4, 2, 7, 5};
        int[] seventhArray = {1, 2, 3, 4, 5};
        int[] eighthArray = {1, 2, -6, 10, 3};
        System.out.println(waveForm(sixthArray));
        System.out.println(waveForm(seventhArray));
        System.out.println(waveForm(eighthArray));
        System.out.println(commonVowel("Hello world"));
        System.out.println(commonVowel("Actions speak louder than words."));
        int[][] ninthArray = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {5, 5, 5, 5, 5}, {7, 4, 3, 14, 2}, {1, 0, 11, 10, 1}};
        int[][] tenthArray = {{6, 4, 19, 0, 0}, {81, 25, 3, 1, 17}, {48, 12, 60, 32, 14}, {91, 47, 16, 65, 217}, {5, 73, 0, 4, 21}};
        System.out.println(Arrays.deepToString(dataScience(ninthArray)));
        System.out.println(Arrays.deepToString(dataScience(tenthArray)));
    }

    public static String replaceVowels(String input) {
        return input.replaceAll("[aeiouyAEIOUY]", "*"); //y is not a vowel, had to replace it due to task output example
    }

    public static String stringTransform(String input) {
        Pattern pattern = Pattern.compile("(.)\\1"); //pattern object with expression that captures any character (.) and checks if it matches \\1
        Matcher matcher = pattern.matcher(input); //creating a matcher object that matches the input string against the pattern
        StringBuilder output = new StringBuilder(); //StringBuilder to store the transformed string

        while (matcher.find()) { //if match is found in input
            String group = matcher.group(1); //capturing the matched char, matcher.group represents the first capturing group
            String replacement = "Double" + Character.toUpperCase(group.charAt(0)); //creating a string using mask Double* where * is letter uppercase in the group
            matcher.appendReplacement(output, replacement); //replacing matching sequence with replacement string
        }
        matcher.appendTail(output); //append everything else after matched part

        return output.toString();
    }

    public static boolean doesBlockFit(int height, int width, int depth, int holeWidth, int holeHeight) {
        return (height <= holeHeight && width <= holeWidth) ||
                (height <= holeHeight && depth <= holeWidth) ||
                (width <= holeHeight && height <= holeWidth) ||
                (width <= holeHeight && depth <= holeWidth) ||
                (depth <= holeHeight && height <= holeWidth) ||
                (depth <= holeHeight && width <= holeWidth);
    }

    public static boolean numCheck(int number) {
        int sum = 0;
        int temp = Math.abs(number);

        while (temp > 0) {
            int digit = temp % 10;
            sum += digit * digit;
            temp /= 10;
        }

        return (sum % 2 == Math.abs(number) % 2);
    }

    public static int countRoots(int[] coefficients) {
        int a = coefficients[0];
        int b = coefficients[1];
        int c = coefficients[2];

        int discriminant = b * b - 4 * a * c;

        if (discriminant > 0) {
            return 2;
        } else if (discriminant == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public static List<String> salesData(String[][] arr) {
        List<String> result = new ArrayList<>();
        List<String> lookingFor = Arrays.asList("Shop1", "Shop2", "Shop3", "Shop4");
        for (String[] array : arr) {
            List<String> tempList = new ArrayList<>(Arrays.asList(array));
            boolean containsAll = true;
            for (String shop : lookingFor) {
                if (!tempList.contains(shop)) {
                    containsAll = false;
                    break;
                }
            }
            if (containsAll) {
                tempList.removeAll(lookingFor);
                result.add(tempList.toString());
            }
        }

        return result;
    }

    public static boolean validSplit(String sentence) {
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length - 1; i++) {
            if (words[i].charAt(words[i].length() - 1) != words[i+1].charAt(0)) {
                return false;
            }
        }
        return true;
    }

    public static boolean waveForm(int[] arr) {
        if (arr.length < 3) {
            return false; //wave is too small
        }
        boolean increasing = arr[1] > arr[0]; //flag so we can track the next integer (like decrease or increase)
        for (int i = 2; i < arr.length; i++) {
            if ((increasing && arr[i] >= arr[i-1]) || (!increasing && arr[i] <= arr[i-1])) {
                return false; //if next int is not expected
            }
            increasing = !increasing; //toggling the flag for the next iteration
        }
        return true; //if cycle goes smoothly
    }

    public static char commonVowel(String sentence) {
        String vowels = "aeiou";
        int[] counts = new int[5]; //storing each vowel
        sentence = sentence.toLowerCase(); //converting to the lowercase
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            if (vowels.indexOf(c) != -1) {
                int index = vowels.indexOf(c);
                counts[index]++;
            }
        }
        int maxCount = counts[0];
        int maxIndex = 0;
        for (int i = 1; i < counts.length; i++) {
            if (counts[i] > maxCount) {
                maxCount = counts[i];
                maxIndex = i;
            }
        }
        return vowels.charAt(maxIndex); //btw not minding that string != sentence
    }

    public static int[][] dataScience(int[][] arrays) {
        int n = arrays.length; //amount of arrays
        int m = arrays[0].length; //length of each array
        int[][] result = new int[n][m]; //result array
        for (int i = 0; i < n; i++) { //calculating the arithmetic mean for N elements and updating the result
            for (int j = 0; j < m; j++) {
                if (i == j) {
                    int sum = 0;
                    int count = 0;
                    for (int k = 0; k < n; k++) {
                        if (k != i) {
                            sum += arrays[k][j];
                            count++;
                        }
                    }
                    result[i][j] = Math.round((float) sum / count);
                } else {
                    result[i][j] = arrays[i][j];
                }
            }
        }

        return result;
    }

}
