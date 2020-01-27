package dev.claucookielabs.kotlinreposapp.common.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Repo(
    val name: String,
    val description: String,
    val starsCount: String,
    val owner: Owner
) : Parcelable {

    val thumbnailUrl: String
        get() = owner.avatarUrl

}

@Parcelize
data class Owner(
    val userName: String,
    val avatarUrl: String
) : Parcelable
