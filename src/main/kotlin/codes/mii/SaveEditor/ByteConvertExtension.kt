package codes.mii.SaveEditor

import codes.mii.SaveEditor.Types.NBT_String

val converter = Converter()

@ExperimentalUnsignedTypes
fun ByteArray.toNBT_String(position: Int): Pair<NBT_String, Int> {
    var readingPosition = position

    val nowByte = this[readingPosition]
    val nameLength = converter.readMultiByteHex(this, readingPosition, 2).toInt()
    readingPosition += 2
    val name = converter.byteToString(this, readingPosition, nameLength)
    readingPosition += nameLength

    val textLength = converter.readMultiByteHex(this, readingPosition, 2).toInt()
    readingPosition += 2

    val text = converter.byteToString(this, readingPosition, textLength)
    readingPosition += textLength

    return NBT_String(nameLength.toByte(), name, textLength.toByte(), text) to 4 + nameLength + textLength
}