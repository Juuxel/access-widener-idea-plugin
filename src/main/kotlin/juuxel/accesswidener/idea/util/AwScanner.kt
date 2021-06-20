package juuxel.accesswidener.idea.util

import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiField
import com.intellij.psi.PsiManager
import com.intellij.psi.PsiMethod
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.util.PsiTreeUtil
import juuxel.accesswidener.idea.AccessWidenerFileType
import juuxel.accesswidener.idea.psi.AwClassDefinition
import juuxel.accesswidener.idea.psi.AwDefinition
import juuxel.accesswidener.idea.psi.AwFieldDefinition
import juuxel.accesswidener.idea.psi.AwFile
import juuxel.accesswidener.idea.psi.AwMethodDefinition

object AwScanner {
    private fun findDefinitionsInModule(psi: PsiElement): Sequence<AwDefinition> =
        FileTypeIndex.getFiles(AccessWidenerFileType, psi.moduleScope)
            .asSequence()
            .mapNotNull { PsiManager.getInstance(psi.project).findFile(it) as? AwFile }
            .flatMap { PsiTreeUtil.getChildrenOfTypeAsList(it, AwDefinition::class.java) }

    fun findDefinitionsFor(psi: PsiElement): Sequence<AwDefinition> =
        findDefinitionsInModule(psi)
            .filter { def ->
                def.kindMatches(psi) && def.references.any { it.isReferenceTo(psi) }
            }

    private fun AwDefinition.kindMatches(psi: PsiElement): Boolean =
        (this is AwClassDefinition && psi is PsiClass) ||
            (this is AwMethodDefinition && psi is PsiMethod) ||
            (this is AwFieldDefinition && psi is PsiField)
}
