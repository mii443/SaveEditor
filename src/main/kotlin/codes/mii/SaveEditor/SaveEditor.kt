package codes.mii.SaveEditor

import codes.mii.SaveEditor.Types.*
import java.io.File
import java.io.IOException
import java.nio.file.Files

class SaveEditor {

    val converter: Converter = Converter()

    fun showNBT(data: Data) {
        when(data) {
            is NBT_String -> {
                println("STRING, Name: ${data.name}, Data: ${data.data}")
            }

            is NBT_End -> {
                println("END")
            }

            is NBT_Byte -> {
                println("BYTE, Name: ${data.name}, Data: ${data.data}")
            }

            is NBT_Compound -> {
                println("COMPOUND, Name: ${data.name}")
                data.data.forEach {
                    showNBT(it)
                }
            }

            is NBT_Int -> {
                println("INT, Name: ${data.name}, Data: ${data.data}")
            }

            is NBT_Short -> {
                println("SHORT, Name: ${data.name}, Data: ${data.data}")
            }

            is NBT_Long -> {
                println("LONG, Name: ${data.name}, Data: ${data.data}")
            }

            is NBT_Float -> {
                println("FLOAT, Name: ${data.name}, Data: ${data.data}")
            }

            is NBT_Double -> {
                println("DOUBLE, Name: ${data.name}, Data: ${data.data}")
            }

            is NBT_Byte_Array -> {
                println("BYTE_ARRAY, Name: ${data.name}, Data: ${data.data.joinToString() }}")
            }

            is NBT_Int_Array -> {
                println("BYTE_ARRAY, Name: ${data.name}, Data: ${data.data.joinToString() }")
            }

            is NBT_List -> {
                println("LIST, Name: ${data.name}")
                data.data.forEach {
                    showNBT(it)
                }
            }

            else -> {
                println("UNKNOWN, ${data::class.java}")
            }
        }
    }

    fun start(args: Array<String>) {
        println("NBT Editor.")

        if (args.count() >= 1) {
            val bytes = converter.convertFile(File(args[0]))

            /*
            val inp = StringBuilder()
            bytes?.forEach {
                val n = it.toString(16)
                inp.append(if (n.length == 1) { "0$n " } else { "$n " })
            }
            println("input: $inp")
             */
            println("Converting...")
            val result = converter.convert(bytes!!)
            println("Converted.\n\nresult\n")

            result.data.forEach {
                showNBT(it)
            }
        }
    }
}