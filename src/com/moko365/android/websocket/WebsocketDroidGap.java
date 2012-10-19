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

import org.apache.cordova.DroidGap;

import android.util.Log;
import android.webkit.WebView;

public class WebsocketDroidGap extends DroidGap {

	@Override
	public void init() {
		super.init();
		initWebsocket();				
	}

	private static final String TAG = "WebsocketDropGap";

	@Override
	public void loadUrl(String url) {
		super.loadUrl(url);
	}
	
	private void initWebsocket() {
		Log.i(TAG, "initWebsocket");
		
		this.appView.getSettings().setJavaScriptEnabled(true);
		this.appView.addJavascriptInterface(new WebSocketClientBinding(this), "WebSocketAndroid");	
	}

	public WebView getAppView() {
		return this.appView;
	}
}
