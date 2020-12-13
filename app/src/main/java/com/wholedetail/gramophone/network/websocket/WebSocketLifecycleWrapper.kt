package com.wholedetail.gramophone.network.websocket

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.neovisionaries.ws.client.WebSocket
import com.neovisionaries.ws.client.WebSocketException
import com.neovisionaries.ws.client.WebSocketFactory
import com.wholedetail.gramophone.BuildConfig
import java.io.IOException


class WebSocketLifecycleWrapper(socketEndpoint: String, private val onMessage: (String) -> Unit) : LifecycleObserver {

    private var ws: WebSocket = WebSocketFactory().createSocket("${BuildConfig.serverUrl}/$socketEndpoint")//ws://

    val isConnected: Boolean
        get() = ws.isOpen


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun connect() {
        ws = ws.connect()
        ws.addListener(SimpleWebSocketAdapter(::reconnect, ::onSocketMessage))
    }

    //ToDo onResume reconnect?

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun disconnect() {
        ws.disconnect()
    }


    private fun reconnect() {
        if (isConnected) return

        try {
            ws = ws.recreate().connect()
        } catch (e: WebSocketException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


    private fun onSocketMessage(message: String) {

    }

}