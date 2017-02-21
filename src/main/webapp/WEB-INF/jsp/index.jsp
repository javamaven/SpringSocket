<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>  
<html>  
<head>  
<meta charset="UTF-8">  
<title>Insert title here</title>  
  
  
  
<script type="text/javascript">  
        function setConnected(connected) {  
            document.getElementById('connect').disabled = connected;  
            document.getElementById('disconnect').disabled = !connected;  
            document.getElementById('response').innerHTML = '';  
        }  
  
        function connect() {  
            if ('WebSocket' in window){  
                  console.log('Websocket supported');
                  socket = new WebSocket('ws://169.254.57.131:8080/SpringSocket/websocket?username=123456');
  
                  console.log('Connection attempted');  
  
                  socket.onopen = function(){  
                       console.log('Connection open!');  
                       setConnected(true);  
                    }  
  
                  socket.onclose = function(){  
                      console.log('Disconnecting connection');  
                  }  
  
                  socket.onmessage = function (evt)   
                     {   
                        var received_msg = evt.data;  
                        console.log(received_msg);  
                        console.log('message received!');  
                        showMessage(received_msg);  
                     }  
  
                } else {  
                  console.log('Websocket not supported');  
                }  
        }  
  
        function disconnect() {  
            setConnected(false);  
            console.log("Disconnected");  
        }  
  
        function sendName() {  
            var message = document.getElementById('message').value;  
            socket.send(JSON.stringify({ 'message1': message }));  
        }  
  
        function showMessage(message) {  
            var response = document.getElementById('response');  
            var p = document.createElement('p');  
            p.style.wordWrap = 'break-word';  
            p.appendChild(document.createTextNode(message));  
            response.appendChild(p);  
        }  
  
/*   
1. new WebSocket('ws://localhost:8080//websocket')尝试与服务器建立连接;  
2. 握手成功并建立连接后，socket.onopen被调用  
3. 当接收来自服务器的消息，socket.onmessage被调用  
4. socket.send()用来发送消息至服务端  
*/  
</script>  
</head>  
<body>  
  
<button id="connect" onclick="connect()">connect</button>  
<button id="disconnect" onclick="disconnect()"/>disconnect</button><br>  
<input id="message" value="send message"/>  
<button onclick="sendName()">发送消息</button>  
<div id="response"></div>  
</body>  
</html>  
