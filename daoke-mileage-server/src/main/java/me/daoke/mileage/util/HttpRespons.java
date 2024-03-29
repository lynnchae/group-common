package me.daoke.mileage.util;

import java.util.Vector;

/**
 * @author chenlong
 * @Email chenlong@ddmap.com
 * @version 1.0.1
 * @time 创建时间：Dec 2, 2011
 * 
 * 
 *       响应对象
 * 
 */

public class HttpRespons {

	String urlString;

	int defaultPort;

	String file;

	String host;

	String path;

	int port;

	String protocol;

	String query;

	String ref;

	String userInfo;

	String contentEncoding;

	String content;

	String contentType;

	int code;

	String message;

	String method;

	int connectTimeout;

	int readTimeout;

	Vector<String> contentCollection;

	public String getContent() {
		return content;
	}

	public String getContentType() {
		return contentType;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public Vector<String> getContentCollection() {
		return contentCollection;
	}

	public String getContentEncoding() {
		return contentEncoding;
	}

	public String getMethod() {
		return method;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public int getReadTimeout() {
		return readTimeout;
	}

	public String getUrlString() {
		return urlString;
	}

	public int getDefaultPort() {
		return defaultPort;
	}

	public String getFile() {
		return file;
	}

	public String getHost() {
		return host;
	}

	public String getPath() {
		return path;
	}

	public int getPort() {
		return port;
	}

	public String getProtocol() {
		return protocol;
	}

	public String getQuery() {
		return query;
	}

	public String getRef() {
		return ref;
	}

	public String getUserInfo() {
		return userInfo;
	}

    @Override
    public String toString() {
        return "HttpRespons{" +
                "urlString='" + urlString + '\'' +
                ", defaultPort=" + defaultPort +
                ", file='" + file + '\'' +
                ", host='" + host + '\'' +
                ", path='" + path + '\'' +
                ", protocol='" + protocol + '\'' +
                ", port=" + port +
                ", query='" + query + '\'' +
                ", ref='" + ref + '\'' +
                ", userInfo='" + userInfo + '\'' +
                ", contentEncoding='" + contentEncoding + '\'' +
                ", content='" + content + '\'' +
                ", contentType='" + contentType + '\'' +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", method='" + method + '\'' +
                ", connectTimeout=" + connectTimeout +
                ", readTimeout=" + readTimeout +
                ", contentCollection=" + contentCollection +
                '}';
    }
}
