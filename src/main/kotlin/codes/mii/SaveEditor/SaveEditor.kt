package codes.mii.SaveEditor

import codes.mii.SaveEditor.Types.*
import java.io.File
import java.io.IOException
import java.nio.file.Files

class SaveEditor {

    val converter: Converter = Converter()

    fun start(args: Array<String>) {
        println("Minecraft Save Editor.")

        if (args.count() >= 1) {
            val bytes = converter.convertFile(File(args[0]))

            val result = converter.convert(bytes!!)

            result.data.forEach {
                when(it) {
                    is NBT_String -> {
                        println("STRING, Name: ${it.name}, Data: ${it.data}")
                    }

                    is NBT_End -> {
                        println("END")
                    }

                    is NBT_Byte -> {
                        println("BYTE, Name: ${it.name}, Data: ${it.data}")
                    }

                    is NBT_Compound -> {
                        println("COMPOUND, Name: ${it.name}")
                    }
                }
            }
        }
    }
}