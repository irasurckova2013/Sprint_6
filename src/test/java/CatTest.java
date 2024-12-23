import com.example.Cat;
import com.example.Feline;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CatTest {

    @Mock
    private Feline feline;
    @InjectMocks// Создаем мок-объект Feline
    private Cat cat; // Создаем экземпляр Cat, в который будет инъектирован мок

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this); // Инициализация моков (необязательно, если используем MockitoJUnitRunner)
    }

    @Test
    public void testConstructor() {
        // Проверяем, что конструктор корректно инициализирует predator
        assertNotNull(cat);
        assertNotNull(cat.predator);
    }

    @Test
    public void testGetSound() {
        // Проверяем, что метод getSound возвращает "Мяу"
        assertEquals("Мяу", cat.getSound());
    }

    @Test
    public void testGetFood() throws Exception {
        // Настраиваем мок для возврата списка еды
        List<String> expectedFood = Arrays.asList("Мясо", "Рыба");
        when(feline.eatMeat()).thenReturn(expectedFood); // Предполагаем, что eatMeat() возвращает список еды
        assertEquals(expectedFood, cat.getFood());
    }

    @Test
    public void testGetFood_Exception() throws Exception {
        // Настраиваем мок для выбрасывания исключения
        when(feline.eatMeat()).thenThrow(new Exception("Ошибка получения еды"));
        try {
            cat.getFood();
            fail("Ожидалось исключение");
        } catch (Exception e) {
            assertEquals("Ошибка получения еды", e.getMessage());
        }
    }
}