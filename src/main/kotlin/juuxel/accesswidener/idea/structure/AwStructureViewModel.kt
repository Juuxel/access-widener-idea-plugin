package juuxel.accesswidener.idea.structure

import com.intellij.ide.structureView.StructureViewModel
import com.intellij.ide.structureView.StructureViewModelBase
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.Sorter
import com.intellij.psi.PsiFile

class AwStructureViewModel(file: PsiFile) :
    StructureViewModelBase(file, AwFileTreeElement(file)),
    StructureViewModel.ElementInfoProvider {
    init {
        withSorters(Sorter.ALPHA_SORTER)
    }

    override fun isAlwaysShowsPlus(element: StructureViewTreeElement) =
        element is AwFileTreeElement

    override fun isAlwaysLeaf(element: StructureViewTreeElement) =
        element is AwDefinitionTreeElement
}
