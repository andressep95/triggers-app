<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<div id="js-container"></div>
<br/>
<!---<a href="helloServlet">Hello Servlet</a> -->
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<script>
    (function ($) {
        var url = '/helloServlet';

        $.ajax({
            url: url,
            success: function (response) {
                console.log(response);

            }
        })
    })(jQuery);
</script>
</body>
</html>