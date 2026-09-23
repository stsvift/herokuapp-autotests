# Практическая работа №12 — HerokuApp

Java 17, Maven 3.9+, Selenium WebDriver 4.28.1, TestNG 7.10.2 и Page Object.
Реализованы **все восемь разделов** задания, **8 тест-классов**, **28 запусков** с учётом параметров.

[Открытый Pull Request №1](https://github.com/stsvift/herokuapp-autotests/pull/1) · [Сценарии и дефекты](docs/TEST_CASES.md) · [Отчёт Word](docs/Отчёт_ПР12_HerokuApp_Волков.docx)

## Запуск

Установить JDK 17+, Maven 3.9+ и Chrome. Драйвер автоматически подбирает Selenium Manager; при первом запуске нужен Интернет.

```sh
git clone https://github.com/stsvift/herokuapp-autotests.git
cd herokuapp-autotests
git switch feature/ISP42_Volkov_selenium
mvn clean test
```

Полный запуск сохраняет проверки дефектов сайта: переходы в профили сейчас открывают Not Found, а Typos содержит случайную опечатку. Для стабильного набора:

```sh
mvn clean test -Pstable
mvn test -Pstable -Dheadless=false
mvn test -Dtest=InputsTest
mvn test -Pstable -Dbrowser=edge
mvn test -Pstable -Dbrowser=firefox
```

Проверено локально в Chrome. Поддержка Edge/Firefox предусмотрена, эти браузеры в зафиксированном прогоне не проверялись. Параметр `baseUrl` позволяет изменить базовый адрес.

## Результаты от 23 сентября 2026 года

| Набор | Запуски | Успех | Падения | Ошибки | Пропуски |
|---|---:|---:|---:|---:|---:|
| Полный | 28 | 25 | 3 | 0 | 0 |
| stable | 24 | 24 | 0 | 0 | 0 |

`stable` явно исключает группу `known-defect` из 4 запусков: 3 проверки отсутствия Not Found и строгую орфографию Typos. Исключённые тесты не считаются skipped. Все исходные проверки сохранены, повторов до успеха нет.

После запуска: `target/surefire-reports/` (XML, TXT, HTML TestNG) и `target/screenshots/` (PNG и URL/статус). Зафиксированные результаты: `evidence/`. CI запускает два задания: stable и полный набор. Для полного набора установлен `continue-on-error: true` из-за известных дефектов сайта; ошибки сохраняются в отчётах. Результаты CI следует смотреть во вкладке Actions отдельно от локального прогона.

## Структура

`src/test/java/ru/ks54/pages` — BasePage, Page Object для каждого раздела и ProfilePage.
`src/test/java/ru/ks54/tests` — BaseTest и восемь тест-классов. Каждый тест получает отдельный драйвер; явные ожидания вместо sleep; quit выполняется в finally.

## Отдельные ветки

`main` содержит только описание. Тесты в main не добавлены и PR не слит.

| Содержание | Ветка |
|---|---|
| Каркас | `feature/ISP42_Volkov_framework` |
| Add/Remove | `feature/ISP42_Volkov_add_remove` |
| Checkboxes | `feature/ISP42_Volkov_checkboxes` |
| Dropdown | `feature/ISP42_Volkov_dropdown` |
| Inputs | `feature/ISP42_Volkov_inputs` |
| Typos | `feature/ISP42_Volkov_typos` |
| Tables | `feature/ISP42_Volkov_tables` |
| Hovers | `feature/ISP42_Volkov_hovers` |
| Notifications | `feature/ISP42_Volkov_notifications` |
| Полный проект и отчёт | `feature/ISP42_Volkov_selenium` |

Все восемь тематических веток ответвлены от каркаса и имеют отдельный test-коммит. Их истории объединены в общей feature-ветке. В нужной ветке можно выполнять `mvn test` для её страницы; параметры запуска совпадают.

Проверенный коммит реализации: `ec2826dc0e39254abd1e701b95b9573dd18dd764`. Коммиты после него добавляют отчётность и CI. Текущий hash: `git rev-parse HEAD`.

Логин преподавателя для запроса Reviewer необходимо указать отдельно. Ревью преподавателя ещё не получено.

## Источники

- [Задание №12](https://drive.google.com/file/d/1fi-h0sa9iGzQvNbzs5iUd_i54T7DE1Gw/view)
- [Тестируемый сайт](https://the-internet.herokuapp.com)
- [Ожидания Selenium](https://www.selenium.dev/documentation/webdriver/waits/)
