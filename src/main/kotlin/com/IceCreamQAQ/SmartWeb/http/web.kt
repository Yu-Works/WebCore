package com.IceCreamQAQ.SmartWeb.http

import com.IceCreamQAQ.YuWeb.H

interface Request : H.Request {
    val methodEnum: Method?
}

interface Response : H.Response

enum class Method {
    GET, POST, PUT, UPDATE;

    companion object {
        @JvmStatic
        fun valueOrNullOf(str: String) = when (str) {
            "get" -> GET
            "post" -> POST
            "put" -> PUT
            "UPDATE" -> UPDATE
            else -> null
        }

        fun String.toHttpMethodOrNull() = valueOrNullOf(this)
    }
}