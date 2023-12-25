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

<style>
    div {
        border-style: double;
        width: 500px;
    }
</style>

<div>
    <form action="/singleUpload" enctype="multipart/form-data" method="post">
        <input type="text" name="name"/>
        <input type="file" name="uploadFile"/>
        <input type="submit" value="单个上传"/>
    </form>
</div>

<#--<div>-->
<#--    <form action="/batchUpload" enctype="multipart/form-data" method="post">-->
<#--        <input type="file" multiple="multiple" name="uploadFiles"/>-->
<#--        <input type="submit" value="批量上传"/>-->
<#--    </form>-->
<#--</div>-->

<#--************************************************************分割线************************************************************-->
<#-- todo AJAX上传 -->

<#--<div>-->
<#--    <form id="uploadForm">-->
<#--        <input type="text" name="name"/>-->
<#--        <input type="file" name="uploadFiles" multiple="multiple"/>-->
<#--        <a href="javascript:void(0)" onclick="AJAXUpload()">AJAX上传</a>-->
<#--    </form>-->
<#--</div>-->

<#--<script>-->
<#--    function AJAXUpload() {-->
<#--        let input = $('<input>').attr("type", "hidden").attr("name", "name").val("456")-->
<#--        $("#uploadForm").append(input)-->
<#--        let formData = new FormData($("#uploadForm")[0])-->
<#--        // console.log($("#uploadForm")[0])-->
<#--        // console.log(formData)-->
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
<#-- todo 下载 -->

<#--<script>-->
<#--    function test() {-->
<#--        // window.open("/download?CN=test.pdf", "newwindow", "alwaysRaised=no") /* alwaysRaised=yes新窗口下载且不关闭 */-->
<#--        window.open("/download?CN=test.pdf")-->
<#--    }-->
<#--</script>-->

<#--<a href="/download?CN=表格.xlsx">表格.xlsx</a>-->
<#--<a href="/download2?CN=表格2.xlsx">表格2.xlsx</a>-->
<#--<a href="/download3?CN=test.pdf">test.pdf</a>-->
<#--<a href="javascript:void(0)" onclick="test()">下载（新窗口）</a>-->

</body>
</html>
