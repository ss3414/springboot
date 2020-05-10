<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="/static/frames/jQuery/jquery-2.2.4.min.js"></script>
</head>
<body>

<#--
①浏览器直接输入127.0.0.1/static/frames/PDFJS-1.1.159/web/viewer.html（不拦截静态资源），访问的是/PDFJS-1.1.159/web/viewer.js中的DEFAULT_URL（默认PDF）
②输入127.0.0.1/static/frames/PDFJS-1.1.159/web/viewer.html?file=/displayPDF（file可以是任何字符串），访问的是displayPDF方法读取的PDF
-->
<#--<iframe src="/static/frames/PDFJS-1.1.159/web/viewer.html?file=/displayPDF" width="800px" height="500px">-->
<#--</iframe>-->
<iframe id="displayIframe" width="800px" height="500px">
</iframe>

<script>
    /* 借助JS encodeURIComponent传参 */
    $(function () {
        $("#displayIframe").attr("src", "/static/frames/PDFJS-1.1.159/web/viewer.html?file=" + encodeURIComponent("/displayPDF?id=1"))
    })
</script>

</body>
</html>
