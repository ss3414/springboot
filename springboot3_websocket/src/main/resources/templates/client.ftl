<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<table border="1">
    <tr>
        <td><a href="javascript:void(0)" onclick="connect()">客户端连接</a></td>
    </tr>
    <tr>
        <td><input id="msg"></td>
        <td><a href="javascript:void(0)" onclick="msg()">发送消息</a></td>
    </tr>
</table>

<script>
    let websocket = null
    let url = "ws://" + location.host + ":" + location.port + "/websocket1"

    function connect() {
        if ("WebSocket" in window) {
            websocket = new WebSocket(url)
        } else if ("MozWebSocket" in window) {
            websocket = new WebSocket(url)
        } else {
            console.log("该浏览器不支持WebSocket")
        }

        websocket.onopen = function () {
            console.log("客户端连接成功")
        }
        websocket.onclose = function () {
            console.log("客户端连接中断")
        }
        websocket.onmessage = function (evt) {
            console.log("服务端:" + evt.data)
        }
    }

    function msg() {
        let msg = document.getElementById("msg").value
        console.log("msg:" + msg)
        websocket.send(msg)
    }
</script>

</body>
</html>
