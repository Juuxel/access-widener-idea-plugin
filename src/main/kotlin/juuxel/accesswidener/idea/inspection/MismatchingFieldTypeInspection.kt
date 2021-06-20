package juuxel.accesswidener.idea.inspection

import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.PsiField
import juuxel.accesswidener.idea.psi.AwFieldDefinition
import juuxel.accesswidener.idea.psi.AwVisitor
import juuxel.accesswidener.idea.reference.AwFieldReference
import juuxel.accesswidener.idea.util.MessageBundle

class MismatchingFieldTypeInspection : LocalInspectionTool() {
    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor =
        object : AwVisitor() {
            override fun visitFieldDefinition(o: AwFieldDefinition) {
                val ref = (o.references.firstOrNull { it is AwFieldReference } ?: return) as AwFieldReference
                val types = ref.multiResolve(false)
                    .asSequence()
                    .mapNotNull { it.element as? PsiField }
                    .map { it.type }
                    .toList()

                if (types.isNotEmpty() && types.none { it == o.typeDescriptor.toPsiType() }) {
                    holder.registerProblem(
                        o.typeDescriptor,
                        MessageBundle["inspection.mismatchingFieldType", o.name, types.first().presentableText]
                    )
                }
            }
        }
}
