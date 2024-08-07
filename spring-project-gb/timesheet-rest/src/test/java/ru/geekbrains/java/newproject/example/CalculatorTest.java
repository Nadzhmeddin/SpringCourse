package ru.geekbrains.java.newproject.example;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    // JUnit 5 - фреймворк для тестирования в Java
    // Mockito - библиотека для моков
    // AssertJ - библиотека для удобных ассертов (утверждений)


    @Test
    void testSum() {
        Calculator calculator = new Calculator();

        int actual = calculator.sum(2, 3);
        int expected = 5;

        assertEquals(expected,actual);
    }


}
