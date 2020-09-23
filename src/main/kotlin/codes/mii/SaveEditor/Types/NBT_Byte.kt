package codes.mii.SaveEditor.Types

class NBT_Byte(override val nameLength: Byte, override val name: String, val data: Byte) : Data {
    override val typeByte: Byte = 1
}