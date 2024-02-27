## inc-ppl
Подключение кода из `файла` на языке `ppl`.

### Применение

1. `(inc-ppl file)`<br>
`file` - _имя файла_.

### Примеры

*src.pht*
```pihta
(app-fn
    (#show (presentation "Тестовая презентация" 2500 false
        (inc-ppl "src.ppl"))))
```

*src.ppl*
```ppl
[Страница]:
    [Заголовок (32)]: Россия - это наша Родина!
```