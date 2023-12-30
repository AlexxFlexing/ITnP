import java.util.*;
import java.util.stream.Collectors;

class MathException extends Exception {
    public MathException(String exception_text) {
        System.out.println(exception_text);
    }
}

public class task6 {
    public static void main(String[] args) throws MathException {

        System.out.println(hiddenAnagram("My world evolves in a beautiful space called Tesh.","sworn love lived"));
        System.out.println(hiddenAnagram("An old west action hero actor", "Clint Eastwood"));
        System.out.println(hiddenAnagram("Mr. Mojo Rising could be a song title", "Jim Morrison"));
        System.out.println(hiddenAnagram("Banana? margaritas", "ANAGRAM"));
        System.out.println(hiddenAnagram("D  e b90it->?$ (c)a r...d,,#~", "bad credit"));
        System.out.println(hiddenAnagram("Bright is the moon", "Bongo mirth"));
        System.out.println(collect("intercontinentalisationalism", 6));
        System.out.println(collect("strengths", 3));
        System.out.println(collect("pneumonoultramicroscopicsilicovolcanoconiosis", 15));
        System.out.println(nicoCipher("myworldevolvesinhers", "tesh"));
        System.out.println(nicoCipher("andiloveherso", "tesha"));
        System.out.println(nicoCipher("mubashirhassan", "crazy"));
        System.out.println(nicoCipher("edabitisamazing", "matt"));
        System.out.println(nicoCipher("iloveher", "612345"));
        System.out.println(twoProduct(new int[]{1, 2, 3, 9, 4, 15, 3, 5}, 45));
        System.out.println(twoProduct(new int[]{1, 2, 3, 9, 4, 5, 15}, 45));
        System.out.println(twoProduct(new int[]{1, 2,-1, 4, 5, 6, 10, 7}, 20));
        System.out.println(twoProduct(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 10));
        System.out.println(twoProduct(new int[]{100, 12, 4, 1, 2}, 15));
        System.out.println(isExact(6));
        System.out.println(isExact(24));
        System.out.println(isExact(125));
        System.out.println(isExact(720));
        System.out.println(isExact(1024));
        System.out.println(isExact(40320));
        System.out.println(fractions("0.(6)"));
        System.out.println(fractions("1.(1)"));
        System.out.println(fractions("3.(142857)"));
        System.out.println(fractions("0.19(2367)"));
        System.out.println(fractions("0.1097(3)"));
        System.out.println(pilish_string("33314444"));
        System.out.println(pilish_string("TOP"));
        System.out.println(pilish_string("X"));
        System.out.println(pilish_string(""));
        System.out.println(generateNonconsecutive("3 + 5 * (2 - 6)") );
        System.out.println(generateNonconsecutive("6 - 18 / (-1 + 4)") );
        System.out.println(isValid("aabbcd"));
        System.out.println(isValid("aabbccddeefghi"));
        System.out.println(isValid("abcdefghhgfedecba") );
        System.out.println(findLCS("abcd", "bd"));
        System.out.println(findLCS("aggtab", "gxtxamb") );
        

    }

    public static Map<Character, Integer> getLetterSet(String str) {
        Map<Character, Integer> set = new HashMap<>();
        for (char c : str.toCharArray()) {
            if (Character.isLowerCase(c)) {
                set.put(c, set.getOrDefault(c, 0) + 1);
            }
        }
        return set;
    }
    
