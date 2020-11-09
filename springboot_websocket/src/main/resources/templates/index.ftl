<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<table border="1">
    <tr>
        <td><a href="javascript:void(0)" onclick="test('websocket1')">Java EE原生WebSocket</a></td>
    </tr>
    <tr>
        <td><a href="javascript:void(0)" onclick="test('websocket2')">spring-websocket</a></td>
    </tr>
    <tr>
        <td><a href="/client">客户端</a></td>
    </tr>
    <tr>
        <td><a href="/server">服务端</a></td>
    </tr>
</table>

<script>
    function test(url) {
        url = "ws://" + location.host + "/" + url
        let websocket = new WebSocket(url)
        websocket.onopen = function () {
            console.log("websocket onopen")
        }
    }
</script>

</body>
</html>
