<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="/static/frames/jQuery/jquery-2.2.4.min.js"></script>
</head>
<body>

<form id="form" method="post">
    <table>
        <tr>
            <td>name</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td>pwd</td>
            <td><input type="text" name="pwd"></td>
        </tr>
        <tr>
            <td>pwd</td>
            <td><input type="text" name="pwd"></td>
        </tr>
        <tr>
            <td><input type="button" value="test" onclick="test()"></td>
            <td><input type="button" value="test2" onclick="test2()"></td>
        </tr>
    </table>
</form>

<script>
    // $(function () {
    //     let data = ""
    //     $.ajax({
    //         type: "post",
    //         url: "/responseJSON",
    //         data: {},
    //         dataType: "json",
    //         async: false, /* 设置同步 */
    //         success: function (response) {
    //             console.log(response)
    //             data = response
    //         },
    //         error: function () {
    //         }
    //     })
    //     console.log(data)
    // })

    function test() {
        let param = $("#form").serialize()
        console.log(param)

        let pwdList = ""
        for (let i of $("input[name='pwd']")) {
            let name = $(i).val()
            pwdList += (name + ",")
        }
        pwdList = pwdList.replace(/,$/, "")
        param = param + "&pwdList=" + pwdList /* AJAX发送List */
        console.log(param)

        $.ajax({
            type: "post",
            url: "/form",
            data: param,
            dataType: "json",
            success: function (response) {
                console.log(response)
            }
        })
    }

    function test2() {
        let formList = [
            {
                "name": "name1",
                "pwdList": ["pwd1", "pwd2"]
            },
            {
                "name": "name1",
                "pwdList": ["pwd1", "pwd2"]
            }
        ]
        $.ajax({
            type: "post",
            url: "/formList",
            data: {
                formList: formList
            },
            dataType: "json",
            success: function (response) {
            }
        })
    }
</script>
</body>
</html>
