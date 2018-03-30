package com.jxliu.calf.mapper;

import com.jxliu.calf.pojo.AuthoriRole;
import com.jxliu.calf.pojo.AuthoriRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuthoriRoleMapper {
    int countByExample(AuthoriRoleExample example);

    int deleteByExample(AuthoriRoleExample example);

    int deleteByPrimaryKey(Integer authorRoleId);

    int insert(AuthoriRole record);

    int insertSelective(AuthoriRole record);

    List<AuthoriRole> selectByExample(AuthoriRoleExample example);

    AuthoriRole selectByPrimaryKey(Integer authorRoleId);

    int updateByExampleSelective(@Param("record") AuthoriRole record, @Param("example") AuthoriRoleExample example);

    int updateByExample(@Param("record") AuthoriRole record, @Param("example") AuthoriRoleExample example);

    int updateByPrimaryKeySelective(AuthoriRole record);

    int updateByPrimaryKey(AuthoriRole record);
}