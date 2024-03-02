package first;

public class Factorial {
    public static long fact(long x) {
        return (x == 1) ? 1 : fact(x - 1) * x;
    }
}
