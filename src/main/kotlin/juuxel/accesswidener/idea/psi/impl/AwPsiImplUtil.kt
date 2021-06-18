package juuxel.accesswidener.idea.psi.impl

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IElementType
import juuxel.accesswidener.idea.AccessType
import juuxel.accesswidener.idea.psi.AwClassDefinition
import juuxel.accesswidener.idea.psi.AwDefinition
import juuxel.accesswidener.idea.psi.AwHeader
import juuxel.accesswidener.idea.psi.AwMemberDefinition
import juuxel.accesswidener.idea.psi.AwMethodDescriptor
import juuxel.accesswidener.idea.psi.AwTypeDescriptor
import juuxel.accesswidener.idea.psi.AwTypes

object AwPsiImplUtil {
    private inline fun <T> ofChild(elt: PsiElement, type: IElementType, fn: (ASTNode) -> T): T? =
        elt.node.findChildByType(type)?.let(fn)

    private fun textOfChild(elt: PsiElement, type: IElementType): String? =
        ofChild(elt, type, ASTNode::getText)

    fun getAccessType(access: ASTNode): AccessType? =
        when (access.text) {
            "accessible" -> AccessType.ACCESSIBLE
            "extendable" -> AccessType.EXTENDABLE
            "mutable" -> AccessType.MUTABLE
            else -> null
        }

    // AwDefinition

    @JvmStatic
    fun getAccessType(definition: AwDefinition): AccessType? =
        ofChild(definition, AwTypes.ACCESS, ::getAccessType)

    @JvmStatic
    fun getName(definition: AwDefinition): String? =
        when (definition) {
            is AwClassDefinition -> textOfChild(definition, AwTypes.CLASS_NAME)
            else -> textOfChild(definition, AwTypes.MEMBER_NAME)
        }

    // AwMemberDefinition

    @JvmStatic
    fun getOwner(definition: AwMemberDefinition): String? =
        textOfChild(definition, AwTypes.CLASS_NAME)

    @JvmStatic
    fun getMemberIdentifier(definition: AwMemberDefinition): PsiElement? =
        ofChild(definition, AwTypes.MEMBER_NAME, ASTNode::getPsi)

    // AwHeader

    @JvmStatic
    fun getVersionNumber(header: AwHeader): Int? =
        ofChild(header, AwTypes.VERSION) { it.text.substring(1).toInt() }

    @JvmStatic
    fun getNamespace(header: AwHeader): String? =
        textOfChild(header, AwTypes.NAMESPACE)

    // AwTypeDescriptor

    @JvmStatic
    fun getDescriptorString(descriptor: AwTypeDescriptor): String =
        descriptor.text

    @JvmStatic
    fun isPrimitive(descriptor: AwTypeDescriptor): Boolean =
        descriptor.node.findChildByType(AwTypes.PRIMITIVE_DESCRIPTOR) != null

    @JvmStatic
    fun isLiteral(descriptor: AwTypeDescriptor): Boolean =
        !isPrimitive(descriptor)

    @JvmStatic
    fun getClassName(descriptor: AwTypeDescriptor): String? =
        textOfChild(descriptor, AwTypes.CLASS_NAME)

    // AwMethodDescriptor

    // TODO: Investigate these since they are naive wrt erroring parsings

    @JvmStatic
    fun getReturnType(descriptor: AwMethodDescriptor): AwTypeDescriptor =
        descriptor.typeDescriptorList.last()

    @JvmStatic
    fun getParameters(descriptor: AwMethodDescriptor): List<AwTypeDescriptor> =
        descriptor.typeDescriptorList.dropLast(1)
}
