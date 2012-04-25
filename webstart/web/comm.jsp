<%-- 
    Document   : comm
    Created on : 2012-4-15, 1:52:15
    Author     : wuwj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <object
            classid = "clsid:CAFEEFAC-0016-0000-0030-ABCDEFFEDCBA"
            codebase = "http://10.188.182.24:8080/webstart/jre/jre-6u30-windows-i586.exe"
            NAME = "appletTest" >
            <PARAM NAME = CODE VALUE = "com.saber.comm.Comm" >
            <PARAM NAME = CODEBASE VALUE = "/webstart/jar/applet">
            <PARAM NAME = ARCHIVE VALUE = "comm.jar, applet.jar" >
            <PARAM NAME = NAME VALUE = "appletTest" >
            <param name = "type" value = "application/x-java-applet;jpi-version=1.6.0_30">
            <param name = "scriptable" value = "false">

            <comment>
                <embed
                    type = "application/x-java-applet;jpi-version=1.6.0_30" \
                    CODE = "com.saber.comm.Comm" \
                    CODEBASE = "/webstart/jar/applet" \
                    ARCHIVE = "comm.jar, applet.jar" \
                    NAME = "appletTest"
                    scriptable = false
                    pluginspage = "http://java.sun.com/products/plugin/index.html#download">
                    <noembed>

                </noembed>
                </embed>
            </comment>
        </object>
    </body>
</html>
