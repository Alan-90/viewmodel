# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
  -keepattributes Signature
  -keep class sun.misc.Unsafe { *; }
  -keep class com.taobao.** {*;}
  -keep class com.alibaba.** {*;}
  -keep class com.alipay.** {*;}
  -dontwarn com.taobao.**
  -dontwarn com.alibaba.**
  -dontwarn com.alipay.**
  -keep class com.ut.** {*;}
  -dontwarn com.ut.**
  -keep class com.ta.** {*;}
  -dontwarn com.ta.**
  -keep class org.json.** {*;}
  -keep class com.ali.auth.**  {*;}

  -keep public class * extends cn.bingoogolapple.bgabanner.BGABanner

  -keepattributes EnclosingMethod
  -keep public class * extends com.snsj.ngr_library.base.BaseMvpActivity
  -keep public class * extends com.snsj.ngr_library.base.BaseActivity
  -keep public class * extends com.snsj.ngr_library.base.BaseMvcActivity
  -keep public class * extends com.snsj.ngr_library.base.BaseMvpFragment
  -keep public class * extends com.snsj.ngr_library.base.BasePresenter
  -keep public class * extends com.snsj.ngr_library.base.BaseView
  -keep public class * extends android.app.Activity
  -keep public class * extends android.app.Fragment
  -keep public class * extends android.app.Application
  -keep public class * extends android.app.Service
  -keep public class * extends android.app.IntentService
  -keep public class * extends android.content.BroadcastReceiver
  -keep public class * extends android.content.ContentProvider
  -keep public class * extends android.app.backup.BackupAgentHelper
  -keep public class * extends android.preference.Preference
  -keep public class com.android.vending.licensing.ILicensingService
  -keep public class * extends android.support.v4.**
  -keep public class * extends android.support.v7.**
  -dontwarn android.app.Notification
  -dontwarn android.net.SSLCertificateSocketFactory
  -dontwarn android.util.FloatMath
  -dontwarn android.test.**
  -dontwarn com.snsj.ngr_library.**
  -dontwarn com.snsj.snjk.**
  -dontwarn org.junit.**
  -dontwarn android.utils.imagecache.*
  -dontwarn android.utils.*
  -dontwarn android.view.*
  -dontwarn com.alibaba.baichuan.android.trade.*
  -dontwarn com.alibaba.baichuan.android.trade.common.*
  -keepattributes *Annotation*


  -keepclassmembers enum * { *; }
  -keepnames class * implements java.io.Serializable
  -keepnames class * implements java.io.View
  -keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
  }


  # To enable ProGuard in your project, edit project.properties
  # to define the proguard.config property as described in that file.
  #
  # Add project specific ProGuard rules here.
  # By default, the flags in this file are appended to flags specified
  # in ${sdk.dir}/tools/proguard/proguard-android.txt
  # You can edit the include path and order by changing the ProGuard
  # include property in project.properties.
  #
  # For more details, see
  #   http://developer.android.com/guide/developing/tools/proguard.html

  # Add any project specific keep options here:

  # If your project uses WebView with JS, uncomment the following
  # and specify the fully qualified class name to the JavaScript interface
  # class:
  #-keepclassmembers class fqcn.of.javascript.interface.for.webview {
  #   public *;
  #}
  -keepattributes EnclosingMethod
  -keep public class * extends android.app.Activity
  -keep public class * extends android.view.View
  -keep public class * extends android.app.Fragment
  -keep public class * extends android.app.Application
  -keep public class * extends android.app.Service
  -keep public class * extends android.app.IntentService
  -keep public class * extends android.content.BroadcastReceiver
  -keep public class * extends android.content.ContentProvider
  -keep public class * extends android.app.backup.BackupAgentHelper
  -keep public class * extends android.preference.Preference
  -keep public class com.android.vending.licensing.ILicensingService
  -keep public class * extends android.support.v4.**
  -keep public class * extends android.support.v7.**
  -dontwarn android.app.Notification
  -dontwarn android.net.SSLCertificateSocketFactory
  -dontwarn android.util.FloatMath
  -dontwarn android.test.**
  -dontwarn org.junit.**
  -dontwarn android.utils.imagecache.*
  -dontwarn android.utils.*
  -dontwarn android.view.*
  -keepattributes *Annotation*
  -keep class * extends java.lang.annotation.Annotation { *; }
  -keepattributes Signature
  -keep class sun.misc.Unsafe { *; }
  -keep class android.app.Notification { *;}

  #v4
  -dontwarn android.support.v4.**

  #BuildConfig
  -keep class com.android.syslib.BuildConfig { *; }
  -keep class com.easygroup.ngaridoctor.BuildConfig { *; }

  -dontwarn com.j256.**
  -keep class com.j256.** { *; }

  -keep public class * extends com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
  -keep public class * extends com.j256.ormlite.android.apptools.OpenHelperManager
  -keep public class * extends com.j256.ormlite.dao
  -keep class com.j256.ormlite.** { *; }
  -keep class com.j256.ormlite.android.** { *; }
  -keep class com.j256.ormlite.field.** { *; }
  -keep class com.j256.ormlite.stmt.** { *; }
  -keep public class * extends cn.bingoogolapple.bgabanner.BGABanner
  -keep public class * extends in.srain.cube.views.ptr.PtrFrameLayout
  #Alipay
  -keep class com.alipay.android.app.IAlixPay{*;}
  -keep class com.alipay.android.app.IAlixPay$Stub{*;}
  -keep class com.alipay.android.app.IRemoteServiceCallback{*;}
  -keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
  -keep class com.alipay.sdk.app.PayTask{ public *;}
  -keep class com.alipay.sdk.app.AuthTask{ public *;}

  #webView
  -keepattributes *JavascriptInterface*
  -keepclassmembers class * extends android.webkit.WebViewClient {
      public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
      public boolean *(android.webkit.WebView, java.lang.String);
  }
  -keepclassmembers class * extends android.webkit.WebViewClient {
      public void *(android.webkit.WebView, java.lang.String);
  }
  -keep public class android.net.http.SslError

  #easeui
  -keep class com.hyphenate.** {*;}
  -dontwarn com.hyphenate.**
  -keep class org.xmlpull.** {*;}
  -keep class com.baidu.** {*;}
  -keep public class * extends com.umeng.**
  -keep class com.umeng.** { *; }
  -keep class com.squareup.picasso.* {*;}
  -keep class com.hyphenate.chat.** {*;}
  -keep class org.jivesoftware.** {*;}
  #另外，demo中发送表情的时候使用到反射，需要keep SmileUtils,注意前面的包名，
  #不要SmileUtils复制到自己的项目下keep的时候还是写的demo里的包名
  -keep class com.hyphenate.chatuidemo.utils.SmileUtils {*;}

  #2.0.9后加入语音通话功能，如需使用此功能的api，加入以下keep
  -keep class net.java.sip.** {*;}
  -keep class org.webrtc.voiceengine.** {*;}
  -keep class org.bitlet.** {*;}
  -keep class org.slf4j.** {*;}
  -keep class ch.imvs.** {*;}

  -keep class com.huawei.android.pushagent.** {*;}
  -keep class com.huawei.android.pushselfshow.** {*;}
  -keep class com.huawei.android.microkernel.** {*;}
  -keep class com.baidu.mapapi.** {*;}
  -dontwarn com.huawei.android.**

  #jackson
  -keep class com.fasterxml.jackson.**
  -keep class com.fasterxml.jackson.databind.**
  -dontwarn org.w3c.dom.bootstrap.DOMImplementationRegistry
  -keepclassmembers public final enum com.fasterxml.jackson.annotation.JsonAutoDetect$Visibility {
  public static final com.fasterxml.jackson.annotation.JsonAutoDetect$Visibility *; }
  -dontwarn com.fasterxml.jackson.databind.**
  #xutils
  -dontwarn com.lidroid.xutils.**
  -keep class com.lidroid.xutils.**{*;}
  -keep class * extends com.android.sys.component.dbentity.EntityBase { *; }

  #xinge
  -keep class com.tencent.android.tpush.**  {* ;}
  -keep class com.tencent.mid.**  {* ;}


  #umeng
  -keepclassmembers class * {
     public <init> (org.json.JSONObject);
  }


  #httpclient (org.apache.http.legacy.jar)
  -dontwarn android.net.compatibility.**
  -dontwarn android.net.http.**
  -dontwarn com.android.internal.http.multipart.**
  -dontwarn org.apache.commons.**
  -dontwarn org.apache.http.**
  -dontwarn org.apache.http.protocol.**
  -keep class android.net.compatibility.**{*;}
  #-keep class android.net.http.**{*;}
  -keep class com.android.internal.http.multipart.**{*;}
  -keep class org.apache.commons.**{*;}
  -keep class org.apache.org.**{*;}
  -keep class org.apache.harmony.**{*;}
  -keep class org.apache.http.** {*; }
  -keep class org.apache.**{*;}
  #glide
  -keep public class * implements com.bumptech.glide.module.GlideModule
  -keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
  }
  -keep class com.bumptech.glide.** { *; }

  #gson
  -dontwarn com.google.gson**
  -keep class com.google.gson.**{*;}

  #eventbus
  -keepclassmembers class ** {
      public void onEvent*(**);
  }

  #ngariself
  -keep class com.easygroup.ngaridoctor.Config
  -keep class com.easygroup.ngaridoctor.http.model.** { *;}
  -keep class com.easygroup.ngaridoctor.http.request.** { *;}
  -keep class com.easygroup.ngaridoctor.http.request_legency.** { *;}
  -keep class com.easygroup.ngaridoctor.http.response.** { *;}
  -keep class com.easygroup.ngaridoctor.http.response_legency.** { *;}
  -keep class com.easygroup.ngaridoctor.response.** { *;}
  -keep class com.easygroup.ngaridoctor.request.** { *;}
  -keep class com.easygroup.ngaridoctor.utils.JsonParse { *; }
  -keepnames class com.easygroup.ngaridoctor.utils.JsonParse$* {
      public <fields>;
      public <methods>;
  }
  -keep class eh.entity.** { *;}
  -keep public class * implements java.io.Serializable {
          public *;
          private *;
  }

  ## xiaoyu

  -keep class api.business.** { *; }
  -keep class com.ngarivideo.nemo.module.** { *;}
  -keep class android.utils.** { *; }
  -keep public class android.log.UnifiedHandler { *; }

  -optimizationpasses 5
  -dontpreverify
  -dontoptimize
  -verbose
  -keepattributes *Annotation*
  -ignorewarnings
  -keepclasseswithmembernames class * {
       native <methods>;
  }
  -keepattributes *Annotation*
  -keep enum com.ainemo.sdk.NemoSDKListener** {
      **[] $VALUES;
      public *;
  }
  -keepclassmembers enum * { *; }
  -keepnames class * implements java.io.Serializable
  -keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
  }

  -keep class com.ainemo.sdk.model.Settings{*;}
  -keep class com.ainemo.sdk.NemoSDK{
    public *;
  }
  -keep class com.ainemo.**{*;}
  -keep class android.utils.**{*;}
  -keep class android.util.**{*;}
  -keep class android.log.**{*;}
  -keep class android.http.**{*;}
  -keep class vulture.module.**{*;}
  -keep class vulture.home.call.media.omap.**{*;}

  -keep class com.google.gson.stream.** {*;}
  -keep class com.google.gson.** {*;}
  -keep class com.google.gson.Gson {*;}
  -keep class com.google.gson.examples.android.model.** {*;}
  -keep class rx.internal.util.**{*;}
  ##xioayu end

  -keep class com.tencent.mm.sdk.**{*;}


  -keep class com.ainemo.sdk.utils.SignatureHelper{*;}
  -keep class com.ainemo.sdk.module.ConnectNemoCallback{*;}
  -keep class com.ainemo.sdk.NemoReceivedCallListener{*;}
  -keep class com.ainemo.sdk.NemoSDKListener{*;}
  -keep class com.ainemo.sdk.module.data.VideoInfo{*;}
  -keep class com.ainemo.sdk.module.push.**{*;}
  -keep class com.ainemo.sdk.module.rest.**{*;}
  -keep class com.ainemo.sdk.NemoSDKErrorCode{*;}

  -keep class com.tencent.smtt.export.external.**{

      *;

  }

  -keep class com.tencent.tbs.video.interfaces.IUserStateChangedListener {

      *;

  }

  -keep class com.tencent.smtt.sdk.CacheManager {

      public *;

  }

  -keep class com.tencent.smtt.sdk.CookieManager {

      public *;

  }

  -keep class com.tencent.smtt.sdk.WebHistoryItem {

      public *;

  }

  -keep class com.tencent.smtt.sdk.WebViewDatabase {

      public *;

  }

  -keep class com.tencent.smtt.sdk.WebBackForwardList {

      public *;

  }

  -keep public class com.tencent.smtt.sdk.WebView {

      public <fields>;
      public <methods>;

  }

  -keep public class com.tencent.smtt.sdk.WebView$HitTestResult {

      public static final <fields>;
      public java.lang.String getExtra();
      public int getType();

  }

  -keep public class com.tencent.smtt.sdk.WebView$WebViewTransport {

      public <methods>;

  }

  -keep public class com.tencent.smtt.sdk.WebView$PictureListener {

      public <fields>;
      public <methods>;

  }


  -keep public enum com.tencent.smtt.sdk.WebSettings$** {

      *;

  }

  -keep public enum com.tencent.smtt.sdk.QbSdk$** {

      *;

  }

  -keep public class com.tencent.smtt.sdk.WebSettings {

      public *;

  }

  -keepattributes Signature

  -keep public class com.tencent.smtt.sdk.ValueCallback {

      public <fields>;
      public <methods>;

  }

  -keep public class com.tencent.smtt.sdk.WebViewClient {

      public <fields>;
      public <methods>;

  }

  -keep public class com.tencent.smtt.sdk.DownloadListener {

      public <fields>;
      public <methods>;

  }

  -keep public class com.tencent.smtt.sdk.WebChromeClient {

      public <fields>;
      public <methods>;

  }

  -keep public class com.tencent.smtt.sdk.WebChromeClient$FileChooserParams {

      public <fields>;
      public <methods>;

  }

  -keep class com.tencent.smtt.sdk.SystemWebChromeClient{

      public *;

  }



  -keep public class com.tencent.smtt.export.external.extension.interfaces.* {

      public protected *;

  }



  -keep public class com.tencent.smtt.export.external.interfaces.* {

      public protected *;

  }

  -keep public class com.tencent.smtt.sdk.WebViewCallbackClient {

      public protected *;

  }

  -keep public class com.tencent.smtt.sdk.WebStorage$QuotaUpdater {

      public <fields>;
      public <methods>;

  }

  -keep public class com.tencent.smtt.sdk.WebIconDatabase {

      public <fields>;
      public <methods>;

  }

  -keep public class com.tencent.smtt.sdk.WebStorage {

      public <fields>;
      public <methods>;

  }

  -keep public class com.tencent.smtt.sdk.DownloadListener {

      public <fields>;
      public <methods>;

  }

  -keep public class com.tencent.smtt.sdk.QbSdk {

      public <fields>;
      public <methods>;

  }

  -keep public class com.tencent.smtt.sdk.QbSdk$PreInitCallback {

      public <fields>;
      public <methods>;

  }

  -keep public class com.tencent.smtt.sdk.CookieSyncManager {

      public <fields>;
      public <methods>;

  }

  -keep public class com.tencent.smtt.sdk.Tbs* {

      public <fields>;
      public <methods>;

  }

  -keep public class com.tencent.smtt.utils.LogFileUtils {

      public <fields>;
      public <methods>;

  }

  -keep public class com.tencent.smtt.utils.TbsLog {

      public <fields>;
      public <methods>;

  }

  -keep public class com.tencent.smtt.utils.TbsLogClient {

      public <fields>;
      public <methods>;

  }

  -keep public class com.tencent.smtt.sdk.CookieSyncManager {

      public <fields>;
      public <methods>;

  }



  -keep public class com.tencent.smtt.sdk.TBSGamePlayer {

      public <fields>;
      public <methods>;

  }

  -keep public class com.tencent.smtt.sdk.TBSGamePlayerClient* {

      public <fields>;
      public <methods>;

  }

  -keep public class com.tencent.smtt.sdk.TBSGamePlayerClientExtension {

      public <fields>;
      public <methods>;

  }

  -keep public class com.tencent.smtt.sdk.TBSGamePlayerService* {

      public <fields>;
      public <methods>;

  }

  -keep public class com.tencent.smtt.utils.Apn {

      public <fields>;
      public <methods>;

  }



  -keep public class com.tencent.smtt.export.external.extension.proxy.ProxyWebViewClientExtension {

      public <fields>;
      public <methods>;

  }

  -keep class MTT.ThirdAppInfoNew {

      *;

  }

  -keep class com.tencent.mtt.MttTraceEvent {

      *;

  }



  -keep public class com.tencent.smtt.gamesdk.* {

      public protected *;

  }

  -keep public class com.tencent.smtt.sdk.TBSGameBooter {

          public <fields>;
          public <methods>;

  }

  -keep public class com.tencent.smtt.sdk.TBSGameBaseActivity {

      public protected *;

  }

  -keep public class com.tencent.smtt.sdk.TBSGameBaseActivityProxy {

      public protected *;

  }

  -keep public class com.tencent.smtt.gamesdk.internal.TBSGameServiceClient {

      public *;

  }
  #ARouter
  -keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}
  -keep class * implements com.alibaba.android.arouter.facade.template.IProvider{*;}
  -keep class com.easygroup.ngaridoctor.moduleservice.** { *;}
  -keep class com.easygroup.ngaridoctor.router.** { *;}
  -keep class com.alibaba.android.arouter.routes.** { *;}

  #Okhttp
  -dontwarn okio.**
  -dontwarn javax.annotation.Nullable
  -dontwarn javax.annotation.ParametersAreNonnullByDefault
  #com.yanzhenjie:permission
  -keepclassmembers class ** {
      @com.yanzhenjie.permission.PermissionYes <methods>;
  }
  -keepclassmembers class ** {
      @com.yanzhenjie.permission.PermissionNo <methods>;
  }

  -keep class com.xiaomi.mipush.sdk.PushMessageReceiver

  -keepattributes Exceptions
  -keepattributes SourceFile,LineNumberTable
  -keep class com.hianalytics.android.**{*;}
  -keep class com.huawei.updatesdk.**{*;}
  -keep class com.huawei.hms.**{*;}
  -keep class com.android.hms.agent.**{*;}
  -keep class com.tencent.** { *; }


  -keep class com.xikang.xksocket.**{*;}
  -dontwarn com.xikang.xksocket.**
  -keep class com.xikang.xksocket.socket.bean.** {*;}
  -keep class io.socket.** {*;}
  -keep class qalsdk.**{*;}
  -dontwarn qalsdk.**

  -keep class com.alibaba.baichuan.trade.common.**{*;}
  -keep class com.alibaba.baichuan.trade.trade.**{*;}


