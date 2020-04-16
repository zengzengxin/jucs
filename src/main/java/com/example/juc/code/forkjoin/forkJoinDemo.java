package com.example.juc.code.forkjoin;

import java.util.concurrent.RecursiveTask;

/*
forkjoin就是将大任务拆解成小任务，再合并得出结果
下面用forkjion实现加法计算
* */
public class forkJoinDemo extends RecursiveTask<Long> {
    private long start;

    private long end;

    private long temp = 10000L;


    public forkJoinDemo(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute(){
      long sum = 0L;
      if ((end-start)<= temp){
          for (Long i = start; i <= end; i++) {
              sum += i;
          }
          return sum;
      }else {
          Long middle = (start+end)/2;
          forkJoinDemo task1 = new forkJoinDemo(start, middle);
          task1.fork(); // 拆分任务，把任务压入线程队列
          forkJoinDemo task2 = new forkJoinDemo(middle+1, end);
          task2.fork(); // 拆分任务，把任务压入线程队列
          return task1.join() + task2.join();
      }
    }
}
