package www.hbj.cloud.baselibrary.ngr_library.component;

public enum SysImageSizeConfig {
    DoctorAvatar("?w=128&h=128"), //医生头像
    PatientAvatar("?w=128&h=128"),  //病人头像
    Image("?w=160&h=160"),  //一般图片资料，包括电子病历的缩略图等
    OrganImage("?w=160&h=120"),  //机构图片
    ChatImage("?w=200&h=200"), //讨论组中的聊天图片
    RawImage("?w=1080&h=1920"); //原始尺寸的图片（暂时ios用）

    public String appendStr;

    private SysImageSizeConfig(String appendStr) {
        this.appendStr = appendStr;
    }

    @Override
    public String toString() {
        return appendStr;
    }
}