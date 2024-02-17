package ru.DmN.phtx.ppl

import ru.DmN.phtx.ppl.console.PresentationCommands
import ru.DmN.siberia.console.BaseCommands
import ru.DmN.siberia.console.BaseConsole

object ConsoleImpl : BaseConsole() {
    @JvmStatic
    fun main(args: Array<String>) {
        run(args)
    }

    init {
        commands += BaseCommands.ALL_COMMANDS
        commands += PresentationCommands.ALL_COMMANDS
    }
}