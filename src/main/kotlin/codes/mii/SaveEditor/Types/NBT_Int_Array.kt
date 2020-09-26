package codes.mii.SaveEditor.Types

class NBT_Int_Array(override val nameLength: Byte, override val name: String, val data: MutableList<Int>) : Data {
    override val typeByte: Byte = 11
}