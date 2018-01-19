package cn.wangjiannan.manager.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.wangjiannan.common.util.HttpClientUtils;
import cn.wangjiannan.manager.InvokeBaiduManager;

@Component
public class InvokeBaiduManagerImpl implements InvokeBaiduManager {
	private static final Logger logger = LoggerFactory.getLogger(InvokeBaiduManagerImpl.class);
	private static final String BAIDU_GEOCODER_URL = "http://api.map.baidu.com/geocoder/v2/";
	private static final String OUTPUT = "json";
	private static final String COORD_TYPE = "wgs84ll";
	private static final int POIS = 0;

	/**
	 * 百度地址解析
	 * 
	 * @author wangjiannan
	 * @date 2018年1月9日 下午3:17:26
	 * @param latLng
	 *            lat + "," + lng // 维度值，经度值
	 * @return
	 */
	@Override
	public String getPositionByLatLng(String latLng) {
		HttpClientUtils hc = new HttpClientUtils(BAIDU_GEOCODER_URL);
		hc.setParams("location", latLng);
		hc.setParams("ak", "kyIPiaAaKIxWHKhD0GUwoYUq");
		hc.setParams("output", OUTPUT);
		hc.setParams("coordtype", COORD_TYPE);
		hc.setParams("pois", POIS);
		String httpResult = hc.doPost();
		JSONObject jsonObject = JSON.parseObject(httpResult);
		logger.info("jsonObject={}", jsonObject);
		if (jsonObject == null) {
			return null;
		}
		String status = jsonObject.getString("status");
		if (StringUtils.isEmpty(status) || !"0".equals(status)) {
			return null;
		}
		String result = jsonObject.getString("result");
		return result;
	}

	@Override
	public String getLnyLatByPosition(String address, String city) {
		HttpClientUtils hc = new HttpClientUtils(BAIDU_GEOCODER_URL);
		hc.setParams("address", address);
		hc.setParams("city", city);
		hc.setParams("ak", "kyIPiaAaKIxWHKhD0GUwoYUq");
		hc.setParams("output", OUTPUT);
		String httpResult = hc.doPost();
		JSONObject jsonObject = JSON.parseObject(httpResult);
		logger.info("jsonObject={}", jsonObject);
		if (jsonObject == null) {
			return null;
		}
		String status = jsonObject.getString("status");
		if (StringUtils.isEmpty(status) || !"0".equals(status)) {
			return null;
		}
		String result = jsonObject.getString("result");
		String location = JSON.parseObject(result).getString("location");
		JSONObject locationJsonObject = JSON.parseObject(location);
		String lat = locationJsonObject.getString("lat");
		String lng = locationJsonObject.getString("lng");
		return lat + "," + lng;
	}

}
