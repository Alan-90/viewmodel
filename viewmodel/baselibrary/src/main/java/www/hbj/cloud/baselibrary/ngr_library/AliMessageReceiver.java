package www.hbj.cloud.baselibrary.ngr_library;

//import com.alibaba.sdk.android.push.MessageReceiver;
//import com.alibaba.sdk.android.push.notification.CPushMessage;


//public class AliMessageReceiver extends MessageReceiver {
//
//
//    // 消息接收部分的LOG_TAG
//    public static final String REC_TAG = "receiver";
//    public static final String TYPE = "type"; //这个type是为了Notification更新信息的，这个不明白的朋友可以去搜搜，很多
//
//
//    @Override
//    public void onNotification(Context context, String title, String summary, Map<String, String> extraMap) {
//// TODO 处理推送通知
//        Log.e("MyMessageReceiver", "Receive notification, title: " + title + ", summary: " + summary + ", extraMap: " + extraMap);
////        showNotification(context,title,summary);
//    }
//
//    @Override
//    public void onMessage(Context context, CPushMessage cPushMessage) {
//        Log.e("MyMessageReceiver", "onMessage, messageId: " + cPushMessage.getMessageId() + ", title: " + cPushMessage.getTitle() + ", content:" + cPushMessage.getContent());
//        buildNotification(context,cPushMessage);
//    }
//
//    @Override
//    public void onNotificationOpened(Context context, String title, String summary, String extraMap) {
//        Log.e("MyMessageReceiver", "onNotificationOpened, title: " + title + ", summary: " + summary + ", extraMap:" + extraMap);
//    }
//
//    @Override
//    protected void onNotificationClickedWithNoAction(Context context, String title, String summary, String extraMap) {
//        Log.e("MyMessageReceiver", "onNotificationClickedWithNoAction, title: " + title + ", summary: " + summary + ", extraMap:" + extraMap);
//    }
//
//    @Override
//    protected void onNotificationReceivedInApp(Context context, String title, String summary, Map<String, String> extraMap, int openType, String openActivity, String openUrl) {
//        Log.e("MyMessageReceiver", "onNotificationReceivedInApp, title: " + title + ", summary: " + summary + ", extraMap:" + extraMap + ", openType:" + openType + ", openActivity:" + openActivity + ", openUrl:" + openUrl);
//    }
//
//    @Override
//    protected void onNotificationRemoved(Context context, String messageId) {
//        Log.e("MyMessageReceiver", "onNotificationRemoved");
//    }
//    public void buildNotification(Context context, CPushMessage message) {
//        //1.获取通知管理器类
//        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        /**
//         * 兼容Android版本8.0系统
//         */
//        String channeId = "1";
//        String channelName = "default";
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel(channeId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
//            channel.enableLights(true);         // 开启指示灯，如果设备有的话
//            channel.setLightColor(Color.RED);   // 设置指示灯颜色
//            channel.setShowBadge(true);         // 检测是否显示角标
//            notificationManager.createNotificationChannel(channel);
//        }
//        //2.构建通知类
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(Config.mContext, "1");
//        builder.setSmallIcon(R.drawable.ic_launcher);//设置小图标
//        builder.setContentTitle(message.getTitle());//标题
//        builder.setContentText(message.getContent());//内容
//        builder.setWhen(System.currentTimeMillis());    //时间
//        builder.setAutoCancel(true);
////        Intent intent=new  Intent(context, CaptureActivity.class);
////        PendingIntent pi=PendingIntent.getActivity(context, 0,
////                intent, 0);
//        Intent intentCancel = new Intent(context, AliMessageClickReceiver.class);
//        intentCancel.setAction("notification_clicked");
//        intentCancel.putExtra(AliMessageClickReceiver.TYPE, "notification_clicked");
//        PendingIntent pendingIntentCancel = PendingIntent.getBroadcast(context, 0, intentCancel, PendingIntent.FLAG_ONE_SHOT);
//
//        builder .setContentIntent(pendingIntentCancel);
//        builder.setDeleteIntent(pendingIntentCancel);
//
//        //3.获取通知
//        Notification notification = builder.build();
//
////        notification.contentIntent = buildClickContent(context, message);
////        notification.deleteIntent = buildDeleteContent(context, message);
//
//        //4.发送通知
//        notificationManager.notify(100, notification);
////        notification.contentIntent = buildClickContent(context, message);
////        notification.deleteIntent = buildDeleteContent(context, message);
////        notificationManager.notify(message.hashCode(), notification);
//    }
//
//    // 通知栏显示当前播放信息，利用通知和 PendingIntent来启动对应的activity
//    public void showNotification(Context context, String title,String summery) {
//        //1.获取通知管理器类
//        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        /**
//         * 兼容Android版本8.0系统
//         */
//        String channeId = "1";
//        String channelName = "default";
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel(channeId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
//            channel.enableLights(true);         // 开启指示灯，如果设备有的话
//            channel.setLightColor(Color.RED);   // 设置指示灯颜色
//            channel.setShowBadge(true);         // 检测是否显示角标
//            notificationManager.createNotificationChannel(channel);
//        }
//        //2.构建通知类
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(Config.mContext, "1");
//        builder.setSmallIcon(R.drawable.ic_launcher);//设置小图标
//        builder.setContentTitle(title);//标题
//        builder.setContentText(summery);//内容
//        builder.setWhen(System.currentTimeMillis());    //时间
//
//        //3.获取通知
//        Notification notification = builder.build();
//
////        notification.contentIntent = buildClickContent(context, message);
////        notification.deleteIntent = buildDeleteContent(context, message);
//
//        //4.发送通知
//        notificationManager.notify(100, notification);
//    }
//    private int clickNotificationCode=0;
//    public PendingIntent buildClickContent(Context context, CPushMessage message) {
//        Intent clickIntent = new Intent();
//        clickIntent.setAction("your notification click action");
//        //添加其他数据
//        clickIntent.putExtra("message key",  message);//将message放入intent中，方便通知自建通知的点击事件
//        return PendingIntent.getService(context, clickNotificationCode, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//    }
//    public PendingIntent buildDeleteContent(Context context, CPushMessage message) {
//        Intent deleteIntent = new Intent();
//        deleteIntent.setAction("your notification click action");
//        //添加其他数据
//        deleteIntent.putExtra("message key",  message);//将message放入intent中，方便通知自建通知的点击事件
//        return PendingIntent.getService(context, clickNotificationCode, deleteIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//    }
//
//}



