<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试</title>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
</head>
<body>
    <button id="btn01">Send text</button>
</body>

<script type="text/javascript" src="resources/jquery/jquery-2.1.1.min.js"></script>

<script>
    $(function () {
        $("#btn01").click(function (){
            $.ajax({
                url: "send/array1.do",
                type: "post",
                data: {
                    "array":[5,8,12]
                },
                dataType: "text",
                success: function (response){
                    console.log(response);
                },
                error: function (response){
                    console.log(response);
                }
            });
        });
    });
</script>
</html>
