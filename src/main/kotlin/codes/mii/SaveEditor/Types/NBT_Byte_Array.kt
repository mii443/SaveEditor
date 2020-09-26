package codes.mii.SaveEditor.Types

class NBT_Byte_Array(override val nameLength: Byte, override val name: String, val data: MutableList<Byte>) : Data {
    override val typeByte: Byte = 7
}