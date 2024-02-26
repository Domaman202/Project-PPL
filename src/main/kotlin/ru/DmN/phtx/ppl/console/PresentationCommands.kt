package ru.DmN.phtx.ppl.console

import ru.DmN.pht.console.JvmCommands
import ru.DmN.siberia.console.BaseCommands
import ru.DmN.siberia.console.BuildCommands
import ru.DmN.siberia.console.Console
import ru.DmN.siberia.console.utils.Argument
import ru.DmN.siberia.console.utils.ArgumentType
import ru.DmN.siberia.console.utils.Command
import java.io.File

object PresentationCommands {
    val PRESENTATION_INIT = Command(
        "presentation-init",
        "pi",
        "Презентация",
        "Создание презентации",
        "Создаёт модуль презентации.",
        listOf(
            Argument(
                "name",
                "Название",
                ArgumentType.STRING,
                "Название презентации.",
                "Введите название презентации"
            ),
            Argument(
                "author",
                "Автор",
                ArgumentType.STRING,
                "Автор(ы) презентации.",
                "Введите автор(а/ов) презентации"
            )
        ),
        BaseCommands::interactiveAvailable,
        PresentationCommands::presentationInit
    )

    val PRESENTATION_RUN = Command(
        "presentation-run",
        "pr",
        "Презентация",
        "Запуск презентации",
        "Собирает и запускает модуль презентации.",
        emptyList(),
        BaseCommands::interactiveAvailable,
        PresentationCommands::presentationRun
    )

    val ALL_COMMANDS = listOf(PRESENTATION_INIT, PRESENTATION_RUN)

    @JvmStatic
    fun presentationRun(console: Console, vararg  args: Any?) {
        console.println("[T] Презентации:")
        val presentation =
            File(".").list()!!
                .filter { File(it).list()?.contains("module.pht") == true }
                .run {
                    forEachIndexed { i, it ->
                        console.print(
                            if (i + 1 < size)
                                "[|] "
                            else "[V] "
                        )
                        console.println("$i. ${nameModuleToRu(it)}")
                    }
                    get(console.readInt("\nВыберите презентацию"))
                }
        //
        BuildCommands.platformSelect(console, "JVM")
        BuildCommands.moduleSelect(console, presentation)
        BuildCommands.moduleCompile(console)
        JvmCommands.moduleRun(console)
    }

    @JvmStatic
    fun presentationInit(console: Console, vararg args: Any?) {
        val name = args[0] as String
        val author = args[1] as String
        //
        console.println("Создание презентации...")
        val dir = nameRuToModule(args[0] as String)
        try {
            File(dir).mkdir()
            File("$dir/module.pht").writer().use {
                it.write(
                    """
                        (module
                            (name "$dir")
                            (version "1.0.0")
                            (author "${author.ifEmpty { "Phantom" }}")
                            (deps ["pht" "phtx/ppl"])
                            (uses ["pht" "phtx/ppl"])
                            (src ["src.pht"]))
                    """.trimIndent()
                )
            }
            File("$dir/src.pht").writer().use {
                it.write(
                    """
                        (app-fn
                            (#show (presentation "$name" 2500
                                (inc-ppl "src.ppl"))))
                    """.trimIndent()
                )
            }
            File("$dir/src.ppl").writer().use {
                it.write(
                    """
                        [Страница]:
                            [Заголовок (36)]: $name
                            [Картинка]: res/logo.jpg
                            [Заголовок (24)]: Авторы: $author
                    """.trimIndent()
                )
            }
            File("$dir/res").mkdir()
            File("$dir/res/logo.jpg").writeBytes(PresentationCommands::class.java.getResourceAsStream("/phtx/ppl/logo.jpg")!!.readBytes())
            console.println("Создание презентации успешно завершено!")
        } catch (t: Throwable) {
            console.println("Создание презентации завершено с ошибками:")
            t.printStackTrace(console.print)
        }
    }

    @JvmStatic
    private fun nameRuToModule(name: String): String {
        val sb = StringBuilder()
        name.forEach {
            sb.append(
                when (it) {
                    'а', 'А' -> 'A'
                    'б', 'Б' -> '6'
                    'в', 'В' -> 'B'
                    'г', 'Г' -> 'r'
                    'д', 'Д' -> '9'
                    'е', 'Е' -> 'e'
                    'ё', 'Ё' -> 'o'
                    'ж', 'Ж' -> 'j'
                    'з', 'З' -> '3'
                    'и', 'И' -> 'U'
                    'й', 'Й' -> 'i'
                    'к', 'К' -> 'K'
                    'л', 'Л' -> 'l'
                    'м', 'М' -> 'M'
                    'н', 'Н' -> 'H'
                    'о', 'О' -> 'O'
                    'п', 'П' -> 'n'
                    'р', 'Р' -> 'P'
                    'с', 'С' -> 'C'
                    'т', 'Т' -> 'T'
                    'у', 'У' -> 'Y'
                    'ф', 'Ф' -> 'f'
                    'х', 'Х' -> 'X'
                    'ц', 'Ц' -> 'c'
                    'ч', 'Ч' -> '4'
                    'ш', 'Ш' -> 'W'
                    'щ', 'Щ' -> 'w'
                    'ъ', 'Ъ' -> 'd'
                    'ы', 'Ы' -> 'u'
                    'ь', 'Ь' -> 'b'
                    'э', 'Э' -> 'E'
                    'ю', 'Ю' -> 'y'
                    'я', 'Я' -> 'a'
                    ' '      -> '-'
                    else     -> it
                }
            )
        }
        return sb.toString()
    }

    @JvmStatic
    private fun nameModuleToRu(name: String): String {
        val sb = StringBuilder()
        var i = 0
        while (i < name.length) {
            sb.append(
                when (val it = name[i++]) {
                    'A' -> 'А'
                    '6' -> 'Б'
                    'B' -> 'B'
                    'r' -> 'Г'
                    '9' -> 'Д'
                    'e' -> 'E'
                    'o' -> 'Ё'
                    'j' -> 'Ж'
                    '3' -> 'З'
                    'U' -> 'И'
                    'i' -> "Й"
                    'K' -> 'К'
                    'l' -> 'Л'
                    'M' -> 'М'
                    'H' -> 'Н'
                    'O' -> 'О'
                    'n' -> 'П'
                    'P' -> 'Р'
                    'C' -> 'С'
                    'T' -> 'Т'
                    'Y' -> 'У'
                    'f' -> 'Ф'
                    'X' -> 'Х'
                    'c' -> 'Ц'
                    '4' -> 'Ч'
                    'W' -> 'Ш'
                    'w' -> 'Щ'
                    'd' -> 'Ъ'
                    'u' -> 'Ы'
                    'b' -> 'Ь'
                    'E' -> 'Э'
                    'y' -> 'Ю'
                    'a' -> 'Я'
                    '-' -> ' '
                    else-> it
                }
            )
        }
        return sb.toString()
    }
}