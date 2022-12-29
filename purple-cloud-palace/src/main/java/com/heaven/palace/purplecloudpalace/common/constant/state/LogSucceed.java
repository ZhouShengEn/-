package com.heaven.palace.purplecloudpalace.common.constant.state;

/**
 * 业务是否成功的日志记录
 *
 * @author ZhouShengEn
 * @Date 2022年8月25日
 */
public enum LogSucceed {

    SUCCESS("成功"),
    FAIL("失败");

    String message;

    LogSucceed(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
