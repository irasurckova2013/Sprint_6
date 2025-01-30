import com.example.Feline;
import com.example.Lion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class LionTest {
    @Mock
    private Feline feline;


    private Lion lion;


    @Test
    public void testConstructor_SexMale() throws Exception {
        lion = new Lion("Самец", feline); // Инициализируем Lion с полом "Самец"
        assertTrue(lion.doesHaveMane());
    }

    @Test
    public void testConstructor_SexFemale() throws Exception {
        lion = new Lion("Самка", feline); // Инициализируем Lion с полом "Самка"
        assertFalse(lion.doesHaveMane());
    }

    @Test
    public void testConstructor_InvalidSex() {
        try {
            lion = new Lion("Некорректный пол", feline); // Инициализируем Lion с некорректным полом
            fail("Ожидалось исключение");
        } catch (Exception e) {
            assertEquals("Используйте допустимые значения пола животного - самец или самка", e.getMessage());
        }
    }
    @Test
    public void testGetKittens() throws Exception {
        when(feline.getKittens()).thenReturn(3); // Настраиваем мок для возврата 3 котят
        lion = new Lion("Самец", feline); // Инициализируем Lion
        assertEquals(3, lion.getKittens());
    }
    @Test
    public void testGetFood() throws Exception {
        List<String> expectedFood = Arrays.asList("Мясо", "Рыба");
        when(feline.getFood("Хищник")).thenReturn(expectedFood); // Настраиваем мок для возврата списка еды
        lion = new Lion("Самец", feline); // Инициализируем Lion
        assertEquals(expectedFood, lion.getFood());
    }

    @Test
    public void testGetFood_Exception() throws Exception {
        when(feline.getFood("Хищник")).thenThrow(new Exception("Ошибка получения еды")); // Настраиваем мок для выбрасывания исключения
        lion = new Lion("Самец", feline); // Инициализируем Lion
        try {
            lion.getFood();
            fail("Ожидалось исключение");
        } catch (Exception e) {
            assertEquals("Ошибка получения еды", e.getMessage());
        }
    }
}
