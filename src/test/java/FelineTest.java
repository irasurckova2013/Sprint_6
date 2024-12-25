import com.example.Feline;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class FelineTest {

    private Feline feline;

    @Before
    public void setUp() {
        feline = new Feline(); // Создаем экземпляр Feline
    }

    @Test
    public void testEatMeat() throws Exception {
        // Настраиваем мок для метода getFood
        Feline mockFeline = Mockito.spy(feline);
        when(mockFeline.getFood("Хищник")).thenReturn(Arrays.asList("Мясо", "Рыба"));

        List<String> food = mockFeline.eatMeat();
        assertEquals(Arrays.asList("Мясо", "Рыба"), food);
    }

    @Test(expected = Exception.class)
    public void testEatMeat_Exception() throws Exception {
        // Настраиваем мок для метода getFood, чтобы он выбрасывал исключение
        Feline mockFeline = Mockito.spy(feline);
        when(mockFeline.getFood("Хищник")).thenThrow(new Exception("Ошибка получения еды"));

        mockFeline.eatMeat(); // Ожидаем, что будет выброшено исключение
    }

    @Test
    public void testGetFamily() {
        assertEquals("Кошачьи", feline.getFamily());
    }

    @Test
    public void testGetKittens_Default() {
        assertEquals(1, feline.getKittens()); // Проверяем, что по умолчанию возвращается 1
    }
}