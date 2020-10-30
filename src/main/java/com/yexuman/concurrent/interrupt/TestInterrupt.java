package com.yexuman.concurrent.interrupt;

import java.math.BigInteger;

/**
 * @author yexuman
 * @version 1.0
 * @date 2020/10/20 3:10 下午
 */
public class TestInterrupt implements Runnable {

    private volatile boolean stop = false;

    public static void main(String[] args) throws Exception {
//        Thread thread = new Thread(new TestInterrupt(), "My Thread2");
//        System.out.println("Starting thread...");
//        thread.start();
//        Thread.sleep(3000);
//        System.out.println("Interrupting thread...");
//        thread.interrupt();
//        System.out.println("线程是否中断：" + thread.isInterrupted());
//        thread.interrupt();
//        System.out.println("线程是否中断：" + thread.isInterrupted());
//        Thread.sleep(3000);
//        System.out.println("Stopping application...");
        //16进制  以16进制储存:0x02080085,当前版本为2.8.0.133.
        int s_major_ver = 34078853;
//        (s_major_ver << 24) | (s_minor_ver << 16) | (s_build_num << 8) | s_revision_num;
        int i = s_major_ver << 24;

    }

    public static String getVersionString(int decimalNumber) {
        //将十进制数转为十六进制数
        String hex = Integer.toHexString(decimalNumber);
        //转为大写
        hex = hex.toUpperCase();
        //加长到8位字符，用0补齐
        while (hex.length() < 8) {
            hex = "0" + hex;
        }

        StringBuffer sb = new StringBuffer();
        int step = 0;
        while (step <= 6) {
            String num = new BigInteger(hex.substring(step, step + 2), 16).toString();
            sb.append(num);
            if (step < 6) {
                sb.append(".");
            }
            step += 2;
        }
        return sb.toString();
    }

    @Override
    public void run() {
        while (!stop) {
            System.out.println("My Thread is running...");
            // 让该循环持续一段时间，使上面的话打印次数少点
            long time = System.currentTimeMillis();
            while ((System.currentTimeMillis() - time < 1000)) {
            }
            if (Thread.currentThread().isInterrupted()) {
                return;
            }
        }
        System.out.println("My Thread exiting under request...");
    }

}
