package juuxel.accesswidener.idea.psi.util

import com.intellij.psi.PsiElement
import juuxel.accesswidener.idea.psi.AwFile
import juuxel.accesswidener.idea.psi.AwHeader
import juuxel.accesswidener.idea.psi.AwVisitor

// This file contains extensions for access widener PSI elements.
// Generic extensions for external PSI elements are in util.PsiExtensions.kt.
// Note: Having these in AwPsiImplUtil is preferred, unless it's inconvenient to put them in there.
//       (For example, isInNamedAw would require a common supertype for all AW PSI elements, which is annoying to add.)

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
