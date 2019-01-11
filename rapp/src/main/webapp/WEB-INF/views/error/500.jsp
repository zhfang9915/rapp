<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>服务器内部错误</title>
    <meta name="keywords" content="500">
    <meta name="description" content="500">

    <link href="${rapp.nginx }css/bootstrap.min.css" rel="stylesheet">
    <link href="${rapp.nginx }css/style.min862f.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
	<%@include file="../common/header.jsp"%>
    <div class="middle-box text-center animated fadeInDown minheight">
        <h1>500</h1>
        <h3 class="font-bold">出错啦</h3>

        <div class="error-desc">
            	服务器内部发生错误，请联系管理员
        </div>
    </div>
    <script src="${rapp.nginx }js/jquery.min.js"></script>
    <script src="${rapp.nginx }js/bootstrap.min.js"></script>
    <%@include file="../common/footer.jsp"%>
</body>
</html>
