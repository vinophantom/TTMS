package cn.tedu.ttms.common.service.impl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.ttms.common.exception.ServiceException;
import cn.tedu.ttms.common.service.SysShiroService;
import cn.tedu.ttms.system.dao.SysUserDao;
@Service
public class SysShiroServiceImpl  implements SysShiroService {
	public void login(String username, String password) {
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated())return;
		// 把用户名和密码封装为 UsernamePasswordToken 对象
        UsernamePasswordToken token = 
        new UsernamePasswordToken(username, password);
        try{//登录认证 - 调用userRealm
        	subject.login(token);
        }catch (IncorrectCredentialsException ice) {
        throw new ServiceException("密码错误！");
        } catch(AuthenticationException ae){
        ae.printStackTrace();
        throw new ServiceException("认证失败");
        }
	}
}
