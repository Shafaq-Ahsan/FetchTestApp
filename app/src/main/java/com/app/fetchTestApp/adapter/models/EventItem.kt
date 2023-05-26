package com.app.fetchTestApp.adapter.models

import androidx.annotation.NonNull
import com.app.network_module.models.response.DataResponse


class EventItem(@NonNull event: DataResponse) : ListItem() {
    @NonNull
    private val event: DataResponse

    init {
        this.event = event
    }

    @NonNull
    fun getEvent(): DataResponse {
        return event
    }

    // here getters and setters
    // for title and so on, built
    // using event
    override fun getType(): Int {
        return TYPE_EVENT
    }
}