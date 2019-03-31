package com.rat.entity.local;

import java.io.Serializable;

/**
 * 实体类
 *
 * @author L.jinzhu
 * @date 2018-03-31 18:07
 */
public class File implements Serializable {
    private long id;
    private String type;
    private String fullName;
    private String path;
    private String name;
    private String suffix;
    private String classFullName;
    private String lineCount;
    private String size;

    public File() {
    }

    public File(long id, String name) {
        this.id = id;
        this.name = name;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getClassFullName() {
        return classFullName;
    }

    public void setClassFullName(String classFullName) {
        this.classFullName = classFullName;
    }

    public String getLineCount() {
        return lineCount;
    }

    public void setLineCount(String lineCount) {
        this.lineCount = lineCount;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
