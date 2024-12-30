package com.example.kotkit.ui.utils

import com.example.kotkit.R
import com.example.kotkit.data.api.BASE_URL_MINIO

object FormatUtils {

    private var DEFAULT_IMAGE_URL = R.drawable.image_not_found;

    fun formatNumber(number: Int): String {
        return when {
            number < 1000 -> number.toString()
            number < 1000000 -> String.format("%.1fK", number / 1000.0)
            else -> String.format("%.1fM", number / 1000000.0)
        }
    }

    fun formatVideoUrl(videoUrl: String) : String {
        return BASE_URL_MINIO + videoUrl;
    }

    fun formatImageUrl(imageUrl: String?) : Any {
        return when {
            imageUrl.isNullOrEmpty() -> DEFAULT_IMAGE_URL

            else -> BASE_URL_MINIO + imageUrl
        }
    }
}