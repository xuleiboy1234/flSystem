<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/main.css"></link>
<link rel="stylesheet" href="../js/easyui/themes/default/easyui.css"></link>
<link rel="stylesheet" href="../js/easyui/themes/icon.css"></link>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	function save(){
		$("form").submit();
	}
	$(function(){
		$("form").form({
			success:function(data){
				var d = eval("(" + data + ")");
				if(d.success){
					$.messager.alert("信息","保存成功...","info");
				}
				else{
					$.messager.alert("服务器端错误",d.errMsg,"error");
				}
			}		
		});
	});
</script>
<title>${initParam.systemName}——</title>
</head>
<body>
		<div style="font-size: 20px;font-weight: bold;line-height: 30px;">
			<img src="../images/head.png" width="32"/>个人信息
		</div>
		<div class="easyui-tabs" fit="true" tabHeight="40">
			<div title="<span class='tabHead'>基本信息</span>" class="tab">
				<form action="stu_persionSave">
					<table width="100%" border="2" class="detail">
						<caption>个人基本信息</caption>
						<tr>
							<th width="100">学号：</th>
							<td>${self_Rec.loginName}</td>
							<th width="100">姓名：</th>
							<td>${self_Rec.uName}</td>
						</tr>
						<tr>
							<th>学科大类：</th>
							<td>${self_Rec.bigClassName}</td>
							<th>专业：</th>
							<td>${self_Rec.specialtyName}</td>
						</tr>
						<tr>
							<th>年级：</th>
							<td>${self_Rec.grade}</td>
							<th>班级：</th>
							<td>${self_Rec.className}</td>
						</tr>
						<tr>
							<th>培养通道：</th>
							<td>${self_Rec.channel}</td>
							<th>六级最高成绩：</th>
							<td>${self_Rec.sixGrade}</td>
						</tr>
						<tr>
							<th>QQ：</th>
							<td><s:textfield name="self_Rec.qq" maxlength="16"/> </td>
							<th>Email：</th>
							<td><s:textfield name="self_Rec.email" maxlength="30" size="32"/></td>
						</tr>
						<tr>
							<th>联系电话：</th>
							<td><s:textfield name="self_Rec.phone" maxlength="11"/> </td>
							<th>个人网站/博客：</th>
							<td><s:textfield name="self_Rec.webAddr" maxlength="60" size="65"/></td>
						</tr>
					</table>
					<s:hidden name="self_Rec.loginName" />
				</form>
				<div class="toolbar">
					<a class="easyui-linkbutton" href="javascript:void(0)" iconCls="icon-save" onclick="save()">保存</a> 
				</div>
			</div>
			<div title="<span class='tabHead'>先修课程信息</span>" class="tab">
				<table width="800" class="listBorder" style="margin: 30px auto">
					<caption>先修课程信息</caption>
					<tr>
						<th width="100">课程编号</th>
						<th>课程名称</th>
						<th width="60">成绩</th>
						<th width="60">学时</th>
						<th width="60">学分</th>
						<th width="120">课程类型</th>
					</tr>
					<s:iterator value="self_Rec.pcourse" var="c">
						<tr>
							<td>${c.courseID}</td>
							<td>${c.courseName}</td>
							<td>${c.score}</td>
							<td>${c.courseHours}</td>
							<td>${c.credit}</td>
							<td>${c.courseType}</td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</div>
</body>
</html>