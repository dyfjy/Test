package org.andy.shop.action;

import java.util.List;

import org.andy.shop.common.PageList;
import org.andy.shop.entity.UserInfo;
import org.andy.shop.service.UserInfoService;
import org.andy.shop.utils.AjaxUtil;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * 
 *@Title:  
 *@Description:  
 *@Author:cdi  
 *@Since:2016年8月24日  
 *@Version:1.1.0
 */
@ParentPackage("struts-default")  
//父包  
@Namespace("/user")  
@Results( { @Result(name = "success", location = "/WEB-INF/content/user/userinfo.jsp"),  
     @Result(name = "error", location = "/error.jsp") })  
@ExceptionMappings( { @ExceptionMapping(exception = "java.lange.RuntimeException", result = "error") }) 
public class UserinfoAction extends ActionSupport implements
		ModelDriven<UserInfo>, Preparable {
	private static final long serialVersionUID = -2301203156032690317L;

	private static final Logger LOGGER = Logger.getLogger(UserinfoAction.class);
	private Integer id;
	private UserInfo userInfo;
	private List<UserInfo> userInfos;

	@Autowired
	private UserInfoService userInfoService;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public List<UserInfo> getUserInfos() {
		return userInfos;
	}

	public void setUserInfos(List<UserInfo> userInfos) {
		this.userInfos = userInfos;
	}

	public UserInfo getModel() {
		if (null != id) {
			userInfo = userInfoService.get(id);
		} else {
			userInfo = new UserInfo();
		}
		return userInfo;
	}

	@Override
	public String execute() throws Exception {
		LOGGER.info("查询所有用户");
		userInfos = userInfoService.list();
		return SUCCESS;
		
	}
	@Action(value="detaill")
	public void detail() {
		String id = ServletActionContext.getRequest().getParameter("id");
		LOGGER.info("查看用户详情：" + id);
		userInfo = userInfoService.get(Integer.valueOf(id));
		AjaxUtil.ajaxJSONResponse(userInfo);

	}
	 @Action(value = "add", results = { @Result(name = "success" , location = "/WEB-INF/ftl/index.ftl") })  
	public String test() {
		return SUCCESS;
	}
	@Action(value="test1",results={@Result(name="success",type="redirectAction"   
		    ,params={"actionName","add"   
//		            ,"namespace","/"   
		            ,"id","09"   
		            ,"count","90"})})
	 public String test1() {
		 return SUCCESS;
	 }
	
	@Action(value="page")
	public void page() {
		PageList<UserInfo> plist = userInfoService.queryByPage();
		AjaxUtil.ajaxJSONResponse(plist);

	}

	public void prepare() throws Exception {
		
	}

}
