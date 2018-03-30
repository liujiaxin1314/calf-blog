package com.jxliu.calf.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.jxliu.calf.mapper.AccountExtMapper;
import com.jxliu.calf.mapper.AccountMapper;
import com.jxliu.calf.mapper.AuthorityExtMapper;
import com.jxliu.calf.pojo.Account;
import com.jxliu.calf.pojo.AccountExample;
import com.jxliu.calf.pojo.AccountExample.Criteria;
import com.jxliu.calf.pojo.Role;
import com.jxliu.calf.pojo.ext.AccountExt;
import com.jxliu.calf.pojo.ext.AuthorityExt;
import com.jxliu.calf.util.RelationalMappingUtil;

/**
 * 进行shiro验证时，如果前后台密码不同，会直接导致登录失败，报异常did not match the expected credentials.
 * 解决方案：使用main方法生成密文密码保存到数据库中，再登录
 * @author jxliu
 * @date 2018年3月28日下午6:55:54
 */
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private AccountMapper accountMapper; 
    @Autowired
    private AccountExtMapper accountExtMapper;
    @Autowired
    private AuthorityExtMapper authorityExtRoleMapper;
    String pass;
    String roleId;

    /**
     * 授权:
     * 
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
    	List<String> permissionList = new ArrayList<String>(); 
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();        
        String principal = (String) principalCollection.getPrimaryPrincipal();//获取登录的用户名
        Account acc = new Account();
        acc.setAccountName(principal);
        AccountExt accountExt = accountExtMapper.getRole(acc);
        if(accountExt!=null){
        	Integer roleId = accountExt.getRoleId();
        	Role role = new Role();
        	role.setRoleId(roleId);
        	List<AuthorityExt> authoriList = authorityExtRoleMapper.getAuthoriList(role);
        	for (AuthorityExt authorityExt : authoriList) {
        		permissionList.add(RelationalMappingUtil.authorityIdToAuthorityName(authorityExt.getAuthorId()));
			}
        	info.addRole(RelationalMappingUtil.roleIdToRoleName(roleId));
        	info.addStringPermissions(permissionList);
        }
     /*   if("liujiaxin".equals(principal)){//两个if根据判断赋予登录用户权限
            info.addRole("admin");
            info.addStringPermission("admin:manage");
            System.out.println("已为用户[liujiaxin]赋予了[admin]角色和[admin:manage]权限");
            return info;
        }else if("tom".equals(principal)){  
        	info.addRole("user");
            info.addStringPermission("user:manage");
            System.out.println("已为用户[tom]赋予了[admin]角色和[admin:manage]权限");  
            return info;  
        }else if("person".equals(principal)){  
        	info.addRole("person");
            info.addStringPermission("person:manage");
            System.out.println("已为用户[person]赋予了[admin]角色和[admin:manage]权限");  
            return info;  
        }  */
        return info;
    }

    /*
     * 用户验证
     * 
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {   
        //1. token 中获取登录的 username! 注意不需要获取password.
        Object principal = token.getPrincipal();
                
        //2. 利用 username 查询数据库得到用户的信息. 
        AccountExample example = new AccountExample();
        Criteria criteria = example.createCriteria();
        criteria.andAccountNameEqualTo((String)principal);
        List<Account> list = accountMapper.selectByExample(example);
        if(list.size()!=0){
            pass=list.get(0).getPassword();
        }
        String credentials = pass;
        //3.设置盐值 ，（加密的调料，让加密出来的东西更具安全性，一般是通过数据库查询出来的。 简单的说，就是把密码根据特定的东西而进行动态加密，如果别人不知道你的盐值，就解不出你的密码）
        String source = "abcdefg";
        ByteSource credentialsSalt = new Md5Hash(source);
   
        
        //当前 Realm 的name
        String realmName = getName();
        //返回值实例化
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
        return info;
    }

    //init-method 配置. 
    public void setCredentialMatcher(){
        HashedCredentialsMatcher  credentialsMatcher = new HashedCredentialsMatcher();    
        credentialsMatcher.setHashAlgorithmName("MD5");//MD5算法加密
        credentialsMatcher.setHashIterations(1024);//1024次循环加密      
        setCredentialsMatcher(credentialsMatcher);
    }
    
    
    //用来测试的算出密码password盐值加密后的结果，下面方法用于新增用户添加到数据库操作的，我这里就直接用main获得，直接数据库添加了，省时间
    public static void main(String[] args) {
        String saltSource = "abcdefg";    
        String hashAlgorithmName = "MD5";
        String credentials = "000";
        Object salt = new Md5Hash(saltSource);
        int hashIterations = 1024;            
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println(result);//231e9d53673411ff77017454559ac348
    }

}