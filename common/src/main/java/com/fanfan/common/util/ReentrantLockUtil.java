package com.fanfan.common.util;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockUtil {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
    }

}
