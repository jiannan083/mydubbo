package cn.wangjiannan.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import cn.wangjiannan.test.base.BaseWebTest;

public class WebTest extends BaseWebTest {

	/**
	 * 测试普通控制器
	 * 
	 * @author wangjiannan
	 * @date 2018年1月2日 下午12:59:41
	 */
	@Test
	public void testView1() {
		try {
			MvcResult result = mockMvc
					.perform(MockMvcRequestBuilders.post("/user/add").param("loginName", "loginName001").param("name", "name001").param("password", "test")
							.param("roleIds", "1,2")) // 执行请求
					// .andExpect(MockMvcResultMatchers.view().name("views/admin/user/userAdd.jsp")) // 验证viewName
					// .andExpect(MockMvcResultMatchers.model().attributeExists("user")) // 验证存储模型数据
					.andDo(MockMvcResultHandlers.print()) // 输出MvcResult到控制台
					.andReturn(); // 返回验证成功后的MvcResult；用于自定义验证/下一步的异步处理；
			System.out.println(result);
			// Assert.assertNotNull(result.getModelAndView().getModel().get("user"));
		} catch (Exception e) {
			logger.error("----", e);
		}
	}

	/**
	 * JSON请求/响应验证
	 * 
	 * @author wangjiannan
	 * @date 2018年1月2日 下午12:59:48
	 */
	@Test
	public void testJson() {
		try {
			String requestBody = "{\"id\":1, \"name\":\"zhang\"}";
			mockMvc.perform(
					MockMvcRequestBuilders.post("/user").contentType(MediaType.APPLICATION_JSON).content(requestBody).accept(MediaType.APPLICATION_JSON)) // 执行请求
					.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)) // 验证响应contentType
					.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1)); // 使用Json path验证JSON 请参考http://goessner.net/articles/JsonPath/

			String errorBody = "{id:1, name:zhang}";
			MvcResult result = mockMvc
					.perform(MockMvcRequestBuilders.post("/user").contentType(MediaType.APPLICATION_JSON).content(errorBody).accept(MediaType.APPLICATION_JSON)) // 执行请求
					.andExpect(MockMvcResultMatchers.status().isBadRequest()) // 400错误请求
					.andReturn();

			Assert.assertTrue(HttpMessageNotReadableException.class.isAssignableFrom(result.getResolvedException().getClass()));// 错误的请求内容体
		} catch (Exception e) {
			logger.error("----", e);
		}
	}

	@Test
	public void testValidated() {
		try {
			mockMvc.perform(MockMvcRequestBuilders.post("/test/testValidated").param("loginName", "王j"))//
					.andDo(MockMvcResultHandlers.print())//
					.andReturn();//
		} catch (Exception e) {
			logger.error("----", e);
		}
	}

	@Test
	public void testInvokeBaidu() {
		try {
			mockMvc.perform(MockMvcRequestBuilders.post("/test/testInvokeBaidu").param("latLng", "40.1538,116.2933"))//
					// .andDo(MockMvcResultHandlers.print())//
					.andReturn();//
		} catch (Exception e) {
			logger.error("----", e);
		}
	}

}
