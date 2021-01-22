package www.hbj.cloud.baselibrary.ngr_library;

/**
 * Manage all sharedpreference keys
 *
 * @author Administrator
 */
public class PreferenceKey {


    //SharedPreference define, begin with "app_"
    public static String PREF_LOCALSTORE = "app_local_store";
    //record all the users logined on this devices. value: phonenumber
    public static String PREF_LOCALUSER = "app_logined_user";
    public static String PREF_CONSULTATION_REPLY = "app_consultation_reply";//会诊回复的保存
    public static String PREF_CONSULTATION_APPLYFLAG = "app_consultation_applyflag";//会诊是否发起过
    public static String PREF_RELATION = "app_relation";//点赞或者粉丝的已读数
    public static String PREF_EVENT = "app_event";//启动活动页缓存
    public static String PREF_DICTIONARY = "app_dictionary";
    public static String PREF_MAIN_NEW = "app_main_new";
    public static String PREF_MARK_IN_MARKET = "app_mark_in_market";//应用市场打分
    public static String PREF_MODEL_COUNT = "app_model_count";//随机模板引导次数
    public static String PREF_MYPATIENT = "app_mypatient";//我的患者引导页
    public static String PREF_RECIPE = "pref_recipe";
    //SharedPreference key define, begin with "appkey_"
    public static String KEY_LOGINEDUSERNAME = "appkey_logined_username";
    public static String KEY_LOGINEDPWD = "appkey_logined_userpwd";

    public static String KEY_TOKEN = "appkey_token";
    public static String KEY_USER_STATE = "appkey_user_state";//busy or free; value:true->free false->busy

    public static String KEY_SIGNPATIENT_LIST = "appkey_signpatient";

    public static String KEY_SHOW_MODEL_COUNT = "key_show_model_count";
    public static String KEY_DIANZANSHU = "appkey_dianzanshu";
    public static String KEY_FENSISHU = "appkey_fensishu";
    public static String KEY_CONSULTATION_APPLYED = "appkey_consultation_applyed";
    public static String PREF_INFOS = "pref_infos";
    public static String KEY_SHOW_INQUIRECHAT_POP = "key_show_inquirechat_pop";
    public static String KEY_UPDATE_STRATEGY = "key_update_strategy";
    public static String KEY_SHOW_INQUIRE_EXPIRE_ALERT = "key_show_inquire_expire_alert";
    public static String KEY_SHOW_DOCLIST_GUIDE = "key_show_doclist_guide";
    public static String KEY_SHOW_INQUIRE_GUIDE = "key_show_inquire_guide";
    public static String KEY_SHOW_INQUIRE_GUIDE2 = "key_show_inquire_guide2";//是否在咨询首页显示引导页
    public static String KEY_SHOW_INQUIRE_GUIDE3 = "key_show_inquire_guide3";//是否显示咨询业务介绍页
    public static String KEY_SHOW_PATIENT_GUIDE = "key_show_patient_guide";
    public static String KEY_SHOW_MYPATIENT_GUIDE = "key_show_mypatient_guide";
    public static String KEY_SHOW_CHAT_GUIDE = "key_show_chat_guide";
    public static String KEY_SHOW_MAINACTIVITY_GUIDE = "key_show_mainactivity_guide";
    public static String KEY_SHOW_CHATFOLLOWUP_GUIDE = "key_show_chat_followup_guide";
    public static String KEY_SHOW_CONSULTATION_GUIDE = "key_show_consultation_guide";
    public static String KEY_SHOW_CHATFORMEETCLCNIC_GUIDE = "key_show_chatformeetclinic_guide";
    public static String KEY_SHOW_HOSPITAL_TRANSFER_GUIDE = "key_show_hospital_transfer_guide";
    public static String KEY_CONTACTS_NUM = "key_contacts_num";
    public static String KEY_SHOW_HINT_QRCODE = "key_show_hint_qrcode";
    public static String KEY_EVENT_PHOTO = "appkey_event_photo";
    public static String KEY_EVENT_LINK = "appkey_event_link";
    public static String KEY_EVENT_CONTENT = "appkey_event_content";
    public static String KEY_EVENT_STARTDATE = "appkey_event_startdate";
    public static String KEY_EVENT_ENDDATE = "appkey_event_enddate";
    public static String KEY_MEDICINE_INSTRUCTION = "appkey_medicine_instruction";
    public static String KEY_MEDICINE_USEWAY = "appkey_medicine_useway";
    public static String KEY_MEDICINE_FREQUENCY = "appkey_medicine_frequency";
    public static String KEY_MEDICINE_TYPE = "appkey_medicine_type";
    public static String KEY_MEDICINE_REQUIRE = "appkey_medicine_require";
    public static String KEY_MAININDEX_NEW = "appkey_mainindex_new";
    public static String KEY_MARK_IN_MARKET_TIMES = "appkey_mark_in_market_times";
    public static String KEY_MARK_IN_MARKET_FLAG = "appkey_mark_in_market_flag";
    public static String KEY_MARK_IN_MARKET_VERSION = "appkey_mark_in_market_version";
    public static String KEY_SHOW_ACCOUNT_POINT_DIALOG = "key_in_me_account_point";
    public static String KEY_PATIENT_NAME = "key_patient_name";
    public static String KEY_CLOUDCLINIC_STATUS = "key_cloudclinic_status";

