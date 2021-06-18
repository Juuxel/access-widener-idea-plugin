// This is a generated file. Not intended for manual editing.
package juuxel.accesswidener.idea.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static juuxel.accesswidener.idea.psi.AwTypes.*;
import juuxel.accesswidener.idea.psi.*;

public class AwMethodDefinitionImpl extends AwMemberDefinitionImpl implements AwMethodDefinition {

  public AwMethodDefinitionImpl(ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull AwVisitor visitor) {
    visitor.visitMethodDefinition(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof AwVisitor) accept((AwVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public AwMethodDescriptor getMethodDescriptor() {
    return findNotNullChildByClass(AwMethodDescriptor.class);
  }

}
