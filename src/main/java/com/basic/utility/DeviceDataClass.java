package com.basic.utility;


import java.util.Map;



import com.basic.utility.Constants;

public class DeviceDataClass implements DeviceDataClassImpl{

	private String applicationName;
	private String appPackage;
	private String appActivity;
	private String UDID;
	private String osName;
	private String osVersion;
	private String portNo;
	
	public DeviceDataClass(String applicationName) {
		Map<String, String> deviceData = KeywordFunctions.getDataFromFiles(applicationName,Constants.excelExecutionDataPage);
		this.applicationName=deviceData.get("applicationName");
		this.appPackage=deviceData.get("appPackage");
		this.appActivity=deviceData.get("appActivity");
		this.UDID=deviceData.get("UDID");
		this.osName=deviceData.get("osName");
		this.osVersion=deviceData.get("osVersion");
		this.portNo=deviceData.get("portNo");
	}
	
	public String getPortNo() {
		return portNo;
	}

	public void setPortNo(String portNo) {
		this.portNo = portNo;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getAppPackage() {
		return appPackage;
	}


	public void setAppPackage(String appPackage) {
		this.appPackage = appPackage;
	}


	public String getAppActivity() {
		return appActivity;
	}

	public void setAppActivity(String appActivity) {
		this.appActivity = appActivity;
	}

	public String getUDID() {
		return UDID;
	}

	public void setUDID(String uDID) {
		UDID = uDID;
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	
	
}
