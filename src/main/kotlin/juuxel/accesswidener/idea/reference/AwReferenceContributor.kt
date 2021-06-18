package juuxel.accesswidener.idea.reference

import com.intellij.patterns.PlatformPatterns
import com.intellij.patterns.StandardPatterns
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceContributor
import com.intellij.psi.PsiReferenceProvider
import com.intellij.psi.PsiReferenceRegistrar
import com.intellij.psi.tree.TokenSet
import com.intellij.util.ProcessingContext
import juuxel.accesswidener.idea.psi.AwDefinition
import juuxel.accesswidener.idea.psi.AwTypes

class AwReferenceContributor : PsiReferenceContributor() {
    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        registrar.registerReferenceProvider(
            StandardPatterns.or(
                PlatformPatterns.psiElement(AwDefinition::class.java),
                PlatformPatterns.psiElement(AwTypes.TYPE_DESCRIPTOR),
            ),
            object : PsiReferenceProvider() {
                override fun getReferencesByElement(
                    element: PsiElement,
                    context: ProcessingContext
                ): Array<PsiReference> {
                    val refs = element.node.getChildren(CLASS_NAME_TOKENS)
                        .map {
                            val name = it.psi
                            AwClassReference(element, name.textRangeInParent)
                        }

                    return if (refs.isNotEmpty()) refs.toTypedArray() else PsiReference.EMPTY_ARRAY
                }
            }
        )
    }

    companion object {
        private val CLASS_NAME_TOKENS = TokenSet.create(AwTypes.CLASS_NAME)
    }
}
