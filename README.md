# App CoinGecko

## Описание
Android-Приложение написанное на Kotlin, которое обращается к API CoinGecko и показывает список криптовалют


## Декомпозиция

* Необходимо добавить интернет разрешение
* Установить нужные библиотеки
* Прописать новые цвета и установить шрифты
* Добавить Retrofit
* Создать интерфейс и репозиторий
* Создать DataClass под 2 запроса
* Написать еще 2 дополнительных класса для удобной работы
* Добавить навигацию

* Добавить 2 Screen под работу приложения (CryptoListScreen, CryptoDetailScreen)
* К Screen добавить State и ViewModel

* Написать 3 состояния для CryptoListState
* Создать запрос для вывода списка в CryptoListViewModel
* Модифицировать результат запроса
* Создать компонент, который будет использован как элемент в LazyItem
* Создать TopBar в CryptoListScreen
* Добавить Content(List) в CryptoListScreen
* Добавить визуализацию загрузки и ошибки

* Написать 3 состояния для CryptoListState
* Создать запрос для вывода допольнительный информации в CryptoListViewModel
* Модифицировать результат запроса
* Создать TopBar в CryptoDetailScreen
* Добавить Content в CryptoDetailScreen
* Добавить визуализацию загрузки и ошибки

## Стек
* Kotlin
* Coroutine
* Single Activity
* Gson
* MVVM
* Retrofit2
* Jetpack Compose
## Установка
Скопируйте следующий код в Git Bash:
```
$ git clone https://github.com/YrChubai/AppCoinGecko.git
```
