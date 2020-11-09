<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="/static/frames/jQuery/jquery-2.2.4.min.js"></script>
    <style>
        div {
            display: flex;
            justify-content: center; /* flex水平居中 */
            /*align-items: center; !* flex垂直居中 *!*/
            text-align: center; /* 文字居中 */
        }
    </style>
    <script>
        $(function () {
            // let url = document.referrer
            let url = location.href
            console.log("pretreat url:" + url)

            let paraStr = url.substring(url.indexOf("?") + 1, url.length)
            let paraArray = paraStr.split("&")
            let paraMap = {}
            for (let para of paraArray) {
                paraMap[para.split("=")[0]] = para.split("=")[1]
            }

            $.ajax({
                type: "post",
                url: "/sonAJAX",
                data: paraMap,
                dataType: "json",
                traditional: true,
                success: function (data) {
                    $(".current").text(data.currentPage)
                    $(".size").text(data.pageSize)
                },
                error: function () {
                }
            })
        })
    </script>
</head>
<body>

<div>
    <table border="1">
        <tr>
            <td>currentPage</td>
            <td>pageSize</td>
            <td>currentPage</td>
            <td>pageSize</td>
            <td>currentPage</td>
            <td>pageSize</td>
            <td>currentPage</td>
            <td>pageSize</td>
            <td>currentPage</td>
            <td>pageSize</td>
            <td>currentPage</td>
            <td>pageSize</td>
        </tr>
        <tr>
            <td class="current"></td>
            <td class="size"></td>
            <td class="current"></td>
            <td class="size"></td>
            <td class="current"></td>
            <td class="size"></td>
            <td class="current"></td>
            <td class="size"></td>
            <td class="current"></td>
            <td class="size"></td>
            <td class="current"></td>
            <td class="size"></td>
        </tr>
    </table>
</div>

</body>
</html>
