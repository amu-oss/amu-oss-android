package io.amu.oss.data.model

data class Notification(
        val title: String = "Notification Update",
        val body: String = "",
        val color: String = "#aaaaaa",
        val timestamp: String? = null,
        val skip: Boolean = false,
        val sent: Boolean = false,
        val failed: Boolean = false,
        val failureCause: String? = null
)
