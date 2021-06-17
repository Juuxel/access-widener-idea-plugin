package juuxel.accesswidener.idea.psi

import com.intellij.psi.tree.IElementType
import juuxel.accesswidener.idea.AccessWidenerLanguage
import org.jetbrains.annotations.NonNls
import java.util.Locale

class AwTokenType(@NonNls debugName: String) : IElementType(debugName, AccessWidenerLanguage) {
    override fun toString(): String {
        val base = super.toString()

        return if ('_' in base || (base.length > 1 && base.all { it.isUpperCase() })) {
            // Convert the snake case names to readable strings
            "<${base.toLowerCase(Locale.ROOT).replace('_', ' ')}>"
        } else {
            base
        }
    }
}
