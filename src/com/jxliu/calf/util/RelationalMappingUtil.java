package com.jxliu.calf.util;


public class RelationalMappingUtil {
	
	/*public static Map<Integer, String> ROLEMAP;
	public static Map<Integer, String> AUTHORIMAP;
	
	static{
		ROLEMAP = new HashMap<Integer, String>();
		ROLEMAP.put(1, "admin");
		ROLEMAP.put(2, "user");
		AUTHORIMAP = new HashMap<Integer, String>();
		AUTHORIMAP.put(1, "admin:add,admin:update,admin:delete,admin:select");
		AUTHORIMAP.put(2, "admin:add,admin:update,admin:delete");
		AUTHORIMAP.put(3, "admin:select");
	}*/
	/**
	 * 角色id转角色名(英文)
	 * @param code
	 * @return
	 */
	public static String roleIdToRoleName(Integer roleId) {
		String ret = "";
		if (roleId == 1) {
			ret = "administrator";//超级管理员
		} else if (roleId == 2) {
			ret = "admin";//管理员
		} else if (roleId == 3) {
			ret = "user";//普通用户
		}
		return ret;
	}
	
	/**
	 * 权限id转权限名(英文)
	 * @param code
	 * @return
	 */
	public static String authorityIdToAuthorityName(Integer authorityId){
		String ret = "";
		if (authorityId == 1) {
			ret = "administrator:manage";//私密模块
		} else if (authorityId == 2) {
			ret = "admin:manage";//系统模块
		} else if (authorityId == 3) {
			ret = "diary:manage";//日志模块
		} else if (authorityId == 4) {
			ret = "personal:manage";//个人模块
		}
		return ret;
	}
}
