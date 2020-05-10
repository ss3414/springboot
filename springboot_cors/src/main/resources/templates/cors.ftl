<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="/static/frames/jQuery/jquery-2.2.4.min.js"></script>
</head>
<body>

<table border="1">
    <tr>
        <td><a href="javascript:void(0)" onclick="test()">服务端跨域</a></td>
    </tr>
    <tr>
        <td><a href="javascript:void(0)" onclick="test2()">使用jsonp</a></td>
    </tr>
</table>

<script>
    /*
    * 产生跨域错误的条件
    * ①必须是浏览器发送的请求
    * ②必须是XMLHttpRequest请求
    * ③跨域（协议/域名/端口任何一个不同即为跨域）
    * */

    /*
    * 服务端允许跨域
    * ①准备工作：hosts映射（127.0.0.1 www.test.com）+WebMvcConfig跨域配置/@CrossOrigin注解
    * ②如果服务端不允许跨域，浏览器会报跨域错误，但是请求执行成功（无法获取返回值）
    * */
    function test() {
        $.ajax({
            type: "get",
            url: "http://www.test.com/get",
            data: {},
            dataType: "json",
            success: function (data) {
                console.log(data)
            },
            error: function () {
            }
        })
    }

    /*
    * 客户端跨域
    * ①准备工作：注释WebMvcConfig跨域配置/@CrossOrigin注解
    * ②使用jsonp（浏览器警告，无返回值）
    * ③反向代理
    * ④带Cookie的跨域请求
    * ⑤自定义Header的跨域请求
    * */
    function test2() {
        $.ajax({
            type: "get",
            url: "http://www.test.com/get",
            data: {},
            dataType: "jsonp",
            success: function (data) {
                console.log(data)
            },
            error: function () {
            }
        })
    }
</script>

</body>
</html>
