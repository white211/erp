package com.naswork.erp.utils.framework.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;
import com.naswork.erp.utils.framework.enums.DateRangeType;

import java.util.Date;
import java.util.List;

/**
 * @Program: BaseModel
 * @Description:
 * @Author: White
 * @DateTime: 2019-03-11 10:11:46
 **/

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseModel {
    protected static final String DEFAULE_ORDER_BY = "sort, id desc";
    private Integer id;
    private Integer orgId;
    private String mobile;
    private String email;
    private String code;
    private Integer userId;
    private Integer sort;
    private String comments;
    private Integer version;
    private Integer status;
    private Integer isPage;
    private Integer pageNo;
    private Integer pageSize;
    private String orderBy;
    private List<Integer> ids;
    private String keyword;
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date timeFrom;
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @DateTimeFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date timeTo;
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date createTime;
    private Integer createBy;
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date lastUpdateTime;
    private Integer lastUpdateBy;
    private DateRangeType dateRangeType;
    private String messageId;
    private Long count;
    private Integer debug;

    public void init() {
        if (this.pageNo == null || this.pageNo < 1) {
            this.pageNo = 1;
        }

        if (this.pageSize == null || this.pageSize < 1) {
            this.pageSize = 10;
        }

    }

    public BaseModel() {
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getOrgId() {
        return this.orgId;
    }

    public String getMobile() {
        return this.mobile;
    }

    public String getEmail() {
        return this.email;
    }

    public String getCode() {
        return this.code;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public Integer getSort() {
        return this.sort;
    }

    public String getComments() {
        return this.comments;
    }

    public Integer getVersion() {
        return this.version;
    }

    public Integer getStatus() {
        return this.status;
    }

    public Integer getIsPage() {
        return this.isPage;
    }

    public Integer getPageNo() {
        return this.pageNo;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public String getOrderBy() {
        return this.orderBy;
    }

    public List<Integer> getIds() {
        return this.ids;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public Date getTimeFrom() {
        return this.timeFrom;
    }

    public Date getTimeTo() {
        return this.timeTo;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public Integer getCreateBy() {
        return this.createBy;
    }

    public Date getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public Integer getLastUpdateBy() {
        return this.lastUpdateBy;
    }

    public DateRangeType getDateRangeType() {
        return this.dateRangeType;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public Long getCount() {
        return this.count;
    }

    public Integer getDebug() {
        return this.debug;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public void setOrgId(final Integer orgId) {
        this.orgId = orgId;
    }

    public void setMobile(final String mobile) {
        this.mobile = mobile;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public void setUserId(final Integer userId) {
        this.userId = userId;
    }

    public void setSort(final Integer sort) {
        this.sort = sort;
    }

    public void setComments(final String comments) {
        this.comments = comments;
    }

    public void setVersion(final Integer version) {
        this.version = version;
    }

    public void setStatus(final Integer status) {
        this.status = status;
    }

    public void setIsPage(final Integer isPage) {
        this.isPage = isPage;
    }

    public void setPageNo(final Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(final Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setOrderBy(final String orderBy) {
        this.orderBy = orderBy;
    }

    public void setIds(final List<Integer> ids) {
        this.ids = ids;
    }

    public void setKeyword(final String keyword) {
        this.keyword = keyword;
    }

    public void setTimeFrom(final Date timeFrom) {
        this.timeFrom = timeFrom;
    }

    public void setTimeTo(final Date timeTo) {
        this.timeTo = timeTo;
    }

    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }

    public void setCreateBy(final Integer createBy) {
        this.createBy = createBy;
    }

    public void setLastUpdateTime(final Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public void setLastUpdateBy(final Integer lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public void setDateRangeType(final DateRangeType dateRangeType) {
        this.dateRangeType = dateRangeType;
    }

    public void setMessageId(final String messageId) {
        this.messageId = messageId;
    }

    public void setCount(final Long count) {
        this.count = count;
    }

    public void setDebug(final Integer debug) {
        this.debug = debug;
    }

    public String toString() {
        return "BaseModel(id=" + this.getId() + ", orgId=" + this.getOrgId() + ", mobile=" + this.getMobile() + ", email=" + this.getEmail() + ", code=" + this.getCode() + ", userId=" + this.getUserId() + ", sort=" + this.getSort() + ", comments=" + this.getComments() + ", version=" + this.getVersion() + ", status=" + this.getStatus() + ", isPage=" + this.getIsPage() + ", pageNo=" + this.getPageNo() + ", pageSize=" + this.getPageSize() + ", orderBy=" + this.getOrderBy() + ", ids=" + this.getIds() + ", keyword=" + this.getKeyword() + ", timeFrom=" + this.getTimeFrom() + ", timeTo=" + this.getTimeTo() + ", createTime=" + this.getCreateTime() + ", createBy=" + this.getCreateBy() + ", lastUpdateTime=" + this.getLastUpdateTime() + ", lastUpdateBy=" + this.getLastUpdateBy() + ", dateRangeType=" + this.getDateRangeType() + ", messageId=" + this.getMessageId() + ", count=" + this.getCount() + ", debug=" + this.getDebug() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof BaseModel)) {
            return false;
        } else {
            BaseModel other = (BaseModel)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$id = this.getId();
                Object other$id = other.getId();
                if (this$id == null) {
                    if (other$id != null) {
                        return false;
                    }
                } else if (!this$id.equals(other$id)) {
                    return false;
                }

                Object this$orgId = this.getOrgId();
                Object other$orgId = other.getOrgId();
                if (this$orgId == null) {
                    if (other$orgId != null) {
                        return false;
                    }
                } else if (!this$orgId.equals(other$orgId)) {
                    return false;
                }

                Object this$mobile = this.getMobile();
                Object other$mobile = other.getMobile();
                if (this$mobile == null) {
                    if (other$mobile != null) {
                        return false;
                    }
                } else if (!this$mobile.equals(other$mobile)) {
                    return false;
                }

                label302: {
                    Object this$email = this.getEmail();
                    Object other$email = other.getEmail();
                    if (this$email == null) {
                        if (other$email == null) {
                            break label302;
                        }
                    } else if (this$email.equals(other$email)) {
                        break label302;
                    }

                    return false;
                }

                label295: {
                    Object this$code = this.getCode();
                    Object other$code = other.getCode();
                    if (this$code == null) {
                        if (other$code == null) {
                            break label295;
                        }
                    } else if (this$code.equals(other$code)) {
                        break label295;
                    }

                    return false;
                }

                Object this$userId = this.getUserId();
                Object other$userId = other.getUserId();
                if (this$userId == null) {
                    if (other$userId != null) {
                        return false;
                    }
                } else if (!this$userId.equals(other$userId)) {
                    return false;
                }

                label281: {
                    Object this$sort = this.getSort();
                    Object other$sort = other.getSort();
                    if (this$sort == null) {
                        if (other$sort == null) {
                            break label281;
                        }
                    } else if (this$sort.equals(other$sort)) {
                        break label281;
                    }

                    return false;
                }

                label274: {
                    Object this$comments = this.getComments();
                    Object other$comments = other.getComments();
                    if (this$comments == null) {
                        if (other$comments == null) {
                            break label274;
                        }
                    } else if (this$comments.equals(other$comments)) {
                        break label274;
                    }

                    return false;
                }

                Object this$version = this.getVersion();
                Object other$version = other.getVersion();
                if (this$version == null) {
                    if (other$version != null) {
                        return false;
                    }
                } else if (!this$version.equals(other$version)) {
                    return false;
                }

                Object this$status = this.getStatus();
                Object other$status = other.getStatus();
                if (this$status == null) {
                    if (other$status != null) {
                        return false;
                    }
                } else if (!this$status.equals(other$status)) {
                    return false;
                }

                label253: {
                    Object this$isPage = this.getIsPage();
                    Object other$isPage = other.getIsPage();
                    if (this$isPage == null) {
                        if (other$isPage == null) {
                            break label253;
                        }
                    } else if (this$isPage.equals(other$isPage)) {
                        break label253;
                    }

                    return false;
                }

                label246: {
                    Object this$pageNo = this.getPageNo();
                    Object other$pageNo = other.getPageNo();
                    if (this$pageNo == null) {
                        if (other$pageNo == null) {
                            break label246;
                        }
                    } else if (this$pageNo.equals(other$pageNo)) {
                        break label246;
                    }

                    return false;
                }

                Object this$pageSize = this.getPageSize();
                Object other$pageSize = other.getPageSize();
                if (this$pageSize == null) {
                    if (other$pageSize != null) {
                        return false;
                    }
                } else if (!this$pageSize.equals(other$pageSize)) {
                    return false;
                }

                label232: {
                    Object this$orderBy = this.getOrderBy();
                    Object other$orderBy = other.getOrderBy();
                    if (this$orderBy == null) {
                        if (other$orderBy == null) {
                            break label232;
                        }
                    } else if (this$orderBy.equals(other$orderBy)) {
                        break label232;
                    }

                    return false;
                }

                Object this$ids = this.getIds();
                Object other$ids = other.getIds();
                if (this$ids == null) {
                    if (other$ids != null) {
                        return false;
                    }
                } else if (!this$ids.equals(other$ids)) {
                    return false;
                }

                label218: {
                    Object this$keyword = this.getKeyword();
                    Object other$keyword = other.getKeyword();
                    if (this$keyword == null) {
                        if (other$keyword == null) {
                            break label218;
                        }
                    } else if (this$keyword.equals(other$keyword)) {
                        break label218;
                    }

                    return false;
                }

                Object this$timeFrom = this.getTimeFrom();
                Object other$timeFrom = other.getTimeFrom();
                if (this$timeFrom == null) {
                    if (other$timeFrom != null) {
                        return false;
                    }
                } else if (!this$timeFrom.equals(other$timeFrom)) {
                    return false;
                }

                Object this$timeTo = this.getTimeTo();
                Object other$timeTo = other.getTimeTo();
                if (this$timeTo == null) {
                    if (other$timeTo != null) {
                        return false;
                    }
                } else if (!this$timeTo.equals(other$timeTo)) {
                    return false;
                }

                Object this$createTime = this.getCreateTime();
                Object other$createTime = other.getCreateTime();
                if (this$createTime == null) {
                    if (other$createTime != null) {
                        return false;
                    }
                } else if (!this$createTime.equals(other$createTime)) {
                    return false;
                }

                label190: {
                    Object this$createBy = this.getCreateBy();
                    Object other$createBy = other.getCreateBy();
                    if (this$createBy == null) {
                        if (other$createBy == null) {
                            break label190;
                        }
                    } else if (this$createBy.equals(other$createBy)) {
                        break label190;
                    }

                    return false;
                }

                label183: {
                    Object this$lastUpdateTime = this.getLastUpdateTime();
                    Object other$lastUpdateTime = other.getLastUpdateTime();
                    if (this$lastUpdateTime == null) {
                        if (other$lastUpdateTime == null) {
                            break label183;
                        }
                    } else if (this$lastUpdateTime.equals(other$lastUpdateTime)) {
                        break label183;
                    }

                    return false;
                }

                Object this$lastUpdateBy = this.getLastUpdateBy();
                Object other$lastUpdateBy = other.getLastUpdateBy();
                if (this$lastUpdateBy == null) {
                    if (other$lastUpdateBy != null) {
                        return false;
                    }
                } else if (!this$lastUpdateBy.equals(other$lastUpdateBy)) {
                    return false;
                }

                label169: {
                    Object this$dateRangeType = this.getDateRangeType();
                    Object other$dateRangeType = other.getDateRangeType();
                    if (this$dateRangeType == null) {
                        if (other$dateRangeType == null) {
                            break label169;
                        }
                    } else if (this$dateRangeType.equals(other$dateRangeType)) {
                        break label169;
                    }

                    return false;
                }

                label162: {
                    Object this$messageId = this.getMessageId();
                    Object other$messageId = other.getMessageId();
                    if (this$messageId == null) {
                        if (other$messageId == null) {
                            break label162;
                        }
                    } else if (this$messageId.equals(other$messageId)) {
                        break label162;
                    }

                    return false;
                }

                Object this$count = this.getCount();
                Object other$count = other.getCount();
                if (this$count == null) {
                    if (other$count != null) {
                        return false;
                    }
                } else if (!this$count.equals(other$count)) {
                    return false;
                }

                Object this$debug = this.getDebug();
                Object other$debug = other.getDebug();
                if (this$debug == null) {
                    if (other$debug != null) {
                        return false;
                    }
                } else if (!this$debug.equals(other$debug)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof BaseModel;
    }

    @Override
    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $orgId = this.getOrgId();
        result = result * 59 + ($orgId == null ? 43 : $orgId.hashCode());
        Object $mobile = this.getMobile();
        result = result * 59 + ($mobile == null ? 43 : $mobile.hashCode());
        Object $email = this.getEmail();
        result = result * 59 + ($email == null ? 43 : $email.hashCode());
        Object $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        Object $userId = this.getUserId();
        result = result * 59 + ($userId == null ? 43 : $userId.hashCode());
        Object $sort = this.getSort();
        result = result * 59 + ($sort == null ? 43 : $sort.hashCode());
        Object $comments = this.getComments();
        result = result * 59 + ($comments == null ? 43 : $comments.hashCode());
        Object $version = this.getVersion();
        result = result * 59 + ($version == null ? 43 : $version.hashCode());
        Object $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        Object $isPage = this.getIsPage();
        result = result * 59 + ($isPage == null ? 43 : $isPage.hashCode());
        Object $pageNo = this.getPageNo();
        result = result * 59 + ($pageNo == null ? 43 : $pageNo.hashCode());
        Object $pageSize = this.getPageSize();
        result = result * 59 + ($pageSize == null ? 43 : $pageSize.hashCode());
        Object $orderBy = this.getOrderBy();
        result = result * 59 + ($orderBy == null ? 43 : $orderBy.hashCode());
        Object $ids = this.getIds();
        result = result * 59 + ($ids == null ? 43 : $ids.hashCode());
        Object $keyword = this.getKeyword();
        result = result * 59 + ($keyword == null ? 43 : $keyword.hashCode());
        Object $timeFrom = this.getTimeFrom();
        result = result * 59 + ($timeFrom == null ? 43 : $timeFrom.hashCode());
        Object $timeTo = this.getTimeTo();
        result = result * 59 + ($timeTo == null ? 43 : $timeTo.hashCode());
        Object $createTime = this.getCreateTime();
        result = result * 59 + ($createTime == null ? 43 : $createTime.hashCode());
        Object $createBy = this.getCreateBy();
        result = result * 59 + ($createBy == null ? 43 : $createBy.hashCode());
        Object $lastUpdateTime = this.getLastUpdateTime();
        result = result * 59 + ($lastUpdateTime == null ? 43 : $lastUpdateTime.hashCode());
        Object $lastUpdateBy = this.getLastUpdateBy();
        result = result * 59 + ($lastUpdateBy == null ? 43 : $lastUpdateBy.hashCode());
        Object $dateRangeType = this.getDateRangeType();
        result = result * 59 + ($dateRangeType == null ? 43 : $dateRangeType.hashCode());
        Object $messageId = this.getMessageId();
        result = result * 59 + ($messageId == null ? 43 : $messageId.hashCode());
        Object $count = this.getCount();
        result = result * 59 + ($count == null ? 43 : $count.hashCode());
        Object $debug = this.getDebug();
        result = result * 59 + ($debug == null ? 43 : $debug.hashCode());
        return result;
    }

}
