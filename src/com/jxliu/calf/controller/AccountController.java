package com.jxliu.calf.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jxliu.calf.pojo.Account;
import com.jxliu.calf.service.AccountService;
/**
 * @author jxliu
 * @date 2018年3月28日下午3:49:46
 */
@RequestMapping("/account")
@Controller
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	/**
	 * 跳转页面
	 * @author jxliu
	 * @date 2018年3月30日  
	 * @version 1.0.0 
	 * @return
	 */
	@RequestMapping("/index.action")
	public String inde(){
		return "redirect:register.action";
	}
	
	/**
	 * 登录验证
	 * @author jxliu
	 * @date 2018年3月30日  
	 * @version 1.0.0 
	 * @param account
	 * @return
	 */
	@RequestMapping("/login.action")
	public String accountLogin(Account account){
		Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(account.getAccountName(),account.getPassword());        
        try {
            //执行认证操作. 
            subject.login(token);
        }catch (AuthenticationException ae) {
            System.out.println("登陆失败: " + ae.getMessage());
            return "error";
        }
		return "success";
	}
	
	/**
	 * 注册用户
	 * @author jxliu
	 * @date 2018年3月30日  
	 * @version 1.0.0 
	 * @param account
	 * @return
	 */
	@RequestMapping("/register.action")
	public String accountRegister(Account account){
		accountService.accountRegister(account);
		return "redirect:/jsp/login.jsp";
	}
}
