import java.util.*;

public class task4 {
    public static void main(String[] args) {
        System.out.println(nonRepeatable("abracadabra"));
        System.out.println(nonRepeatable("paparazzi"));
        System.out.println(generateBrackets(1));
        System.out.println(generateBrackets(2));
        System.out.println(generateBrackets(3));
        System.out.println(binarySystem(3));
        System.out.println(binarySystem(4));
        System.out.println(alphabeticRow("abcdjuwx"));
        System.out.println(alphabeticRow("abcd"));
        System.out.println(alphabeticRow("klmabzyxw"));
        System.out.println(fifthFunction("aaabbcdd"));
        System.out.println(fifthFunction("vvvvaajaaaaa"));
        System.out.println(convertToNum("eight"));
        System.out.println(convertToNum("five hundred sixty seven"));
        System.out.println(convertToNum("thirty one"));
        System.out.println(uniqueSubstring("123412324"));
        System.out.println(uniqueSubstring("111111"));
        System.out.println(uniqueSubstring("77897898"));
        int[][] firstGrid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        int[][] secondGrid = {
                {2, 7, 3},
                {1, 4, 8},
                {4, 5, 9}
        };
        System.out.println(shortestWay(firstGrid));
        System.out.println(shortestWay(secondGrid));
        System.out.println(numericOrder("t3o the5m 1One all6 r4ule ri2ng"));
        System.out.println(numericOrder("re6sponsibility Wit1h gr5eat power3 4comes g2reat"));
        System.out.println(switchNums(519, 723));
        System.out.println(switchNums(491, 3912));
        System.out.println(switchNums(6274, 71259));

    }

