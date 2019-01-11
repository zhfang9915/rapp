<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>请求未授权</title>
    <meta name="keywords" content="401">
    <meta name="description" content="401">

    <link href="${rapp.nginx }css/bootstrap.min.css" rel="stylesheet">
    <link href="${rapp.nginx }css/style.min862f.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
	<%@include file="../common/header.jsp"%>
    <div class="middle-box text-center animated fadeInDown minheight">
        <h1>401</h1>
        <h3 class="font-bold">您没有权限执行此操作！</h3>

        <div class="error-desc">
            	抱歉，您的权限不足，无法执行此类操作
        </div>
    </div>
    <script src="${rapp.nginx }js/jquery.min.js"></script>
    <script src="${rapp.nginx }js/bootstrap.min.js"></script>
    <%@include file="../common/footer.jsp"%>
</body>
</html>
