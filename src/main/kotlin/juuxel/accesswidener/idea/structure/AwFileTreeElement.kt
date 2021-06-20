package juuxel.accesswidener.idea.structure

import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.structureView.impl.common.PsiTreeElementBase
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import juuxel.accesswidener.idea.psi.AwDefinition

class AwFileTreeElement(element: PsiFile) : PsiTreeElementBase<PsiFile>(element) {
    override fun getPresentableText(): String? = element?.name

    override fun getChildrenBase(): Collection<StructureViewTreeElement> {
        return PsiTreeUtil.getChildrenOfTypeAsList(element ?: return emptySet(), AwDefinition::class.java)
            .map { AwDefinitionTreeElement(it) }
    }
}
