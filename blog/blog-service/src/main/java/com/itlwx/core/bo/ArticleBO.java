package com.itlwx.core.bo;

import com.itlwx.common.valid.GroupGeneral;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;

public class ArticleBO implements Serializable {
    @NotNull(groups = GroupGeneral.class)
    private Integer id;

    @NotEmpty
    private String title;

    private String intro;

    private Integer authroId;

    private Integer hits;

    private Integer postNum;

    private Integer deleted;

    private Date createTime;

    private Date updateTime;

    private Date postTime;

    @NotNull
    private Integer categoryId;

    @NotEmpty
    private String content;

    private String categoryName;

    private String authroAlias;

    public String getAuthroAlias() {
        return authroAlias;
    }

    public void setAuthroAlias(String authroAlias) {
        this.authroAlias = authroAlias;
    }


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getAuthroId() {
        return authroId;
    }

    public void setAuthroId(Integer authroId) {
        this.authroId = authroId;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Integer getPostNum() {
        return postNum;
    }

    public void setPostNum(Integer postNum) {
        this.postNum = postNum;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
