package org.example.jdk_21_demo;

/**
 * @author shaoke
 * @desc VirtualThreadDemo
 * @since 2024/8/20
 */
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;

import java.util.stream.IntStream;

public class VirtualThreadDemo {
    public static void main(String[] args) {
        // 使用虚拟线程来处理多个并发任务
        var threads = IntStream.range(0, 100)  // 创建100个任务
                .mapToObj(i -> Thread.ofVirtual().start(() -> {
                    System.out.println(DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_FORMAT) + ":Task " + i + " is running on " + Thread.currentThread());
                    try {
                        Thread.sleep(1000);  // 模拟任务执行时间
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_FORMAT) + ":Task " + i + " is completed on " + Thread.currentThread());
                }))
                .toList();  // 将所有线程收集为列表

        // 等待所有任务完成
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("All tasks are completed.");
    }
}

