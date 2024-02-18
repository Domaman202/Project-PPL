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
        (#show (presentation "Тестовая Презентация" 1000
            (page-list
                (e-title "Крещение Руси" 36)
                (e-text  (inc-txt "res/0.txt") 24)
                (e-image (inc-img "res/0.jpg"))
                (e-text  (inc-txt "res/1.txt") 24))
            (page-list
                (e-title "Флаги Российской Империи" 36)
                (e-image (inc-img "res/1.jpg"))
                (e-title "Флаги Российской Империи" 36)
                (e-image (inc-img "res/2.jpg")))
            (page-list
                (for [i (range 0 19)]
                    (e-title (+ "i = " i) 36)))))))
```