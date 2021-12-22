package com.hongpro.demo.common.juc.future;

import java.util.concurrent.*;

/**
 * @author zhangzihong
 * @version 1.0.0.0
 * @description
 * @date 2021/11/10 17:56
 */
public class FutureTaskTest {
    public static void main(String[] args) throws Exception {
        Callable<String> call = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("call");
                TimeUnit.SECONDS.sleep(1);
                return "complete";
            }
        };
        FutureTask<String> future = new FutureTask<>(call);
  /*       ExecutorService executorService = Executors.newSingleThreadExecutor();
       executorService.execute(future);
        try {
            System.out.println(future.get(500, TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            future.cancel(true);    //取消任务
            System.out.print("提前中断");
        } catch (ExecutionException e) {
            future.cancel(true);    //取消任务
            System.out.print("任务崩溃");
        } catch (TimeoutException e) {
            future.cancel(true);    //取消任务
            System.out.print("超时异常");
        } finally {
            executorService.shutdown();
        }*/


        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<String> schedule = scheduledExecutorService.schedule(call, 100, TimeUnit.MILLISECONDS);
        System.out.println(schedule.get());
    }
}
