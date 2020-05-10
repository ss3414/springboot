<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="/static/frames/jQuery/jquery-2.2.4.min.js"></script>
</head>
<body>

<script>
    $(function () {
        let data = ""
        $.ajax({
            type: "post",
            url: "/responseJSON",
            data: {},
            dataType: "json",
            async: false, /* 设置同步 */
            success: function (response) {
                console.log(response)
                data = response
            },
            error: function () {
            }
        })
        console.log(data)
    })
</script>
</body>
</html>
