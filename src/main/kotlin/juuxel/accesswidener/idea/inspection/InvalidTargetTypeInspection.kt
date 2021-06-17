package juuxel.accesswidener.idea.inspection

import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElementVisitor
import juuxel.accesswidener.idea.psi.AwDefinition
import juuxel.accesswidener.idea.psi.AwTypes
import juuxel.accesswidener.idea.psi.AwVisitor
import juuxel.accesswidener.idea.util.MessageBundle

class InvalidTargetTypeInspection : LocalInspectionTool() {
    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor =
        object : AwVisitor() {
            override fun visitDefinition(o: AwDefinition) {
                val targetTypeNode = o.node.findChildByType(AwTypes.TARGET_TYPE) ?: return

                when (val targetType = targetTypeNode.text) {
                    "class", "method", "field" -> {} // all good
                    else -> {
                        holder.registerProblem(
                            targetTypeNode.psi,
                            MessageBundle["inspection.invalidTargetType", targetType]
                        )
                    }
                }
            }
        }
}
