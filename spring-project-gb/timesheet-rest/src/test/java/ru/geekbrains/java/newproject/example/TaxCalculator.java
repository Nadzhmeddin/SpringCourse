package ru.geekbrains.java.newproject.example;

public class TaxCalculator {

    private final TaxResolver taxResolver;

    public TaxCalculator(TaxResolver taxResolver) {
        this.taxResolver = taxResolver;
    }

    // Вычисляет стоимость товара с учетом НДС
    public double getPriceWithTax(double price) {
        double tax = taxResolver.getCurrentTax();
        return price + price * tax;
    }
}
