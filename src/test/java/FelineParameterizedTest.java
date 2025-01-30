import com.example.Feline;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class FelineParameterizedTest {

    private Feline feline;

    @Parameterized.Parameter
    public int inputKittensCount; // Входной параметр для теста

    @Parameterized.Parameter(1)
    public int expectedKittensCount; // Ожидаемое значение

    @Before
    public void setUp() {
        feline = new Feline(); // Создаем экземпляр Feline
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 1, 1 }, // Тест для 1 котенка
                { 5, 5 }, // Тест для 5 котят
                { 10, 10 }, // Тест для 10 котят
                { 0, 0 } // Тест для 0 котят
        });
    }

    @Test
    public void testGetKittens_WithParameter() {
        assertEquals(expectedKittensCount, feline.getKittens(inputKittensCount));
    }
}