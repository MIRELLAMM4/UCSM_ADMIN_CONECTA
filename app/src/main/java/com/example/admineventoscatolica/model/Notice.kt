package com.example.admineventoscatolica.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Notice(
    val noticeid: String?,
    val title: String?,
    val description: String?,
    val image: Int?,  // Cambiar a URL
    val date: String?,
    val isUpcoming: Boolean = false
) : Parcelable