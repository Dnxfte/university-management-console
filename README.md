# University Management Console

Консольна система управління університетом на Java.

## Мета

Проєкт імітує просту систему для роботи зі студентами, викладачами, курсами та зарахуваннями на курси.

## Структура

```text
src/
└── university/
    ├── Main.java
    ├── entities/
    ├── enums/
    ├── interfaces/
    ├── services/
    └── util/
```

## Запуск

```bash
javac -d out $(find src -name "*.java")
java -cp out university.Main
```
