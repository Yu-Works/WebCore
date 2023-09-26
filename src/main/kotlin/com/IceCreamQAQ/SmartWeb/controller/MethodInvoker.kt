package com.IceCreamQAQ.SmartWeb.controller

import com.IceCreamQAQ.Yu.controller.ActionContext
import com.IceCreamQAQ.YuWeb.WebActionContext
import com.IceCreamQAQ.YuWeb.validation.ValidateData
import java.lang.reflect.Method
import kotlin.reflect.jvm.kotlinFunction

interface MethodInvoker<T> {

    suspend fun invoke(context: ActionContext, actionParams: HashMap<String, String>, throwable: Throwable?): Any?

}

class ReflectMethodInvoker<T>(val controllerClass: Class<T>, val method: Method, val instance: T) : MethodInvoker<T> {

    data class MethodPara(
        val clazz: Class<*>,
        val name: String,
        val type: Int,
        val data: Any,
        val isArray: Boolean,
        val isSimple: Boolean,
        val isSaved: Boolean,
        val default: String?,
        var isBody: Boolean,
        var isPara: Boolean,
        val cts: (Array<String>.() -> Any?)? = null,
        val vds: Array<ValidateData>?
    )

    val className = controllerClass.name
    val methodName = method.name

    private var returnFlag: Boolean = false

    val invoker: suspend (WebActionContext, Map<String, String>, Throwable?) -> Any?

    init {
        returnFlag = (method.returnType?.name ?: "void") != "void"

        val isKotlin = method.kotlinFunction != null

        invoker = if (isKotlin) {
            { _, _, _ -> null }
        } else {
            {_, _, _ -> null }
        }
    }

    override suspend fun invoke(
        context: ActionContext,
        actionParams: HashMap<String, String>,
        throwable: Throwable?
    ): Any? {
        TODO("Not yet implemented")
    }

}