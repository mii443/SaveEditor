package codes.mii.SaveEditor.Types

class NBT_Compound(override val nameLength: Byte, override val name: String) : Data {
    override val typeByte: Byte = 10
    val data: MutableList<Data> = mutableListOf()
}