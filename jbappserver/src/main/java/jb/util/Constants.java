package jb.util;

import java.text.SimpleDateFormat;

public interface Constants {
	
	/**
	 * 系统发布环境：正式、开发、测试
	 */
	public static String SYSTEM_PUBLISH_SETTING = "SV004";
	
	/**
	 * 全局异常提示
	 */
	public static String SYSTEM_GLOBAL_MESSAGE = "EX0001";
	
	/**
	 * 短格式日期
	 */
	public static final SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 时分秒格式日期
	 */
	public static final SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	/**
	 * 时分秒格式日期
	 */
	public static final SimpleDateFormat df3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * ID模式
	 */
	public static final SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmmssS");

	/**
	 * 后台服务角色
	 */
	public static String SYSTEM_APP_SERVER_ROLE = "SV110";
	
	/**
	 * 测点树挂在那个资源下
	 */
	public static String SYSTEM_APP_RESOURCE_ID = "SV115";
	
	/**
	 * 指定文件夹目录
	 */
	public static String SYSTEM_FOLDER_PATH = "SV130";
	
	
	/**
	 * HTML5 前缀
	 */
	public static String SYSTEM_PRE_URL_PATH = "SV135";
	
	/**
	 * TCP 協議IP 端口
	 */
	public static String SYSTEM_TCP_URL = "SV140";

}
