package com.IceCreamQAQ.SmartWeb.controller

import com.IceCreamQAQ.YuWeb.WebActionContext

interface Invoker {
    operator fun invoke(context: WebActionContext, index: Int): Boolean
}

abstract class WebRouter : Invoker {

    val staticRouters = HashMap<String, WebRouter>()
    val matchRouters = ArrayList<WebMatchRouter>()

    val actions = ArrayList<WebAction>()

    override fun invoke(context: WebActionContext, index: Int): Boolean {
        val nextIndex = index + 1
        if (context.path.size > nextIndex){
            staticRouters[context.path[nextIndex]]?.let { if (it(context, nextIndex)) return true }
            matchRouters.forEach { if (it(context, nextIndex)) return true }
            actions.forEach { if (it(context, index)) return true }
        }
        return false
    }
}

class WebMatchRouter(private val match: (WebActionContext, index: Int) -> Boolean) : WebRouter() {

    override fun invoke(context: WebActionContext, index: Int): Boolean {
        if (!match(context, index)) return false
        return super.invoke(context, index)
    }

}

class WebAction : Invoker {

    override fun invoke(context: WebActionContext, index: Int): Boolean {
        TODO("Not yet implemented")
    }

}