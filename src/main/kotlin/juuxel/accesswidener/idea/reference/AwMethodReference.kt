package juuxel.accesswidener.idea.reference

import com.intellij.openapi.util.TextRange
import com.intellij.psi.JavaPsiFacade
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiSubstitutor
import juuxel.accesswidener.idea.psi.AwMethodDefinition
import juuxel.accesswidener.idea.util.erasure

class AwMethodReference(element: PsiElement, textRange: TextRange) : AwReference(element, textRange) {
    override val classBinaryName = (element as AwMethodDefinition).owner

    override fun getReferenceTargets(c: PsiClass, soft: Boolean): Sequence<PsiElement> {
        if (soft) {
            val method = element as AwMethodDefinition

            if (method.name == "<init>") {
                return c.constructors.asSequence()
            }

            return c.findMethodsByName(method.name, false).asSequence()
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

            val byName =
                if (method.name == "<init>") c.constructors
                else c.findMethodsByName(method.name, false)

            // TODO: Constructors aren't highlighted.
            //   I think this is caused by usage searching failing?
            return byName.asSequence()
                .filter {
                    it.erasure().getSignature(PsiSubstitutor.EMPTY) == pattern.getSignature(PsiSubstitutor.EMPTY)
                }
        }
    }
}
