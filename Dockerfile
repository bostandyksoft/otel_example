# Используем официальный образ OpenJDK с Maven для сборки
FROM maven:3.8.3-openjdk-17 AS build

WORKDIR /app

# Копируем pom.xml и src в контейнер
COPY pom.xml .
COPY src ./src

# Собираем приложение
RUN --mount=type=cache,target=./.m2 mvn clean package

# Используем образ с OpenJDK для рантайма
FROM openjdk:17-jdk-slim

WORKDIR /app

# Копируем собранный JAR из стадии сборки
COPY --from=build /app/target/*.jar app.jar

# Открываем порт, на котором работает Spring Boot
EXPOSE 8080

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "app.jar"]