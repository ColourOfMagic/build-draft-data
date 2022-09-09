package builder.utils

object PathHelper {

    var startDirectory = ""

    fun initialize(startDirectory: String) {
        PathHelper.startDirectory = startDirectory
    }

    fun filePath(name: String) = "$startDirectory/$name"

}