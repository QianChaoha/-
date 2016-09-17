package com.example.railway.uitl;

/**
 * Title:
 * Description:
 * Company:
 *
 * @author qianchao
 * @date 2016-8-3122:52
 */
public class Config {
    public static final String JU_ID="c2c2b7f346edfda3598ed48253d87461";
    public static final String AN_WO_ID="6c9b59eca7484697a3071083f0f40f1e";
    /**设置true为测试广告模式，提交安沃审核和发布市场时请务必设置为false*/
    public static final boolean IS_TEST_AD=true;
    /**	isTiming值为true时，表示在调用该方法时起，定时 一小时请求一次（此模式下，
     * 网络和屏幕状态变更将会触发请求，这时将不受定时控制）；
    isTiming值为false时，表示只在该方法被调用时请求广告（主动请求广告，无其他触发条件）。*/
    public static final boolean JU_PUSH=false;
    public static final String HYY_TITLE1="hyy_title1";
}
