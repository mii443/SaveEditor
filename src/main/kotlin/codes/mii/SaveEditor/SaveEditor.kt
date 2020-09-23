package codes.mii.SaveEditor

import codes.mii.SaveEditor.Types.*
import java.io.File
import java.io.IOException
import java.nio.file.Files

class SaveEditor {

    val converter: Converter = Converter()

    fun start(args: Array<String>) {
        println("NBT Editor.")

        if (args.count() >= 1) {
            val bytes = converter.convertFile(File(args[0]))

            val inp = StringBuilder()
            bytes?.forEach {
                val n = it.toString(16)
                inp.append(if (n.length == 1) { "0$n " } else { "$n " })
            }
            println("input: $inp")
            println("Converting...")
            val result = converter.convert(bytes!!)
            println("Converted.\n\nresult\n")


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

                    is NBT_Int -> {
                        println("INT, Name: ${it.name}, Data: ${it.data}")
                    }

                    is NBT_Short -> {
                        println("SHORT, Name: ${it.name}, Data: ${it.data}")
                    }

                    is NBT_Long -> {
                        println("LONG, Name: ${it.name}, Data: ${it.data}")
                    }

                    else -> {
                        println("UNKNOWN, ${it::class.java}")
                    }
                }
            }
        }
    }
}