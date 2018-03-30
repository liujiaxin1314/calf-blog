package com.jxliu.calf.mapper;

import com.jxliu.calf.pojo.Account;
import com.jxliu.calf.pojo.ext.AccountExt;

/**
 * @author jxliu
 * @date 2018年3月29日下午2:52:20
 */
public interface AccountExtMapper {
	public AccountExt getRole(Account account);
}
