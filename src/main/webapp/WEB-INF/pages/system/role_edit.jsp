<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<div class="container">
	<!-- 页面导航 -->
	<div class="page-header">
		<div class="page-title" style="padding-bottom: 5px">
			<ol class="breadcrumb">
				<li class="active">添加角色</li>
			</ol>
		</div>
	</div>
	 <div class="row col-md-12">
			<form class="form-horizontal" id="editRoleForm">
				<div class="form-group">
					<div class="col-sm-2 control-label">角色名称</div>
					<div class="col-sm-10">
						<input type="text" id="roleName" placeholder="角色名称" class="form-control required">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">备注</div>
					<div class="col-sm-10">
						<input type="text" id="note" placeholder="备注" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label">授权</div>
					<div class="col-sm-10">
						<ul id="menuTree" class="ztree"></ul>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 control-label"></div>
					<input type="button"  class="btn btn-primary btn_save">
					&nbsp;&nbsp;
					<input type="button" value="返回" class="btn btn-warning btn_return">
				</div>
			</form>
		</div>
</div>
<script type="text/javascript" src="${basePath}/ttms/system/role_edit.js"></script>