/*
 * Copyright (C) 2012 Moko365 Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.moko365.android.websocket;

import de.tavendo.autobahn.WebSocket;
import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketConnectionHandler;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class WebsocketHandler extends WebSocketConnectionHandler implements WebSocketHolder {

	private static final String TAG = "WebsocketHandler";

	public static final int WEBSOCKET_ONOPEN = 0;
	public static final int WEBSOCKET_ONCLOSE = 1;
	public static final int WEBSOCKET_ONERROR = 2;
	public static final int WEBSOCKET_ONMESSAGE = 3;
	
	private WebsocketDroidGap mContext;

	public WebsocketHandler(WebsocketDroidGap context) {
		mContext = context;
	}

	@Override
	public void onOpen() {
		Log.i(TAG, "onOpen");
		
		mContext.getAppView().loadUrl("javascript: var ws = WebSocketImpl(); ws.onopen();");
		super.onOpen();
	}

	@Override
	public void onTextMessage(String payload) {
		Log.i(TAG, "onTextMessage");
		
		String jsObj = "{data: '" + payload + "'}";
		mContext.getAppView().loadUrl("javascript: var ws = WebSocketImpl(); ws.onmessage(" + jsObj + ");");
						
		super.onTextMessage(payload);
	}

	@Override
	public void onRawTextMessage(byte[] payload) {
		Log.i(TAG, "onRawTextMessage");
		super.onRawTextMessage(payload);
	}

	@Override
	public void onBinaryMessage(byte[] payload) {
		Log.i(TAG, "onBinaryMessage");
		super.onBinaryMessage(payload);
	}
	

	public void onConnectionError() {
		Log.i(TAG, "onConnectionError");

		mContext.getAppView().loadUrl("javascript: var ws = WebSocketImpl(); ws.onerror();");		
	}
}
