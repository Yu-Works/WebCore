package com.IceCreamQAQ.SmartWeb.controller

import com.IceCreamQAQ.YuWeb.WebActionContext

interface Invoker {
    operator fun invoke(context: WebActionContext, index: Int): Boolean
}

open class WebRouter : Invoker {

    val staticRouters = HashMap<String, WebRouter>()
    val matchRouters = ArrayList<WebMatchRouter>()

    val actions = ArrayList<WebAction>()

    override fun invoke(context: WebActionContext, index: Int): Boolean {
        if (index >= context.path.size) return false
        val nextIndex = index + 1

        staticRouters[context.path[index]]?.let { if (it(context, nextIndex)) return true }
        matchRouters.forEach { if (it.match(context, index) && it(context, nextIndex)) return true }
        actions.forEach { if (it(context, index)) return true }

        return false
    }
}

class WebMatchRouter(private val matchImpl: (WebActionContext, index: Int) -> Boolean) : WebRouter() {

    fun match(context: WebActionContext, index: Int): Boolean = this.matchImpl(context, index)

}

class WebAction : Invoker {

    override fun invoke(context: WebActionContext, index: Int): Boolean {
        TODO("Not yet implemented")
    }

}