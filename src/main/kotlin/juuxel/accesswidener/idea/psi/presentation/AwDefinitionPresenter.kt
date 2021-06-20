package juuxel.accesswidener.idea.psi.presentation

import com.intellij.ide.projectView.PresentationData
import com.intellij.navigation.ItemPresentation
import com.intellij.navigation.ItemPresentationProvider
import juuxel.accesswidener.idea.psi.AwDefinition
import juuxel.accesswidener.idea.psi.AwMemberDefinition

class AwDefinitionPresenter : ItemPresentationProvider<AwDefinition> {
    override fun getPresentation(item: AwDefinition): ItemPresentation =
        PresentationData(
            item.accessType?.sourceCode + ' ' + getQualifiedName(item),
            item.containingFile.name,
            item.icon,
            null
        )

    private fun getQualifiedName(definition: AwDefinition): String =
        when (definition) {
            is AwMemberDefinition -> "${definition.owner}.${definition.name}"
            else -> definition.name!!
        }
}
