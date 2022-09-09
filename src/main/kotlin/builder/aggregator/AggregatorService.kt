package builder.aggregator

import builder.utils.DataPathHelper
import builder.utils.FileHelper
import builder.utils.OutPathHelper
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.random.Random

object AggregatorService {

    fun aggregateToFile(fileName: String, elementsCount: Int) {
        val dataFileNames = listFileNames(DataPathHelper.dataDirectory)
        val outFile = File(OutPathHelper.filePath(fileName))
        outFile.parentFile.mkdirs()
        outFile.printWriter().use { out ->
            out.println("[")
            (1..elementsCount).forEach { i ->
                out.print(getRandomJson(dataFileNames))
                if (i != elementsCount) out.print(',')
                out.println()
            }
            out.println("]")
        }
    }

    private fun getRandomJson(dataFileNames: List<String>): String {
        val filePath = DataPathHelper.filePath(dataFileNames[Random.nextInt(0, dataFileNames.size)])
        val selectedLine = Random.nextInt(0, FileHelper.getLinesCount(filePath))
        return readLineByNumber(filePath, selectedLine)
    }

    private fun listFileNames(dir: String): List<String> =
        File(dir)
            .listFiles()
            .let { checkNotNull(it) }
            .filter { file -> !file.isDirectory }
            .map { obj -> obj.name }

    private fun readLineByNumber(filePath: String, lineNumber: Int): String =
        Files.lines(Paths.get(filePath)).use { it.skip(lineNumber - 1L).findFirst().get() }
}
