package me.daoke.poweroff.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public interface ConstantsUtil {

	public static final Log logger = LogFactory.getLog(ConstantsUtil.class);

	/**
	 * 语境内部错误
	 */
	public static final String ERRORCODE_APPKEY_ERROR = "ME01002";
	public static final String ERRORCODE_SING_ERROR = "ME01019";
	public static final String ERRORCODE_INNER_ERROR = "ME18001";
	public static final String ERRORCODE_INNER_JSON_ERROR = "ME18024";
	public static final String ERRORCODE_INNER_PARAMETERS_ERROR = "ME18040";
	
    /**
     * 本系统错误码
     */
	public static final String ERRORCODE_AGENT_ERROR = "ME01001";
	public static final String ERRORCODE_SERVICETYPE_ERROR = "ME01002";
	public static final String ERRORCODE_SIGN_ERROR = "ME01019";
	public static final String ERRORCODE_JSON_ERROR = "ME01006";                                                       
	public static final String ERRORCODE_SERVICE_ERROR = "ME01001";                                                    
	public static final String ERRORCODE_NOTNAME_ERROR = "ME19003";                                                    
	public static final String ERRORCODE_NOUSER_ERROR = "ME19004";                                                     
	public static final String ERRORCODE_CREATE_ORDER_ERROR = "ME19005";                                               
	public static final String ERRORCODE_PARAMETERS_ERROR = "ME01023";                                          
	public static final String ERRORCODE_ACCEPT_ORDER__ERROR = "ME19007";
	public static final String ERRORCODE_PAY_ORDER_ERROR = "ME19008";
	public static final String ERRORCODE_EMPTY_ERROR = "ME19009";
	public static final String ERRORCODE_UPDATE_ORDER_ERROR = "ME19010";
	public static final String ERRORCODE_USER_NAME_EXIST_ERROR = "ME19011";
	public static final String ERRORCODE_USER_NOT_EXIST_ERROR = "ME19012";
	public static final String ERRORCODE_USER_EXIST_ERROR = "ME19013";
    public static final String ERRORCODE_PERMISSIONS_QUOTA_ERROR = "Permissions or quota check failed";
    public static final String ERRORCODE_SUCCESS = "0";

    /**
     * 本系统错误说明
     */
    public static final String RESULT_AGENT_ERROR = "agent is errror!";
    public static final String RESULT_SERVICETYPE_ERROR = "servicetype is error!";
    public static final String RESULT_SIGN_ERROR = "sign is error!";
	public static final String RESULT_JSON_ERROR = "Json is error!";
	public static final String RESULT_SERVICE_ERROR = "Service is error!";
	public static final String RESULT_NOTNAME_ERROR = "User's name is null!";
	public static final String RESULT_NOUSER_ERROR = "The user information is empty!";
	public static final String RESULT_CREATE_ORDER_ERROR = "Create order fail!";
	public static final String RESULT_PARAMETERS_ERROR = "Input param is error!";
	public static final String RESULT_ACCEPT_ORDER_ERROR = "The accept order fail!";
	public static final String RESULT_PAY_ORDER_ERROR = "Pay order fail!";
	public static final String RESULT_EMPTY_ERROR = "The content is empty!";
	public static final String RESULT_UPDATE_ORDER_ERROR = "The update order fail!";
	public static final String RESULT_USER_NAME_EXIST_ERROR = "The username has exist!";
	public static final String RESULT_USER_EXIST_ERROR = "The user has exist!";
	public static final String ERRORCODE_RESULT_SERVICE_ERROR = "{\"ERRORCODE\": \"19002\",\"RESULT\": \"Service is error!\"}";

	/**
	 * 语境内部错误说明
	 */
	public static final String RESULT_APPKEY_ERROR = "appKey error!";
	public static final String RESULT_SING_ERROR = "sign is error!";
	public static final String RESULT_INNER_ERROR = "server is error!";
	public static final String RESULT_INNER_JSON_ERROR = "json is error!";
	public static final String RESULT_INNER_PARAMETERS_ERROR = "parameters is error!";
	
	/**
	 * 返回常量
	 */
	public static final String ERRORCODE  ="ERRORCODE";
	public static final String RESULT  ="RESULT";





}
