package first;


public class TangentSeriesExpansion {

    public static double tg(double x) {
        if (x == Math.PI / 2) {
            return Double.POSITIVE_INFINITY;
        }
        else if (x == -Math.PI / 2) {
            return Double.NEGATIVE_INFINITY;
        }

        double eps = 0.1;

        if (x >= Math.PI / 2) {
            while (x >= Math.PI / 2) x -= Math.PI;
        }
        else if (x <= -Math.PI / 2) {
            while (x <= -Math.PI / 2) x += Math.PI;
        }

        double sum = x;
        double previousSum = Integer.MIN_VALUE;
        int n = 2;

        while (Math.abs(sum - previousSum) >= eps) {
            previousSum = sum;
            var decimal = BernoulliNumber.bernouilli(2 * n);
            double bernouilliCoefficient = (double) decimal.getNumeratorAsLong() / decimal.getDenominatorAsLong();
            sum += (bernouilliCoefficient * (Math.pow(-4, n)) * (1 - Math.pow(4, n)) / Factorial.fact(2L * n)) * (Math.pow(x, 2 * n - 1));
            n++;
        }
        return sum;
    }
}
