package com.IceCreamQAQ.SmartWeb.controller

import com.IceCreamQAQ.Yu.annotation.*
import com.IceCreamQAQ.Yu.controller.*
import com.IceCreamQAQ.Yu.di.YuContext
import com.IceCreamQAQ.Yu.isBean
import com.IceCreamQAQ.Yu.loader.LoadItem
import com.IceCreamQAQ.Yu.loader.Loader
import java.lang.reflect.Method
import java.util.HashMap
import javax.inject.Inject
import javax.inject.Named

class SRControllerLoader : Loader {

    @Inject
    private lateinit var context: YuContext

    val rootRouters = HashMap<String, RootRouter>()

    override fun load(items: Map<String, LoadItem>) {
        for (item in items.values) {
            if (!item.type.isBean()) continue
            val clazz = item.type
            val name = clazz.getAnnotation(Named::class.java)?.value
                ?: item.loadBy::class.java.interfaces[0].getAnnotation(Named::class.java)?.value ?: ""

            val rootRouter = rootRouters.getOrPut(name) { RootRouter() }


        }
    }

    fun loadController(rootRouter: RootRouter, controllerClass: Class<*>, instance: Any) {
        val globalBeforeList = rootRouter.interceptorInfo.befores
        val globalAfterList = rootRouter.interceptorInfo.afters
        val globalCatchList = rootRouter.interceptorInfo.catchs

        val allMethods = ArrayList<Method>()
        getMethods(allMethods, controllerClass)
        val with = controllerClass.getAnnotation(With::class.java)?.value
        if (with != null)
            for (kClass in with) {
                getMethods(allMethods, kClass.java)
            }

        val methods = controllerClass.methods

//        val befores = ArrayList<DoMethod<Before, MethodInvoker>>()
//        val afters = ArrayList<DoMethod<After, MethodInvoker>>()
//        val catchs = ArrayList<DoMethod<Catch, CatchInvoker>>()

//        for (method in allMethods) {
//            val before = method.getAnnotation(Before::class.java)
//            if (before != null) {
//                val beforeInvoker = createMethodInvoker(instance, method)
//                val dm = DoMethod(before, beforeInvoker)
//                if (method.getAnnotation(Global::class.java) != null) globalBeforeList.add(dm)
//                else befores.add(dm)
//            }
//            val after = method.getAnnotation(After::class.java)
//            if (after != null) {
//                val afterInvoker = createMethodInvoker(instance, method)
//                val dm = DoMethod(after, afterInvoker)
//                if (method.getAnnotation(Global::class.java) != null) globalAfterList.add(dm)
//                else afters.add(dm)
//            }
//            val catch = method.getAnnotation(Catch::class.java)
//            if (catch != null) {
//                val catchInvoker = createCatchMethodInvoker(instance, method, catch.error.java)
//                val dm = DoMethod(catch, catchInvoker)
//                if (method.getAnnotation(Global::class.java) != null) globalCatchList.add(dm)
//                else catchs.add(dm)
//            }
//        }


//        val interceptorInfo = InterceptorInfo(befores, afters, catchs)
    }

    fun getMethods(methods: MutableList<Method>, clazz: Class<*>) {
        methods.addAll(clazz.methods)
        getMethods(methods, clazz.superclass ?: return)
    }

}