package ru.poetofcode.whatahorror.helper

import android.os.Build
import android.text.Html
import androidx.annotation.ColorRes


class TVHelper {

    inner class Pair(val key: String, val value: String) {
        override fun toString(): String {
            return "${key} = \"$value\""
        }
    }

    var buffer: StringBuffer = StringBuffer()

    fun build() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(buffer.toString(), Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(buffer.toString())
    }

    fun plain(text: String): TVHelper {
        replaceOn(text)
        return this
    }

    fun colored(text: String, @ColorRes colorRes: Int): TVHelper {
        val p = Pair("color", hexFromRes(colorRes))
        replaceOn(wrapTag(text, "font", p))
        return this
    }

    private fun hexFromRes(@ColorRes colorRes: Int): String {
        return "#" + Integer.toHexString(
            colorRes and 0x00ffffff
        )
    }

    private fun replaceOn(newStr: String) {
        buffer.toString().replaceFirst("%s", newStr).apply {
            buffer.setLength(0)
            buffer.append(this)
        }
    }

    private fun wrapTag(body: String, tag: String, vararg params: Pair): String {
        return StringBuffer().apply {
            append("<$tag")
            if (params.isNotEmpty()) {
                append(" ")
                append(params.joinToString(" ") { it.toString() })
            }
            append(">$body</$tag>")
        }.toString()
    }

    companion object {
        @JvmStatic
        fun from(str: String): TVHelper {
            return TVHelper().apply { buffer.append(str) }
        }
    }

}