package juuxel.accesswidener.idea

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import juuxel.accesswidener.idea.psi.AwFieldDefinition
import juuxel.accesswidener.idea.psi.AwMethodDefinition

class AccessWidenerAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        when (element) {
            is AwFieldDefinition -> {
                val memberIdentifier = element.memberIdentifier

                if (memberIdentifier != null) {
                    holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                        .range(memberIdentifier)
                        .textAttributes(AccessWidenerSyntaxHighlighter.FIELD)
                        .create()
                }
            }

            is AwMethodDefinition -> {
                val memberIdentifier = element.memberIdentifier

                if (memberIdentifier != null) {
                    holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                        .range(memberIdentifier)
                        .textAttributes(AccessWidenerSyntaxHighlighter.METHOD)
                        .create()
                }
            }
        }
    }
}
