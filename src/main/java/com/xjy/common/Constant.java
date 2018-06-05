package com.xjy.common;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/310:45
 * @Description:
 * @Modified By:
 */
public class Constant {

    /**
     * 定时任务状态
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
        NORMAL(0),
        /**
         * 暂停
         */
        PAUSE(1);

        private int value;

        private ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