    public static String onlyLetters(String str) {
        str = str.toLowerCase();
        StringBuilder resBuilder = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (Character.isLowerCase(c)) {
                resBuilder.append(c);
            }
        }
        return resBuilder.toString();
    }
    
    public static String hiddenAnagram(String a, String b) {
        a = onlyLetters(a);
        b = onlyLetters(b);
        Map<Character, Integer> setB = getLetterSet(b);
        int bLength = b.length();
        for (int i = 0; i <= a.length() - bLength; i++) {
            String substr = a.substring(i, i + bLength);
            Map<Character, Integer> setA = getLetterSet(substr);
            if (setA.equals(setB)) {
                return onlyLetters(substr);
            }
        }
        return "notfound";
    }
    
    public static List<Object> collect(String str, int n) {
    List<Object> lst = new ArrayList<>();
    if (str.length() < n) {
        return lst;
    }
    lst.add(str.substring(0, n));
    lst.addAll(collect(str.substring(n), n));
    return lst.stream()
            .sorted()
            .collect(Collectors.toList());
}
    public static int[] getCharset(String word) {
        int[] charset = new int[127];
        for (char c : word.toCharArray()) charset[c]++;
        return charset;
    }

    public static String nicoCipher(String message, String key) {
        int messageLength = message.length();
        int keyLength = key.length();
    
        int paddingLength = (messageLength + keyLength) % keyLength;
        for (int i = 0; i < paddingLength; i++) {
            message += ' ';
        }
    
        int[] charset = getCharset(key);
        int[] charsetCount = charset.clone();
        int counter = 1;
        for (int i = 0; i < charset.length; i++) {
            if (charset[i] > 0) {
                counter += charset[i] - 1;
                charset[i] = counter++;
            }
        }
    
        int[] offsets = new int[keyLength];
        for (int i = 0; i < keyLength; i++) {
            char c = key.charAt(i);
            offsets[charset[c] - charsetCount[c]--] = i;
        }
    
        StringBuilder resultBuilder = new StringBuilder();
        for (int i = 0; i < messageLength; i++) {
            int index = (i / keyLength) * keyLength + offsets[i % keyLength];
            resultBuilder.append(message.charAt(index));
        }
    
        return resultBuilder.toString();
    }
    public static String twoProduct(int[] arr, int n) {
        Set<Integer> set = new HashSet<>();
        for (int m : arr) {
            if (n % m == 0 && set.contains(n / m)) {
                return Arrays.toString(new int[]{n / m, m});
            }
            set.add(m);
        }
        return Arrays.toString(new int[]{});
    }
    public static String isExact(int n) {
        int[] result = calculateExact(1, 1, n);
        return (result[0] == n) ? Arrays.toString(result) : Arrays.toString(new int[]{});
    }
    
    private static int[] calculateExact(int f, int m, int n) {
        return (f < n) ? calculateExact(f * (m + 1), m + 1, n) : new int[]{f, m};
    }
    public static String fractions(String frac) {
        int startBracket = frac.indexOf('(');
        if (startBracket != -1) {
            String repeatingPart = frac.substring(startBracket + 1, frac.length() - 1);
            frac = frac.substring(0, startBracket);
            for (int i = 0; i < 9 - repeatingPart.length(); i++) {
                frac += repeatingPart;
            }
        }
        double a = Double.parseDouble(frac);
        int divisor = findDivisor(a);
        return formatFraction(a * divisor, divisor);
    }
    
    private static int findDivisor(double a) {
        int divisor = 2;
        while (Math.ceil(a * divisor) - a * divisor > 0.000001) {
            divisor++;
        }
        return divisor;
    }
    
    private static String formatFraction(double numerator, int denominator) {
        return (int) Math.ceil(numerator) + "/" + denominator;
    }
    public static String pilish_string(String str) {
        StringBuilder result = new StringBuilder();
        String pi = String.valueOf(Math.PI).replace(".", "");
        int piIndex = 0;
        while (!str.isEmpty()) {
            int p = pi.charAt(piIndex) - '0';
            int n = Math.min(p, str.length());
            result.append(str.substring(0, n));
            str = str.substring(n);
            piIndex++;
            if (!str.isEmpty()) {
                result.append(' ');
            } else if (p > n) {
                for (int i = 0; i < p - n; i++) {
                    result.append(result.charAt(result.length() - 1));
                }
            }
        }
        return result.toString();
    }

    public static int generateNonconsecutive(String str) throws MathException {
        String operations = "+-/*";
        str = str.replaceAll("\\(", "( ");
        str = str.replaceAll("\\)", " )");
        String[] strArray = str.split("\\s+");
    
        boolean lastNum = false;
        String now;
        int bracketCounter = 0;
        int openBrackets = 0;
    
        for (int i = 0; i < strArray.length; i++) {
            now = strArray[i];
            if (operations.contains(now)) {
                if ((i == 0 || i == strArray.length - 1)) {
                    throw new MathException("Incorrect input format - starts or ends with an operation");
                }
                if (!lastNum) {
                    throw new MathException("Incorrect input format (two operations in a row or a parenthesis error)");
                }
                lastNum = false;
            } else if (now.equals("(")) {
                openBrackets += 1;
                bracketCounter += 1;
            } else if (now.equals(")")) {
                openBrackets -= 1;
                if (openBrackets < 0) {
                    throw new MathException("Incorrect input format (wrong order of brackets)");
                }
            } else if (now.matches("-?\\d+")) {
                if (lastNum) {
                    throw new MathException("Incorrect input format (two numbers in a row or a parenthesis error)");
                }
                lastNum = true;
            } else {
                throw new MathException("Invalid input format (unknown substring occurs)");
            }
        }
        if (openBrackets != 0) {
            throw new MathException("Invalid input format (wrong order of brackets)");
        }
    
        if (strArray.length == 3) {
            int firstNum = Integer.parseInt(strArray[0]);
            int secondNum = Integer.parseInt(strArray[2]);
            switch (strArray[1]) {
                case "+":
                    return firstNum + secondNum;
                case "-":
                    return firstNum - secondNum;
                case "*":
                    return firstNum * secondNum;
                case "/":
                    try {
                        return firstNum / secondNum;
                    } catch (ArithmeticException e) {
                        throw new MathException("Error: Division by zero occurs in the string");
                    }
            }
        } else {
            if (bracketCounter != 0) {
                int firstBracket = -1;
                int secondBracket = -1;
                for (int i = 0; i < strArray.length; i++) {
                    now = strArray[i];
                    if (now.equals("(") && firstBracket == -1) {
                        firstBracket = i;
                    }
                    if (now.equals(")")) {
                        secondBracket = i;
                    }
                }
                String newStr = "";
                if (firstBracket != 0) {
                    newStr += String.join(" ", Arrays.copyOfRange(strArray, 0, firstBracket)) + " ";
                }
                newStr += generateNonconsecutive(String.join(" ", Arrays.copyOfRange(strArray, firstBracket + 1, secondBracket)));
                if (secondBracket != strArray.length) {
                    newStr += " " + String.join(" ", Arrays.copyOfRange(strArray, secondBracket + 1, strArray.length));
                }
                return generateNonconsecutive(newStr);
            } else {
                int operationIndex = -1;
                boolean higher = false;
                for (int i = 0; i < strArray.length; i++) {
                    now = strArray[i];
                    if ("+-".contains(now) && operationIndex == -1) {
                        operationIndex = i;
                    }
                    if ("*/".contains(now) && !higher) {
                        operationIndex = i;
                        higher = true;
                    }
                }
                String newStr = "";
                if (operationIndex != 1) {
                    newStr += String.join(" ", Arrays.copyOfRange(strArray, 0, operationIndex - 1)) + " ";
                }
                newStr += generateNonconsecutive(String.join(" ", Arrays.copyOfRange(strArray, operationIndex - 1, operationIndex + 2)));
                if (openBrackets != strArray.length - 1) {
                    newStr += " " + String.join(" ", Arrays.copyOfRange(strArray, operationIndex + 2, strArray.length));
                }
                return generateNonconsecutive(newStr);
            }
        }
        return 0;
    }
    public static String isValid(String str) {
        int[] charCount = new int[26];
        for (char c : str.toCharArray()) {
            charCount[c - 'a']++;
        }
    
        int oddCount = 0;
        for (int count : charCount) {
            if (count % 2 != 0) {
                oddCount++;
            }
        }
        
        return oddCount <= 1 ? "YES" : "NO";
    }

    public static String findLCS(String str1, String str2) {
        int[][] matrix = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i <= str1.length(); i++) {
            for (int j = 0; j <= str2.length(); j++) {
                if (i == 0 || j == 0) {
                    matrix[i][j] = 0;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i][j - 1], matrix[i - 1][j]);
                }
            }
        }
    
        List<Character> chars = new ArrayList<>();
        int temp_i = str1.length() - 1;
        int temp_j = str2.length() - 1;
        while (temp_i >= 0 && temp_j >= 0) {
            if (str1.charAt(temp_i) == str2.charAt(temp_j)) {
                chars.add(str1.charAt(temp_i));
                temp_i -= 1;
                temp_j -= 1;
            } else if (matrix[temp_i][temp_j + 1] >= matrix[temp_i + 1][temp_j]) {
                temp_i -= 1;
            } else {
                temp_j -= 1;
            }
        }
    
        Collections.reverse(chars);
        StringBuilder answer = new StringBuilder();
        for (char c : chars) {
            answer.append(c);
        }
        return answer.toString();
    }
}