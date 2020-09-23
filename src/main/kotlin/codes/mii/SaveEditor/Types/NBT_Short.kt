package codes.mii.SaveEditor.Types

class NBT_Short(override val nameLength: Byte, override val name: String, val data: Int) : Data {
    override val typeByte: Byte = 2
}