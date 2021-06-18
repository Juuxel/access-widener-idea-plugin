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

public class AwMethodDescriptorImpl extends ASTWrapperPsiElement implements AwMethodDescriptor {

  public AwMethodDescriptorImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull AwVisitor visitor) {
    visitor.visitMethodDescriptor(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof AwVisitor) accept((AwVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<AwTypeDescriptor> getTypeDescriptorList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, AwTypeDescriptor.class);
  }

  @Override
  public @NotNull AwTypeDescriptor getReturnType() {
    return AwPsiImplUtil.getReturnType(this);
  }

  @Override
  public @NotNull List<AwTypeDescriptor> getParameters() {
    return AwPsiImplUtil.getParameters(this);
  }

}
