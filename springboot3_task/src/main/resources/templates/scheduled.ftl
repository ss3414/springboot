<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<table border="1">
    <#list methods as method>
        <tr>
            <td>${method.name}</td>
            <td><a href="/execute?methodName=${method.name}">执行</a></td>
        </tr>
    </#list>
</table>

</body>
</html>
