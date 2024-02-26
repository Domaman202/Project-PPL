package ru.DmN.test.phtx.ppl

import ru.DmN.phtx.ppl.element.EImage
import ru.DmN.phtx.ppl.element.ETitle
import ru.DmN.phtx.ppl.page.PageList
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
                        ETitle("Слава России!", 26),
                        ETitle("Слава России!", 26),
                        ETitle("Слава России!", 26),
                        EImage(ImageIO.read(Test::class.java.getResource("/test/phtx/ppl/img0.jpg")))
                    )
                )
                //
                this.show()
            }
        }
    }
}