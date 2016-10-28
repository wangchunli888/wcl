<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<meta charset="UTF-8">
<title>学生管理系统</title>
<link rel="stylesheet" type="text/css"
	href="/StudentSys_fw/assets/css/bootstrap.min.css">
	
	<link rel="stylesheet" type="text/css"
	href="/StudentSys_fw/assets/css/bootstrapValidator.css">
<script src="/StudentSys_fw/assets/js/jquery.min.js"></script>
<script src="/StudentSys_fw/assets/js/bootstrap.min.js"></script>
<script src="/StudentSys_fw/assets/js/bootstrapValidator.js"></script>
<script src="/StudentSys_fw/sweetalert/dist/sweetalert.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="/StudentSys_fw/sweetalert/dist/sweetalert.css">
<script type="text/javascript">
	$(document).ready(function() {
		$('tbody tr').click(function(e) {
			e.preventDefault();

			$('tbody tr').removeClass('success');
			$(this).addClass('success');
		});

		$('#btn_cls_del').click(function(e) {
			var name = $('a.active').text();

			swal({
				title : "删除班级",
				text : "你确定删除 " + name + " ？",
				type : "warning",
				showCancelButton : true,
				confirmButtonColor : "#DD6B55",
				confirmButtonText : "是的, 删除它!",
				cancelButtonText : "取消",
				closeOnConfirm : false
			}, function() {

				var action = $('form#frm_cld').attr("action");
				$('form#frm_cld').attr("action", action + "?opt=dele");
				$('form#frm_cld').submit();
			});
		});

		$('#btn_cls_edit').click(function(e) {

			var name = $('a.active').text();
			$('input#cname').val(name);
			var t_name = $('strong#t_name').text();
			$('input#c_tname').val(t_name);
			var action = $('form#frm_cld').attr("action");
			$('form#frm_cld').attr("action", action + "?opt=edit");
		});

		$('#btn_cls_add').click(function(e) {
			$('input#cname').val("");
			$('input#c_tname').val("");
			$('form#frm_cld').attr("action", "ClsOptServlet?opt=add1");
		});

		$('#btn_std_del').click(function(e) {
			var name = $('tr.success').children("#name").text();
			var id = $('tr.success').children("#id").text();
			swal({
				title : "删除学生",
				text : "你确定删除 " + name + " ？",
				type : "warning",
				showCancelButton : true,
				confirmButtonColor : "#DD6B55",
				confirmButtonText : "是的, 删除它!",
				cancelButtonText : "取消",
				closeOnConfirm : false
			}, function() {
				$('input#sid').val(id);
				var action = $('form#frm_std_del').attr("action");
				$('form#frm_std_del').attr("action", "/StudentSys_fw/main/studDel.action");
				$('form#frm_std_del').submit();
			});
		});

		$('#btn_std_add').click(function(e) {
			$('input#studId').val("");
			$('input#sname').val("");
			$('input#scode').val("");
			$('input#ssex_m').removeAttr("checked");
			$('input#ssex_w').removeAttr("checked");
			$('input#ssex_m').attr("checked");
			$('input#ssex_m').click();
			$('input#sbirth').val("");

			$('form#frm_std').attr("action", "/StudentSys_fw/main/studAdd.action");
		});

		$('#btn_std_edit').click(function(e) {

			var code = $('tr.success').children("#code").text();
			var name = $('tr.success').children("#name").text();
			var sex = $('tr.success').children("#sex").text();
			var birth = $('tr.success').children("#birth").text();
			var id = $('tr.success').children("#id").text();
			$('input#sname').val(name);
			$('input#scode').val(code);
			$('input#ssex_m').removeAttr("checked");
			$('input#ssex_w').removeAttr("checked");
			if (sex == '男') {
				$('input#ssex_m').attr("checked", "checked");
				$('input#ssex_m').click();

			} else {
				$('input#ssex_w').attr("checked", "checked");
				$('input#ssex_w').click();
			}
			$('input#sbirth').val(birth);
			$('input#studId').val(id);
			//var action = $('form#frm_std').attr("action");
			$('form#frm_std').attr("action", "/StudentSys_fw/main/studEdit.action");
		});

	});
