package juuxel.accesswidener.idea.completion

import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.ProcessingContext
import juuxel.accesswidener.idea.AccessType
import juuxel.accesswidener.idea.psi.AwTypes
import juuxel.accesswidener.idea.psi.impl.AwPsiImplUtil

class AwCompletionContributor : CompletionContributor() {
    init {
        extend(
            CompletionType.BASIC,
            PlatformPatterns.psiElement(AwTypes.ACCESS),
            object : CompletionProvider<CompletionParameters>() {
                override fun addCompletions(
                    parameters: CompletionParameters,
                    context: ProcessingContext,
                    resultSet: CompletionResultSet
                ) {
                    for (accessType in AccessType.values()) {
                        resultSet.addElement(LookupElementBuilder.create(accessType.sourceCode))
                    }
                }
            }
        )

        extend(
            CompletionType.BASIC,
            PlatformPatterns.psiElement(AwTypes.TARGET_TYPE),
            object : CompletionProvider<CompletionParameters>() {
                override fun addCompletions(
                    parameters: CompletionParameters,
                    context: ProcessingContext,
                    resultSet: CompletionResultSet
                ) {
                    val prev = PsiTreeUtil.prevCodeLeaf(parameters.position) ?: return

                    if (prev.node.elementType == AwTypes.ACCESS) {
                        val targetTypes = when (AwPsiImplUtil.getAccessType(prev.node)) {
                            AccessType.ACCESSIBLE -> listOf("class", "method", "field")
                            AccessType.EXTENDABLE -> listOf("class", "method")
                            AccessType.MUTABLE -> listOf("field")
                            else -> emptyList()
                        }

                        for (type in targetTypes) {
                            resultSet.addElement(LookupElementBuilder.create(type))
                        }
                    }
                }
            }
        )
    }
}
