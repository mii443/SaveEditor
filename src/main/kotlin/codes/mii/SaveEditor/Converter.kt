package codes.mii.SaveEditor

import codes.mii.SaveEditor.Types.NBT_Byte
import codes.mii.SaveEditor.Types.NBT_Compound
import codes.mii.SaveEditor.Types.NBT_End
import codes.mii.SaveEditor.Types.NBT_String
import java.io.File
import java.io.IOException
import java.nio.file.Files

class Converter {
    fun convert(byte: ByteArray): NBT_Compound {
        val result = NBT_Compound(0, "")
        val byteSize = byte.size
        var readingPosition = 0

        while (byteSize > readingPosition) {
            println(byte[readingPosition])

            val nowByte = byte[readingPosition]
            var nameLength = 0
            val name = StringBuilder()

            if (nowByte != 0.toByte()) {
                var nameLengthText = ""
                repeat(2) {
                    readingPosition++
                    nameLengthText += byte[readingPosition].toString(16)
                }
                nameLength = nameLengthText.toInt(16)

                repeat (nameLength) {
                    readingPosition++
                    name.append(hexToAscii(byte[readingPosition].toString(16)))
                }
            }

            when(nowByte) {
                0.toByte() -> {
                    result.data.add(NBT_End())
                    readingPosition++
                }

                1.toByte() -> {
                    readingPosition++
                    result.data.add(NBT_Byte(nameLength.toByte(), name.toString(), byte[readingPosition]))
                }

                8.toByte() -> {
                    var textLengthText = ""
                    repeat(2) {
                        readingPosition++
                        textLengthText += byte[readingPosition].toString(16)
                    }
                    val textLength = textLengthText.toInt(16)

                    val text = StringBuilder()
                    repeat (textLength) {
                        readingPosition++
                        text.append(hexToAscii(byte[readingPosition].toString(16)))
                    }

                    result.data.add(NBT_String(nameLength.toByte(), name.toString(), textLength.toByte(), text.toString()))
                }

                10.toByte() -> {
                    result.data.add(NBT_Compound(nameLength.toByte(), name.toString()))

                }
            }

            readingPosition++
        }

        return result
    }

    @Throws(IOException::class)
    fun convertFile(file: File): ByteArray? {
        return Files.readAllBytes(file.toPath())
    }

    private fun hexToAscii(hexStr: String): String? {
        val output = StringBuilder("")
        var i = 0
        while (i < hexStr.length) {
            val str = hexStr.substring(i, i + 2)
            output.append(str.toInt(16).toChar())
            i += 2
        }
        return output.toString()
    }
}