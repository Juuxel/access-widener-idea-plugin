// This is a generated file. Not intended for manual editing.
package juuxel.accesswidener.idea.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface AwMethodDescriptor extends PsiElement {

  @NotNull
  List<AwTypeDescriptor> getTypeDescriptorList();

  AwTypeDescriptor getReturnType();

  @NotNull List<AwTypeDescriptor> getParameters();

}
