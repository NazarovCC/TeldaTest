# Тестовое задание для Telda
## Разработка REST API

### Запуск проекта в docker-compose
 - воспользоваться командой, для поднятия контейнера, 
   `docker-compose -f docker-compose.yaml up -d`
<hr/>

### API
 - [GET]: 
   - `api/regions` - получение всех регионов
     - **PARAMS** `title, shortTitle` 
   - `api/regions/{id}` - получение региона по id
 - [POST]:
   - `api/regions` - добавление региона
     - **BODY** `{title: наименование, shortTitle: сокращенное наименование}`
 - [DELETE]:
   - `api/regions/{id}` - удаление региона по id
 - [PUT]:
   - `api/regions/{id}` - обновление региона по id
     - **BODY** `{id: идентификатор, title: наименование, shortTitle: сокращенное наименование}` 
<hr/>

### SWAGGER

[Ссылка на swagger](http://localhost:8181/swagger-ui/index.html)