package pers.chris.base.task;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import pers.chris.base.datatype.Context;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/14
 */
public class ParallelTaskInstance<Input, Output> extends BaseTaskInstance<Input, Output>{

    private final BaseParallelTaskDefinition<Input, Output> taskDefinition;

    public ParallelTaskInstance(BaseParallelTaskDefinition<Input, Output> taskDefinition, Context context) {
        super(context);
        this.taskDefinition = taskDefinition;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void run() {
        ConcurrentLinkedQueue<Output> outputQueue = new ConcurrentLinkedQueue<>();
        Input input = (Input) context.get();
        List<Input> splits = taskDefinition.split(input);
        CountDownLatch latch = new CountDownLatch(splits.size());
        ExecutorService threadPool = Executors.newFixedThreadPool(splits.size());
        for (Input split : splits) {
            threadPool.execute(new Thread(() -> {
                Output output0 = taskDefinition.execute(split);
                outputQueue.add(output0);
                latch.countDown();
            }));
        }
        threadPool.shutdown();
        Thread mergerThread = new Thread(() -> {
            while (outputQueue.size() > 1 || latch.getCount() != 0) {
                Output output1 = outputQueue.poll();
                Output output2 = outputQueue.poll();
                outputQueue.offer(taskDefinition.merge(output1, output2));
            }
        });
        mergerThread.start();
        try {
            mergerThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        context.set(outputQueue.poll());
    }
}
