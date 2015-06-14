<%-- 
    Document   : login
    Created on : Apr 6, 2015, 7:41:33 PM
    Author     : HMA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% response.setHeader("Pragma","no-cache");%> 
<% response.setHeader("Cache-Control","no-store");%> 
<% response.setDateHeader("Expires",-1);%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE"> 
        <META HTTP-EQUIV="Expires" CONTENT="-1">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/stylelogin.css" />
        <title>JSP Page</title>
        <!--        <script type="text/javascript">
                    var authenticated = "<%= (String) session.getAttribute("authenticated")%>";
                    if (authenticated === "true"){
                        window.location.href = "MSP/Home.jsp";
                    }
                </script>-->
    </head>
    <body>
        <c:if test="${authenticated == 'true'}">
            <c:redirect url="MSP/Home.jsp"/>
        </c:if>
        <section class="main">
            <form class="form-4" action="LoginControler" method="POST">                    
                <h1>Login</h1>
                <table width="300px" cellspacing="0">
                    <tr>
                        <td>
                            <p style="color: red">
                                ${ErrorMessage}
                            </p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p>
                                <input type="text" name="username" id="username" placeholder="User Name" required>
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