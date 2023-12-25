<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<form action="/create" method="post">
    <table border="1">
        <tr>
            <td><input type="text" name="jobName" value="test"></td>
            <td><input type="text" name="cronExpression" value="*/30 * * * * ?"></td><#-- Cron表达式末尾必须是? -->
        </tr>
        <tr>
            <td><input type="submit" value="新建"></td>
        </tr>
    </table>
</form>
<form action="/update" method="post">
    <table border="1">
        <tr>
            <td><input type="text" name="jobName" value="test"></td>
            <td><input type="text" name="cronExpression" value="*/45 * * * * ?"></td>
        </tr>
        <tr>
            <td><input type="submit" value="修改"></td>
        </tr>
    </table>
</form>
<table border="1">
    <tr>
        <td><a href="/trigger?jobName=test">立即执行</a></td>
    </tr>
    <tr>
        <td><a href="/pause?jobName=test">暂停</a></td>
    </tr>
    <tr>
        <td><a href="/resume?jobName=test">恢复</a></td>
    </tr>
    <tr>
        <td><a href="/delete?jobName=test">删除</a></td>
    </tr>
    <tr>
        <td><a href="/listJob">listJob</a></td>
    </tr>
    <tr>
        <td><a href="/listTrigger">listTrigger</a></td>
    </tr>
    <tr>
        <td><a href="/listCron">listCron</a></td>
    </tr>
    <tr>
        <td><a href="/listFired">listFired</a></td>
    </tr>
    <tr>
        <td><a href="/listScheduled">listScheduled</a></td>
    </tr>
</table>

</body>
</html>
