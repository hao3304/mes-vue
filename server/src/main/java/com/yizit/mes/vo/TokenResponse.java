package com.yizit.mes.vo;
/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 李江峰
 * Date: 2017-12-15
 * Time: 13:25
 */
public class TokenResponse {

    private Long expired;

    private String token;

    public Long getExpired() {
        return expired;
    }

    public TokenResponse(String token,Long expiration) {
        this.token = token;
        this.expired = expiration;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setExpired(Long expired) {
        this.expired = expired;
    }


}
