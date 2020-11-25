package www.glaway.baselibrary.utils;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程计数器，用于判断是否全部执行结束/超时
 *
 * 注意:ThreadFinishCall回调是Timer线程，不是UI线程
 */
public class ThreadsIsFinish {
    private Timer timer;//计时器
    private int sumTime = 0;
    private AtomicInteger atomicInteger;//线程计数器,总任务数量
    private ThreadFinishCall threadFinishCall;//回调
    private int timespae = 1;//检查时间间隔 1 单位s
    private int timeout = 5;//超时时间 TIMESPACE*TIMESPACETIMEOUT = 5*1s =  5s
    private boolean isTimeOut = false;//是否需要超时

    public ThreadsIsFinish(int sum, ThreadFinishCall threadFinishCall) {
        atomicInteger = new AtomicInteger(sum);
        this.threadFinishCall = threadFinishCall;
    }

    /**
     * 设置是否需要超时判断
     */
    public void setIsTimeout(boolean isTimeOut) {
        this.isTimeOut = isTimeOut;
    }

    /**
     * 设置参数
     */
    public void setParams(int timespae, int timeout) {
        this.timespae = timespae;
        this.timeout = timeout;
    }

    /**
     * 开始统计
     */
    public void start() {
        distory();
        sumTime = 0;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (atomicInteger.get() <= 0) {//线程总数《=0代表全部执行完
                    threadFinishCall.finish();
                    distory();
                }
                if (isTimeOut) {
                    sumTime++;
                    if (sumTime >= timeout) {
                        threadFinishCall.timeout();
                        distory();
                    }
                }
            }
        }, 0, timespae * 1000);//每个1s检查一次
    }

    /**
     * 线程计数器减一
     */
    public void decrement() {
        atomicInteger.decrementAndGet();
    }

    /**
     * 销毁
     */
    public void distory() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    /**
     * 回调
     */
    public interface ThreadFinishCall {
        void finish();

        void timeout();
    }
}
