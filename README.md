**Инструкция по установке:**

1) Context path приложения "/", после деплоя через Tomcat оно доступно по адресу http://localhost:8080/meetings/
2) Таблицы создаются в БД "meetings" (url=jdbc:mysql://localhost:3306/meetings, user=root, password=root, driver=com.mysql.jdbc.Driver)

**Интерфейс:**

RESTful

**Используемые технологии:**

1) DB: MySQL + Hibernate + Spring Data
2) Back-end: Spring web + webMVC с конфигурацией через аннотации в Java-классах
3) Front-end: JSP + JSTL + JQuery + AJAX + Bootstrap + Datatable

**Тестирование:**

Набор JUnit тестов

Исходный код приложения доступен на https://github.com/Kowi77/Meetings