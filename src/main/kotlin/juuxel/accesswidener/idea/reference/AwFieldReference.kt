package juuxel.accesswidener.idea.reference

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement
import juuxel.accesswidener.idea.psi.AwFieldDefinition

class AwFieldReference(element: PsiElement, textRange: TextRange) : AwReference(element, textRange) {
    override val classBinaryName = (element as AwFieldDefinition).owner

    override fun getReferenceTargets(c: PsiClass, soft: Boolean): Sequence<PsiElement> =
        sequenceOf(c.findFieldByName((element as AwFieldDefinition).name, false))
            .filterNotNull()
}