    public static String nonRepeatable(String input) {
        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) { //each char in char array
            if (result.indexOf(String.valueOf(c)) == -1) { //c == -1 -> not in result
                result.append(c);
            }
        }
        return result.toString(); //overall just iterating through the string, checking if present in result, if not then adding
    }

    public static List<String> generateBrackets(int n) {
        List<String> result = new ArrayList<>();
        forGeneration(result, "", 0, 0, n);
        return result;
    }

    public static void forGeneration(List<String> result, String current, int open, int close, int max) {
        if (current.length() == max * 2) { //current, open, close, max are values for brackets
            result.add(current); //adding to list
            return;
        }
        if (open < max) {
            forGeneration(result, current + "(", open + 1, close, max); //current string + open bracket to explore possibility of adding an open bracket 
        }
        if (close < open) {
            forGeneration(result, current + ")", open, close + 1, max); //exploring possibility of a closed bracket
        }
    }

    public static List<String> binarySystem(int n) {
        List<String> result = new ArrayList<>();
        forBinary(result, "", n);
        return result;
    }

    public static void forBinary(List<String> result, String current, int n) {
        if (current.length() == n) { //exploring variations
            result.add(current); //adding to list
            return;
        }
        if (!current.endsWith("0")) { //adding 0 if 0 is not met (leads to no double 0)
            forBinary(result, current + "0", n);
        }
        forBinary(result, current + "1", n);
    }

    public static String alphabeticRow(String str) {
        String longestRow = "";
        String currentRow = "";
        String result = "";
        //btw we r iterating until str.length - 1 so we do not hit boundaries cuz code has access to both i and i+1 char
        //therefore we could go out of the loop if we didnt use str.length-1 so that would result in IndexOutOfBoundException
        //summarizing we use -1 to avoid any boundaries exceptions and add +1 back further because we didnt add it to currentrow before
    
        for (int i = 0; i < str.length() - 1; i++) { //iterating forward through every char except for the last one
            currentRow += str.charAt(i);
    
            if (str.charAt(i + 1) - str.charAt(i) == 1) {
                continue;
            }
    
            if (currentRow.length() > longestRow.length()) {
                longestRow = currentRow;
            }
    
            currentRow = "";
        }
        
        //currentRow += str.charAt(str.length() - 1); //adding last char cuz we didnt iterate with it in the loop
    
        if (currentRow.length() > longestRow.length()) {
            result = currentRow;
        } else {
            result = longestRow;
        }
        currentRow = "";
        longestRow = "";
    
        for (int i = str.length()-1; i > 0; i--) { //iterating backwards, same goes here without the last one
            currentRow += str.charAt(i);
    
            if (str.charAt(i-1) - str.charAt(i) == 1) {
                continue; //if diff is more than 1 then longestrow = current
            }
    
            if (currentRow.length() > longestRow.length()) {
                longestRow = currentRow;
            }
    
            currentRow = "";
        }
    
        currentRow += str.charAt(0);
    
        if (currentRow.length() > result.length()) { //updating
            result = currentRow;
        } else if (longestRow.length() > result.length()) {
            result = longestRow;
        }
    
        return result;
    }

    public static String fifthFunction(String input) {
        List<String> combinations = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            char symbol = input.charAt(i);
            int count = 1;

            while (i + 1 < input.length() && input.charAt(i + 1) == symbol) { //iterating while next char is different or string has reached the end
                count++;
                i++;
            }

            String combination = symbol + String.valueOf(count); //connecatating the symbol and its count value
            combinations.add(combination);
        }

        combinations.sort(new Comparator<String>() { //sorting using custom comparator
            @Override
            public int compare(String string1, String string2) {
                int value1 = Integer.parseInt(string1.substring(1)); //parsing int value
                int value2 = Integer.parseInt(string2.substring(1));
                return Integer.compare(value1, value2); //comparing them
            }
        });

        StringBuilder result = new StringBuilder();

        for (String combination : combinations) { //appending sorted by ascending values 
            result.append(combination);
        }

        return result.toString();
    }

    public static int convertToNum(String word) {
        String[] ones = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
                "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
                "eighteen", "nineteen"}; //not only ones cuz ten-nineteen
        String[] tens = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

        if (word.equals("thousand")) {
            return 1000;
        }

        int number = 0;
        int currentNumber = 0;

        for (String part : word.split(" ")) {
            for (int i = 0; i < ones.length; i++) {
                if (part.equals(ones[i])) {
                    currentNumber += i;
                    break;
                }
            }

            for (int i = 2; i < tens.length; i++) {
                if (part.equals(tens[i])) {
                    currentNumber += i * 10;
                    break;
                }
            }

            if (part.equals("hundred")) {
                currentNumber *= 100;
            }

            if (currentNumber >= 100) {
                number += currentNumber;
                currentNumber = 0;
            }
        }

        number += currentNumber;
        return number;
    }

    public static String uniqueSubstring(String str) {
        int maxLength = 0;
        String longestSubstring = "";

        for (int i = 0; i < str.length(); i++) { //iterating over each char
            HashSet<Character> uniqueSet = new HashSet<>();
            StringBuilder currentSubstring = new StringBuilder();

            for (int j = i; j < str.length(); j++) { //j for current char 
                char currentChar = str.charAt(j);
                if (uniqueSet.contains(currentChar)) { //substring is not unique - breaking 
                    break;
                }

                uniqueSet.add(currentChar);
                currentSubstring.append(currentChar);

                if (currentSubstring.length() > maxLength) {
                    maxLength = currentSubstring.length();
                    longestSubstring = currentSubstring.toString();
                }
            }
        }

        return longestSubstring;
    }


    public static int shortestWay(int[][] grid) {
        //leetcode 64 ^_^ using dijkstra
        int n = grid.length;
        int[][] dynamicGrid = new int[n][n];

        dynamicGrid[0][0] = grid[0][0];
        for (int j = 1; j < n; j++) {
            dynamicGrid[0][j] = dynamicGrid[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < n; i++) {
            dynamicGrid[i][0] = dynamicGrid[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                dynamicGrid[i][j] = Math.min(dynamicGrid[i - 1][j], dynamicGrid[i][j - 1]) + grid[i][j];
            }
        }

        return dynamicGrid[n - 1][n - 1];
    }

    public static String numericOrder(String str) {
        String[] words = str.split("\\s+"); //one or more spaces

        Arrays.sort(words, Comparator.comparing(task4::forOrder)); //using custom comparator based on forOrder

        StringBuilder result = new StringBuilder();
        for (String word : words) {
            result.append(word.replaceAll("\\d", "")).append(" "); //replacing all digits with nothing
        }

        return result.toString().trim(); //making sure no spaces after sentence (or before)
    }

    public static int forOrder(String word) { 
        StringBuilder number = new StringBuilder();

        for (char c : word.toCharArray()) { //iterating through every char in the word, catching digits and appending them to final number (so 2-3 digit numbers work as well)
            if (Character.isDigit(c)) {
                number.append(c);
            } else if (number.length() > 0) { //if number is filled with something, then only word is left
                break;
            }
        }

        return number.length() > 0 ? Integer.parseInt(number.toString()) : 0;
    }

    public static ArrayList<Integer> intQuicksort(ArrayList<Integer> arr) {
        if (arr.size() == 0) {
            return arr;
        }
        int pivot = arr.get(0);
        ArrayList<Integer> leftArray = new ArrayList<Integer>();
        ArrayList<Integer> rightArray = new ArrayList<Integer>();
        for (int i = 1; i < arr.size(); i++) { //if element is greater than the pivot it adds to left
            if (pivot < arr.get(i)) {
                leftArray.add(arr.get(i));
            } else {
                rightArray.add(arr.get(i));
            }
        }
        ArrayList<Integer> result = new ArrayList<Integer>(intQuicksort(leftArray)); //recursively call quicksort and store the sorted results in the arraylist
        result.add(pivot);
        result.addAll(intQuicksort(rightArray));
        return result;
    }
    public static int switchNums(int a, int b) {
        ArrayList<Integer> digits = new ArrayList<Integer>();
        do {
            if (a%10 > 0) {
                digits.add(a % 10); //extracting each digit 
            }
            a /= 10;
        } while (a > 0);
        digits = intQuicksort(digits); //sorting digits of first number
        //checking 1st digit of 2nd number, if 1st digit of newmade array is greater than the current digit
        //converting 2nd number to array, replacing digit with new one and remove the new one from digits
        for (int i = 0; i < String.valueOf(b).length(); i++) {
            if (digits.get(0) > Character.getNumericValue(String.valueOf(b).charAt(i))) {
                char[] tmp = String.valueOf(b).toCharArray();
                tmp[i] = (char) (digits.get(0)+'0');
                b = Integer.parseInt(String.valueOf(tmp));
                digits.remove(0);
            }
        }

        return b;
    }




}
