<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<table border="1">
    <tr>
        <td><a href="/swagger-ui.html">swagger2</a></td>
    </tr>
    <tr>
        <td><a href="/xdoc/index.html">xDoc</a></td>
    </tr>
    <tr>
        <td><a href="/user/">获取列表</a></td>
    </tr>
    <tr>
        <td><a href="/user/1">获取单个</a></td>
    </tr>
</table>

<form action="/user/" method="post">
    <table border="1">
        <tr>
            <td>name</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td>password</td>
            <td><input type="text" name="password"></td>
        </tr>
        <tr>
            <td><input type="submit" value="新增"></td>
        </tr>
    </table>
</form>

</body>
</html>
