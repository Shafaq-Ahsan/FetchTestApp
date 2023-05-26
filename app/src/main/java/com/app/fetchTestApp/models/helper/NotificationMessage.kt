package com.app.fetchTestApp.models.helper

import com.app.fetchTestApp.utils.DisplayNotification


data class NotificationMessage(
    var show: Boolean = true,
    var message: String = "",
    var style: DisplayNotification.STYLE = DisplayNotification.STYLE.INFO
)