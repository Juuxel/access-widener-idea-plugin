// This is a generated file. Not intended for manual editing.
package juuxel.accesswidener.idea.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class AwVisitor extends PsiElementVisitor {

  public void visitClassDefinition(@NotNull AwClassDefinition o) {
    visitDefinition(o);
  }

  public void visitDefinition(@NotNull AwDefinition o) {
    visitPsiElement(o);
  }

  public void visitFieldDefinition(@NotNull AwFieldDefinition o) {
    visitMemberDefinition(o);
  }

  public void visitHeader(@NotNull AwHeader o) {
    visitPsiElement(o);
  }

  public void visitMemberDefinition(@NotNull AwMemberDefinition o) {
    visitDefinition(o);
  }

  public void visitMethodDefinition(@NotNull AwMethodDefinition o) {
    visitMemberDefinition(o);
  }

  public void visitMethodDescriptor(@NotNull AwMethodDescriptor o) {
    visitPsiElement(o);
  }

  public void visitTypeDescriptor(@NotNull AwTypeDescriptor o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
