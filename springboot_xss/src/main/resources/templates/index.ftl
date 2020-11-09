<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<form action="/form" method="post">
    <table border="1">
        <tr>
            XSS测试
        </tr>
        <tr>
            <td>id</td>
            <td><input type="text" name="id" value="1"></td>
        </tr>
        <tr>
            <td>name</td>
            <td><input type="text" name="name" value="user"></td>
        </tr>
        <tr>
            <td>password</td>
            <td><input type="text" name="password" value="<style>td {color: red;}</style>"></td>
        </tr>
        <tr>
            <td><input type="submit" value="提交"></td>
        </tr>
    </table>
</form>

<table border="1">
    <tr>
        <td>单个参数</td>
        <td><a href="/para?test=1">para</a></td>
    </tr>
    <tr>
        <td>多个参数</td>
        <td><a href="/para2?test1=1&test2=2">para2</a></td>
    </tr>
</table>

</body>
</html>
