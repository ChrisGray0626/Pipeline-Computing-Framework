package pers.chris.base.task;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/13
 */
public class TaskInstance {

    private final BaseTaskDefinition task;
    private Object input;
    private Object output;

    public TaskInstance(BaseTaskDefinition task, Object input) {
        this.task = task;
        this.input = input;
    }

    public Object execute() {
        // TODO 线程不安全
        if (task instanceof BaseParallelTaskDefinition) {
            ConcurrentLinkedQueue<Object> outputQueue = new ConcurrentLinkedQueue<>();
            BaseParallelTaskDefinition parallelTask = (BaseParallelTaskDefinition) task;
            List splits = parallelTask.split(input);
            CountDownLatch latch = new CountDownLatch(splits.size());
            ExecutorService threadPool = Executors.newFixedThreadPool(splits.size());
            for (Object split : splits) {
                threadPool.execute(new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Object output = parallelTask.execute(split);
                        outputQueue.add(output);
                        latch.countDown();
                    }
                }));
            }
            threadPool.shutdown();
            Thread mergerThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (outputQueue.size() > 1 && latch.getCount() != 0) {
                        Object output1 = outputQueue.poll();
                        Object output2 = outputQueue.poll();
                        outputQueue.offer(parallelTask.merge(output1, output2));
                    }
                }
            });
            mergerThread.start();
            output = outputQueue.poll();
        }
        else {
            output = task.execute(input);
        }
        return output;
    }

    public Object getOutput() {
        return output;
    }
}
