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
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.util.Iconable.IconFlags;
import javax.swing.Icon;
import juuxel.accesswidener.idea.AccessType;

public class AwDefinitionImpl extends AwReferencingElementImpl implements AwDefinition {

  public AwDefinitionImpl(ASTNode node) {
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
  @Nullable
  public AccessType getAccessType() {
    return AwPsiImplUtil.getAccessType(this);
  }

  @Override
  @Nullable
  public String getName() {
    return AwPsiImplUtil.getName(this);
  }

  @Override
  @Nullable
  public String getQualifiedName() {
    return AwPsiImplUtil.getQualifiedName(this);
  }

  @Override
  @NotNull
  public Icon getIcon(@IconFlags int flags) {
    return AwPsiImplUtil.getIcon(this, flags);
  }

  @Override
  @NotNull
  public Icon getIcon() {
    return AwPsiImplUtil.getIcon(this);
  }

  @Override
  @Nullable
  public ItemPresentation getPresentation() {
    return AwPsiImplUtil.getPresentation(this);
  }

}
