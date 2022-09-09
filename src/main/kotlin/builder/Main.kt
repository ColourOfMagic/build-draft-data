package tool

fun main(args: Array<String>) {
    App().run(args.settings)
}

internal val Array<String>.settings: String
    get() = first().trim()