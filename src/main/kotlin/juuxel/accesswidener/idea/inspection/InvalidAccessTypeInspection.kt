package juuxel.accesswidener.idea.inspection

import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElementVisitor
import juuxel.accesswidener.idea.AccessType
import juuxel.accesswidener.idea.psi.AwClassDefinition
import juuxel.accesswidener.idea.psi.AwFieldDefinition
import juuxel.accesswidener.idea.psi.AwMethodDefinition
import juuxel.accesswidener.idea.psi.AwTypes
import juuxel.accesswidener.idea.psi.AwVisitor
import juuxel.accesswidener.idea.util.MessageBundle

class InvalidAccessTypeInspection : LocalInspectionTool() {
    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor =
        object : AwVisitor() {
            override fun visitClassDefinition(o: AwClassDefinition) {
                if (o.accessType == null || o.accessType !in AccessType.FOR_CLASS) {
                    val node = o.node.findChildByType(AwTypes.ACCESS)!!
                    holder.registerProblem(
                        node.psi,
                        MessageBundle["inspection.invalidAccessType.class", node.text]
                    )
                }
            }

            override fun visitFieldDefinition(o: AwFieldDefinition) {
                if (o.accessType == null || o.accessType !in AccessType.FOR_FIELD) {
                    val node = o.node.findChildByType(AwTypes.ACCESS)!!
                    holder.registerProblem(
                        node.psi,
                        MessageBundle["inspection.invalidAccessType.field", node.text]
                    )
                }
            }

            override fun visitMethodDefinition(o: AwMethodDefinition) {
                if (o.accessType == null || o.accessType !in AccessType.FOR_METHOD) {
                    val node = o.node.findChildByType(AwTypes.ACCESS)!!
                    holder.registerProblem(
                        node.psi,
                        MessageBundle["inspection.invalidAccessType.method", node.text]
                    )
                }
            }
        }
}
