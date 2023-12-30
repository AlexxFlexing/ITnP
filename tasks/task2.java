import java.util.Arrays;
import java.util.Random;

public class task2 {
    public static void main(String[] args) {
        System.out.println(duplicateChars("Donald"));
        System.out.println(duplicateChars("orange"));
        System.out.println(getInitials("Ryan Gosling"));
        System.out.println(getInitials("Barack Obama"));
        int[] firstArray = {44, 32, 86, 19};
        System.out.println(differenceEvenOdd(firstArray));
        int[] secondArray = {22, 50, 16, 63, 31, 55};
        System.out.println(differenceEvenOdd(secondArray));
        int[] thirdArray = {1, 2, 3, 4, 5};
        System.out.println(equalToAvg(thirdArray));
        int[] fourthArray = {1, 2, 3, 4, 6};
        System.out.println(equalToAvg(fourthArray));
        int[] fifthArray = {1, 2, 3};
        System.out.println(indexMult(fifthArray));
        int[] sixthArray = {3, 3, -2, 408, 3, 31};
        System.out.println(indexMult(sixthArray));
        System.out.println(reverse("Hello World"));
        System.out.println(reverse("The quick brown fox."));
        System.out.println(tribonacci(7));
        System.out.println(tribonacci(11));
        System.out.println('"' + pseudoHash(5) + '"');
        System.out.println('"' + pseudoHash(10) + '"');
        System.out.println('"' + pseudoHash(0) + '"');
        System.out.println(botHelper("Hello, I'm under the water, please help me"));
        System.out.println(botHelper("Two pepperoni pizzas please"));
        System.out.println(isAnagram("listen", "silent"));
        System.out.println(isAnagram("eleven plus two", "twelve plus one"));
        System.out.println(isAnagram("hello", "world"));
    }

    public static boolean duplicateChars(String input) {
        String lowercaseInput = input.toLowerCase();
        //creating a boolean array
        boolean[] charAppearance = new boolean[Character.MAX_VALUE]; //max.value determines amount of value that can be stored in 1 char (16-bit unicode)
        //iterating through the string for each char, checking if char was met already 
        for (int i = 0; i < lowercaseInput.length(); i++) {
            char c = lowercaseInput.charAt(i);
            if (charAppearance[c])
                return true;
            charAppearance[c] = true;
        }
        return false;
    }

    public static String getInitials(String fullName) {
        String initials = "";
        for (int i = 0; i < fullName.length(); i++) {
            if (i == 0 || fullName.charAt(i - 1) == ' ') { //checking if its the first letter or if it is the letter that goes after space
                initials += Character.toUpperCase(fullName.charAt(i));
            }
        }
        return initials;
    }

    public static int differenceEvenOdd(int[] array) {
        int evenSum = 0;
        int oddSum = 0;
        for (int num : array) {
            if (num % 2 == 0) {
                evenSum += num;
            } else {
                oddSum += num;
            }
        }
        return Math.abs(evenSum - oddSum);
    }

    public static boolean equalToAvg(int[] array) {
        double sum = 0;
        for (int num : array) {
            sum += num;
        }
        double average = sum / array.length;
        for (double num : array) {
            if (num == average) {
                System.out.println(average);
                return true;
            }
        }
        return false;
    }
    public static String indexMult(int[] numbers) {
        int[] multipliedArray = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            multipliedArray[i] = numbers[i] * i;
        }
        return Arrays.toString(multipliedArray);
    }

    public static String reverse(String input) {
        StringBuilder reversed = new StringBuilder(); //using stringbuilder so i can use append method
        for (int i = input.length() - 1; i >= 0; i--) {
            reversed.append(input.charAt(i));
        }
        return reversed.toString();
    }

    public static int tribonacci(int n) {
        if (n == 0 || n == 1 || n == 2) {
            return 0;
        } else if (n == 3) {
            return 1;
        } else {
            return tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
        }
    }

    public static String pseudoHash(int length) {
        StringBuilder pseudoHash = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(16); //generating random index to select from numbers and letters
            if (randomIndex < 10) { //java uses unicode -> ascii is subset of unicode
                pseudoHash.append((char) (randomIndex + '0')); //'0' value is 48 + random value
            } else {
                pseudoHash.append((char) (randomIndex - 10 + 'a')); //'a' ascii value is 97, every next letter has +1 value
            }
        }
        return pseudoHash.toString();
    }

    public static String botHelper(String input) {
        input = input.toLowerCase();
        if (input.contains("help")) {
            return "calling for a staff";
        } else {
            return "keep waiting";
        }
    }

    public static boolean isAnagram(String input1, String input2) {
        input1 = input1.replaceAll("\\s", "").toLowerCase();
        input2 = input2.replaceAll("\\s", "").toLowerCase();
        char[] charArray1 = input1.toCharArray(); //turn strings into arrays
        char[] charArray2 = input2.toCharArray();
        Arrays.sort(charArray1); //sort arrays
        Arrays.sort(charArray2);
        return Arrays.equals(charArray1, charArray2); //checking if they r equal
    }

}
