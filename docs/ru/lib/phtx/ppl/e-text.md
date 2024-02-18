## e-text
Создаёт `текст`.

### Применение

1. `(e-text text font)`<br>
`text` - _текст_.<br>
`font` - _размер шрифта_.

### Примеры

```pihta
(use-ctx pht phtx/ppl
    (app-fn
        (#show (presentation "Тестовая Презентация" 1000
            (page-list
                (e-text
                    """
                    Петр Первый - великий царь и реформатор своей страны. 
                    Он мудро правил, стремясь к прогрессу и процветанию. 
                    Реформы Петра призваны сделать Россию сильной и современной. 
                    Он создал прочные основы для будущего развития государства. 
                    Петр был не только политиком, но и настоящим военачальником. 
                    Он победил важные сражения и расширил границы России. 
                    Слава Петра Первого до сих пор остается неизгладимой. 
                    Он оставил неизмеримый след в истории и сердцах народа. 
                    Петр - символ силы, твердости, прогресса и мужества. 
                    Его имя будет жить вечно в сердцах поколений русских людей.
                    """
                    36))))))
```