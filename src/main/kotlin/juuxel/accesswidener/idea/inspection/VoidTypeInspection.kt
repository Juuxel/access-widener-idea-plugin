package juuxel.accesswidener.idea.inspection

import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.PsiType
import juuxel.accesswidener.idea.psi.AwMethodDescriptor
import juuxel.accesswidener.idea.psi.AwTypeDescriptor
import juuxel.accesswidener.idea.psi.AwVisitor
import juuxel.accesswidener.idea.util.MessageBundle

class VoidTypeInspection : LocalInspectionTool() {
    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
        return object : AwVisitor() {
            override fun visitTypeDescriptor(o: AwTypeDescriptor) {
                if (o.toPsiType() == PsiType.VOID) {
                    val parent = o.parent

                    if (!(parent is AwMethodDescriptor && parent.returnType == o)) {
                        holder.registerProblem(o, MessageBundle["inspection.voidType"])
                    }
                }
            }
        }
    }
}
