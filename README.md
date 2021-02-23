# Тестовое задание
***

### О веб-приложении:
***

Задачей было создать сервис, который обращается к сервису курсов валют, и отдает gif в ответ:
если курс по отношению к рублю за сегодня стал выше вчерашнего, то отдает рандомную гифку отсюда https://giphy.com/search/rich,
иначе - отсюда https://giphy.com/search/broke.

Манипуляция со внешними ссылками происходит через файл application.properties. 

**По заданию, нужно было иметь возможность выбирать
валюту, относительно которой вычисляется курс. Но, к сожалению, у REST API сервиса валют эта функция
платная и не работает. Возможность ее использовать сохранена.**
### Инструкция по запуску:
***

1. `gradle clean build`
2. `gradle docker --info`
3. `docker run --name=gifcontainer -it gifcurrencyapp`
4. `docker exec -i -t gifcontainer bash`
