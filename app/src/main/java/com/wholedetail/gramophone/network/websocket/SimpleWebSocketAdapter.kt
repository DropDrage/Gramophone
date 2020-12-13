package com.wholedetail.gramophone.network.websocket

import android.util.Log
import com.neovisionaries.ws.client.WebSocket
import com.neovisionaries.ws.client.WebSocketAdapter
import com.neovisionaries.ws.client.WebSocketException
import com.neovisionaries.ws.client.WebSocketFrame

private const val TAG = "WebSocket"

class SimpleWebSocketAdapter(private val reconnect: () -> Unit, private val onSocketMessage: (String) -> Unit) :
    WebSocketAdapter() {

    @Throws(Exception::class)
    override fun onConnected(websocket: WebSocket, headers: Map<String, List<String>>) {
        super.onConnected(websocket, headers)
        Log.i(TAG, "onConnected")
    }

    override fun onTextMessage(websocket: WebSocket, message: String) {
        onSocketMessage(message)
        Log.i(TAG, "Text Message $message")
    }

    override fun onError(websocket: WebSocket, cause: WebSocketException) {
        Log.i(TAG, "Error: " + cause.message)
        reconnect()
    }

    override fun onDisconnected(
        websocket: WebSocket,
        serverCloseFrame: WebSocketFrame, clientCloseFrame: WebSocketFrame,
        closedByServer: Boolean
    ) {
        Log.i(TAG, "onDisconnected")
        if (closedByServer) {
            reconnect()
        }
    }

    override fun onUnexpectedError(websocket: WebSocket, cause: WebSocketException) {
        Log.i(TAG, "Unexpected error: " + cause.message)
        reconnect()
    }

    @Throws(Exception::class)
    override fun onPongFrame(websocket: WebSocket, frame: WebSocketFrame) {
        super.onPongFrame(websocket, frame)
        websocket.sendPing("Are you there?")
    }

}