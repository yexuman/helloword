package com.yexuman.tool;

import com.google.common.collect.Interner;
import com.google.common.collect.Interners;

/**
 * @author yexuman
 * 生成字符串锁的工具
 * @version 1.0
 * @date 2021/4/6 16:26
 */
public class StringUtilLock {

        private static Interner<String> pool = Interners.newWeakInterner();

        public static String getLoginLock(String key) {
            return pool.intern("login_" + key);
        }

        public static String getGalaxyTaskLock(String key) {
            return pool.intern("galaxy_task_" + key);
        }

        public static String getFailReasonLock(String fromUser, String curUser) {
            return pool.intern(fromUser + "-" + curUser);
        }

        public static String getAnnouncementLock(String key) {
            return pool.intern("announcement_" + key);
        }

        public static String getMqttInitLock(String key) {
            return pool.intern("mqtt_init_" + key);
        }

        public static String getWxRoomLock(String wxId, String chatroom) {
            return pool.intern(wxId + "-" + chatroom);
        }

        public static String getWxLock(String wxId) {
            return pool.intern("announcement_task-" + wxId);
        }

        /**
         * 防止逆向上报多条文件消息
         *
         * @param key
         * @return
         */
        public static String getGalaxyFileLock(String key) {
            return pool.intern("galaxy_file_" + key);
        }

        /**
         * 任务锁,防止任务重复
         *
         * @param key
         * @return
         */
        public static String getTaskIdUniqueLock(String key) {
            return pool.intern("task_id_unique_" + key);
        }

        public static String getChatroomLock(String chatroom) {
            return pool.intern("chatroom_" + chatroom);
        }

        public static String getChatroomPrepare(String wxid) {
            return pool.intern("chatroom_prepare_task" + wxid);
        }

        public static String getChatroomPrepareTaskSecondFeedBack(String wxid) {
            return pool.intern("chatroom_prepare_task_second_feed_back" + wxid);
        }

        public static String getChatroomPrepareTaskFirstFeedBack(String wxid) {
            return pool.intern("chatroom_prepare_task_first_feed_back" + wxid);
        }
    }

