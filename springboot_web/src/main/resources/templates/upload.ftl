<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="/static/frames/jQuery/jquery-2.2.4.min.js"></script>
    <link rel="stylesheet" href="/static/frames/LayUI-2.5.4/css/layui.css">
    <script src="/static/frames/LayUI-2.5.4/layui.js"></script>
</head>
<body>

<#--************************************************************分割线************************************************************-->
<#-- todo 表单上传 -->

<#--<style>-->
<#--    div {-->
<#--        border-style: double;-->
<#--        width: 500px;-->
<#--    }-->
<#--</style>-->

<#--<div>-->
<#--    <form action="/singleUpload" enctype="multipart/form-data" method="post">-->
<#--        <input type="file" name="uploadFile"/>-->
<#--        <input type="submit" value="单个上传"/>-->
<#--    </form>-->
<#--</div>-->

<#--<div>-->
<#--    <form action="/batchUpload" enctype="multipart/form-data" method="post">-->
<#--        <input type="file" name="uploadFiles" multiple="multiple"/>-->
<#--        <input type="submit" value="批量上传"/>-->
<#--    </form>-->
<#--</div>-->

<#--************************************************************分割线************************************************************-->
<#-- fixme AJAX批量上传 -->

<#--<div>-->
<#--    <form id="uploadForm" enctype="multipart/form-data">-->
<#--        <input type="file" name="uploadFiles" multiple="multiple"/>-->
<#--        <a href="javascript:void(0)" onclick="AJAXUpload()">AJAX批量上传</a>-->
<#--    </form>-->
<#--</div>-->

<#--<script>-->
<#--    function AJAXUpload() {-->
<#--        console.log($("#uploadForm")[0])-->
<#--        let formData = new FormData($("#uploadForm"))-->
<#--        $.ajax({-->
<#--            type: "post",-->
<#--            url: "/batchUpload",-->
<#--            data: formData,-->
<#--            contentType: false,-->
<#--            processData: false,-->
<#--            success: function (data) {-->
<#--                console.log(data)-->
<#--            },-->
<#--            error: function () {-->
<#--            }-->
<#--        })-->
<#--    }-->
<#--</script>-->

<#--************************************************************分割线************************************************************-->
<#-- todo LayUI上传 -->

<a id="upload">上传</a>

<script>
    layui.use(["upload"], function () {
        let upload = layui.upload

        upload.render({
            elem: "#upload",
            url: "/singleUpload",
            exts: "jpg|pdf", /* 允许上传的文件类型 */
            field: "uploadFile", /* 自定义上传文件名（对应MultipartFile文件名） */
            done: function (res) {
                console.log(res)
            },
            error: function () {
            }
        })
    })
</script>

<#--************************************************************分割线************************************************************-->
<#-- todo 下载 -->

<#--<script>-->
<#--    function test() {-->
<#--        window.open("/download?CN=idea使用教程2017-06-01.pdf", "newwindow", "alwaysRaised=yes") /* alwaysRaised=yes新窗口下载且不关闭 */-->
<#--    }-->
<#--</script>-->

<#--<a href="/download?CN=idea使用教程2017-06-01.pdf">下载（当前页）</a>-->
<#--<a href="javascript:void(0)" onclick="test()">下载（新窗口）</a>-->

</body>
</html>
