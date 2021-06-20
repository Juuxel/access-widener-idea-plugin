package juuxel.accesswidener.idea.psi.impl

import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.navigation.ItemPresentationProviders
import com.intellij.openapi.util.Iconable
import com.intellij.psi.JavaPsiFacade
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiType
import com.intellij.psi.tree.IElementType
import juuxel.accesswidener.idea.AccessType
import juuxel.accesswidener.idea.psi.AwClassDefinition
import juuxel.accesswidener.idea.psi.AwDefinition
import juuxel.accesswidener.idea.psi.AwFieldDefinition
import juuxel.accesswidener.idea.psi.AwHeader
import juuxel.accesswidener.idea.psi.AwMemberDefinition
import juuxel.accesswidener.idea.psi.AwMethodDefinition
import juuxel.accesswidener.idea.psi.AwMethodDescriptor
import juuxel.accesswidener.idea.psi.AwTypeDescriptor
import juuxel.accesswidener.idea.psi.AwTypes
import juuxel.accesswidener.idea.util.Types
import javax.swing.Icon

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

    @JvmStatic
    fun getIcon(definition: AwDefinition, @Iconable.IconFlags flags: Int): Icon =
        getIcon(definition)

    @JvmStatic
    fun getIcon(definition: AwDefinition): Icon =
        when (definition) {
            is AwClassDefinition -> AllIcons.Nodes.Class
            is AwMethodDefinition -> AllIcons.Nodes.Method
            is AwFieldDefinition -> AllIcons.Nodes.Field
            else -> throw IllegalArgumentException("Unknown definition: $definition")
        }

    @JvmStatic
    fun getPresentation(definition: AwDefinition): ItemPresentation? =
        ItemPresentationProviders.getItemPresentation(definition)

    // AwMemberDefinition
    // All methods are guaranteed to be nonnull because members are pinned on their descriptors

    @JvmStatic
    fun getOwner(definition: AwMemberDefinition): String =
        textOfChild(definition, AwTypes.CLASS_NAME)!!

    @JvmStatic
    fun getMemberIdentifier(definition: AwMemberDefinition): PsiElement =
        ofChild(definition, AwTypes.MEMBER_NAME, ASTNode::getPsi)!!

    @JvmStatic
    fun getName(definition: AwMemberDefinition): String =
        textOfChild(definition, AwTypes.MEMBER_NAME)!!

    @JvmStatic
    fun getDescriptor(definition: AwMemberDefinition): PsiElement =
        when (definition) {
            is AwMethodDefinition -> definition.methodDescriptor
            is AwFieldDefinition -> definition.typeDescriptor
            else -> throw IllegalArgumentException("Unknown AW member: $definition")
        }

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
        textOfChild(descriptor, AwTypes.CLASS_NAME)?.let(Types::toJavaName)

    @JvmStatic
    fun toPsiType(descriptor: AwTypeDescriptor): PsiType? =
        if (isPrimitive(descriptor)) {
            when (val desc = descriptor.descriptorString) {
                "B" -> PsiType.BYTE
                "S" -> PsiType.SHORT
                "I" -> PsiType.INT
                "J" -> PsiType.LONG
                "F" -> PsiType.FLOAT
                "D" -> PsiType.DOUBLE
                "Z" -> PsiType.BOOLEAN
                "C" -> PsiType.CHAR
                "V" -> PsiType.VOID
                else -> error("Unknown primitive descriptor: $desc")
            }
        } else {
            getClassName(descriptor)?.let { name ->
                JavaPsiFacade.getElementFactory(descriptor.project)
                    .createTypeByFQClassName(name)
            }
        }

    // AwMethodDescriptor

    // TODO: Investigate these since they are naive wrt erroring parsings

    @JvmStatic
    fun getReturnType(descriptor: AwMethodDescriptor): AwTypeDescriptor =
        descriptor.typeDescriptorList.last()

    @JvmStatic
    fun getParameters(descriptor: AwMethodDescriptor): List<AwTypeDescriptor> =
        descriptor.typeDescriptorList.dropLast(1)
}
