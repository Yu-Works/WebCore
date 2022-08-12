package com.IceCreamQAQ.SmartWeb.controller

import com.IceCreamQAQ.SmartWeb.http.Method
import com.IceCreamQAQ.Yu.controller.InterceptorInfo
import com.IceCreamQAQ.YuWeb.WebActionContext

class RootRouter {

    val getRouter = WebRouter()
    val postRouter = WebRouter()
    val putRouter = WebRouter()
    val updateRouter = WebRouter()

    val interceptorInfo = InterceptorInfo(arrayListOf(), arrayListOf(), arrayListOf())

    operator fun invoke(context: WebActionContext): Boolean {
        return context.request.methodEnum?.let {
            when (it) {
                Method.GET -> getRouter
                Method.POST -> postRouter
                Method.PUT -> putRouter
                Method.UPDATE -> updateRouter
            }
        }?.invoke(context, 0) ?: false
    }

}