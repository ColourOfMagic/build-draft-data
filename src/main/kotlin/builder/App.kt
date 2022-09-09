package tool

import com.github.ajalt.clikt.output.TermUi.echo
import com.yg.kotlin.inquirer.components.promptConfirm
import com.yg.kotlin.inquirer.components.promptList
import com.yg.kotlin.inquirer.core.KInquirer
import tool.appearance.Designer.toColoredOption
import tool.appearance.TextRes
import tool.appearance.View
import tool.data.settings.SettingsProvider
import tool.data.settings.model.BackCommandNode
import tool.data.settings.model.FolderNode
import tool.data.settings.model.ScriptNode
import tool.data.settings.model.StructureNode
import tool.launch.NodeListManager
import javax.naming.NameNotFoundException

class App {

    fun run(settingsPath: String) {
        echo(settingsPath)
        echo(View.titleTable)
        echo(TextRes.appVersion)
        mainCycle(
            settings = SettingsProvider.getSettings(settingsPath).also(NodeListManager::initialize)
        )
        echo(TextRes.endMsg)
    }

    private fun mainCycle(settings: List<StructureNode>) {
        selectScript(settings)

        val retry = KInquirer.promptConfirm(TextRes.againMsg, default = true)
        if (retry) mainCycle(settings)
    }

    private fun selectScript(settings: List<StructureNode>) {
        val optionsMap = settings.associateBy { it.toColoredOption() }
        val name: String = KInquirer.promptList("Select: ", optionsMap.keys.toList())

        val nextNode = optionsMap[name] ?: throw NameNotFoundException()

        when (nextNode) {
            is FolderNode -> selectScript(nextNode.elements)
            is BackCommandNode -> selectScript(nextNode.previousElements)
            is ScriptNode -> startProcess(nextNode)
        }
    }

    private fun startProcess(node: ScriptNode) {
        echo("Starting ${node.name} ...")
        Runtime.getRuntime().exec("/usr/bin/open -a Terminal ${node.path}");
    }
}
