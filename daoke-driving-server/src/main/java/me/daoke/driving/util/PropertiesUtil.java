package me.daoke.driving.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/** 

 * @author chenlong
 * @email:chenlong@daoke.me
 * @date 创建时间：May 22, 2014 10:27:03 AM 
 * 获取属性文件工具类
 */
public class PropertiesUtil {
	
	private String resourceFilePath ="config/common.properties";
	
	private Properties props;
	
	
	
	private static PropertiesUtil instance  = null;
	/*
	 * 私有构造方法，防止被实例化
	 */
	private PropertiesUtil(){
		initProperties();
	}
	
	/*
	 *使用一个内部类来维护单例
	 */
	/*private static class PropertiesUtilFactory{
		private static PropertiesUtil instance = new PropertiesUtil();
	}*/
	/*
	 *单独用同步方法实现实例  做到延迟加载 
	 */
	private static synchronized void syncInit(){
		if(instance == null){
			instance = new PropertiesUtil();
		}
	} 
	
	/*
	 *获取实例  延迟加载 不使用synchronized　避免同步影响性能
	 */
	public static PropertiesUtil getInstance(){
		if(instance == null){
			syncInit();
		}
		//return PropertiesUtilFactory.instance;
		return instance;
	}
	/*
	 * 如果该对象被用于序列化，可以保证在序列化前后保持一致
	 */
	public Object readReslove(){
		return getInstance();
	}
	
	/*
	 *初始化属性文件 
	 */
	private void initProperties(){
		if(props == null || props.size() == 0 ){
			try {
				props = PropertiesLoaderUtils.loadAllProperties(resourceFilePath);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("文件不存在:"+ resourceFilePath);
			}
		}
	}
	/**
	 * 获取属性值
	 * @param key
	 * @return
	 */
	public String getProperty(String key){
		if(StringUtils.isBlank(key)){
			return "";
		}
		if(StringUtils.isNotBlank(props.getProperty(key))){
			return props.getProperty(key);
		}else{
			return "";
		}
	}
}
 
