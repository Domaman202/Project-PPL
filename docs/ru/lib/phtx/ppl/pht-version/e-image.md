## e-image
Создаёт `картинку`.

### Применение

1. `(e-image image)`<br>
`image` - _картика_.

### Примеры

```pihta
(use-ctx pht phtx/ppl
    (app-fn
        (#show (presentation "Тестовая Презентация" 1000 false
            (page-list
                (e-image (inc-img "res/0.jpg")))))))
```
***Требует наличие файла `0.jpg` в папке `res` исполняемого модуля.***

```pihta
(use-ctx pht phtx/ppl
    (app-fn
        (#show (presentation "Тестовая Презентация" 1000
            (page-list
                (e-image (inc-img "https://dpru.obs.ru-moscow-1.hc.sbercloud.ru/images/article/2023/10/18/d20efc18-6734-476e-be50-f6d570010c10.jpg")))))))
```