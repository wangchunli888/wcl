<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>黑名单</title>
	<link rel="stylesheet" type="text/css" href="/StudentSys/assets/css/bootstrap.min.css">
	<script src="/StudentSys/assets/js/jquery.min.js"></script>
    <script src="/StudentSys/assets/js/bootstrap.min.js"></script>
    <script type="text/javascript" >
       $(document).ready(function () {
       	    $('tbody tr').click(function (e) {
               e.preventDefault();
              
               $('tbody tr').removeClass('active');
               $(this).addClass('active');
               var bname=$(this).children("#bname").text();
               $('input#bname').val(bname);
           });
           
           $('#btn_in').click(function(e){
            $('#frm_opt').attr('action','BlackOptServlet?opt=0');
            
           });
           
            $('#btn_out').click(function(e){
            $('#frm_opt').attr('action','BlackOptServlet?opt=1');
           });
           
            $('#btn_upload').click(function(e){
            $('#img_file').click();
           });
           
            $('#img_file').change(function(e){
            $('#frm_upload').submit();
           });
       });
    </script>
</head>
<body>
    <div class="row">
       <div class="col-md-7" style="text-align: right;"><h1>用户黑名单管理</h1></div>
       <div class="col-md-4" style="text-align: right;">
       <form id="frm_upload" action="UploadServlet" method="post" enctype="multipart/form-data">
       	  <input name="img_file" id="img_file" type="file" style="display: none;"></input>
       	  <button id="btn_upload" type="button" class="btn" style="width: 60px; height: 60px; -moz-border-radius: 50%; -webkit-border-radius: 50%; border-radius: 50%;background-image:url(/StudentSys/head_img/header.jpg);background-position:center; background-size: 60px 60px;margin-top: 20px; border-color:#000; "/>
       </form>
       </div>
    </div>

      <div class="panel-heading" style="text-align: center;">
      	<form id="frm_opt" action="BlackOPtServlet" method="post" class="form-inline" role="form">
          <div class="form-group">
             <label class="sr-only" for="exampleInputEmail2">用户名称</label>
             <input name="bname" id="bname" type="text" class="form-control"  placeholder="输入黑名单的用户名称">
          </div>
          <button id="btn_in" type="submit" class="btn btn-danger">列入黑名单</button>
           <button id="btn_out" type="submit" class="btn btn-success">解除黑名单</button>
           <button id="btn" type="submit" class="btn btn-success">访问页面</button>
        </form>
      </div>
      <div class="panel-body">
        <table class="table table-hover">
	      <thead>
	        <tr>
	          <th>#</th>
	          <th>用户名称</th>
	          <th>列入日期</th>
	          <th>状态</th>
	        </tr>
	      </thead>
	      <tbody>
	        <c:forEach items="${blackList}" var="black" varStatus="s">
	      <tr>
	      <td>${s.index}</td>
	      <td id="bname">${black.userName}</td>
	      <td>${black.formatDate}</td>
	      <td id="rem"><span
					class="label label-${black.removed==0?'success':'danger'}">
					${black.removed==0?"解除":"列入"}</span></td>

	  </tr>
	  </c:forEach>
	      </tbody>
	     </table>
      </div>
    </div>
</body>
</html>