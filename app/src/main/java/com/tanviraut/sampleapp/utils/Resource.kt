package com.tanviraut.sampleapp.utils

data class Resource<out T>(val status: Status, val data : T?, val message : String?){
    companion object{
        fun <T> success(data: T): Resource<T> = Resource(status = Status.SUCCESS, data = data, message = null)

        fun <T> error(data: T): Resource<T> = Resource(status = Status.ERROR, data = data, message = "API failed!")

        fun <T> loading(data: T): Resource<T> = Resource(status = Status.LOADING, data = data, message = null)
    }
}