package codes.mii.SaveEditor.Types

enum class NBT(val type: Data) {
    End(NBT_End()),
    Byte(NBT_Byte(0,"",0)),
    Int(NBT_Int(0, "", 0)),
    Long(NBT_Long(0, "", 0)),
    String(NBT_String(0, "", 0, "tes")),
    Compound(NBT_Compound(0, ""))
}