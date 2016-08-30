package cn.rvlr.recyclerviewloadrefresh.data.constants;

import android.os.Build;
import android.os.Environment;
import java.math.BigDecimal;

import cn.rvlr.recyclerviewloadrefresh.BuildConfig;

/**
 *常量对象
 */

public class Constant {

    // APP_ID 替换为你的应用从官方网站申请到的合法appId
    //public static final String APP_ID = "wx8840ecf505962451";

    public static class ShowMsgActivity {
        public static final String STitle = "showmsg_title";
        public static final String SMessage = "showmsg_message";
        public static final String BAThumbData = "showmsg_thumb_data";
    }


    public static final String TOKEN = "Auth";
    public static final String USER_AGENT = "User-Agent";
    public static final int LOGIN_CODE = 999;
    public static final int SUCCESS_CODE = 100;
    public static final BigDecimal ZERO = new BigDecimal("0.00");

    public static String USER_AGENT_VALUE = "rvlr " + BuildConfig.APPLICATION_ID + " " + Build.MODEL + " " + Build.BRAND + " " + Build.VERSION.SDK_INT + " " + BuildConfig.VERSION_NAME + " " + BuildConfig.VERSION_CODE;


    public static final String NEW_FRIENDS_USERNAME = "item_new_friends";
    public static final String GROUP_USERNAME = "item_groups";
    public static final String CHAT_ROOM = "item_chatroom";
    public static final String ACCOUNT_REMOVED = "account_removed";
    public static final String ACCOUNT_CONFLICT = "conflict";
    public static final String CHAT_ROBOT = "item_robots";
    public static final String MESSAGE_ATTR_ROBOT_MSGTYPE = "msgtype";
    public static final String ACTION_GROUP_CHANAGED = "action_group_changed";
    public static final String ACTION_CONTACT_CHANAGED = "action_contact_changed";


    public static final String MESSAGE_ATTR_IS_VOICE_CALL = "is_voice_call";
    public static final String MESSAGE_ATTR_IS_VIDEO_CALL = "is_video_call";

    public static final String MESSAGE_ATTR_IS_BIG_EXPRESSION = "em_is_big_expression";
    public static final String MESSAGE_ATTR_EXPRESSION_ID = "em_expression_id";
//    /**
//     * app自带的动态表情，直接去resource里获取图片
//     */
//    public static final String MESSAGE_ATTR_BIG_EXPRESSION_ICON = "em_big_expression_icon";
//    /**
//     * 动态下载的表情图片，需要知道表情url
//     */
//    public static final String MESSAGE_ATTR_BIG_EXPRESSION_URL = "em_big_expression_url";


    /**
     * CHATTYPE_SINGLE =1为单聊天
     ***/
    public static final int CHATTYPE_SINGLE = 1;
    /**
     * CHATTYPE_GROUP = 2为群聊天
     ***/
    public static final int CHATTYPE_GROUP = 2;

    public static final int CHATTYPE_CHATROOM = 3;

    /****
     * 聊天类型 EXTRA_CHAT_TYPE = "chatType"
     ***/
    public static final String EXTRA_CHAT_TYPE = "chatType";
    /***
     * 会话人或着群id, EXTRA_USER_ID = "userId"
     ***/
    public static final String EXTRA_USER_ID = "userId";


    /***传入聊天的用户头像 UserAvatar="useravatar"****/
//    public static  final String  UserAvatar="useravatar";
    /*****传入聊天的用户昵称NickName="nickname"****/
//    public static  final  String  NickName="nickname";


    /***
     * 到相册或者拍照来获取图片
     */
    public static final int UPDATE_AVATAR = 11086;
    /***
     * 更新昵称
     */
    public static final int UPDATE_NICKNAME = 11087;
    /***
     * 更新简介介绍
     */
    public static final int UPDATE_ABOUT = 11089;
    /***
     * 更改门牌号
     */
    public static final int UPDATE_ADDRESS_DOOR = 11088;
    /*
     * 更新省市区信息
     */
    public static final String UPDATE_ADDRESS = "UPDATE_ADDRESS";

    /*
     * 同意好友申请或者群组申请
     */
    public static final String AGRESS_FRIENDS_INVITE = "AGRESS_FRIENDS_INVITE";

    /*
     *搜索页面选择目的地信息的广播
     */
    public static final String SEARCH_ADDRESS = "SEARCH_ADDRESS";
    /**
     * 使用照相机拍照获取图片
     */
    public static final int SELECT_PIC_BY_TACK_PHOTO = 1;
    /**
     * 使用相册中的图片
     */
    public static final int SELECT_PIC_BY_PICK_PHOTO = 2;

    /*
     *每分钟更新地址信息的广播
     */
    public static final String UPDATE_ADDRESS_POINT_BROADCAST = "UPDATE_ADDRESS_POINT_BROADCAST";


    /**
     * SD卡路径
     */
    public static final String PATH = Environment.getExternalStorageDirectory()
            .getPath() + "/fmy/chat";


    public static String FILE_CACHE = PATH + "/data/";// sd卡数据缓存目录：
    public static String FILE_IMGDATA = PATH + "/imgdata/";// sd存的图片


    //appid
    //请同时修改  androidmanifest.xml里面，.PayActivityd里的属性<data android:scheme="wxb4ba3c02aa476ea1"/>为新设置的appid
    public static final String APP_ID = "wx8840ecf505962451";

    /**
     * 担保支付
     */
    public static final int TRADE_CREDIT = 1;
    /**
     * 支付宝支付
     */
    public static final int TRADE_ALIPAY = 2;

    /**
     * 微信支付
     */
    public static final int TRADE_WXPAY = 3;


    public static final int SDK_PAY_FLAG = 1;

    public static final String key = ")OefNB]cF}+[6,Y5";

    public static final String iv = "2014-01-30617:12";


    //视频

    public static final String VIDEO_POSITION = "video_position";
    public static final String VIDEO_FILES = "video_files";
    public static final String VIDEO_FROM = "video_from";

    public static final int ONLINE = 0x1;
    public static final int LOCAL = 0x2;


    /**********
     * 主页mainActivity
     ****************/
    // 首页fragment索引值
    public static final int HOME_FRAGMENT_INDEX = 0;
    // 订单fragment索引值
    public static final int CATEGORY_FRAGMENT_INDEX = 1;
    // 家具圈fragment索引值
    public static final int COLLECT_FRAGMENT_INDEX = 2;
    // 设置fragment索引值
    public static final int SETTING_FRAGMENT_INDEX = 3;


    public static final int TYPE_ZERO = 0;
    public static final int TYPE_ONE = 1;
    public static final int TYPE_TWO = 2;
    public static final int TYPE_THREE = 3;
    public static final int TYPE_FOUR = 4;
    public static final int TYPE_FIVE = 5;
    public static final int TYPE_SIX = 6;
    public static final int TYPE_SEVEN = 7;
    public static final int TYPE_EIGHT = 8;
    public static final int TYPE_NINE = 9;




}
