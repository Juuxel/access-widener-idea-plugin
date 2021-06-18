package juuxel.accesswidener.idea.reference

import com.intellij.openapi.util.TextRange
import com.intellij.psi.JavaPsiFacade
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement
import juuxel.accesswidener.idea.psi.AwMethodDefinition

class AwMethodReference(element: PsiElement, textRange: TextRange) : AwReference(element, textRange) {
    override val classBinaryName = (element as AwMethodDefinition).owner

    override fun getReferenceTargets(c: PsiClass, soft: Boolean): Sequence<PsiElement> {
        if (soft) {
            return c.findMethodsByName((element as AwMethodDefinition).name, false).asSequence()
        } else {
            val factory = JavaPsiFacade.getElementFactory(element.project)
            val method = element as AwMethodDefinition

            val pattern =
                if (method.name == "<init>") factory.createConstructor(c.name!!)
                else factory.createMethod(method.name, method.methodDescriptor.returnType.toPsiType())

            for ((i, param) in method.methodDescriptor.parameters.withIndex()) {
                val parameter = factory.createParameter("p$i", param.toPsiType() ?: return emptySequence())
                pattern.parameterList.add(parameter)
            }

            return sequenceOf(c.findMethodBySignature(pattern, false)).filterNotNull()
        }
    }
}
