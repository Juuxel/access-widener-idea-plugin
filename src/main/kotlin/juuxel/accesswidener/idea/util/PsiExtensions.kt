package juuxel.accesswidener.idea.util

import com.intellij.psi.JavaPsiFacade
import com.intellij.psi.PsiMethod
import com.intellij.psi.util.TypeConversionUtil

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
