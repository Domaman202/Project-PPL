## page-list
Создаёт `слайд` _списочного типа_.<br>
Последовательно добавляет элементы.<br>
Автоматически просчитывает размер и последовательно расставляет `элементы`.

### Применение

1. `(page-list (expr0) (exprN))`<br>
`expr0` `exprN` - _элементы_.

### Примеры

```pihta
(use-ctx pht phtx/ppl
    (app-fn
        (#show (presentation "Тестовая Презентация" 1000 false
            (page-list
                (e-title "Тестовая Презентация" 36)
                (e-title "Автор: DomamaN202" 26)
                (e-text "Какой-то текст:\n1. Какой-то пункт.\n2. Ещё один какой-то пункт." 26))))))
```