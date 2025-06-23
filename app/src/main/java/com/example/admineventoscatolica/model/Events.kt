package com.example.admineventoscatolica.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Events(
    val eventid: String? = null,
    val title: String? = null,
    val time: String? = null,
    val date: String? = null,
    val upcoming: Boolean = false
) : Parcelable
