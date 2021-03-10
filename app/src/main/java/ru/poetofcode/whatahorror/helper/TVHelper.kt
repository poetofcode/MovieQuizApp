package ru.poetofcode.whatahorror.helper

import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.databinding.BindingAdapter
import ru.poetofcode.whatahorror.App


class TVHelper {

    inner class Pair(val key: String, val value: String) {
        override fun toString(): String {
            return "${key} = \"$value\""
        }
    }

    inner class ImageGetter : Html.ImageGetter {
        override fun getDrawable(source: String): Drawable {
//            val id: Int
//            val d: Drawable = getResources().getDrawable(id)
            val d = Drawable.createFromStream(App.instance.assets.open(source), null)
            d.setBounds(0, 0, d.intrinsicWidth, d.intrinsicHeight)
            return d
        }
    }

    var buffer: StringBuffer = StringBuffer()

    fun build(): Spanned = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)  {
        Html.fromHtml(buffer.toString(), Html.FROM_HTML_MODE_LEGACY, ImageGetter(), null)
    } else {
        Html.fromHtml(buffer.toString(), ImageGetter(), null)
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

    fun link(url: String, text: String = ""): TVHelper {
        val content = if (text.isBlank()) url else text
        replaceOn(wrapTag(content, "a", Pair("href", url)))
        return this
    }

    fun img(src: String): TVHelper {
        replaceOn(wrapTag("", "img", Pair("src", src)))
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
            if (body.isNotBlank()) {
                append(">$body</$tag>")
            } else {
                append("/>")
            }
        }.toString()
    }

    companion object {
        @JvmStatic
        fun from(str: String): TVHelper {
            return TVHelper().apply { buffer.append(str) }
        }

        @JvmStatic @BindingAdapter("app:html")
        fun processHtml(tv: TextView, helper: TVHelper) {
            tv.movementMethod = LinkMovementMethod.getInstance()
            tv.text = helper.build()
        }
    }

}