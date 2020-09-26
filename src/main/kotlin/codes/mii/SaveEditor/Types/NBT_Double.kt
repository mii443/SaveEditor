package codes.mii.SaveEditor.Types

class NBT_Double(override val nameLength: Byte, override val name: String, val data: Double) : Data {
    override val typeByte: Byte = 6
}