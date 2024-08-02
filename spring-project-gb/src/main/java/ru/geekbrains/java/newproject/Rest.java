package ru.geekbrains.java.newproject;

public class Rest {

    /**
     * API - Application Programming Interface - спецификация, протокол, интерфейс, правила, договор, контракт ...
     *
     * REST `- набор соглашений для взаимодействия
     *
     *
     *
     * Проектирование магазина : Shop
     *
     * Набор ресурсов:
     * GET /products - Все продукты
     * GET /products/{id} - получить конкретный продукт
     * -- Предусмотреть запрос для поиска (aka like)
     *
     * POST /products - создать продукт
     * PUT /products - обновить продукт
     * PATCH /products - обновить продукты (с выборочными полями)
     *
     *
     * GET/profile/{id} - получить настройки пользователя
     * PUT/profile/{id} - обновить
     * -- Для получения данных текущего пользователя лучше передавать некий токен в заголовке Head
     *
     * Registration - регистрация нового пользователя
     * POST /registration body = {}
     *
     *
     * Cart - корзина пользователя
     * Все запросы должны содержать некий токен
     * GET /cart - получить корзину текущего пользователя
     * POST /cart/{productId} - добавление в корзину
     * или POST /cart body = {productId = x, count = x, ...}
     * DELETE /cart/{productId} - удалить товар из корзины (с любым количеством)
     *
     */
}
