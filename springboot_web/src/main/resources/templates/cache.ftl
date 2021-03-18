<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="/static/frames/jQuery/jquery-2.2.4.min.js"></script>
</head>
<body>

<style>
    img {
        width: 30%;
        height: 30%;
    }
</style>

<#-- 同一张图片只会请求一次 -->
<img src="/static/img/test.jpg">
<img src="/static/img/test.jpg">

</body>
</html>
