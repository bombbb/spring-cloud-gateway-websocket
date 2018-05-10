# Reproducing https://github.com/spring-cloud/spring-cloud-gateway/issues/260

Execute the application and inside a web browser's console write the following:

```
var ws = new WebSocket("ws://localhost:8080/ws2");
ws.close;
```
The above code snippet will work as expected immediately closing the websocket connection. The code below (which is proxied by the gateway) will make the WebSocket connection hang in the pending state.

```
var ws = new WebSocket("ws://localhost:8080/ws");
ws.close;
```
