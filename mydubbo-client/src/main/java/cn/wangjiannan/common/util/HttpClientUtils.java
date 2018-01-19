package cn.wangjiannan.common.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HttpClientUtils
 * 
 * @author wangjiannan
 * @date 2017年6月19日 下午5:48:38
 *
 */
public class HttpClientUtils {
	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

	private CloseableHttpClient client;
	private RequestConfig requestConfig;
	private StringBuffer url = new StringBuffer();
	private List<NameValuePair> params = new ArrayList<NameValuePair>();
	private String encoding = "UTF-8";

	public HttpClientUtils(String url) {
		this.url.append(url);
		this.url.setLength(0);
		this.url.append(url);
		client = HttpClients.createDefault();
		this.requestConfig = RequestConfig.custom().setConnectTimeout(1 * 60 * 1000).setSocketTimeout(1 * 60 * 1000).build();
	}

	// public String doPostWithNoParamsName(String content) {
	// String result = "";
	// try {
	// HttpPost postMethod = new HttpPost(this.url.toString());
	// StringEntity se = new StringEntity(content);
	// se.setContentEncoding(encoding);
	// se.setContentType("application/json");
	// postMethod.setEntity(se);
	// postMethod.setConfig(requestConfig);
	// HttpResponse response = client.execute(postMethod);
	// result = EntityUtils.toString(response.getEntity(), encoding);
	// } catch (Exception e) {
	// logger.error("", e);
	// }
	// return result;
	// }

	public void setParams(String key, Object value) {
		if (value != null) {
			params.add(new BasicNameValuePair(key, value.toString()));
		}
	}

	public String doPost() {
		String result = "";
		try {
			HttpPost post = new HttpPost(this.url.toString());
			// post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			// 注:HttpClient4在HTTP类里原先定义了一系列编码集常量UTF-8,UTF-16等，但从4.2版本开始，这些常量被标识了deprecated标志。
			// 因为从JDK7起，引入了StandardCharsets类，标识了这些编码集常量
			post.setEntity(new UrlEncodedFormEntity(params, encoding));
			post.setConfig(requestConfig);
			result = getRespone(post);
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			close();
		}
		return result;
	}

	public String doPost(String content) {
		String result = "";
		try {
			HttpPost post = new HttpPost(this.url.toString());
			StringEntity se = new StringEntity(content);
			se.setContentEncoding(encoding);
			se.setContentType("application/json");
			post.setEntity(se);
			post.setConfig(requestConfig);
			result = getRespone(post);
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			close();
		}
		return result;
	}

	// public void setInitUrl(String newUrl) {
	// url.append(newUrl);
	// }

	public String doGet() {
		String result = "";
		try {
			int count = 0;
			for (NameValuePair nv : params) {
				if (count == 0) {
					url.append("?");
				} else {
					url.append("&");
				}
				String key = nv.getName();
				String value = nv.getValue();
				url.append(key);
				url.append("=");
				url.append(value);
				count++;
			}
			// System.out.println(this.url.toString());
			HttpGet get = new HttpGet(this.url.toString());
			result = getRespone(get);
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			close();
		}
		return result;
	}

	// public String getUrl() throws Exception {
	// int count = 0;
	// for (NameValuePair nv : params) {
	// if (count == 0) {
	// url.append("?");
	// } else {
	// url.append("&");
	// }
	// String key = nv.getName();
	// String value = nv.getValue();
	// url.append(key);
	// url.append("=");
	// url.append(value);
	//
	// count++;
	// }
	// return this.url.toString();
	// }

	private String getRespone(HttpUriRequest req) throws ClientProtocolException, IOException {
		// 查看默认request头部信息
		// System.out.println("Accept-Charset:" +
		// req.getFirstHeader("Accept-Charset"));
		// 以下这条如果不加会发现无论你设置Accept-Charset为gbk还是utf-8，他都会默认返回gb2312（本例针对google.cn来说）
		req.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.2)");
		// 用逗号分隔显示可以同时接受多种编码
		req.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
		req.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
		// 验证头部信息设置生效
		HttpResponse response = client.execute(req);
		HttpEntity entity = response.getEntity();
		String result = "";
		if (entity != null) {
			result = EntityUtils.toString(entity, encoding);
		}
		return result;
	}

	// public void setEncoding(String encoding) {
	// this.encoding = encoding;
	// }

	/**
	 * 关闭httpClient 请求
	 */
	private void close() {
		try {
			client.close();
		} catch (IOException e) {
			logger.error("", e);
		}
	}
}
