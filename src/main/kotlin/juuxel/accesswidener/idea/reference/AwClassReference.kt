package juuxel.accesswidener.idea.reference

import com.intellij.openapi.module.ModuleUtilCore
import com.intellij.openapi.util.TextRange
import com.intellij.psi.JavaPsiFacade
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementResolveResult
import com.intellij.psi.PsiPolyVariantReference
import com.intellij.psi.PsiReferenceBase
import com.intellij.psi.ResolveResult
import com.intellij.psi.search.GlobalSearchScope

class AwClassReference(element: PsiElement, textRange: TextRange) :
    PsiReferenceBase<PsiElement>(element, textRange),
    PsiPolyVariantReference {
    private val binaryName = textRange.substring(element.text)
    // If you have a manual $ in your class names, you are evil.
    // TODO: Should I actually generate the full set of $->. permutations?
    private val javaName = binaryName.replace('/', '.').replace('$', '.')

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
            .map { PsiElementResolveResult(it) }
            .toTypedArray()
    }

    override fun resolve(): PsiElement? =
        multiResolve(false).singleOrNull()?.element

    override fun getVariants(): Array<out Any> =
        JavaPsiFacade.getInstance(element.project)
            .findClasses(javaName, GlobalSearchScope.allScope(element.project))
}
