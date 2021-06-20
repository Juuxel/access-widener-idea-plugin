// This is a generated file. Not intended for manual editing.
package juuxel.accesswidener.idea.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.util.Iconable.IconFlags;
import javax.swing.Icon;
import juuxel.accesswidener.idea.AccessType;

public interface AwDefinition extends NavigatablePsiElement {

  @Nullable
  AccessType getAccessType();

  @Nullable
  String getName();

  @NotNull
  Icon getIcon(@IconFlags int flags);

  @NotNull
  Icon getIcon();

  @Nullable
  ItemPresentation getPresentation();

}
