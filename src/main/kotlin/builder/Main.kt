package builder

import builder.utils.DataPathHelper
import builder.utils.OutPathHelper

fun main(args: Array<String>) {
    initialize(args)
    App().run()
}

private fun initialize(args: Array<String>) {
    val directory = args[0]
    DataPathHelper.initialize(directory)
    OutPathHelper.initialize(directory)
}