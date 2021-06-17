package juuxel.accesswidener.idea

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import juuxel.accesswidener.idea.parser.AccessWidenerParser
import juuxel.accesswidener.idea.psi.AwFile
import juuxel.accesswidener.idea.psi.AwTypes

class AccessWidenerParserDefinition : ParserDefinition {
    override fun createLexer(project: Project): Lexer = AccessWidenerLexerAdapter()
    override fun createParser(project: Project): PsiParser = AccessWidenerParser()
    override fun getFileNodeType(): IFileElementType = FILE
    override fun getCommentTokens(): TokenSet = COMMENT_TOKENS
    override fun getStringLiteralElements(): TokenSet = TokenSet.EMPTY
    override fun createElement(node: ASTNode): PsiElement = AwTypes.Factory.createElement(node)
    override fun createFile(viewProvider: FileViewProvider): PsiFile = AwFile(viewProvider)

    companion object {
        val COMMENT_TOKENS = TokenSet.create(AwTypes.COMMENT)
        val FILE: IFileElementType = IFileElementType(AccessWidenerLanguage)
    }
}
