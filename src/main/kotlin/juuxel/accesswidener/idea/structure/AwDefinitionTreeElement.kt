package juuxel.accesswidener.idea.structure

import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.structureView.impl.common.PsiTreeElementBase
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement
import juuxel.accesswidener.idea.psi.AwDefinition

class AwDefinitionTreeElement(
    private val definition: AwDefinition
) : PsiTreeElementBase<AwDefinition>(definition), SortableTreeElement {
    override fun getPresentableText(): String? = definition.presentation?.presentableText
    override fun getChildrenBase(): Collection<StructureViewTreeElement> = emptySet()
    override fun getAlphaSortKey(): String = definition.qualifiedName!!
}
