import java.util.*;

public class task5 {
    public static void main(String[] args) {
        System.out.println(sameLetterPattern("ABAB", "CDCD"));
        System.out.println(sameLetterPattern("ABCBA", "BCDCB"));
        System.out.println(sameLetterPattern("FFGG", "CDCD"));
        System.out.println(sameLetterPattern("FFFF", "ABCD"));
        System.out.println(spiderVsFly("H3", "E2"));
        System.out.println(spiderVsFly("A4", "B2"));
        System.out.println(spiderVsFly("A4", "C2"));
        System.out.println(digitsCount(4666));
        System.out.println(digitsCount(544));
        System.out.println(digitsCount(121317));
        System.out.println(digitsCount(0));
        System.out.println(digitsCount(12345));
        System.out.println(digitsCount(1289396387328L));
        System.out.println(totalPoints(new String[]{"cat", "create", "sat"}, "caster"));
        System.out.println(totalPoints(new String[]{"trance", "recant"}, "recant"));
        System.out.println(totalPoints(new String[]{"dote", "dotes", "toes", "set", "dot", "dots", "sted"}, "tossed"));
        System.out.println(sumsUp(new int[]{1, 2, 3, 4, 5}));
        System.out.println(sumsUp(new int[]{1, 2, 3, 7, 9}));
        System.out.println(sumsUp(new int[]{10, 9, 7, 2, 8}));
        System.out.println(sumsUp(new int[]{1, 6, 5, 4, 8, 2, 3, 7}));
        System.out.println(takeDownAverage(new String[]{"95%", "83%", "90%", "87%", "88%", "93%"}));
        System.out.println(takeDownAverage(new String[]{"10%"}));
        System.out.println(takeDownAverage(new String[]{"53%", "79%"}));
        System.out.println(caesarCipher("encode", "hello World", 3));
        System.out.println(caesarCipher("decode", "almost last task!", 4));
        System.out.println(setSetup(5, 3));
        System.out.println(setSetup(7, 3));
        System.out.println(timeDifference("Los Angeles", "April 1, 2011 23:23", "Canberra"));
        System.out.println(timeDifference("London", "July 31, 1983 23:01", "Rome"));
        System.out.println(timeDifference("New York", "December 31, 1970 13:40", "Beijing"));
        System.out.println(isNew(3));
        System.out.println(isNew(30));
        System.out.println(isNew(321));
        System.out.println(isNew(123));
    }

    public static boolean sameLetterPattern(String str1, String str2) {
    // checking if length is equal
    if (str1.length() != str2.length()) {
        return false;
    }
    // getting ascii value of 1st letters
    int startIndex1 = str1.charAt(0);
    int startIndex2 = str2.charAt(0);
    // calculating the difference
    int diff = Math.abs(startIndex1 - startIndex2);
    // comparing the difference between corresponding chars in both strings
    for (int i = 0; i < str1.length(); i++) {
        int charValue1 = Character.getNumericValue(str1.charAt(i));
        int charValue2 = Character.getNumericValue(str2.charAt(i));
        // if difference is not the same, then pattern is broken thus false is being returned
        if (Math.abs(charValue1 - charValue2) != diff) {
            return false;
        }
    }
    // if all chars have same diff, then they r having same letter pattern
    return true;
    }
    
    public static int digitsCount(long number) {
        // base case if number has 1 digit
        if (number < 10) {
            return 1;
        }
        // recursive case: counting the digits by moving through digits
        return 1 + digitsCount(number / 10);
    }

    public static String sumsUp(int[] nums) {
        LinkedList<String> answer = new LinkedList<>();
        LinkedList<Integer> numsBefore = new LinkedList<>();
        // iterating through the array of numbers
        for (int num : nums) {
            // iterating through previously iterated numbers
            for (int i = 0; i < numsBefore.size(); i++) {
                int numBefore = numsBefore.get(i);
                // checking if the current and previous number are having sum up to 8
                if (num + numBefore == 8) {
                    // adding the pair to the answer
                    answer.add(Arrays.toString(new int[] { Math.min(num, numBefore), Math.max(num, numBefore) }));
                    // removing the previous number so they wont duplicate
                    numsBefore.remove(i);
                    continue;
                }
            }
            // returning removed number
            numsBefore.add(num);
        }
        return Arrays.toString(answer.toArray());
    }

    public static String takeDownAverage(String[] percents) {
        int sum = 0;
        // iterating through the array of percents
        for (String s : percents) {
            // converting to int
            int percent = Integer.parseInt(s.substring(0, s.length() - 1));
            sum += percent;
        }
        // calculating avg score after decreasing each value by 5%
        int average = (sum / percents.length) - (percents.length * 5) - 5;
        return average + "%";
    }

    public static int setSetup(int n, int k) {
        // Base case: when k is 1, return n
        if (k == 1) {
            return n;
        }
        // recursive case: multiply n by result of previous with n and k decreased
        return n * setSetup(n - 1, k - 1);
    }

