package com.dubornogolpo.dubornogolpo2.models

class User (val qrid: String, val name: String, val phone: String, val id: String, val bookmark: String)
{
    constructor():this("", "", "", "", "")
}