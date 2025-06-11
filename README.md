* запускаем конфигурации PriceApplication, ProductApplication
* запускаем docker compose
* генерим запросы на сервисы (GET / PUT )
* сервисы иногда тупят (Random)
* сервисы кидают ошибку на GET если небыло ничего
* в jaeger UI http://localhost:16686/ отображаются только "долгие" запросы и запросы с ошибкой (благодаря tail sampling)