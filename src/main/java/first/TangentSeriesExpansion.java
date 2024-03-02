package first;


public class TangentSeriesExpansion {

    // Метод для вычисления факториала числа
    public static int factorial(int n) {
        if (n == 0)
            return 1;
        else
            return n * factorial(n - 1);
    }

    // Метод для вычисления степени числа
    public static double power(double x, int n) {
        if (n == 0)
            return 1;
        else if (n % 2 == 0)
            return power(x * x, n / 2);
        else
            return x * power(x * x, (n - 1) / 2);
    }

    // Метод для вычисления тангенса через степенной ряд
    public static double tangent(double x, int n) {
        double result = 0;
        for (int i = 0; i < n; i++) {
            result += (power(-1, i) * power(x, 2 * i + 1)) / factorial(2 * i + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        double x = 1.5; // Введите значение аргумента x
        int n = 10; // Количество членов ряда

        double tangentValue = tangent(x, n);
        System.out.println("Значение тангенса(" + x + "): " + tangentValue);
    }
}
