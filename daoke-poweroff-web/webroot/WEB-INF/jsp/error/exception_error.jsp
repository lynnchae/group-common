<%@ page language="java" session="false" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.slf4j.Logger" %>
<%@ page import="org.slf4j.LoggerFactory" %>
<%@ page import="me.daoke.poweroff.controller.PowerroffController" %>

<%

    Logger log = LoggerFactory.getLogger(PowerroffController.class);
    try {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        if (request.getAttribute("exception") instanceof Exception) {
            Exception exception = (Exception) request.getAttribute("exception");
            if (exception != null) {
                log.error("message", exception.getMessage());
                log.error("error",exception);
            }

        }

    }catch (Exception e){
        e.printStackTrace();
    }finally {

    }
%>