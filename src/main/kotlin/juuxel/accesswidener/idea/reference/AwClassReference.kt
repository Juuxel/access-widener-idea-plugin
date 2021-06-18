package juuxel.accesswidener.idea.reference

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement

class AwClassReference(element: PsiElement, textRange: TextRange) : AwReference(element, textRange) {
    override val classBinaryName = textRange.substring(element.text)

    override fun getReferenceTargets(c: PsiClass, soft: Boolean): Sequence<PsiElement> =
        sequenceOf(c)
}
