package me.daoke.poweroff.common.model;

import me.daoke.poweroff.util.ConstantsUtil;
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
     * 接口调用后的反应值
     */
    protected static final String FLAG = "flag";

    /**
     * 接口调用后的反应信息
     */

    protected static final String REASON = "reason";

    /**
     * 数据总条数
     */
    public static final String TOTAL_RECORDS = "totalRecords";

    /**
     * 页面大小
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 下个页面
     */
    public static final String NEXT_PAGE = "nextPage";

    /**
     * 是否有下一页
     */
    public static final String HAS_NEXT_PAGE = "hasNextPage";

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

    @JsonIgnore
    public String getERRORCODE() {
        return ERRORCODE;
    }

    @JsonIgnore
    public void setERRORCODE(String ERRORCODE) {
        this.ERRORCODE = ERRORCODE;
    }
    @JsonIgnore
    public Object getRESULT() {
        return RESULT;
    }
    @JsonIgnore
    public void setRESULT(Object RESULT) {
        this.RESULT = RESULT;
    }

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

    public CommonJsonResult setResultCode(String errorCode, Object result) {
        this.infoMap.put(ConstantsUtil.ERRORCODE, errorCode);
        this.infoMap.put(ConstantsUtil.RESULT, result);
        return this;
    }

    public void setSuccess(String reason) {
        setInfoMap(FLAG, "1");
        setInfoMap(REASON, reason);
    }

    public void setSuccess() {
        setSuccess("调用成功");
    }

    public void setFailure(String reason) {
        setInfoMap(FLAG, "0");
        setInfoMap(REASON, reason);
    }

    public void setFailure() {
        setFailure("调用失败");
    }

    public void setPaging(long totalRecords, int pageSize, int nextPage) {
        setInfoMap(TOTAL_RECORDS, totalRecords);
        setInfoMap(PAGE_SIZE, pageSize);
        setInfoMap(NEXT_PAGE, nextPage);
        setInfoMap(HAS_NEXT_PAGE, nextPage > 0 ? true : false);
    }

    public void setPaging(long totalRecords, int pageSize, int nextPage, boolean hasNextPage) {
        setInfoMap(TOTAL_RECORDS, totalRecords);
        setInfoMap(PAGE_SIZE, pageSize);
        setInfoMap(NEXT_PAGE, nextPage);
        setInfoMap(HAS_NEXT_PAGE, hasNextPage);
    }

    public Map<String, Object> getInfoMap() {
        return infoMap;
    }

    public List<Object> getResultList() {
        return resultList;
    }


}
