<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="/static/frames/jQuery/jquery-2.2.4.min.js"></script>
    <script>
        function parentJump(href) {
            parent.location.href = href /* 父页面当前页面跳转 */
        }

        /* AJAX */
        $(function () {
            $.ajax({
                type: "post",
                url: "/sonAJAX",
                traditional: true,
                data: {
                    currentPage: ${currentPage},
                    pageSize: ${pageSize},
                },
                dataType: "json",
                success: function (data) {
                    console.log(data.currentPage)
                    console.log(data.pageSize)
                },
                error: function () {
                }
            })
        })
    </script>
</head>
<body>

<table border="1">
    <tr>
        <td>currentPage</td>
        <td>${currentPage}</td>
    </tr>
    <tr>
        <td>pageSize</td>
        <td>${pageSize}</td>
    </tr>
    <tr>
        <td>父页面跳转</td>
        <#--<td><a href="javascript:void(0)" onclick="parentJump('http://bbs.wuyou.net')">无忧论坛</a></td>-->
        <td><a href="http://bbs.wuyou.net" target="_blank">无忧论坛</a></td><#-- 父页面新页面跳转 -->
    </tr>
</table>

</body>
</html>
