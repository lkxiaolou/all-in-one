package org.newboo.longadder;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

@State(Scope.Benchmark)
public class LongAdderTest {

    private MyLongAdderV0 myLongAdderV0;
    private MyLongAdderV1 myLongAdderV1;
    private MyLongAdderV2 myLongAdderV2;
    private MyLongAdderV3 myLongAdderV3;
    private MyLongAdderV20 myLongAdderV20;
    private MyLongAdderV21 myLongAdderV21;
    private MyLongAdderV22 myLongAdderV22;
    private MyLongAdderV23 myLongAdderV23;

    @Setup
    public void init() {
        myLongAdderV0 = new MyLongAdderV0(coreSize);
        myLongAdderV1 = new MyLongAdderV1(coreSize);
        myLongAdderV2 = new MyLongAdderV2(coreSize);
        myLongAdderV3 = new MyLongAdderV3(coreSize);
        myLongAdderV20 = new MyLongAdderV20(coreSize);
        myLongAdderV21 = new MyLongAdderV21(coreSize);
        myLongAdderV22 = new MyLongAdderV22(coreSize);
        myLongAdderV23 = new MyLongAdderV23(coreSize);
    }

    private static final int coreSize = 4;

    private final AtomicLong atomicLong = new AtomicLong();
    private final LongAdder longAdder = new LongAdder();
    private long counter = 0;

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(LongAdderTest.class.getSimpleName())
                .forks(1)
                .threads(coreSize)
                .warmupIterations(2)
                .measurementIterations(2)
                .mode(Mode.Throughput)
                .syncIterations(false)
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    public void testAtomic() {
        atomicLong.incrementAndGet();
    }

    @Benchmark
    public void testLongAdder() {
        longAdder.increment();
    }

    @Benchmark
    public synchronized void testLockAdder() {
        counter++;
    }

    @Benchmark
    public void testMyLongAdderV0() {
        myLongAdderV0.increment();
    }

    @Benchmark
    public void testMyLongAdderV1() {
        myLongAdderV1.increment();
    }

    @Benchmark
    public void testMyLongAdderV3() {
        myLongAdderV3.increment();
    }

    @Benchmark
    public void testMyLongAdderV20() {
        myLongAdderV20.increment();
    }

    @Benchmark
    public void testMyLongAdderV2() {
        myLongAdderV2.increment();
    }

    @Benchmark
    public void testMyLongAdderV21() {
        myLongAdderV21.increment();
    }

    @Benchmark
    public void testMyLongAdderV22() {
        myLongAdderV22.increment();
    }

    @Benchmark
    public void testMyLongAdderV23() {
        myLongAdderV23.increment();
    }
}

