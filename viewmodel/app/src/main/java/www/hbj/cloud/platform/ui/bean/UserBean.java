package www.hbj.cloud.platform.ui.bean;

import java.io.Serializable;

/**
 * author : ZYK
 * createTime   : 2020/12/16
 * function   :
 */
public class UserBean implements Serializable {

    private String id;
    private String uid;
    private String email;
    private String password;
    private String salt;
    private boolean emailStatus;
    private String isActivated;
    private String twiceAuthWay;
    private String safeLevel;
    private String tel;
    private int countryCode;
    private boolean telStatus;
    private String zendeskId;
    private String isGoogleAuthed;
    private boolean googleStatus;
    private String googleAuthUrl;
    private String vip;
    private String inviteCode;
    private String userLevel;
    private String checkingLevel;
    private String ip;
    private String timestamp;
    private String isFirstLogin;
    private String uuid;
    private String secondTime;
    private String thirdTime;
    private String userType;
    private String userRegisterType;
    private int transferSwitch;
    private String createTime;
    private String feeFree;
    private String identificationStatus;//0-未认证 1-待审核 2-审核通过 3-审核未通过
    private int countryId;

    public String getId() {
        return id;
    }

    public String getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public boolean isEmailStatus() {
        return emailStatus;
    }

    public String getIsActivated() {
        return isActivated;
    }

    public String getTwiceAuthWay() {
        return twiceAuthWay;
    }

    public String getSafeLevel() {
        return safeLevel;
    }

    public String getTel() {
        return tel;
    }

    public int getCountryCode() {
        return countryCode;
    }

    public boolean isTelStatus() {
        return telStatus;
    }

    public String getZendeskId() {
        return zendeskId;
    }

    public String getIsGoogleAuthed() {
        return isGoogleAuthed;
    }

    public boolean isGoogleStatus() {
        return googleStatus;
    }

    public String getGoogleAuthUrl() {
        return googleAuthUrl;
    }

    public String getVip() {
        return vip;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public String getCheckingLevel() {
        return checkingLevel;
    }

    public String getIp() {
        return ip;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getIsFirstLogin() {
        return isFirstLogin;
    }

    public String getUuid() {
        return uuid;
    }

    public String getSecondTime() {
        return secondTime;
    }

    public String getThirdTime() {
        return thirdTime;
    }

    public String getUserType() {
        return userType;
    }

    public String getUserRegisterType() {
        return userRegisterType;
    }


    public String getCreateTime() {
        return createTime;
    }

    public String getFeeFree() {
        return feeFree;
    }

    public String getIdentificationStatus() {
        return identificationStatus;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setEmailStatus(boolean emailStatus) {
        this.emailStatus = emailStatus;
    }

    public void setIsActivated(String isActivated) {
        this.isActivated = isActivated;
    }

    public void setTwiceAuthWay(String twiceAuthWay) {
        this.twiceAuthWay = twiceAuthWay;
    }

    public void setSafeLevel(String safeLevel) {
        this.safeLevel = safeLevel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setCountryCode(int countryCode) {
        this.countryCode = countryCode;
    }

    public void setTelStatus(boolean telStatus) {
        this.telStatus = telStatus;
    }

    public void setZendeskId(String zendeskId) {
        this.zendeskId = zendeskId;
    }

    public void setIsGoogleAuthed(String isGoogleAuthed) {
        this.isGoogleAuthed = isGoogleAuthed;
    }

    public void setGoogleStatus(boolean googleStatus) {
        this.googleStatus = googleStatus;
    }

    public void setGoogleAuthUrl(String googleAuthUrl) {
        this.googleAuthUrl = googleAuthUrl;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public void setCheckingLevel(String checkingLevel) {
        this.checkingLevel = checkingLevel;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setIsFirstLogin(String isFirstLogin) {
        this.isFirstLogin = isFirstLogin;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setSecondTime(String secondTime) {
        this.secondTime = secondTime;
    }

    public void setThirdTime(String thirdTime) {
        this.thirdTime = thirdTime;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setUserRegisterType(String userRegisterType) {
        this.userRegisterType = userRegisterType;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setFeeFree(String feeFree) {
        this.feeFree = feeFree;
    }

    public void setIdentificationStatus(String identificationStatus) {
        this.identificationStatus = identificationStatus;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getTransferSwitch() {
        return transferSwitch;
    }

    public void setTransferSwitch(int transferSwitch) {
        this.transferSwitch = transferSwitch;
    }
}
