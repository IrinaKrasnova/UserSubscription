#  User Subscription Service

Микросервис на Spring Boot для управления пользователями и их подписками на цифровые сервисы (YouTube Premium, VK Музыка, Яндекс.Плюс, Netflix и др.).

---

##  Технологии

- Java 17
- Spring Boot 3
- Spring Data JPA
- PostgreSQL
- Liquibase
- Springdoc OpenAPI (Swagger UI)
- Docker, Docker Compose
- Gradle (Kotlin DSL)
- JUnit 5, Mockito

---

##  Функциональность

-  Пользователи:
  - Создание
  - Получение
  - Обновление
  - Удаление

-  Подписки:
  - Добавление подписки пользователю
  - Получение всех подписок пользователя
  - Удаление подписки
  - Получение топ-3 популярных подписок

---

##  Структура API

| Метод | Endpoint                                 | Описание                            |
|-------|----------------------------------------  |------------------------------------ |
| POST  | `/api/v1/users`                          | Создание пользователя               |
| GET   | `/api/v1/users/{id}`                     | Получение информации о пользователе |
| PUT   | `/api/v1/users`                          | Обновление данных пользователя      |
| DELETE| `/api/v1/users/{id}`                     | Удаление пользователя               |
| POST  | `/api/v1/subscriptions`                  | Добавить подписку                   |
| GET   | `/api/v1/users/{id}/subscriptions`       | Список подписок пользователя        |
| DELETE| `/api/v1/subscriptions/{subscriptionId}` | Удаление подписки                   |
| GET   | `/api/v1/subscriptions/top`              | ТОП-3 популярных подписок           |

---

##  Инструкция по запуску

### 1.  Настройка окружения

Убедитесь, что установлены:

- Docker
- JDK 17
- Gradle (или используйте wrapper)

### 2.  Сборка проекта

```bash
./gradlew clean build


Документация Swagger

После запуска сервиса Swagger доступен по адресу:
[http://localhost:8080/api/v1/swagger-ui.html](http://localhost:8080/api/v1/swagger-ui.html)

###
##
#
