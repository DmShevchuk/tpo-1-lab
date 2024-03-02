package first;

import org.apache.commons.math3.fraction.BigFraction;

public class BernoulliNumber {
    public static BigFraction bernouilli(int n) {
        BigFraction[] a = new BigFraction[n + 1];
        for (int m = 0; m <= n; m++) {
            a[m] = new BigFraction(1, (m + 1));
            for (int j = m; j >= 1; j--)
                a[j - 1] = (a[j - 1].subtract(a[j])).multiply(new BigFraction(j));
        }
        return a[0];
    }
}
