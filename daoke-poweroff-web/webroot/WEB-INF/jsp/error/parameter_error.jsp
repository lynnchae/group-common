<%@ page language="java" session="false" contentType="application/octet-stream; charset=utf-8"%>
<%@page import="org.springframework.web.bind.MissingServletRequestParameterException"%>
<%@page import="me.daoke.poweroff.common.model.CommonJsonResult"%>
<%@page import ="me.daoke.poweroff.util.JsonMapper" %>
<%@page import ="me.daoke.poweroff.util.ConstantsUtil" %>
<%
	CommonJsonResult rs = new CommonJsonResult();
    response.setCharacterEncoding("UTF-8");
    response.setContentType("application/json");
	MissingServletRequestParameterException exception=(MissingServletRequestParameterException)request.getAttribute("exception");
    rs.setERRORCODE(ConstantsUtil.ERRORCODE_PARAMETERS_ERROR);
    rs.setRESULT("Required " + exception.getParameterType() + " parameter '" + exception.getParameterName() + "' is not present");
    response.getWriter().write(JsonMapper.toJson(rs,true));

%>



