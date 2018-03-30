package com.jxliu.calf.mapper;

import java.util.List;

import com.jxliu.calf.pojo.Role;
import com.jxliu.calf.pojo.ext.AuthorityExt;
/**
 * @author jxliu
 * @date 2018年3月29日下午2:52:20
 */
public interface AuthorityExtMapper {
	public List<AuthorityExt> getAuthoriList(Role role);
}
