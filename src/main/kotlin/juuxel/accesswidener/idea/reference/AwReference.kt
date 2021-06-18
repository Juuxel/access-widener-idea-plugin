package juuxel.accesswidener.idea.reference

import com.intellij.openapi.module.ModuleUtilCore
import com.intellij.openapi.util.TextRange
import com.intellij.psi.JavaPsiFacade
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementResolveResult
import com.intellij.psi.PsiPolyVariantReference
import com.intellij.psi.PsiReferenceBase
import com.intellij.psi.ResolveResult
import com.intellij.psi.search.GlobalSearchScope
import juuxel.accesswidener.idea.util.Types

abstract class AwReference(element: PsiElement, textRange: TextRange) :
    PsiReferenceBase<PsiElement>(element, textRange),
    PsiPolyVariantReference {
    private val javaName by lazy {
        Types.toJavaName(classBinaryName)
    }

    protected abstract val classBinaryName: String
    protected abstract fun getReferenceTargets(c: PsiClass, soft: Boolean): Sequence<PsiElement>

    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
        val scope = run {
            val module = ModuleUtilCore.findModuleForPsiElement(element)

            if (module != null) {
                GlobalSearchScope.moduleWithDependenciesAndLibrariesScope(module)
            } else {
                GlobalSearchScope.allScope(element.project)
            }
        }

        return JavaPsiFacade.getInstance(element.project)
            .findClasses(javaName, scope)
            .asSequence()
            .flatMap { getReferenceTargets(it, false) }
            .map { PsiElementResolveResult(it) }
            .toList()
            .toTypedArray()
    }

    override fun resolve(): PsiElement? =
        multiResolve(false).singleOrNull()?.element

    override fun getVariants(): Array<out Any> =
        JavaPsiFacade.getInstance(element.project)
            .findClasses(javaName, GlobalSearchScope.allScope(element.project))
            .flatMap { getReferenceTargets(it, true) }
            .toTypedArray()
}
