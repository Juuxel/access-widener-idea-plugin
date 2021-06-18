package juuxel.accesswidener.idea.psi.util

import com.intellij.psi.PsiElement
import juuxel.accesswidener.idea.psi.AwFile
import juuxel.accesswidener.idea.psi.AwHeader
import juuxel.accesswidener.idea.psi.AwVisitor

val PsiElement.isInNamedAw: Boolean
    get() {
        val file = containingFile as? AwFile ?: return false
        var named = false

        file.acceptChildren(
            object : AwVisitor() {
                override fun visitHeader(o: AwHeader) {
                    named = o.namespace == "named"
                }
            }
        )

        return named
    }
