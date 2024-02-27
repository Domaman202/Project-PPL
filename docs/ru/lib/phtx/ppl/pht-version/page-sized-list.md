## page-sized-list
Создаёт `слайд` _списочного типа_ с `определённым размером`.<br>
Последовательно добавляет элементы.<br>
Автоматически просчитывает размер и последовательно расставляет `элементы`.

### Применение

1. `(pages-sized-list width height (expr0) (exprN))`<br>
`width` - _ширина_.<br>
`height` - _высота_.<br>
`expr0` `exprN` - _элементы_.

### Примеры

```pihta
(use-ctx pht phtx/ppl
    (app-fn
        (#show (presentation "Тестовая Презентация" 1000 false
            (page-sized-list 640 480
                (a-sized 640 480
                    (e-image (inc-img "res/0.jpg"))))))))
```
***Требует наличие файла `0.jpg` в папке `res` исполняемого модуля.***