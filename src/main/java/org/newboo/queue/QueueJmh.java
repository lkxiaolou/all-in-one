package org.newboo.queue;

import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

@State(Scope.Benchmark)
public class QueueJmh {

    private LogEventProducer logEventProducer;
    private MyRingBuffer<LogEvent> myRingBuffer;
    private Disruptor<LogEvent> disruptor;

    ExecutorService e1 = Executors.newSingleThreadExecutor();
    ExecutorService e2 = Executors.newSingleThreadExecutor();
    private boolean stop = false;

    @Setup
    public void init() {

        // 初始化disruptor
        disruptor = new Disruptor<>(LogEvent::new,
                2,
                e1,
                ProducerType.MULTI,
                new YieldingWaitStrategy()
        );

        disruptor.handleEventsWith(new LogEventHandler());
        disruptor.start();
        logEventProducer = new LogEventProducer(disruptor.getRingBuffer());

        // 初始化myRingBuffer
        myRingBuffer = new MyRingBuffer<>(1024);
        e2.execute(() -> {
            while (!stop) {
                for (int i = 0; i < myRingBuffer.getBufferSize(); i++) {
                    myRingBuffer.poll(i);
                }
                Thread.yield();
            }
        });
    }

    @TearDown
    public void destroy() {
        disruptor.shutdown();
        stop = true;
        e1.shutdown();
        e2.shutdown();
    }

    @Benchmark
    public void testDisruptor() {
        int random = ThreadLocalRandom.current().nextInt(10000);
        logEventProducer.onData(random);
    }

    @Benchmark
    public void testMyRingBuffer() {
        int random = ThreadLocalRandom.current().nextInt(10000);
        LogEvent logEvent = new LogEvent();
        logEvent.setId(random);
        myRingBuffer.offer(logEvent);
    }

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(QueueJmh.class.getSimpleName())
                .forks(1)
                .threads(4)
                .warmupIterations(2)
                .measurementIterations(2)
                .mode(Mode.Throughput)
                .syncIterations(false)
                .build();

        new Runner(opt).run();
    }
}
