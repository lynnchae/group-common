package me.daoke.mileage.common;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * @author jason
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class CommonJsonResult implements Serializable {

    private static final long serialVersionUID = -8832372194080675679L;


    /**
     * 信息表
     */
    private Map<String, Object> infoMap = null;

    /**
     * 结果列表
     */
    private List<Object> resultList = null;

    /**
     * 错误码
     */
    @JsonProperty
    private String ERRORCODE;

    @JsonProperty
    private Object RESULT;

    @JsonProperty
    private String REASON;

    @JsonIgnore
    public String getERRORCODE() {
        return ERRORCODE;
    }
    /**
     * 设置json 转换时忽略大小写
     * @param ERRORCODE
     */
    @JsonIgnore
    public void setERRORCODE(String ERRORCODE) {
        this.ERRORCODE = ERRORCODE;
    }

    @JsonIgnore
    public Object getRESULT() {
        return RESULT;
    }
    /**
     * 设置json 转换时忽略大小写
     * @param RESULT
     */
    @JsonIgnore
    public void setRESULT(Object RESULT) {
        this.RESULT = RESULT;
    }
    @JsonIgnore
    public void setInfoMap(String key, Object value) {
        if (this.infoMap == null) {
            infoMap = new HashMap<String, Object>();
        }
        this.infoMap.put(key, value);
    }

    public void addResultList(Object obj) {
        if (resultList == null) {
            resultList = new LinkedList<Object>();
        }
        this.resultList.add(obj);
    }




    public Map<String, Object> getInfoMap() {
        return infoMap;
    }

    public List<Object> getResultList() {
        return resultList;
    }

    @JsonIgnore
    public String getREASON() {
        return REASON;
    }

    /**
     * 返回字说明
     * @param REASON
     */
    @JsonIgnore
    public void setREASON(String REASON) {
        this.REASON = REASON;
    }

    @Override
    public String toString() {
        return "CommonJsonResult{" +
                "infoMap=" + infoMap +
                ", resultList=" + resultList +
                ", ERRORCODE='" + ERRORCODE + '\'' +
                ", RESULT=" + RESULT +
                '}';
    }
}
