package ru.DmN.phtx.ppl

import ru.DmN.pht.utils.addNP
import ru.DmN.pht.utils.addSNP
import ru.DmN.pht.utils.addSNU
import ru.DmN.phtx.ppl.node.NodeTypes.*
import ru.DmN.phtx.ppl.parsers.NPPage
import ru.DmN.phtx.ppl.processors.NRIncPpl
import ru.DmN.phtx.ppl.processors.NRPageList
import ru.DmN.phtx.ppl.processors.NRPageSizedList
import ru.DmN.phtx.ppl.utils.addSPP
import ru.DmN.siberia.utils.Module

object PresentationHelper : Module("phtx/ppl/presentation/helper") {
    private fun initParsers() {
        // i
        addSNP(INC_PPL)
        // p
        addNP("page-fractal",    NPPage(PAGE_FRACTAL))
        addNP("page-list",       NPPage(PAGE_LIST))
        addNP("page-sized-list", NPPage(PAGE_SIZED_LIST))
    }

    private fun initUnparsers() {
        // i
        addSNU(INC_PPL)
        // p
        addSNU(PAGE_FRACTAL)
        addSNU(PAGE_LIST)
        addSNU(PAGE_SIZED_LIST)
    }

    private fun initProcessors() {
        // i
        add(INC_PPL,         NRIncPpl)
        // p
        addSPP(PAGE_FRACTAL, "ru.DmN.phtx.ppl.page.PageFractal")
        add(PAGE_LIST,       NRPageList)
        add(PAGE_SIZED_LIST, NRPageSizedList)
    }

    init {
        initParsers()
        initUnparsers()
        initProcessors()
    }
}