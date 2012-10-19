(function($) {
	
var ws;	// WebSocket instance and close in this module

$.fn.handleMessage = function () {
	
	 var content = this;
	 var count = 0;
	 var room = $("#room");

     ws.onmessage = function (evt) 
     { 
   		var json = JSON.parse(evt.data);

   		content.fadeOut("slow");
   		if (json.type === 'gold') {
   			count++;
   			content.html('<div id="reply">' + json.data.timestamp + '<br />bid: ' + json.data.bid + '<br />ask: ' + json.data.ask + '</div>');
   		}     
   		content.fadeIn("slow");   	
   		
   		if (json.data.message != ".") {
   			room.prepend('<div id="room">' + json.data.message + '</div>');
     	}
   	 }; 	
   	 
	 ws.connect();	
};	
	
	
$.fn.createWebSocket = function () {

  var content = this;
  	
  if ("WebSocket" in window)
  {
     // Let us open a web socket
	 ws = new WebSocket("ws://svn.moko365.com:8080/", "echo-protocol");
	 
     ws.onopen = function(evt)
     {
       content.html("<p>Websocket connected.</p>");
     };
     ws.onclose = function(evt)
     {
        content.html("Websocket connected.");
     };    
  }
  else
  {
     // The browser doesn't support WebSocket
		ws = new WebSocketImpl("ws://svn.moko365.com:8080/", "echo-protocol");

		ws.onopen = function(evt)
		{
			content.html("<p>Websocket connected.</p>");
		};

		ws.onclose = function(evt)
		{
			content.html("<p>Websocket closed.</p>");
		};    

		ws.onerror = function(evt)
		{
			content.html("<p>Websocket error.</p>");
		};   
  }

};

}) ($);
