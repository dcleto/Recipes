package com.dclet.recipes.utils

import android.text.Html

class StringUtils {
    companion object{
        fun stripHtml(html: String): String {
            return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY).toString()
            } else {
                Html.fromHtml(html).toString()
            }
        }
    }
}