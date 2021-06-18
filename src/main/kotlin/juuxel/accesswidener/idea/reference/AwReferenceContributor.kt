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
import juuxel.accesswidener.idea.psi.AwFieldDefinition
import juuxel.accesswidener.idea.psi.AwMethodDefinition
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

        registrar.registerReferenceProvider(
            PlatformPatterns.psiElement(AwFieldDefinition::class.java),
            object : PsiReferenceProvider() {
                override fun getReferencesByElement(
                    element: PsiElement,
                    context: ProcessingContext
                ): Array<PsiReference> {
                    return arrayOf(
                        AwFieldReference(element, (element as AwFieldDefinition).memberIdentifier.textRangeInParent)
                    )
                }
            }
        )

        registrar.registerReferenceProvider(
            PlatformPatterns.psiElement(AwMethodDefinition::class.java),
            object : PsiReferenceProvider() {
                override fun getReferencesByElement(
                    element: PsiElement,
                    context: ProcessingContext
                ): Array<PsiReference> {
                    return arrayOf(
                        AwMethodReference(element, (element as AwMethodDefinition).memberIdentifier.textRangeInParent)
                    )
                }
            }
        )
    }

    companion object {
        private val CLASS_NAME_TOKENS = TokenSet.create(AwTypes.CLASS_NAME)
    }
}
