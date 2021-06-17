// This is a generated file. Not intended for manual editing.
package juuxel.accesswidener.idea.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static juuxel.accesswidener.idea.psi.AwTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import juuxel.accesswidener.idea.psi.*;
import juuxel.accesswidener.idea.AccessType;

public class AwDefinitionImpl extends ASTWrapperPsiElement implements AwDefinition {

  public AwDefinitionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull AwVisitor visitor) {
    visitor.visitDefinition(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof AwVisitor) accept((AwVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  public @Nullable AccessType getAccessType() {
    return AwPsiImplUtil.getAccessType(this);
  }

  @Override
  public @Nullable String getName() {
    return AwPsiImplUtil.getName(this);
  }

}
