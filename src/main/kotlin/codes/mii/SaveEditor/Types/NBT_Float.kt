package codes.mii.SaveEditor.Types

class NBT_Float(override val nameLength: Byte, override val name: String, val data: Float) : Data {
    override val typeByte: Byte = 5
}