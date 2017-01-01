package com.corporation.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.corporation.mapper.PrivilegeMapper;
import com.corporation.po.Privilege;

/**
 * 
 * @ClassName: PrivilegeInit
 * @Description: 后台管理权限初始化
 * @author: JJChen
 * @date: 2016年12月5日 下午5:27:44
 */
public class PrivilegeInit
{
	/**
	 * 
	 * @Title: test
	 * @Description: 初始化权限
	 * @throws Exception
	 * @return: void
	 */
	@Test
	public void test() throws Exception 
	{
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("applicationContext.xml") ; 
		PrivilegeMapper privilegeMapper = (PrivilegeMapper)applicationContext.getBean("privilegeMapper") ; 
		
		Privilege userManager = new Privilege() ; 
		userManager.setName("用户管理");
		userManager.setUrl("/corporation/manager/userManager/list");
		privilegeMapper.insert(userManager) ;
		
		Privilege userList = new Privilege() ; 
		userList.setName("查看用户");
		userList.setUrl("/corporation/manager/userManager/list");
		userList.setParentID(userManager.getId());
		privilegeMapper.insert(userList) ;
		
		Privilege deleteUser = new Privilege() ; 
		deleteUser.setName("删除用户");
		deleteUser.setUrl("/corporation/manager/userManager/delete");
		deleteUser.setParentID(userManager.getId());
		privilegeMapper.insert(deleteUser) ;
		
		Privilege roleManager = new Privilege() ; 
		roleManager.setName("角色管理");
		roleManager.setUrl("/corporation/manager/roleManager/list") ; 
		privilegeMapper.insert(roleManager) ;
		
		Privilege roleList = new Privilege() ; 
		roleList.setName("查看角色");
		roleList.setUrl("/corporation/manager/roleManager/list");
		roleList.setParentID(roleManager.getId());
		privilegeMapper.insert(roleList) ;
		
		Privilege addRoleUI = new Privilege() ; 
		addRoleUI.setName("添加角色界面");
		addRoleUI.setUrl("/corporation/manager/roleManager/addUI");
		addRoleUI.setParentID(roleManager.getId());
		privilegeMapper.insert(addRoleUI) ;
		
		Privilege addRole = new Privilege() ; 
		addRole.setName("添加角色");
		addRole.setUrl("/corporation/manager/roleManager/add");
		addRole.setParentID(roleManager.getId());
		privilegeMapper.insert(addRole) ;
		
		Privilege deleteRole = new Privilege() ; 
		deleteRole.setName("删除角色");
		deleteRole.setUrl("/corporation/manager/roleManager/delete");
		deleteRole.setParentID(roleManager.getId());
		privilegeMapper.insert(deleteRole) ;
		
		Privilege allocatePrivilegeUI = new Privilege() ; 
		allocatePrivilegeUI.setName("分配权限界面");
		allocatePrivilegeUI.setUrl("/corporation/manager/roleManager/allocatePrivilegeUI");
		allocatePrivilegeUI.setParentID(roleManager.getId());
		privilegeMapper.insert(allocatePrivilegeUI) ;
		
		Privilege allocatePrivilege = new Privilege() ; 
		allocatePrivilege.setName("分配权限");
		allocatePrivilege.setUrl("/corporation/manager/roleManager/allocatePrivilege");
		allocatePrivilege.setParentID(roleManager.getId());
		privilegeMapper.insert(allocatePrivilege) ;
		
		Privilege manager = new Privilege() ; 
		manager.setName("管理员");
		manager.setUrl("/corporation/manager/manager/list");
		privilegeMapper.insert(manager) ;
		
		Privilege addManagerUI = new Privilege() ; 
		addManagerUI.setName("添加管理员界面");
		addManagerUI.setUrl("/corporation/manager/manager/addUI");
		addManagerUI.setParentID(manager.getId());
		privilegeMapper.insert(addManagerUI) ;
		
		Privilege addManager = new Privilege() ; 
		addManager.setName("添加管理员");
		addManager.setUrl("/corporation/manager/manager/add");
		addManager.setParentID(manager.getId());
		privilegeMapper.insert(addManager) ;
		
		Privilege managerList = new Privilege() ; 
		managerList.setName("查看管理员");
		managerList.setUrl("/corporation/manager/manager/list");
		managerList.setParentID(manager.getId());
		privilegeMapper.insert(managerList) ;
		
		Privilege deleteManager = new Privilege() ; 
		deleteManager.setName("删除管理员");
		deleteManager.setUrl("/corporation/manager/manager/delete");
		deleteManager.setParentID(manager.getId());
		privilegeMapper.insert(deleteManager) ;
		
		Privilege allocateRoleUI = new Privilege() ; 
		allocateRoleUI.setName("分配角色界面");
		allocateRoleUI.setUrl("/corporation/manager/manager/allocateRoleUI");
		allocateRoleUI.setParentID(manager.getId());
		privilegeMapper.insert(allocateRoleUI) ;
		
		Privilege allocateRole = new Privilege() ; 
		allocateRole.setName("分配角色");
		allocateRole.setUrl("/corporation/manager/manager/allocateRole");
		allocateRole.setParentID(manager.getId());
		privilegeMapper.insert(allocateRole) ;
		
		Privilege apply = new Privilege() ; 
		apply.setName("处理申请");
		apply.setUrl("/corporation/manager/apply/corporationCreateApplyList");
		privilegeMapper.insert(apply) ;
		
		Privilege corporationCreateApplyList = new Privilege() ; 
		corporationCreateApplyList.setName("查看申请");
		corporationCreateApplyList.setUrl("/corporation/manager/apply/corporationCreateApplyList");
		corporationCreateApplyList.setParentID(apply.getId());
		privilegeMapper.insert(corporationCreateApplyList) ;
		
		Privilege reviewUI = new Privilege() ; 
		reviewUI.setName("审核界面");
		reviewUI.setUrl("/corporation/manager/apply/reviewCorporationCreateApplyUI");
		reviewUI.setParentID(apply.getId());
		privilegeMapper.insert(reviewUI) ;
		
		Privilege review = new Privilege() ; 
		review.setName("审核");
		review.setUrl("/corporation/manager/apply/reviewCorporationCreateApply");
		review.setParentID(apply.getId());
		privilegeMapper.insert(review) ;
		

	}
}
