package cn.nwsuaf.action.admin;

import java.util.HashMap;

import com.opensymphony.xwork2.Action;

import cn.nwsuaf.action.BaseAction;
import cn.nwsuaf.dao.MajorDao;
//import cn.nwsuaf.util.DataPacked;
import cn.nwsuaf.dao.StudentDao;

public class MajorAction extends BaseAction {
	private MajorDao majorDao;
	private HashMap<String, Object> majorList;
	private HashMap<String,String> majorInfo;
	private boolean success = true;
	private String errMsg;
	private Integer specialtyID;
	private Integer bigClassID;
//	private HashMap<String, Object> bcMap;
	
	public MajorAction(){
		majorDao = new MajorDao();
	}
		
	public String list(){
		try {
			majorList = majorDao.getMajorList(createWhere(), getPage(), getRows());
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return "list";
	}

	
	/**
	 * 待审核列表
	 * @return
	 */
	public String checklist(){
		try {
			majorList = majorDao.getMajorList("checked=1", getPage(), getRows());
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return "checklist";
	}
	
	/**
	 * 审核
	 * @return
	 */
	public String check(){
		
		return Action.SUCCESS;
	}
	
	public String add(){
		try {
			return "edit";
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public String edit(){
		try {
			majorInfo = (HashMap<String, String>) majorDao.getMajorById(specialtyID);
//			bcMap = DataPacked.bigclassMap();
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return "edit";
	}
	
	public String save(){
		try {
			majorDao.save(majorInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Action.ERROR;
		}
		return "save";
	}
	
	public String delete(){
		try {
			majorDao.delOneMajor(specialtyID);
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		return "delete";
	}

	/**
	 * 创建查询条件
	 * @return
	 */
	private String createWhere(){
		String where = "1=1 ";
		if(bigClassID != null){
			where += "and bigClassID=" + bigClassID;
		}		
		if(specialtyID != null && !"".equals(specialtyID)){
			where += " and specialtyID=" + specialtyID;
		}
		return where;
	}

	public HashMap<String, Object> getMajorList() {
		return majorList;
	}

	public void setMajorList(HashMap<String, Object> majorList) {
		this.majorList = majorList;
	}

	public HashMap<String, String> getMajorInfo() {
		return majorInfo;
	}

	public void setMajorInfo(HashMap<String, String> majorInfo) {
		this.majorInfo = majorInfo;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public int getSpecialtyID() {
		return specialtyID;
	}

	public void setSpecialtyID(int specialtyID) {
		this.specialtyID = specialtyID;
	}

	public int getBigClassID() {
		return bigClassID;
	}

	public void setBigClassID(int bigClassID) {
		this.bigClassID = bigClassID;
	}

	
}