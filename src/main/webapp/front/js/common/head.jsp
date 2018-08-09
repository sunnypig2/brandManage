<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/7/30
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="brand" />
<!-- 下面是easyui的环境 -->
<link rel="stylesheet" href="${brand}/front/jquery-easyui-1.5.4.4/themes/icon.css" type="text/css" />
<link rel="stylesheet" href="${brand}/front/jquery-easyui-1.5.4.4/themes/default/easyui.css" type="text/css" />
<script type="text/javascript" src="${brand}/front/jquery-easyui-1.5.4.4/jquery.min.js"></script>
<script type="text/javascript" src="${brand}/front/jquery-easyui-1.5.4.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${brand}/front/jquery-easyui-1.5.4.4/locale/easyui-lang-zh_CN.js"></script>