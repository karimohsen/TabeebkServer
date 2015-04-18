<%-- 
    Document   : login
    Created on : Apr 6, 2015, 7:41:33 PM
    Author     : HMA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/stylelogin.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <section class="main">
            <form class="form-4" action="LoginControler" method="POST">                    
                <h1>Login</h1>
                <table width="300px" cellspacing="0">
                    <tr>
                        <td>
                            <p>
                                <input type="text" name="username" id="username" placeholder="hany24435" required>
                            </p>
                        </td>
                        <td><div id="status"></div></td>
                    </tr>
                    <tr>
                        <td>
                            <p>
                                <input type="password" name='password' placeholder="Password" required> 
                            </p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p>
                                <input type="submit" name="submit" value="Continue">
                            </p>
                        </td>
                    </tr>
                </table>
            </form>
        </section>
    </div>
</body>
</html>