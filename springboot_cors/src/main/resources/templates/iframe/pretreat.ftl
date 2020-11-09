<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="/static/frames/jQuery/jquery-2.2.4.min.js"></script>
    <script>
        $(function () {
            // let url = document.referrer
            let url = location.href
            console.log("pretreat url:" + url)

            let para = url.substring(url.indexOf("?") + 1, url.length)
            console.log("pretreat para:" + para)

            let href = location.protocol + "//" + location.host + "/son?" + para
            console.log("pretreat href:" + href)

            if (url.indexOf("/father") != -1) {
                window.location.href = href
            }
        })
    </script>
</head>
<body>

</body>
</html>
