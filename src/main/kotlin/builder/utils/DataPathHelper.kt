package builder.utils

object DataPathHelper {

    var dataDirectory = ""

    fun initialize(directory: String) {
        this.dataDirectory = "$directory/data"
    }

    fun filePath(name: String) = "$dataDirectory/$name"
}