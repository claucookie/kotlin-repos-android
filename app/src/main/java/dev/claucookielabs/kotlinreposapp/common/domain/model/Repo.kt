package dev.claucookielabs.kotlinreposapp.common.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Repo(
    val name: String,
    val description: String,
    val thumbnailUrl: String,
    val starsCount: String
) : Parcelable
