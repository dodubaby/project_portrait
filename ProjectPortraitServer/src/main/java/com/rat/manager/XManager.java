//
//import io.rong.RongCloud;
//import io.rong.models.CheckOnlineResult;
//import io.rong.models.CodeSuccessResult;
//import io.rong.models.TokenResult;
//
///**
// * Created by liangjinzhu on 17/4/10.
// * 融云管理器
// */
//public class RongCloudManager {
//    String appKey = "8brlm7uf8jdq3";//替换成您的appkey
//    String appSecret = "3rqp9XRPyPLSC";//替换成匹配上面key的secret
//
//    private static RongCloudManager instance;
//    private RongCloud rongCloud;
//
//    public RongCloudManager() {
//        rongCloud = RongCloud.getInstance(appKey, appSecret);
//    }
//
//    //单例模式：同步锁，保证线程安全，双重检查，避免同步锁引起的性能问题
//    public static RongCloudManager getInstance() {
//        if (instance == null) {
//            synchronized (RongCloudManager.class) {
//                if (instance == null) {
//                    instance = new RongCloudManager();
//                }
//            }
//        }
//        return instance;
//    }
//
//    /**
//     * 获取 Token 方法
//     */
//    public TokenResult getToken(String userId, String userName, String icon) {
//        TokenResult userGetTokenResult = null;
//        try {
//            userGetTokenResult = rongCloud.user.getToken(userId, userName, icon);
//            System.out.println("getToken:  " + userGetTokenResult.toString());
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//        return userGetTokenResult;
//    }
//
//    /**
//     * 刷新用户信息方法
//     */
//    public CodeSuccessResult refresh() {
//        CodeSuccessResult userRefreshResult = null;
//        try {
//            userRefreshResult = rongCloud.user.refresh("userId1", "username", "http://www.rongcloud.cn/images/logo.png");
//            System.out.println("refresh:  " + userRefreshResult.toString());
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//        return userRefreshResult;
//    }
//
//    /**
//     * 检查用户在线状态 方法
//     */
//    public CheckOnlineResult checkOnline() {
//        CheckOnlineResult userCheckOnlineResult = null;
//        try {
//            userCheckOnlineResult = rongCloud.user.checkOnline("userId1");
//            System.out.println("checkOnline:  " + userCheckOnlineResult.toString());
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//        return userCheckOnlineResult;
//    }
//}
