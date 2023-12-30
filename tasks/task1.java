public class task1 {
    public static void main(String[] args) {
        System.out.println(convert(5));
        System.out.println(convert(3));
        System.out.println(convert(8));
        System.out.println(fitCalc(15, 1));
        System.out.println(fitCalc(24, 2));
        System.out.println(fitCalc(41, 3));
        System.out.println(containers(3, 4, 2));
        System.out.println(containers(5, 0, 2));
        System.out.println(containers(4, 1, 4));
        System.out.println(triangleType(5, 5, 5));
        System.out.println(triangleType(5, 4, 5));
        System.out.println(triangleType(3, 4, 5));
        System.out.println(triangleType(5, 1, 1));
        System.out.println(ternaryEvaluation(8, 4));
        System.out.println(ternaryEvaluation(1, 11));
        System.out.println(ternaryEvaluation(5, 9));
        System.out.println(howManyItems(22, 1.4, 2));
        System.out.println(howManyItems(45, 1.8, 1.9));
        System.out.println(howManyItems(100, 2, 2));
        System.out.println(factorial(3));
        System.out.println(factorial(5));
        System.out.println(factorial(7));
        System.out.println(gcd(48, 18));
        System.out.println(gcd(52, 8));
        System.out.println(gcd(259, 28));
        System.out.println(ticketSaler(70, 1500));
        System.out.println(ticketSaler(24, 950));
        System.out.println(ticketSaler(53, 1250));
        System.out.println(tables(5, 2));
        System.out.println(tables(31, 20));
        System.out.println(tables(123, 58));

    }

    public static double convert(int gal) {
        double liters = gal * 3.785;
        return liters;
    }
    public static int fitCalc(int minutes, int mode) {
        if (mode != 1 && mode != 2 && mode != 3) {
            System.out.println("Invalid input: mode value can not be anything except 1, 2 or 3");
            return 0; //пока не знаю как правильно обработать исключение, поэтому возвращаю 0
        }
        else {
        int calories_burnt = minutes * mode;
        return calories_burnt;
        }
    }
        
    public static int containers(int box, int sack, int barrel) {
        int objects_total = 20 * box + 50 * sack + 100 * barrel;
        return objects_total;
    }
    public static String triangleType(int a_side, int b_side, int c_side) {
        if (a_side + b_side < c_side || b_side + c_side < a_side || c_side + a_side < b_side) {
            return "not a triangle";
        }
        else if (a_side == b_side && b_side == c_side) {
            return "equilateral"; //в задачнике ошибка: isosceles - это равнобедренный
        }
        else if (a_side == b_side || a_side == c_side || b_side == c_side) {
            return "isosceles";
        }
        else {
            return "different-sided";
        }
    }
    
    public static int ternaryEvaluation(int first_num, int second_num) {
        int largest_num = first_num > second_num ? first_num : second_num;
        return largest_num;
    }
    
    public static int howManyItems(int n, double w, double h) {
        int cover = (int) (n / (w * h * 2));
        return cover;
    }
    
    public static int factorial(int num_for_calc) {
        int factorial_value = 1;
        for (int i = 1; i <= num_for_calc; i++) {
                factorial_value = factorial_value * i;
        }
        return factorial_value;
    }
    
    public static int gcd(int first_value, int second_value) {
        int gcd = 1;
        for (int i = 1; i <= first_value && i <= second_value; i++) {
                if (first_value % i == 0 && second_value % i == 0) {
                    gcd = i;
            }
        }
        return gcd;
    }
    
    public static int ticketSaler(int ticket_amount, int ticket_price) {
        int profit = (int) (ticket_amount * ticket_price * 0.72);
        return profit;
    }
    
    public static int tables(int students_amount, int available_tables) {
        if (available_tables * 2 > students_amount) {
            int extra_tables = 0;
            return extra_tables;
        }
        else {
            int students_without_table = students_amount - (available_tables * 2);
            int extra_tables = (students_without_table / 2) + 1;
            return extra_tables;
        }
    }
}
