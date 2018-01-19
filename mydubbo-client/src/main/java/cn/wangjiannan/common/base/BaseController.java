package cn.wangjiannan.common.base;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.wangjiannan.common.result.Result;
import cn.wangjiannan.common.shiro.ShiroUser;

/**
 * 基础 controller
 * 
 * @author wangjiannan
 * @date 2017年12月14日 下午3:46:37
 *
 */
public abstract class BaseController {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 获取当前登录用户对象
	 * 
	 * @return {ShiroUser}
	 */
	public ShiroUser getShiroUser() {
		return (ShiroUser) SecurityUtils.getSubject().getPrincipal();
	}

	/**
	 * 获取当前登录用户id
	 * 
	 * @return {Long}
	 */
	public Long getUserId() {
		return this.getShiroUser().getId();
	}

	/**
	 * 获取当前登录用户名
	 * 
	 * @return {String}
	 */
	public String getStaffName() {
		return this.getShiroUser().getName();
	}

	/**
	 * ajax失败
	 * 
	 * @param msg
	 *            失败的消息
	 * @return {Object}
	 */
	public Object renderError(String msg) {
		Result result = new Result();
		result.setMsg(msg);
		return result;
	}

	/**
	 * ajax成功
	 * 
	 * @return {Object}
	 */
	public Object renderSuccess() {
		Result result = new Result();
		result.setSuccess(true);
		return result;
	}

	/**
	 * ajax成功
	 * 
	 * @param msg
	 *            消息
	 * @return {Object}
	 */
	public Object renderSuccess(String msg) {
		Result result = new Result();
		result.setSuccess(true);
		result.setMsg(msg);
		return result;
	}

	/**
	 * ajax成功
	 * 
	 * @param obj
	 *            成功时的对象
	 * @return {Object}
	 */
	public Object renderSuccess(Object obj) {
		Result result = new Result();
		result.setSuccess(true);
		result.setObj(obj);
		return result;
	}
}
