package juuxel.accesswidener.idea.util

import com.intellij.openapi.module.ModuleUtilCore
import com.intellij.psi.JavaPsiFacade
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiMethod
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.TypeConversionUtil

// This file contains extensions for generic PSI elements, such as the Java PSI.
// AW-specific extensions are in psi.util.Extensions.kt.

val PsiElement.moduleScope: GlobalSearchScope
    get() {
        val module = ModuleUtilCore.findModuleForPsiElement(this)

        return if (module != null) {
            GlobalSearchScope.moduleWithDependenciesAndLibrariesScope(module)
        } else {
            GlobalSearchScope.allScope(project)
        }
    }

fun PsiMethod.erasure(): PsiMethod {
    val factory = JavaPsiFacade.getElementFactory(project)
    val result =
        if (isConstructor) factory.createConstructor(name)
        else factory.createMethod(name, TypeConversionUtil.erasure(returnType))

    for (param in parameterList.parameters) {
        result.parameterList.add(factory.createParameter(param.name, TypeConversionUtil.erasure(param.type)))
    }

    return result
}