    public static boolean isNew(int numb) {
        String strNumb = Integer.toString(numb);
        // creating an array for every digit
        int[] numbs = new int[strNumb.length()];
        String numbWithoutZeros = strNumb.replace("0", "");
        // creating an array with digits without zeros
        int[] numbsWithoutZeros = new int[numbWithoutZeros.length()];
        int j = 0;
        for (int i = 0; i < strNumb.length(); i++) {
            int nowNumb = Integer.parseInt(strNumb.substring(i, i + 1));
            // if current digit is not zero, then adding it to the 2nd array
            if (nowNumb != 0) {
                numbsWithoutZeros[j] = nowNumb;
                j += 1;
            }
            numbs[i] = nowNumb;
        }
        // sorting both arrays
        Arrays.sort(numbs);
        Arrays.sort(numbsWithoutZeros);
        // building the minumum possible number
        StringBuilder minNumb = new StringBuilder(Integer.toString(numbsWithoutZeros[0]));
        // calculating the index of the digit to be skipped
        int index = numbs.length - numbsWithoutZeros.length;
        for (int i = 0; i < numbs.length; i++) {
            // if the current index is not the index to be skipped, append the digit to the minimum number
            if (i != index) {
                minNumb.append(numbs[i]);
            }
        }
        return strNumb.contentEquals(minNumb);
    }

    public static String timeDifference(String city1, String firstDate, String city2) {
        // calculating the time diff between 2 cities
        int time1 = timeZone(city1);
        int time2 = timeZone(city2);
        int deltaTime = time2 - time1;
        int deltaHour = deltaTime / 60;
        int deltaMinute = deltaTime % 60;
        // processing the firstdate str
        String[] words = firstDate.split(" ");
        int month = monthIndex(words[0]);
        int day = Integer.parseInt(words[1].replace(",", ""));
        int year = Integer.parseInt(words[2]);
        String[] time = words[3].split(":");
        int hour = Integer.parseInt(time[0]);
        int minute = Integer.parseInt(time[1]);
        // adding deltas ignoring limits
        int newYear = year;
        int newMonth = month;
        int newDay = day;
        int newHour = hour + deltaHour;
        int newMinute = minute + deltaMinute;
        // readjusting minutes, hours, days, months and years to their limits
        if (newMinute < 0) {
            newMinute += 60;
            newHour -= 1;
        } else if (newMinute >= 60) {
            newMinute -= 60;
            newHour += 1;
        }
        if (newHour < 0) {
            newHour += 24;
            newDay -= 1;
        } else if (newHour >= 24) {
            newHour -= 24;
            newDay += 1;
        }
        if (newDay == 0) {
            newMonth -= 1;
            if (newMonth == 0) {
                newMonth = 12;
                newYear -= 1;
            }
            newDay = dayInMonth(newMonth, newYear);
        } else if (newDay > dayInMonth(newMonth, newYear)) {
            newMonth += 1;
            if (newMonth == 13) {
                newMonth = 1;
                newYear += 1;
            }
            newDay = 1;
        }
        // formatting the answer
        String strMinute;
        if (newMinute < 10) {
            strMinute = "0" + newMinute;
        } else {
            strMinute = Integer.toString(newMinute);
        }
        String strHour;
        if (newHour < 10) {
            strHour = "0" + newHour;
        } else {
            strHour = Integer.toString(newHour);
        }
        return newYear + "-" + newMonth + "-" + newDay + " " + strHour + ":" + strMinute;
    }
    
    public static int timeZone(String city) {
        int time = 0;
        switch (city) {
            case "Los Angeles" -> time = -8 * 60;
            case "New York" -> time = -5 * 60;
            case "Caracas" -> time = -(4 * 60 + 30);
            case "Buenos Aires" -> time = -3 * 60;
            case "London" -> time = 0;
            case "Rome" -> time = 60;
            case "Moscow" -> time = 3 * 60;
            case "Tehran" -> time = 3 * 60 + 30;
            case "New Delhi" -> time = 5 * 60 + 30;
            case "Beijing" -> time = 8 * 60;
            case "Canberra" -> time = 10 * 60;
        }
        return time;
    }
    public static int monthIndex(String month) {
        int index = 0;
        switch (month) {
            case "January" -> index = 1;
            case "February" -> index = 2;
            case "March" -> index = 3;
            case "April" -> index = 4;
            case "May" -> index = 5;
            case "June" -> index = 6;
            case "July" -> index = 7;
            case "August" -> index = 8;
            case "September" -> index = 9;
            case "October" -> index = 10;
            case "November" -> index = 11;
            case "December" -> index = 12;
        }
        return index;
    }
    
