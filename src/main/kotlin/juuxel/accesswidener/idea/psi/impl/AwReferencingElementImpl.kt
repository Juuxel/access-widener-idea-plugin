package juuxel.accesswidener.idea.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiReference
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry

abstract class AwReferencingElementImpl(node: ASTNode) : ASTWrapperPsiElement(node) {
    override fun getReference(): PsiReference? = references.firstOrNull()

    override fun getReferences(): Array<PsiReference> =
        ReferenceProvidersRegistry.getReferencesFromProviders(this)
}