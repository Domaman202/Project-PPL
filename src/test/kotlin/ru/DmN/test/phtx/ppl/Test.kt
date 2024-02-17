package ru.DmN.test.phtx.ppl

import ru.DmN.phtx.ppl.attribute.ACenter
import ru.DmN.phtx.ppl.container.CPair
import ru.DmN.phtx.ppl.element.EImage
import ru.DmN.phtx.ppl.element.EText
import ru.DmN.phtx.ppl.element.ETitle
import ru.DmN.phtx.ppl.page.PageList
import ru.DmN.phtx.ppl.page.PageSizedList
import ru.DmN.phtx.ppl.utils.Presentation
import javax.imageio.ImageIO
import javax.swing.SwingUtilities

object Test {
    @JvmStatic
    fun main(args: Array<String>) {
        SwingUtilities.invokeLater {
            Presentation("Тестовая Презентация", 1000).apply {
                this += PageList(
                    mutableListOf(
                        ETitle("Тестовая Презентация", 26),
                        ACenter(EText("Тестовая Презентация", 26))
                    )
                )
                //
                this.show()
            }
        }
    }
}