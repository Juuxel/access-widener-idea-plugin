package juuxel.accesswidener.idea.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import juuxel.accesswidener.idea.AccessType;
import juuxel.accesswidener.idea.psi.AwClassDefinition;
import juuxel.accesswidener.idea.psi.AwDefinition;
import juuxel.accesswidener.idea.psi.AwHeader;
import juuxel.accesswidener.idea.psi.AwMemberDefinition;
import juuxel.accesswidener.idea.psi.AwMethodDescriptor;
import juuxel.accesswidener.idea.psi.AwTypeDescriptor;
import juuxel.accesswidener.idea.psi.AwTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public final class AwPsiImplUtil {
    @Nullable
    private static <T> T ofChild(PsiElement elt, IElementType type, Function<ASTNode, T> fn) {
        ASTNode node = elt.getNode().findChildByType(type);
        return node != null ? fn.apply(node) : null;
    }

    @Nullable
    private static String textOfChild(PsiElement elt, IElementType type) {
        return ofChild(elt, type, ASTNode::getText);
    }

    @Nullable
    public static AccessType getAccessType(AwDefinition definition) {
        return ofChild(definition, AwTypes.ACCESS, AwPsiImplUtil::getAccessType);
    }

    @Nullable
    public static AccessType getAccessType(ASTNode access) {
        switch (access.getText()) {
            case "accessible":
                return AccessType.ACCESSIBLE;
            case "extendable":
                return AccessType.EXTENDABLE;
            case "mutable":
                return AccessType.MUTABLE;
            default:
                return null;
        }
    }

    @Nullable
    public static String getOwner(AwMemberDefinition definition) {
        return textOfChild(definition, AwTypes.CLASS_NAME);
    }

    @Nullable
    public static PsiElement getMemberIdentifier(AwMemberDefinition definition) {
        return ofChild(definition, AwTypes.MEMBER_NAME, ASTNode::getPsi);
    }

    @Nullable
    public static String getName(AwDefinition definition) {
        if (definition instanceof AwClassDefinition) {
            return textOfChild(definition, AwTypes.CLASS_NAME);
        } else {
            return textOfChild(definition, AwTypes.MEMBER_NAME);
        }
    }

    @Nullable
    public static Integer getVersionNumber(AwHeader header) {
        return ofChild(header, AwTypes.VERSION, node -> Integer.parseInt(node.getText().substring(1)));
    }

    @Nullable
    public static String getNamespace(AwHeader header) {
        return textOfChild(header, AwTypes.NAMESPACE);
    }

    @NotNull
    public static String getDescriptorString(AwTypeDescriptor descriptor) {
        return descriptor.getText();
    }

    public static boolean isPrimitive(AwTypeDescriptor descriptor) {
        return descriptor.getNode().findChildByType(AwTypes.PRIMITIVE_DESCRIPTOR) != null;
    }

    public static boolean isLiteral(AwTypeDescriptor descriptor) {
        return !isPrimitive(descriptor);
    }

    @Nullable
    public static String getClassName(AwTypeDescriptor descriptor) {
        return textOfChild(descriptor, AwTypes.CLASS_NAME);
    }

    @NotNull
    public static AwTypeDescriptor getReturnType(AwMethodDescriptor descriptor) {
        List<AwTypeDescriptor> descriptors = descriptor.getTypeDescriptorList();
        return descriptors.get(descriptors.size() - 1);
    }

    @NotNull
    public static List<AwTypeDescriptor> getParameters(AwMethodDescriptor descriptor) {
        List<AwTypeDescriptor> descriptors = descriptor.getTypeDescriptorList();

        if (descriptors.size() == 1) return Collections.emptyList();
        return descriptors.subList(0, descriptors.size() - 1);
    }
}
