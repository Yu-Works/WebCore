package com.IceCreamQAQ.SmartWeb.ans

import com.IceCreamQAQ.SmartWeb.controller.SRControllerLoader
import com.IceCreamQAQ.Yu.annotation.LoadBy

@LoadBy(SRControllerLoader::class)
annotation class WebController

annotation class GetAction(vararg val value:String)
annotation class PostAction(vararg val value:String)
annotation class PutAction(vararg val value:String)
annotation class DeleteAction(vararg val value:String)