package com.jxliu.calf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jxliu.calf.mapper.AccountMapper;
import com.jxliu.calf.pojo.Account;

/**
 * @author jxliu
 * @date 2018年3月28日下午3:50:26
 */
public interface AccountService {
	/**
	 * 用户登录
	 * @author jxliu
	 * @date 2018年3月28日  
	 * @version 1.0.0 
	 * @return
	 */
	public Account accountLogin(Account account);
	
	/**
	 * 用户注册
	 * @author jxliu
	 * @date 2018年3月30日  
	 * @version 1.0.0
	 */
	public void accountRegister(Account account);
}
