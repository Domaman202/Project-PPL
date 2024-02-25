package ru.DmN.phtx.ppl.processors

import ru.DmN.pht.utils.computeString
import ru.DmN.phtx.ppl.parser.Laxer
import ru.DmN.siberia.Processor
import ru.DmN.siberia.ast.Node
import ru.DmN.siberia.ast.NodeNodesList
import ru.DmN.siberia.processor.ctx.ProcessingContext
import ru.DmN.siberia.processor.utils.ValType
import ru.DmN.siberia.processor.utils.module
import ru.DmN.siberia.processor.utils.nodeProgn
import ru.DmN.siberia.processors.INodeProcessor
import ru.DmN.siberia.processors.NRProgn

object NRIncPpl : INodeProcessor<NodeNodesList> {
    override fun process(node: NodeNodesList, processor: Processor, ctx: ProcessingContext, mode: ValType): Node =
        NRProgn.process(nodeProgn(node.info, Laxer(ctx.module.getModuleFile(processor.computeString(node.nodes[0], ctx))).next()), processor, ctx, mode)
}