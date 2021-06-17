package juuxel.accesswidener.idea

import com.intellij.icons.AllIcons
import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

object AccessWidenerFileType : LanguageFileType(AccessWidenerLanguage) {
    override fun getName(): String = "Access Widener"

    override fun getDescription(): String = "Access widener file"

    override fun getDefaultExtension(): String = "accesswidener"

    override fun getIcon(): Icon? = AllIcons.FileTypes.Config
}