    public static int dayInMonth(int monthIndex, int year) {
        int days = 0;
        switch (monthIndex) {
            case 1, 3, 5, 7, 8, 10, 12 -> days = 31;
            case 4, 6, 9, 11 -> days = 30;
            case 2 -> {
                if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                    days = 29;
                } else {
                    days = 28;
                }
            }
        }
        return days;
    }

    public static String caesarCipher(String mode, String str, int rot) {
        StringBuilder answer = new StringBuilder();
        if (mode.equals("encode")) {
            // iterating through each char in lowercase
            for (char symbol : str.toLowerCase().toCharArray()) {
                // checking if char is a letter
                if (('a') <= symbol && symbol <= 'z') {
                    // shifting the char by rot and converting to uppercase
                    char newSymbol = (char) (symbol - 32 + rot);
                    if (newSymbol > 'Z') newSymbol = (char) (newSymbol - 26);
                    answer.append(newSymbol);
                } else {
                    // processing non letter chars
                    answer.append(symbol);
                }
            }
        }
        //same but backwards for new symbol
        else if (mode.equals("decode")) {
            for (char symbol : str.toCharArray()) {
                if ('a' <= symbol && symbol <= 'z') {
                    char newSymbol = (char) (symbol - 32 - rot);
                    if (newSymbol < 'A') newSymbol = (char) (newSymbol + 26);
                    answer.append(newSymbol);
                } else {
                    answer.append(symbol);
                }
            }
        }
        return answer.toString();
    }

    public static int[] getCharset(String word) {
        int[] charset = new int[127]; // storing amount of char appearences in array
        for (char c : word.toCharArray()) {
            charset[c]++;
        }
        return charset;
    }
    
    public static int totalPoints(String[] words, String scramble) {
        int points = 0;
        int[] scrambleCharset = getCharset(scramble);
        for (String word : words) {
            int[] wordCharset = getCharset(word);
            // flag to show if the word could be made from scramble word
            boolean good = true;
            for (int j = 0; j < 127; j++) {
                if (wordCharset[j] > scrambleCharset[j]) {
                    // if any char appears more times in charset of current word than in scramble then the word cant be formed from scramble
                    good = false;
                    break;
                }
            }
            if (good) {
                points += word.length() - 2; // points based on word length
                if (word.length() == 6) {
                    points += 50;
                }
            }
        }
        return points;
    }

    public static double getRouteLength(int spiderRing, int ringParts, int offset) {
        // length of each small part of ring
        final double RING_PART = Math.sqrt(2 - (2 * Math.cos(45)));
        return offset * 2 + (spiderRing - offset) * ringParts * RING_PART;
    }
    
    public static String getRingRoute(int spiderRay, int flyRay, int rays, int ring) {
        StringBuilder route = new StringBuilder();
        // if clockwise
        if ((spiderRay + rays) % 8 == flyRay) {
            for (int i = 1; i <= rays; i++) {
                route.append("-").append(Character.toString((spiderRay + i) % 8 + 65)).append(ring);
            }
        // if counterclockwise
        } else if ((spiderRay - rays) % 8 == flyRay) {
            for (int i = 1; i <= rays; i++) {
                route.append("-").append(Character.toString((spiderRay - i) % 8 + 65)).append(ring); // Append the route segment to the StringBuilder
            }
        }
        return route.toString();
    }
    
    public static String spiderVsFly(String spiderBase, String flyBase) {
        int spiderRay = spiderBase.charAt(0) - 65; // 0-7
        int spiderRing = spiderBase.charAt(1) - 48; // to int
        int flyRay = flyBase.charAt(0) - 65; // 0-7
        int flyRing = flyBase.charAt(1) - 48; // to int
        int rays;
        StringBuilder route = new StringBuilder(spiderBase);
    
        if (Math.abs(spiderRay - flyRay) >= 4) {
            //calculating the amount of rays to reach the fly using shortest direction both clock- and counterclockwise
            rays = 8 - Math.abs(spiderRay - flyRay);
        } else {
            rays = Math.abs(spiderRay - flyRay);
        }
        if (spiderRing > flyRing) { // if spider is above fly on ring
            for (int i = spiderRing - 1; i >= flyRing; i--) {
                route.append("-").append(Character.toString(spiderRay + 65)).append(i);
                spiderRing--;
            }
        }
        int shortOffset = 0;
        for (int i = 1; i <= spiderRing; i++) {
            if (getRouteLength(spiderRing, rays, shortOffset) > getRouteLength(spiderRing, rays, i)) {
                shortOffset = i;
            }
        }
        // trynna find shortest offset to minimize the route length
        for (int i = spiderRing - 1; i >= spiderRing - shortOffset; i--) {
            route.append("-").append(i == 0 ? "A" : Character.toString(spiderRay + 65)).append(i);
        }
        if (spiderRing - shortOffset > 0) {
            route.append(getRingRoute(spiderRay, flyRay, rays, spiderRing - shortOffset));
        }
        for (int i = spiderRing - shortOffset + 1; i <= flyRing; i++) {
            route.append("-").append(Character.toString(flyRay + 65)).append(i);
        }
    
        return route.toString();
    }

}