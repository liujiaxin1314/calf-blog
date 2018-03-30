package com.jxliu.calf.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxliu.calf.mapper.AccountMapper;
import com.jxliu.calf.mapper.AccountRoleMapper;
import com.jxliu.calf.pojo.Account;
import com.jxliu.calf.pojo.AccountRole;
import com.jxliu.calf.service.AccountService;
import com.jxliu.calf.util.MD5Utils;

/**
 * @author jxliu
 * @date 2018年3月28日下午3:53:55
 */
@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private AccountRoleMapper accountRoleMapper;
	
	@Override
	public Account accountLogin(Account account) {
		Account acc= accountMapper.accountLogin(account);
		return acc;
	}
	@Override
	public void accountRegister(Account account) {
		try {
			account.setCreatetime(new Date());
			account.setUpdatetime(new Date());
			account.setPassword(MD5Utils.encrypt(account.getPassword()).toString());
			accountMapper.insert(account);
			AccountRole accountRole = new AccountRole();
			accountRole.setAccountId(account.getAccountId());
			accountRole.setRoleId(3);//新建用户初始化，赋予普通用户权限
			accountRole.setCreatetime(new Date());
			accountRoleMapper.insert(accountRole);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("注册账号过程中出现异常！");
		}
		
	}

}
