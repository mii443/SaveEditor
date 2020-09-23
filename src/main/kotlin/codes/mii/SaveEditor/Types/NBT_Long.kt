package codes.mii.SaveEditor.Types

class NBT_Long(override val nameLength: Byte, override val name: String, val data: Long) : Data {
    override val typeByte: Byte = 4
}