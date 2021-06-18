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

public class AwHeaderImpl extends ASTWrapperPsiElement implements AwHeader {

  public AwHeaderImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull AwVisitor visitor) {
    visitor.visitHeader(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof AwVisitor) accept((AwVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public Integer getVersionNumber() {
    return AwPsiImplUtil.getVersionNumber(this);
  }

  @Override
  @Nullable
  public String getNamespace() {
    return AwPsiImplUtil.getNamespace(this);
  }

}
