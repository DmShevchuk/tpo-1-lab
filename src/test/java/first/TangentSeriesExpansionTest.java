package first;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TangentSeriesExpansionTest {
    double eps = 0.1;

    @ParameterizedTest
    @ValueSource(doubles = {10.0, 15.0})
    void positiveNumbers(double value) {
        Assertions.assertEquals(Math.tan(value), TangentSeriesExpansion.tg(value), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-10.0, -15.0})
    void negativeNumbers(double value) {
        Assertions.assertEquals(Math.tan(value), TangentSeriesExpansion.tg(value), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 0.0 + 0.01, 0.0 - 0.01})
    void zeroCheck(double value) {
        Assertions.assertEquals(Math.tan(value), TangentSeriesExpansion.tg(value), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = { Math.PI / 2 })
    void positiveBoundCheck(double value) {
        Assertions.assertEquals(Double.POSITIVE_INFINITY, TangentSeriesExpansion.tg(value), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = { -Math.PI / 2 })
    void negativeBoundCheck(double value) {
        Assertions.assertEquals(Double.NEGATIVE_INFINITY, TangentSeriesExpansion.tg(value), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = { -Math.PI / 2 - 0.2, -Math.PI / 2 + 0.2 })
    void nearNegativeBoundCheck(double value) {
        Assertions.assertEquals(Math.tan(value), TangentSeriesExpansion.tg(value), eps * 4);
    }

    @ParameterizedTest
    @ValueSource(doubles = { Math.PI / 2 - 0.2, Math.PI / 2 + 0.2 })
    void nearPositiveBoundCheck(double value) {
        Assertions.assertEquals(Math.tan(value), TangentSeriesExpansion.tg(value), eps * 4);
    }


}
