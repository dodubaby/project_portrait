package com.rat.entity.local;

import com.rat.utils.SafeParseUtils;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * 实体类
 *
 * @author L.jinzhu
 * @date 2018-03-31 18:07
 */
public class Tag implements Serializable {
    private long id;
    private String type;
    private String value;

    public Tag(BigInteger id, String type, String value) {
        this.id = SafeParseUtils.parseLong(String.valueOf(id));
        this.type = type;
        this.value = value;
    }

    public Tag(String value) {
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
