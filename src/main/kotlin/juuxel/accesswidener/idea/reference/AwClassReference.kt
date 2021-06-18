package juuxel.accesswidener.idea.reference

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement

class AwClassReference(element: PsiElement, textRange: TextRange) : AwReference(element, textRange) {
    override val classNameRange = textRange

    override fun getReferenceTargets(c: PsiClass): Sequence<PsiElement> =
        sequenceOf(c)
}
