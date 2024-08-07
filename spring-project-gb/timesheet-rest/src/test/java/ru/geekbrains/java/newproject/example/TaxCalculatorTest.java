package ru.geekbrains.java.newproject.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaxCalculatorTest {

    @Mock
    TaxResolver mock;



    @Test
    void testGetPriceWithTax() {

//        TaxResolver mock = mock(TaxResolver.class);
//        when(mock.getCurrentTax()).thenReturn(0.2);

        doReturn(0.2).when(mock).getCurrentTax();



        TaxCalculator taxCalculator = new TaxCalculator(mock);

        Assertions.assertEquals(120, taxCalculator.getPriceWithTax(100));

        verify(mock).getCurrentTax();


    }
}