    public static String KEY_DRUG_TYPE = "key_drug_type";
    public static String KEY_HEALTH_FILE = "key_health_file";
    public static String KEY_RECIPE_DETAIL = "key_recipe_detail";

    public static String KEY_FIRST_TYPE = "key_first_type";

    public static String KEY_ITEM_SELECT = "key_item_select";
    public static String KEY_CHECK_SELECT = "key_check_select";

    public static String PREF_SELECT_CITY = "pref_select_city";
    public static String KEY_PROVINCE = "key_first_type";
    public static String KEY_CITY = "key_city";
    public static String KEY_AREA = "key_area";
    public static String PREF_LIVE = "pref_live";
    public static String KEY_SHOW_PATIENT_MANAGEMENT_GUIDE = "key_show_patient_management_guide";
    public static String KEY_SHOW_PATIENT_FOLLOWUP_CHANGE_GUIDE = "key_show_patient_followup_change_guide";
    public static String KEY_SHOW_PATIENT_MANAGEMENT_MORE_HINT_DOT = "key_show_patient_management_more_hint_dot";
    public static String KEY_SHOW_PATIENT_MANAGEMENT_TODAY_HINT_DOT = "key_show_patient_management_today_hint_dot";
    public static String PREF_SEARCH_HISTORY = "pref_search_history";
    public static String PREF_SEARCH_EXAMINE_TEST = "pref_search_examine_test_history";
    public static String KEY_SEARCH_PATIENT_HISTORY = "key_search_patient_history";
    public static String KEY_CONSULT_ADVICE_REPLY_PHOTO_GUIDE = "key_consult_advice_reply_photo_guide";
    public static String KEY_FEED_BACK = "key_feedback";//反馈意见引导
    public static String KEY_MYPATIENT_GUID_NEW = "key_mypatient_guid_new";//患者管理页面引导页

    public static String PROTOCOL = "protocol";//用户同意协议标识name
    public static String PROTOCOL_TAG = "protocol_tag";//用户同意协议标识key
    public static String KEY_IMG_SELECT_ALL_IMGS = "app_img_select_all_imgs";//最近使用照片列表
    public static String KEY_ALL_IMGS = "key_all_imgs";//最近使用照片列表
    public static String KEY_SEARCH_HOSPITAL_HISTORY = "key_search_hospital_history";
    public static String KEY_GUIDE="pref_guide";

    public static String TAB_NAME="snjk";
    public static String KEY_SELECT_ADDRESS_DELIVERY="delivery_address";//配送地址
}
