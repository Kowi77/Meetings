Инструкция по установке:

1) Context path приложения "/", после деплоя через Tomcat оно доступно по адресу http://localhost:8080/meet/
2) Таблицы создаются в БД "meetings" (url=jdbc:mysql://localhost:3306/meetings, user=root, password=root, driver=com.mysql.jdbc.Driver),
    которая должна быть подключена перед запуском приложения

Интерфейс:

RESTful

Используемые технологии:

1) DB: MySQL + Hibernate + Spring Data
2) Back-end: Spring web + webMVC с конфигурацией через аннотации в Java-классах
3) Front-end: JSP + JSTL + JQuery + AJAX + Bootstrap + Datatable

Тестирование:

Набор JUnit тестов

Не успел исправить:
- Дублирование кода в JS
- Дублирование кода в HTML
- Сохранение данных о совещании (в методе POST убрать лишние поля и установить автоматический биндинг с объектами JAVA)
- расположение элементов на страницах
- мелкие ошибки в логике приложения
- обработку возможных исключений
- валидацию редактирования данных совещания

Исходный код приложения доступен на https://github.com/Kowi77/Meetings