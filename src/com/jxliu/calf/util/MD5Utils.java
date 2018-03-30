package com.jxliu.calf.util;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @author jxliu
 * @date 2018年3月30日上午11:38:08
 */
public class MD5Utils {
	/**
	 * MD5密码加密
	 * @author jxliu
	 * @date 2018年3月30日  
	 * @version 1.0.0 
	 * @return
	 */
	public static Object encrypt(String credentials){
		String saltSource = "abcdefg";    
        String hashAlgorithmName = "MD5";
        Object salt = new Md5Hash(saltSource);
        int hashIterations = 1024;            
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        return result;
	}
	public static void main(String[] args) {
		System.out.println(encrypt("haha").toString().equals("0b7243a8dcae165c94b8ed462c0308b2"));
	}
}
