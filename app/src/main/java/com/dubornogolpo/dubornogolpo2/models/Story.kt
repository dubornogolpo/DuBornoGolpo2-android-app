package com.dubornogolpo.dubornogolpo2.models

class Story(val name: String, val author: String, val photos: List<String>?, val next: String, val prev: String){
    constructor():this("", "", null, "", "")
}