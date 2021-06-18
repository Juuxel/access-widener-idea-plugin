// This is a generated file. Not intended for manual editing.
package juuxel.accesswidener.idea.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiType;

public interface AwTypeDescriptor extends PsiElement {

  @Nullable
  AwTypeDescriptor getTypeDescriptor();

  @NotNull
  String getDescriptorString();

  boolean isPrimitive();

  boolean isLiteral();

  @Nullable
  String getClassName();

  @Nullable
  PsiType toPsiType();

}
