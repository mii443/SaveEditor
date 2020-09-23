package codes.mii.SaveEditor

import codes.mii.SaveEditor.Types.*
import java.io.File
import java.io.IOException
import java.nio.file.Files

class Converter {
    @ExperimentalUnsignedTypes
    fun convert(byte: ByteArray): NBT_Compound {
        val result = NBT_Compound(0, "")
        val byteSize = byte.size
        var readingPosition = 0

        try {
            while (byteSize > readingPosition) {
                println(byte[readingPosition])

                val nowByte = byte[readingPosition]
                var nameLength = 0
                val name = StringBuilder()

                if (nowByte != 0.toByte()) {
                    nameLength = readMultiByte(byte, readingPosition, 2).toInt()
                    readingPosition += 2

                    repeat(nameLength) {
                        readingPosition++
                        name.append(hexToAscii(byte[readingPosition].toString(16)))
                    }
                }

                when (nowByte) {
                    0.toByte() -> { // END
                        result.data.add(NBT_End())
                        readingPosition++
                    }

                    1.toByte() -> { // BYTE
                        readingPosition++
                        result.data.add(NBT_Byte(nameLength.toByte(), name.toString(), byte[readingPosition]))
                    }

                    2.toByte(), 3.toByte(), 4.toByte() -> {
                        val n = when(nowByte) {
                            3.toByte() -> { 4 }
                            4.toByte() -> { 8 }
                            else -> { 2 }
                        }
                        result.data.add(NBT_Short(nameLength.toByte(), name.toString(), readMultiByte(byte, readingPosition, n).toInt()))
                        readingPosition += n
                    }

                    8.toByte() -> { // STRING

                        val textLength = readMultiByte(byte, readingPosition, 2).toInt()
                        readingPosition += 2

                        val text = StringBuilder()
                        repeat(textLength) {
                            readingPosition++
                            text.append(hexToAscii(byte[readingPosition].toString(16)))
                        }

                        result.data.add(NBT_String(nameLength.toByte(), name.toString(), textLength.toByte(), text.toString()))
                    }

                    10.toByte() -> { // COMPOUND
                        result.data.add(NBT_Compound(nameLength.toByte(), name.toString()))
                    }
                }

                readingPosition++
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return result
    }

    fun readMultiByte(byte: ByteArray, position: Int, count: Int): Long {
        var readingPosition = position
        val multiByteText = StringBuilder()
        repeat(count) {
            readingPosition++
            val n = if (it != 1){
                (byte[readingPosition].toUByte()).toString(16)
            } else {
                (byte[readingPosition]).toString(16)
            }
            multiByteText.append(if (n.length == 2) { n } else { "0$n" })
        }
        return multiByteText.toString().toLong(16)
    }

    fun byteToString(byte: ByteArray, readingPosition: Int) {

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