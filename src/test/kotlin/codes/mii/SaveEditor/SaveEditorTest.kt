package codes.mii.SaveEditor

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class SaveEditorTest {

    @Test
    fun start() {
        val args: Array<String> = arrayOf("D:\\Git\\SaveEditor\\build\\libs\\test")
        SaveEditor().start(args)
    }
}