</script>
<script type="text/javascript">
$(document).ready(function(){
$('#frm_std').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
     fields: {
     scode: {
                group: '.form-group',
                validators: {
                    notEmpty: {
                        message: '学生编号不允许为空!'
                    }
                }
            },
             sname: {
                group: '.form-group',
                validators: {
                    notEmpty: {
                        message: '学生姓名不允许为空!'
                    }
                }
            },
             sbirth: {
             group: '.form-group',
                validators: {
                 notEmpty: {
                        message: '学生的出生日期不允许为空!'
                    },
                    date: {
                        format: 'YYYY-MM-DD',
                        message: '日期格式错误,正确格式为2016-03-01'
                    }
                }
            }
     } 
});
 $('#resetBtn').click(function() {
        $('#frm_std').data('bootstrapValidator').resetForm(true);
    });
});
</script>


</head>
<body>
	<nav class="navbar navbar-default" style="height: 60px">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#"> <img alt="Brand"
				src="/StudentSys_fw/img/logo.png" height="35px" width="200px">
			</a>
		</div>
		<form action="ReturnServlet" method="post">
		<button type="submit" class="btn btn-warning btn-xs navbar-right"
			style="margin-top: 20px; margin-right: 20px;margin-left: 20px">退出</button></form>
		<h5 class="navbar-text navbar-right" style="margin-top: 25px">
			<strong>你好，${sessionScope.user.name}&nbsp;&nbsp;(当前在线人数${onlineNum}人)</strong>
		</h5>
		<button id="btn_cls_add" type="button"
			class="btn btn-info  btn-sm navbar-right" data-toggle="modal"
			data-target="#classModal"
			style="margin-top: 18px;margin-right: 50px;">添加班级</button>
		<button id="btn_cls_edit" type="button"
			class="btn btn-info  btn-sm navbar-right" data-toggle="modal"
			data-target="#classModal"
			style="margin-top: 18px;margin-right: 20px;margin-left: 20px;">编辑班级</button>
		<button id="btn_cls_del" type="button"
			class="btn btn-info  btn-sm navbar-right" style="margin-top: 18px;">删除班级</button>
	</div>
	</nav>
	<div class="container-fluid">
		<div class="row">
			<div class="container col-md-3">
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="list-group">
							<c:forEach items="${nameMap}" var="entry">
								<a id="list-group-item-${entry.key}"
									href="/StudentSys_fw/main/mainAction.action?clsId=${entry.key}"
									class="list-group-item ${entry.key==cls.id?'active':''}">${entry.value}</a>
							</c:forEach>

						</div>
					</div>
				</div>
			</div>
			<div class="container col-md-9">

				<div class="panel panel-default">
					<!-- Default panel contents -->
					<div class="panel-heading">
						<div class="row">
							<div class="col-md-2">
								<h4>
									<strong>${cls.name}</strong>
								</h4>
							</div>
							<div class="col-md-8">
								<h4>班级总共：${count[1]+count[0]}人，其中男生：${count[1]}人，女生：${count[0]}人</h4>
							</div>
							<div class="col-md-2">
								<h4>
									班主任：<strong id="t_name">${cls.t_name}</strong>
								</h4>
							</div>
						</div>
					</div>
					<div class="panel-body">
						<div class="col-md-5">
							<form action="MainServlet" class="form-inline">
								<input id="clsId" name="clsId" type="hidden" value="${cls.id}">
								<div class="form-group">
									<label for="exampleInputName2">姓名</label> <input
										name="findName" type="text" class="form-control"
										id="exampleInputName2" placeholder="输入姓名">
								</div>
								<button type="submit" class="btn btn-default">查询</button>
							</form>
						</div>
						<div class="col-md-7">
							<div class="btn-group pull-right" role="group">
								<div id="btn_std_add" class="btn-group" role="group">
									<button type="button" class="btn btn-default"
										data-toggle="modal" data-target="#studentModal">添加</button>
								</div>
								<div id="btn_std_edit" class="btn-group" role="group">
									<button type="button" class="btn btn-default"
										data-toggle="modal" data-target="#studentModal">修改</button>
								</div>
								<div id="btn_std_del" class="btn-group" role="group">
									<button type="button" class="btn btn-default">删除</button>
								</div>
							</div>
						</div>
					</div>
					<!-- Table -->
					<table class="table">
						<thead>
							<tr>
								<th>#</th>
								<th>学号</th>
								<th>姓名</th>
								<th>性别</th>
								<th>出生日期</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${cls.students}" var="stud" varStatus="s">
								<tr class='${s.index==0 ? "success" : ""}'>
									<th id="id" hidden="hidden">${stud.id}</th>
									<th scope="row">${s.index}</th>
									<td id="code">${stud.code}</td>
									<td id="name">${stud.name}</td>
									<td id="sex">${stud.sex==0?"女":"男"}</td>
									<td id="birth">${stud.birth}</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
              <div style="text-align:right;display:${pageCount==1?'none':''}"> 
				<nav>
				<ul class="pagination">
					<li><a href="MainServlet?pageNum=1&clsId=${cls.id}">&laquo;</a></li>
					<c:forEach begin="1" end="${pageCount}" var="num" varStatus="s">
					   <li class="${pageNum==num?'active':''}"><a href="MainServlet?pageNum=${num}&clsId=${cls.id}">${num}</a></li>
					</c:forEach>					
					<li><a href="MainServlet?pageNum=${pageCount}&clsId=${cls.id}">&raquo;</a></li>
				</ul>
				</nav>
             </div>
			</div>
		</div>
	</div>
	</div>

	<!--班级添加修改弹出框-->
	<div class="modal fade" id="classModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">添加班级信息</h4>
				</div>
				<form id="frm_cld" action="ClsOptServlet" method="post">
					<div class="modal-body">
						<input id="clsId" name="claszId" type="hidden" value="${cls.id}">
						<div class="form-group">
							<label for="recipient-name" class="control-label">班级名称:</label> <input
								name="cname" type="text" class="form-control" id="cname">
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">班主任名称:</label> <input
								name="c_tname" type="text" class="form-control" id="c_tname"></input>
						</div>

					</div>
					<div class="modal-footer">
						<button   type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="submit" class="btn btn-primary">确定</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!--学生删除表单-->
	<form id="frm_std_del" action="/StudentSys_fw/main/studDel.action" method="post" style="display:none">
	<input id="sid" name="stud.id" type="hidden">
	<input id="clsId" name="claszId" type="hidden" value="${cls.id}">
	</form>

	<!--学生添加修改弹出框-->
	<div class="modal fade" id="studentModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">添加学生信息</h4>
				</div>
				<form id="frm_std" action="/StudentSys_fw/main/studAdd.action" method="post">
					<div class="modal-body">
						<input id="studId" name="stud.id" type="hidden">
						<div class="form-group">
							<label for="recipient-name" class="control-label">学生编号:</label> <input
								name="stud.code" type="text" class="form-control" id="scode">
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">学生姓名:</label> <input
								name="stud.name" type="text" class="form-control" id="sname"></input>
						</div>
						<div class="form-group">
							<label for="sex-text" class="control-label">学生性别:</label>
							<div id="sex-text">
								<label class="radio-inline"> <input id="ssex_m"
									name="stud.sex" type="radio" id="ssex" value="1" checked> 男
								</label> <label class="radio-inline"> <input id="ssex_w"
									name="stud.sex" type="radio" id="ssex" value="0"> 女
								</label>
							</div>
							<input id="clsId" name="claszId" type="hidden" value="${cls.id}">
						</div>
						<div class="form-group">
							<label for="birth-text" class="control-label">出生日期:</label> <input
								name="stud.birth" type="text" class="form-control" id="sbirth"></input>
						</div>

					</div>
					<div class="modal-footer">
						<button id="resetBtn" type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="submit" class="btn btn-primary">确定</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
