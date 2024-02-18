package ru.DmN.test.phtx.ppl

import ru.DmN.phtx.ppl.attribute.ACenter
import ru.DmN.phtx.ppl.attribute.ASized
import ru.DmN.phtx.ppl.element.EImage
import ru.DmN.phtx.ppl.page.PageSizedList
import ru.DmN.phtx.ppl.utils.Presentation
import javax.imageio.ImageIO
import javax.swing.SwingUtilities

object Test {
    @JvmStatic
    fun main(args: Array<String>) {
        SwingUtilities.invokeLater {
            Presentation("Тестовая Презентация", 1000).apply {
                this += PageSizedList(
                    1920,
                    1080,
                    mutableListOf(
                        ACenter(
                            ASized(
                                640,
                                -1,
                                EImage(ImageIO.read(Test::class.java.getResource("/test/phtx/ppl/img0.jpg")))
                            )
                        ),
                        ACenter(
                            ASized(
                                640,
                                -1,
                                EImage(ImageIO.read(Test::class.java.getResource("/test/phtx/ppl/img0.jpg")))
                            )
                        ),
                        ACenter(
                            ASized(
                                640,
                                -1,
                                EImage(ImageIO.read(Test::class.java.getResource("/test/phtx/ppl/img0.jpg")))
                            )
                        ),
                    )
                )
                //
                this.show()
            }
        }
    }
}