package juuxel.accesswidener.idea

import com.intellij.icons.AllIcons
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import javax.swing.Icon

class AccessWidenerColorSettingsPage : ColorSettingsPage {
    override fun getIcon(): Icon = AllIcons.FileTypes.Config
    override fun getAttributeDescriptors() = DESCRIPTORS
    override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY
    override fun getDisplayName(): String = "Access Widener"
    override fun getHighlighter(): SyntaxHighlighter = AccessWidenerSyntaxHighlighter
    override fun getAdditionalHighlightingTagToDescriptorMap() = ADDITIONAL_HIGHLIGHTING_DESCRIPTORS

    override fun getDemoText(): String =
        """
        accessWidener v1 named

        # Hello, world!
        accessible class my/AmazingClass # end-of-line comments
        extendable method my/AmazingClass <method>doStuff</method> (Ljava/lang/String;IZ)V
        mutable field my/AmazingClass <field>objects</field> Ljava/util/Set;
        """.trimIndent()

    companion object {
        private val DESCRIPTORS: Array<AttributesDescriptor> = arrayOf(
            AttributesDescriptor("Keyword", AccessWidenerSyntaxHighlighter.KEYWORD),
            AttributesDescriptor("Generic identifier", AccessWidenerSyntaxHighlighter.IDENTIFIER),
            AttributesDescriptor("Class name", AccessWidenerSyntaxHighlighter.CLASS),
            AttributesDescriptor("Method name", AccessWidenerSyntaxHighlighter.METHOD),
            AttributesDescriptor("Field name", AccessWidenerSyntaxHighlighter.FIELD),
            AttributesDescriptor("Class descriptor delimiters", AccessWidenerSyntaxHighlighter.LITERAL),
            AttributesDescriptor("Primitive descriptor", AccessWidenerSyntaxHighlighter.DESCRIPTOR),
            AttributesDescriptor("Comment", AccessWidenerSyntaxHighlighter.COMMENT),
            AttributesDescriptor("Parentheses", AccessWidenerSyntaxHighlighter.PAREN),
        )

        private val ADDITIONAL_HIGHLIGHTING_DESCRIPTORS = mapOf(
            "method" to AccessWidenerSyntaxHighlighter.METHOD,
            "field" to AccessWidenerSyntaxHighlighter.FIELD,
        )
    }
}
