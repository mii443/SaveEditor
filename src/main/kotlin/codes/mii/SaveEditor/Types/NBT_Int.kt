package codes.mii.SaveEditor.Types

class NBT_Int(override val nameLength: Byte, override val name: String, val data: Int) : Data {
    override val typeByte: Byte = 3
}