package ru.DmN.phtx.ppl.parser

import ru.DmN.pht.ast.NodeValue
import ru.DmN.pht.ast.NodeValue.Type.*
import ru.DmN.pht.node.NodeParsedTypes
import ru.DmN.pht.node.NodeParsedTypes.NEW
import ru.DmN.pht.node.NodeTypes
import ru.DmN.pht.node.NodeTypes.VALUE
import ru.DmN.pht.node.nodeValue
import ru.DmN.phtx.ppl.node.NodeTypes.*
import ru.DmN.siberia.ast.Node
import ru.DmN.siberia.ast.NodeNodesList
import ru.DmN.siberia.node.NodeInfoImpl
import java.util.*

class Laxer(input: String) { // todo: line & symbol info
    private val input: String = input.replace("    ", "\t")
    private var ptr: Int = 0
    private var line: Int = 0
    private var symbol: Int = 0
    private val tabs = Stack<Int>()

    fun next(): MutableList<Node> =
        nextOperations(-1)

    private fun next(prevTab: Int): Node? = checkTab(prevTab) { tab ->
        if (!tryC('['))
            return null
        val arguments = ArrayList<Node>()
        val operation = StringBuilder()
        while (!tryC(']')) {
            if (tryC('(')) {
                skipSpace()
                while (!tryC(')')) {
                    arguments += nextNumber()
                    skipSpace()
                }
                checkC(']')
                break
            }
            operation.append(input[inc()])
        }
        when (operation.toString().trim().lowercase()) {
            "страница" -> {
                val sized = arguments.isNotEmpty()
                if (tryC(':'))
                    arguments.addAll(nextOperations(tab))
                NodeNodesList(
                    NodeInfoImpl(
                        if (sized)
                            PAGE_SIZED_LIST
                        else PAGE_LIST,
                        null,
                        line
                    ),
                    arguments
                )
            }

            "заголовок" -> {
                checkC(':')
                arguments.add(0, nextStrings(tab))
                NodeNodesList(NodeInfoImpl(E_TITLE, null, line), arguments)
            }

            "текст" -> {
                checkC(':')
                arguments.add(0, nextStrings(tab))
                NodeNodesList(NodeInfoImpl(E_TEXT, null, line), arguments)
            }

            "картинка" -> {
                checkC(':')
                val image = nextString()
                arguments.add(
                    if (image.value.startsWith("http"))
                        NodeNodesList(
                            NodeInfoImpl(NEW, null, line),
                            mutableListOf(
                                NodeValue(NodeInfoImpl(VALUE, null ,line), CLASS, "java.net.URL"),
                                image
                            )
                        )
                    else NodeNodesList(NodeInfoImpl(INC_IMG, null, line), mutableListOf(image))
                )
                NodeNodesList(NodeInfoImpl(E_IMAGE, null, line), arguments)
            }

            "пара" -> {
                checkC(':')
                for (i in 0..1)
                    arguments.add(next(tab)!!)
                NodeNodesList(NodeInfoImpl(C_PAIR, null, line), arguments)
            }

            "тройка" -> {
                checkC(':')
                for (i in 0..2)
                    arguments.add(next(tab)!!)
                NodeNodesList(NodeInfoImpl(C_TRIPLE, null, line), arguments)
            }

            "четвёрка" -> {
                checkC(':')
                for (i in 0..3)
                    arguments.add(next(tab)!!)
                NodeNodesList(NodeInfoImpl(C_FOURFOLD, null, line), arguments)
            }

            "размер" -> {
                checkC(':')
                arguments.add(next(tab - 1)!!)
                NodeNodesList(NodeInfoImpl(A_SIZED, null, line), arguments)
            }

            "сдвиг" -> {
                checkC(':')
                arguments.add(next(tab - 1)!!)
                NodeNodesList(NodeInfoImpl(A_OFFSET, null, line), arguments)
            }

            "середина" -> {
                checkC(':')
                arguments.add(next(tab - 1)!!)
                NodeNodesList(NodeInfoImpl(A_CENTER, null, line), arguments)
            }

            else -> throw RuntimeException()
        }
    }

    private fun nextOperations(prevTab: Int): MutableList<Node> {
        val nodes = ArrayList<Node>()
        while (true)
            nodes += next(prevTab) ?: break
        return nodes
    }

    private fun nextStrings(prevTab: Int): NodeValue {
        skipSpace()
        return if (tryC('\n')) {
            val sb = StringBuilder()
            while (
                checkTab(prevTab) {
                    while (!tryC('\n') && check())
                        sb.append(input[inc()])
                    sb.append('\n')
                    true
                } == true
            );
            NodeValue(NodeInfoImpl(VALUE, null, line), STRING, sb.substring(0, sb.length - 1))
        } else nextString()
    }

    private fun nextString(): NodeValue {
        val sb = StringBuilder()
        while (!tryC('\n') && check())
            sb.append(input[inc()])
        return NodeValue(NodeInfoImpl(VALUE, null, line), STRING, sb.trimStart().toString())
    }

    private fun nextNumber(): NodeValue {
        val sb = StringBuilder()
        if (input[ptr] == '-') {
            sb.append('-')
            inc()
        }
        while (true) {
            val char = input[ptr]
            if (char.isDigit() || char == '.') {
                inc()
                sb.append(char)
            } else break
        }
        return NodeValue(NodeInfoImpl(VALUE, null, line), INT, sb.toString())
    }

    private inline fun <T> checkTab(prevTab: Int, block: (tab: Int) -> T): T? {
        val tab = parseTabs()
        if (tab != prevTab + 1) {
            tabs.push(tab)
            return null
        }
        return block(tab)
    }

    private fun parseTabs(): Int {
        if (tabs.isNotEmpty())
            return tabs.pop()
        var i = 0
        skipNLSpace()
        while (tryC('\t')) {
            if (skipNL())
                i = 0
            else i++
        }
        return i
    }

    private fun skipNLSpace(): Boolean {
        if (tryC('\n') || tryC(' ')) {
            while (tryC('\n') || tryC(' ')) ;
            return true
        }
        return false
    }

    private fun skipNL(): Boolean {
        if (tryC('\n')) {
            while (tryC('\n')) ;
            return true
        }
        return false
    }

    private fun skipSpace(): Boolean {
        if (tryC(' ')) {
            while (tryC(' ')) ;
            return true
        }
        return false
    }

    private fun tryC(char: Char): Boolean =
        if (check() && input[ptr] == char) {
            inc()
            true
        } else false

    private fun checkC(char: Char) {
        if (input[inc()] == char)
            return
        throw RuntimeException()
    }

    private fun inc(): Int {
        return ptr++
    }

    private fun check() =
        ptr < input.length
}