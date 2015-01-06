<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/main.css"></link>
<link rel="stylesheet" href="../../js/easyui/themes/default/easyui.css"></link>
<link rel="stylesheet" href="../../js/easyui/themes/icon.css"></link>
<script type="text/javascript" src="../../js/jquery.min.js"></script>
<script type="text/javascript" src="../../js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../../js/easyui/locale/easyui-lang-zh_CN.js"></script>
<title>${initParam.systemName}——</title>
</head>
<body>
	<div class="center1000">
		<div class="firstTitle">待审核学生信息列表</div>
		<div class="toolbar">
		
		</div>
		<table class="listBorder" width="100%" cellspacing="0">
			<tr>
				<th width="20"><input type="checkbox" id="checkAll"></th>
				<th width="80">学号</th>
				<th width="140">姓名</th>
				<th  width="30">性别</th>
				<th  width="100">班级</th>
				<th  width="50">年级</th>
				<th width="140">学科分类</th>
				<th width="100">专业</th>
				<th width="60">六级成绩</th>
				<th>操作</th>
			</tr>
			<s:iterator value="stuList.recs" var="stu">
				<tr>
					<td><input type="checkbox" class="checked" value="${stu.loginName}"></td>
					<td>${stu.loginName }</td>
					<td>${stu.uName }</td>
					<td>${stu.sex }</td>
					<td>${stu.className }</td>
					<td>${stu.grade }</td>
					<td>${stu.bigClassName }</td>
					<td>${stu.specialtyName }</td>
					<td>${stu.sixGrade }</td>
					<td>
						<a class="easyui-linkbutton" iconCls="icon-edit" href="student_edit?loginName=${stu.loginName}">详细</a>
						<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" stuid="${stu.loginName }">审核</a>
					</td>
				</tr>
			</s:iterator>
		</table>
		<div class="footbar">
			<div class="easyui-pagination" data-options="
                    total:${stuList.num },
                    pageSize:${rows},
                    pageNumber:${page},
                    onSelectPage:goList,
                    onChangePageSize:goList,
                    layout:['list','sep','first','prev','links','next','last']
                "></div>
		</div>
	</div>
	<script type="text/javascript">
	function goList(page,pgSize){
		  location = "student_checklist?page=" + page + "&rows=" + pgSize;
	  }
	</script>
</body>
</html>