-keep class com.umeng.** {*;}
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep public class [com.snsj.snjk].R$*{
public static final int *;
}


-keepattributes Signature
-ignorewarnings
-keep class javax.ws.rs.** { *; }
-keep class com.alibaba.fastjson.** { *; }
-dontwarn com.alibaba.fastjson.**
-keep class sun.misc.Unsafe { *; }
-dontwarn sun.misc.**
-keep class com.taobao.** {*;}
-keep class com.alibaba.** {*;}
-keep class com.alipay.** {*;}
-dontwarn com.taobao.**
-dontwarn com.alibaba.**
-dontwarn com.alipay.**
-keep class com.ut.** {*;}
-dontwarn com.ut.**
-keep class com.ta.** {*;}
-dontwarn com.ta.**
-keep class org.json.** {*;}
-keep class com.ali.auth.**  {*;}
-dontwarn com.ali.auth.**
-keep class com.taobao.securityjni.** {*;}
-keep class com.taobao.wireless.security.** {*;}
-keep class com.taobao.dp.**{*;}
-keep class com.alibaba.wireless.security.**{*;}
-keep interface mtopsdk.mtop.global.init.IMtopInitTask {*;}
-keep class * implements mtopsdk.mtop.global.init.IMtopInitTask {*;}



# Retrofit

-dontwarn retrofit2.**

-dontwarn org.codehaus.mojo.**

-keep class retrofit2.** { *; }

-keepattributes Signature

-keepattributes Exceptions

-keepattributes *Annotation*

-keepattributes RuntimeVisibleAnnotations

-keepattributes RuntimeInvisibleAnnotations

-keepattributes RuntimeVisibleParameterAnnotations

-keepattributes RuntimeInvisibleParameterAnnotations

-keepattributes EnclosingMethod

-keepclasseswithmembers class * {

@retrofit2.* <methods>;

}

-keepclasseswithmembers interface * {

@retrofit2.* <methods>;

}

  -keep class com.snsj.snjk.model.** { *;}

  -keep class com.snsj.snjk.ui.order.shop.bean.** { *;}

  -keep class com.snsj.snjk.ui.search.bean.** { *;}
-keepattributes InnerClasses


#eventBus
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}
 -keep interface me.jessyan.autosize.** { *; }