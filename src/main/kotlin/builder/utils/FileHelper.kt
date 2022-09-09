package builder.utils

import java.nio.ByteBuffer
import java.nio.channels.FileChannel
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

object FileHelper {

    private var linesCache: MutableMap<String, Int> = mutableMapOf()

    fun getLinesCount(fileName: String): Int =
        linesCache[fileName].let {
        it ?: countLines(fileName).also { count ->
            linesCache[fileName] = count
        }
    }

    private fun countLines(fileName: String): Int =
        FileChannel.open(Paths.get(fileName), StandardOpenOption.READ).use { channel ->
            var lines = 0 // TODO: 1?
            val byteBuffer: ByteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size())
            while (byteBuffer.hasRemaining()) {
                val currentByte: Byte = byteBuffer.get()
                if (currentByte == '\n'.code.toByte()) lines++
            }
            return lines
        }
}