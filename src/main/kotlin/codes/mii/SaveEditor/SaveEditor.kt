package codes.mii.SaveEditor

import java.io.File
import java.io.IOException
import java.nio.file.Files

class SaveEditor {
    fun start(args: Array<String>) {
        println("Minecraft Save Editor.")

        if (args.count() >= 1) {
            var bytes = convertFile(File(args[0]))
            var builder: StringBuilder = StringBuilder()
            bytes?.forEach {
                val byteString = it.toString(16)
                builder.append(if (byteString.length == 1) { "0$byteString" } else { byteString } + " ")
            }
            println(builder.toString())
        }
    }

    @Throws(IOException::class)
    fun convertFile(file: File): ByteArray? {
        return Files.readAllBytes(file.toPath())
    }
}