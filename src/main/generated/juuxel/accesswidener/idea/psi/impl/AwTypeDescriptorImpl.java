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

public class AwTypeDescriptorImpl extends AwReferencingElementImpl implements AwTypeDescriptor {

  public AwTypeDescriptorImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull AwVisitor visitor) {
    visitor.visitTypeDescriptor(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof AwVisitor) accept((AwVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public AwTypeDescriptor getTypeDescriptor() {
    return findChildByClass(AwTypeDescriptor.class);
  }

  @Override
  @NotNull
  public String getDescriptorString() {
    return AwPsiImplUtil.getDescriptorString(this);
  }

  @Override
  public boolean isPrimitive() {
    return AwPsiImplUtil.isPrimitive(this);
  }

  @Override
  public boolean isLiteral() {
    return AwPsiImplUtil.isLiteral(this);
  }

  @Override
  @Nullable
  public String getClassName() {
    return AwPsiImplUtil.getClassName(this);
  }

}
