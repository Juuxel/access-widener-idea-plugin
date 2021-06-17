package juuxel.accesswidener.idea

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import juuxel.accesswidener.idea.psi.AwTypes
import org.jetbrains.kotlin.idea.highlighter.KotlinHighlightingColors
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors as BaseColors

object AccessWidenerSyntaxHighlighter : SyntaxHighlighterBase() {
    val KEYWORD = createTextAttributesKey("AW_KEYWORD", BaseColors.KEYWORD)
    val IDENTIFIER = createTextAttributesKey("AW_IDENTIFIER", BaseColors.IDENTIFIER)
    val CLASS = createTextAttributesKey("AW_CLASS", KotlinHighlightingColors.TYPE_PARAMETER)
    val METHOD = createTextAttributesKey("AW_METHOD", BaseColors.STATIC_METHOD)
    val FIELD = createTextAttributesKey("AW_FIELD", BaseColors.STATIC_FIELD)
    val PAREN = createTextAttributesKey("AW_PAREN", BaseColors.KEYWORD)
    val COMMENT = createTextAttributesKey("AW_COMMENT", BaseColors.LINE_COMMENT)
    val DESCRIPTOR = createTextAttributesKey("AW_DESCRIPTOR", BaseColors.KEYWORD)
    val LITERAL = createTextAttributesKey("AW_LITERAL", BaseColors.BRACKETS)

    private val KEYWORD_ARRAY = arrayOf(KEYWORD)
    private val IDENTIFIER_ARRAY = arrayOf(IDENTIFIER)
    private val CLASS_ARRAY = arrayOf(CLASS)
    private val PAREN_ARRAY = arrayOf(PAREN)
    private val COMMENT_ARRAY = arrayOf(COMMENT)
    private val DESCRIPTOR_ARRAY = arrayOf(DESCRIPTOR)
    private val LITERAL_ARRAY = arrayOf(LITERAL)

    override fun getHighlightingLexer(): Lexer = AccessWidenerLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> =
        when (tokenType) {
            // Headers
            AwTypes.HEADER_START, AwTypes.VERSION -> KEYWORD_ARRAY
            AwTypes.NAMESPACE -> IDENTIFIER_ARRAY

            // Definitions
            AwTypes.ACCESS, AwTypes.TARGET_TYPE -> KEYWORD_ARRAY
            AwTypes.CLASS_NAME -> CLASS_ARRAY
            AwTypes.MEMBER_NAME -> IDENTIFIER_ARRAY

            // Descriptors
            AwTypes.L_PAREN, AwTypes.R_PAREN -> PAREN_ARRAY
            AwTypes.ARRAY, AwTypes.PRIMITIVE_DESCRIPTOR -> DESCRIPTOR_ARRAY
            AwTypes.LITERAL_START, AwTypes.SEMICOLON -> LITERAL_ARRAY

            // Other
            AwTypes.COMMENT -> COMMENT_ARRAY
            else -> emptyArray()
        }
}
