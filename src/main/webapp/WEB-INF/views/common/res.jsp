<%--
  Created by IntelliJ IDEA.
  User: xiaojianyu
  Date: 2018/4/18
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<fmt:setBundle basename="resources.messages" var="messagesBundle"/>
<c:set var="baseurl" value="${pageContext.request.contextPath}/"></c:set>
<link rel="stylesheet" href="${baseurl}static/plugins/layui/css/layui.css" media="all">
<link rel="stylesheet" href="${baseurl}static/plugins/font-awesome/css/font-awesome.min.css" media="all">
<link rel="stylesheet" href="${baseurl}static/css/app.css" media="all">
<link href="${baseurl}static/css/sweetalert.css" rel="stylesheet" type="text/css" />
<script src="${baseurl}static/plugins/layui/layui.js"></script>
<script type="text/javascript" src="${baseurl}static/js/sweetalert.min.js"></script>
<script type="text/javascript" src="${baseurl}static/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${baseurl}static/js/jquery.form.js"></script>
<script type="text/javascript" src="${baseurl}static/js/common.js"></script>
