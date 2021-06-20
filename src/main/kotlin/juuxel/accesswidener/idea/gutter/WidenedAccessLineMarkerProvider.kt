package juuxel.accesswidener.idea.gutter

import com.intellij.codeInsight.daemon.DefaultGutterIconNavigationHandler
import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.codeInsight.daemon.LineMarkerProviderDescriptor
import com.intellij.icons.AllIcons
import com.intellij.openapi.editor.markup.GutterIconRenderer
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiField
import com.intellij.psi.PsiMethod
import com.intellij.psi.PsiNameIdentifierOwner
import juuxel.accesswidener.idea.util.AwScanner
import juuxel.accesswidener.idea.util.MessageBundle
import javax.swing.Icon

class WidenedAccessLineMarkerProvider : LineMarkerProviderDescriptor() {
    override fun getName(): String = "Widened access line marker"

    override fun getIcon(): Icon = AllIcons.Actions.InlayGlobe

    override fun getLineMarkerInfo(element: PsiElement): LineMarkerInfo<*>? {
        if (element !is PsiClass && element !is PsiMethod && element !is PsiField) return null
        element as PsiNameIdentifierOwner // PsiClass, PsiMethod, PsiField all extend this

        val defs = AwScanner.findDefinitionsFor(element).toList()
        if (defs.isNotEmpty()) {
            return LineMarkerInfo(
                element.nameIdentifier!!,
                element.nameIdentifier!!.textRange,
                icon,
                { MessageBundle["gutter.widenedAccess"] },
                DefaultGutterIconNavigationHandler(defs, MessageBundle["gutter.widenedAccess.go"]),
                GutterIconRenderer.Alignment.LEFT,
                { MessageBundle["gutter.widenedAccess"] },
            )
        }

        return null
    }
}
