package builder.utils

object OutPathHelper {

    var outDirectory = ""

    fun initialize(directory: String) {
        this.outDirectory = "$directory/out"
    }

    fun filePath(name: String) = "$outDirectory/$name"
}