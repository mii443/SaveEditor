package codes.mii.SaveEditor.Types

class NBT_List(override val nameLength: Byte, override val name: String, val data: MutableList<Data>) : Data {
    override val typeByte: Byte = 9
}