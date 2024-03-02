import first.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TangentSeriesExpansionTest {

    @Test
    public void testTangent() {
        // Тестирование для нескольких значений аргумента и количества членов ряда
        assertEquals(Math.tan(1), TangentSeriesExpansion.tangent(1, 10), 0.00001);
        assertEquals(Math.tan(0.5), TangentSeriesExpansion.tangent(0.5, 10), 0.00001);
        assertEquals(Math.tan(1), TangentSeriesExpansion.tangent(1, 5), 0.00001);
        assertEquals(Math.tan(2), TangentSeriesExpansion.tangent(2, 8), 0.00001);
    }

    @Test
    public void testFactorial() {
        // Тестирование факториала для нескольких значений
        assertEquals(1, TangentSeriesExpansion.factorial(0));
        assertEquals(1, TangentSeriesExpansion.factorial(1));
        assertEquals(120, TangentSeriesExpansion.factorial(5));
        assertEquals(720, TangentSeriesExpansion.factorial(6));
    }

    @Test
    public void testPower() {
        // Тестирование степени для нескольких значений
        assertEquals(1.0, TangentSeriesExpansion.power(2, 0), 0.00001);
        assertEquals(8.0, TangentSeriesExpansion.power(2, 3), 0.00001);
        assertEquals(16.0, TangentSeriesExpansion.power(2, 4), 0.00001);
        assertEquals(32.0, TangentSeriesExpansion.power(2, 5), 0.00001);
    }
}
