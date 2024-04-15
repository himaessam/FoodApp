package com.example.foodapp.util

sealed class Resorce<T> (
    val data: T? = null,
    val message: String? = null
    ){
        class Success<T>(data: T?): Resorce<T>(data)

        class Error<T>(message: String,data: T?=null): Resorce<T>(data,message)
}