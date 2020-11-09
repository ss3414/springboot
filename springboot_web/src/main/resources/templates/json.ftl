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
        <td><a href="javascript:void(0)" onclick="test()">JS发送JSON</a></td>
    </tr>
    <tr>
        <td><a href="javascript:void(0)" onclick="test2()">模板引擎JSON</a></td>
    </tr>
</table>

<script>
    function test() {
        let list = []
        for (let i = 0; i < 2; i++) {
            let j = {}
            j.name = "姓名" + (i + 1)
            j.pwd = "pwd" + (i + 1)
            list.push(j)
        }
        let data = {
            "status": 1001,
            "list": list
        }

        $.ajax({
            type: "post",
            url: "/requestJSON",
            data: {
                json1: JSON.stringify(data),
                json2: JSON.stringify(list),
            },
            dataType: "json", /* 返回值类型 */
            traditional: true, /* 阻止深度序列化，Java接收AJAX数组参数 */
            success: function (data) {
                console.log(data)
            },
            error: function () {
            }
        })
    }

    function test2() {
        let data = ${jsonStr}
            console.log(data.status)
    }
</script>

</body>
</html>
