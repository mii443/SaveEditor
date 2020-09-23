package codes.mii.SaveEditor.Types

class NBT_String(override val nameLength: Byte, override val name: String, val length: Byte, val data: String) : Data {
    override val typeByte: Byte = 8
}