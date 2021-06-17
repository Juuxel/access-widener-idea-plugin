package juuxel.accesswidener.idea.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import juuxel.accesswidener.idea.AccessWidenerFileType
import juuxel.accesswidener.idea.AccessWidenerLanguage

class AwFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, AccessWidenerLanguage) {
    override fun getFileType(): FileType = AccessWidenerFileType

    override fun toString() = "Access Widener"
}
