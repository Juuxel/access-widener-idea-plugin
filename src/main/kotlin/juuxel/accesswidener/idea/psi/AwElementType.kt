package juuxel.accesswidener.idea.psi

import com.intellij.psi.tree.IElementType
import juuxel.accesswidener.idea.AccessWidenerLanguage
import org.jetbrains.annotations.NonNls

class AwElementType(@NonNls debugName: String) : IElementType(debugName, AccessWidenerLanguage)
