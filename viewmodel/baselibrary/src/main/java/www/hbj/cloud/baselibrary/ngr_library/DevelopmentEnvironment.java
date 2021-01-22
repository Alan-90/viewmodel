package www.hbj.cloud.baselibrary.ngr_library;

public enum DevelopmentEnvironment {
    //http://mp.app307.com/ http://csxcx.app307.com/
    SnjkTest("http://mp.app307.com/", "http://gh.app307.com", "http://api.yy.ttypapp.com/"),
    SnjkRelease("https://snapi.app307.com/", "https://ghxj.app307.com/", "https://api.sn.ttypapp.com/"),
    SnjkPreRelease("http://snzsc.app307.com/", "http://ghxj.app307.com/", "http://api.sn.ttypapp.com/");

    private String chatIdPrefix;

    public String getChatIdPrefixNew() {
        return chatIdPrefixNew;
    }

    public void setChatIdPrefixNew(String chatIdPrefixNew) {
        this.chatIdPrefixNew = chatIdPrefixNew;
    }

    private String chatIdPrefixNew;
    private String hostUrl;

    DevelopmentEnvironment(String chatIdPrefix, String chatIdPrefixNew, String hostUrl) {

        this.chatIdPrefix = chatIdPrefix;
        this.hostUrl = hostUrl;
        this.chatIdPrefixNew = chatIdPrefixNew;

    }

    public String getChatIdPrefix() {
        return chatIdPrefix;
    }

    public void setChatIdPrefix(String chatIdPrefix) {
        this.chatIdPrefix = chatIdPrefix;
    }

    public String getHostUrl() {
        return hostUrl;
    }

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }
}