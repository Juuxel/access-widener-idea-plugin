package juuxel.accesswidener.idea.inspection

import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.PsiPolyVariantReference
import juuxel.accesswidener.idea.psi.AwDefinition
import juuxel.accesswidener.idea.psi.AwVisitor
import juuxel.accesswidener.idea.psi.util.isInNamedAw

class UnknownTargetInspection : LocalInspectionTool() {
    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
        return object : AwVisitor() {
            override fun visitDefinition(o: AwDefinition) {
                if (!o.isInNamedAw) return

                for (ref in o.references) {
                    val missing = if (ref is PsiPolyVariantReference) {
                        ref.multiResolve(false).isEmpty()
                    } else {
                        ref.resolve() == null
                    }

                    if (missing) {
                        holder.registerProblem(ref)
                    }
                }
            }
        }
    }
}
