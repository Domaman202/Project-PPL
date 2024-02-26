package ru.DmN.test.phtx.ppl

import ru.DmN.phtx.ppl.element.EImage
import ru.DmN.phtx.ppl.element.ETitle
import ru.DmN.phtx.ppl.page.PageList
import ru.DmN.phtx.ppl.utils.Presentation
import java.net.URL
import javax.imageio.ImageIO
import javax.swing.SwingUtilities

object Test {
    @JvmStatic
    fun main(args: Array<String>) {
        SwingUtilities.invokeLater {
            Presentation("Тестовая Презентация", 1000).apply {
                this += PageList(
                    mutableListOf(
                        EImage(ImageIO.read(URL("https://dpru.obs.ru-moscow-1.hc.sbercloud.ru/images/article/2023/10/18/d20efc18-6734-476e-be50-f6d570010c10.jpg")))
                    )
                )
                //
                this.show()
            }
        }
    }
}