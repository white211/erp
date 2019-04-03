package com.naswork.erp.utils.framework.pojo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @Program: User
 * @Description:
 * @Author: White
 * @DateTime: 2019-03-11 11:02:28
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User<CmsUser,CmsUserAuth> {

    private String token;
    private String ip;
    private Integer userId;
    private Integer authId;
    private String username;
    private Integer loginTimes;
    private CmsUser user;
    private CmsUserAuth userAuth;
    private List<Integer> adminIds;
    private List<String> roles;

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof User)) {
            return false;
        } else {
            User<?, ?> other = (User)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$token = this.getToken();
                Object other$token = other.getToken();
                if (this$token == null) {
                    if (other$token != null) {
                        return false;
                    }
                } else if (!this$token.equals(other$token)) {
                    return false;
                }

                Object this$ip = this.getIp();
                Object other$ip = other.getIp();
                if (this$ip == null) {
                    if (other$ip != null) {
                        return false;
                    }
                } else if (!this$ip.equals(other$ip)) {
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

                label110: {
                    Object this$authId = this.getAuthId();
                    Object other$authId = other.getAuthId();
                    if (this$authId == null) {
                        if (other$authId == null) {
                            break label110;
                        }
                    } else if (this$authId.equals(other$authId)) {
                        break label110;
                    }

                    return false;
                }

                label103: {
                    Object this$username = this.getUsername();
                    Object other$username = other.getUsername();
                    if (this$username == null) {
                        if (other$username == null) {
                            break label103;
                        }
                    } else if (this$username.equals(other$username)) {
                        break label103;
                    }

                    return false;
                }

                Object this$loginTimes = this.getLoginTimes();
                Object other$loginTimes = other.getLoginTimes();
                if (this$loginTimes == null) {
                    if (other$loginTimes != null) {
                        return false;
                    }
                } else if (!this$loginTimes.equals(other$loginTimes)) {
                    return false;
                }

                label89: {
                    Object this$user = this.getUser();
                    Object other$user = other.getUser();
                    if (this$user == null) {
                        if (other$user == null) {
                            break label89;
                        }
                    } else if (this$user.equals(other$user)) {
                        break label89;
                    }

                    return false;
                }

                label82: {
                    Object this$userAuth = this.getUserAuth();
                    Object other$userAuth = other.getUserAuth();
                    if (this$userAuth == null) {
                        if (other$userAuth == null) {
                            break label82;
                        }
                    } else if (this$userAuth.equals(other$userAuth)) {
                        break label82;
                    }

                    return false;
                }

                Object this$adminIds = this.getAdminIds();
                Object other$adminIds = other.getAdminIds();
                if (this$adminIds == null) {
                    if (other$adminIds != null) {
                        return false;
                    }
                } else if (!this$adminIds.equals(other$adminIds)) {
                    return false;
                }

                Object this$roles = this.getRoles();
                Object other$roles = other.getRoles();
                if (this$roles == null) {
                    if (other$roles != null) {
                        return false;
                    }
                } else if (!this$roles.equals(other$roles)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof User;
    }

    @Override
    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $token = this.getToken();
         result = result * 59 + ($token == null ? 43 : $token.hashCode());
        Object $ip = this.getIp();
        result = result * 59 + ($ip == null ? 43 : $ip.hashCode());
        Object $userId = this.getUserId();
        result = result * 59 + ($userId == null ? 43 : $userId.hashCode());
        Object $authId = this.getAuthId();
        result = result * 59 + ($authId == null ? 43 : $authId.hashCode());
        Object $username = this.getUsername();
        result = result * 59 + ($username == null ? 43 : $username.hashCode());
        Object $loginTimes = this.getLoginTimes();
        result = result * 59 + ($loginTimes == null ? 43 : $loginTimes.hashCode());
        Object $user = this.getUser();
        result = result * 59 + ($user == null ? 43 : $user.hashCode());
        Object $userAuth = this.getUserAuth();
        result = result * 59 + ($userAuth == null ? 43 : $userAuth.hashCode());
        Object $adminIds = this.getAdminIds();
        result = result * 59 + ($adminIds == null ? 43 : $adminIds.hashCode());
        Object $roles = this.getRoles();
        result = result * 59 + ($roles == null ? 43 : $roles.hashCode());
        return result;
    }


}
