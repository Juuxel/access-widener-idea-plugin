// This is a generated file. Not intended for manual editing.
package juuxel.accesswidener.idea.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import juuxel.accesswidener.idea.psi.impl.*;

public interface AwTypes {

  IElementType CLASS_DEFINITION = new AwElementType("CLASS_DEFINITION");
  IElementType FIELD_DEFINITION = new AwElementType("FIELD_DEFINITION");
  IElementType HEADER = new AwElementType("HEADER");
  IElementType METHOD_DEFINITION = new AwElementType("METHOD_DEFINITION");
  IElementType METHOD_DESCRIPTOR = new AwElementType("METHOD_DESCRIPTOR");
  IElementType TYPE_DESCRIPTOR = new AwElementType("TYPE_DESCRIPTOR");

  IElementType ACCESS = new AwTokenType("ACCESS");
  IElementType ARRAY = new AwTokenType("[");
  IElementType CLASS_NAME = new AwTokenType("CLASS_NAME");
  IElementType COMMENT = new AwTokenType("COMMENT");
  IElementType HEADER_START = new AwTokenType("accessWidener");
  IElementType LITERAL_START = new AwTokenType("L");
  IElementType L_PAREN = new AwTokenType("(");
  IElementType MEMBER_NAME = new AwTokenType("MEMBER_NAME");
  IElementType NAMESPACE = new AwTokenType("NAMESPACE");
  IElementType PRIMITIVE_DESCRIPTOR = new AwTokenType("PRIMITIVE_DESCRIPTOR");
  IElementType R_PAREN = new AwTokenType(")");
  IElementType SEMICOLON = new AwTokenType(";");
  IElementType TARGET_TYPE = new AwTokenType("TARGET_TYPE");
  IElementType VERSION = new AwTokenType("VERSION");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == CLASS_DEFINITION) {
        return new AwClassDefinitionImpl(node);
      }
      else if (type == FIELD_DEFINITION) {
        return new AwFieldDefinitionImpl(node);
      }
      else if (type == HEADER) {
        return new AwHeaderImpl(node);
      }
      else if (type == METHOD_DEFINITION) {
        return new AwMethodDefinitionImpl(node);
      }
      else if (type == METHOD_DESCRIPTOR) {
        return new AwMethodDescriptorImpl(node);
      }
      else if (type == TYPE_DESCRIPTOR) {
        return new AwTypeDescriptorImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
