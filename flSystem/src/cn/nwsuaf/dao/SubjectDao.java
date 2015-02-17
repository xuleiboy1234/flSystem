package cn.nwsuaf.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.nwsuaf.util.DataPacked;

public class SubjectDao extends BaseDao {
	/**
	 *  学生信息列表
	 * @param where 筛选条件
	 * @param pg  待显示的页面
	 * @param pgSize  每页显示的记录条数
	 * @return 包含记录条数（num），及记录列表（recs）的map
	 * @throws Exception
	 */
	public HashMap<String,Object> getStuList(String where,int pg,int pgSize) throws Exception{
		 HashMap<String,Object> param = new HashMap<String,Object>();
		 param.put("where", where);
		 param.put("offset",(pg - 1)*pgSize);
		 param.put("pgSize", pgSize);
		 List<?> res = getList("student.stulist", param);
		 String num = getOne("student.stuNum", param).toString();
		 param.clear();
		 param.put("recs", res);
		 param.put("num", num);
		 return param;
	 }
	
	/**
	 * 指定学生信息
	 * @param loginName
	 * @return map 学生详细信息
	 * @throws Exception
	 */
	public Object getStuById(String loginName) throws Exception{
		return getOne("student.stuByid", loginName);
	}
	
	/**
	 * 更新个人信息
	 * @param personInfo
	 * @throws Exception
	 */
	public void personUpdate(Object personInfo) throws Exception{
		update("student.personUpdate", personInfo);
	}
	
	/**
	 * 保存修改或添加
	 * @param stuInfo 学生信息
	 * @throws Exception
	 */
	public void save(HashMap<String,String> stuInfo) throws Exception{
		String oldLogin = stuInfo.get("oldLoginName");
		if( oldLogin == null || "".equals(oldLogin)){
			insert("user.addStudentUser",stuInfo.get("loginName"));
			insert("student.stuInsert", stuInfo);
		}
		else{
			if(!oldLogin.equals(stuInfo.get("loginName"))){
				update("user.renameLogin", stuInfo);
			}
			update("student.stuUpdate", stuInfo);
		}
	}
	
	/**
	 * 学生信息导入
	 * @param dataList 
	 * @return 错误信息
	 * @throws Exception 
	 */
	public String importStuInfo(ArrayList<String[]> dataList) throws Exception{
		StringBuffer errMsg = new StringBuffer();
		ArrayList<HashMap<String, Object>> smapList = DataPacked.stuInfoPacked(dataList);
		int ok = 0;
		int err = 0;
		for(HashMap<String,Object> s:smapList){
			try {
				insert("user.addStudentUser",s.get("loginName"));
				insert("student.stuInsert",s);
				ok++;
			} catch (Exception e) {
				err++;
				String m = e.getMessage();
				if(m.contains("Duplicate entry")){
					m = "学号已经存在...";
				}
				errMsg.append(String.format("%d.%s: %s\n",ok+err,s.get("loginName"),m));
			}
		}
		errMsg.insert(0, String.format("成功导入%d条，有 %d条未导入\n\n", ok,err));
		return errMsg.toString();
	}
	
	/**
	 * 先修课程导入
	 * @param dataList
	 * @return
	 * @throws Exception
	 */
	public String importPCourseInfo(ArrayList<String[]> dataList) throws Exception{
		StringBuffer errMsg = new StringBuffer();
		ArrayList<HashMap<String, Object>> smapList = DataPacked.pcoursePacked(dataList);
		int ok = 0;
		int err = 0;
		for(HashMap<String,Object> s:smapList){
			try {
				insert("student.insertpcourse",s);
				ok++;
			} catch (Exception e) {
				err++;
				String m = e.getMessage();
				if(m.contains("Duplicate entry")){
					m = "课程已经存在...";
				}
				errMsg.append(String.format("%d.%s-%s: %s\n",ok+err,s.get("loginName"),s.get("courseID"),m));
			}
		}
		errMsg.insert(0, String.format("成功导入%d条，有 %d条未导入\n\n", ok,err));
		return errMsg.toString();
	}
	
	/**
	 * 先修课程列表
	 * @param stuId 学号
	 * @return
	 * @throws Exception
	 */
	public Object pcourseList(String stuId) throws Exception{
		return getList("student.pcourseList", stuId);
	}
	
	/**
	 * 年级信息列表
	 * @return
	 * @throws Exception
	 */
	public Object getGradeInfo() throws Exception{
		return getList("student.gradeList");
	}
	
	/**
	 * 按学号删除学生
	 * @param stuId 学号
	 * @throws Exception
	 */
	public void delOneStudent(String stuId) throws Exception{
		delete("student.deleteByLoignname", stuId);
	}
	
	/**
	 * 自荐书
	 * @param stuId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> getStuSelfRec(String stuId) throws Exception{
		return (HashMap<String, Object>) getOne("student.getSelfRec",stuId);
	}
	
	/**
	 * 更新自荐书
	 * @param self_Rec
	 * @throws Exception
	 */
	public void updateStuSelfRec(Object self_Rec) throws Exception{
		update("student.updateSelfRec", self_Rec);
	}
}