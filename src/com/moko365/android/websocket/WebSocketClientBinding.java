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

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import android.util.Log;

public class WebSocketClientBinding {

	private WebsocketDroidGap mContext;
	private WebSocketConnection mConnection;
	private WebsocketHandler mHandler;

	public WebSocketClientBinding(WebsocketDroidGap context) {
		Log.i(TAG, "WebSocketClientBinding()");	
		
		mContext = context;
	}

	private static final String TAG = "WebSocketClientBinding";

	public void connect(String uri, String proto) {	
		Log.i(TAG, "Protocol: " + proto);
		Log.i(TAG, "URL: " + uri);
		
		mConnection = new WebSocketConnection();
		mHandler = new WebsocketHandler(mContext);
		
		try {
			mConnection.connect(uri, proto, mHandler);
		} catch (WebSocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
