package cn.wangjiannan.manager;

public interface InvokeBaiduManager {
	String getPositionByLatLng(String latLng);

	String getLnyLatByPosition(String address, String city);
}
