package com.dubornogolpo.dubornogolpo2.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Author(val name: String, val photo: String, val birthplace: String, val hobbies: String, val bloodGroup: String, val school: String, val college: String) : Parcelable{
    constructor():this("", "DEFAULT", "", "", "", "", "")
}