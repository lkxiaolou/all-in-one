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
    private MyLongAdderV4 myLongAdderV4;
    private MyLongAdderV5 myLongAdderV5;
    private MyLongAdderV24 myLongAdderV24;
    private MyLongAdderV31 myLongAdderV31;
    private MyLongAdderV20 myLongAdderV20;
    private MyLongAdderV21 myLongAdderV21;
    private MyLongAdderV211 myLongAdderV211;
    private MyLongAdderV22 myLongAdderV22;
    private MyLongAdderV23 myLongAdderV23;
    private MyLongAdderV30 myLongAdderV30;

    @Setup
    public void init() {
        myLongAdderV0 = new MyLongAdderV0(coreSize);
        myLongAdderV1 = new MyLongAdderV1(coreSize);
        myLongAdderV2 = new MyLongAdderV2(coreSize);
        myLongAdderV3 = new MyLongAdderV3(coreSize);
        myLongAdderV4 = new MyLongAdderV4(coreSize);
        myLongAdderV5 = new MyLongAdderV5(coreSize);
        myLongAdderV24 = new MyLongAdderV24(coreSize);
        myLongAdderV31 = new MyLongAdderV31(coreSize);
        myLongAdderV20 = new MyLongAdderV20(coreSize);
        myLongAdderV21 = new MyLongAdderV21(coreSize);
        myLongAdderV211 = new MyLongAdderV211(coreSize);
        myLongAdderV22 = new MyLongAdderV22(coreSize);
        myLongAdderV23 = new MyLongAdderV23(coreSize);
        myLongAdderV30 = new MyLongAdderV30(coreSize);
    }

    private static final int coreSize = 4;

    private final AtomicLong atomicLong = new AtomicLong();
    private final LongAdder longAdder = new LongAdder();
    private long counter = 0;

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(LongAdderTest.class.getSimpleName())
                .forks(1)
                .threads(4)
                .jvmArgs("-XX:-RestrictContended")
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
    public void testMyLongAdderV2() {
        myLongAdderV2.increment();
    }

    @Benchmark
    public void testMyLongAdderV31() {
        myLongAdderV31.increment();
    }

    @Benchmark
    public void testMyLongAdderV3() {
        myLongAdderV3.increment();
    }

    @Benchmark
    public void testMyLongAdderV4() {
        myLongAdderV4.increment();
    }

    @Benchmark
    public void testMyLongAdderV5() {
        myLongAdderV5.increment();
    }

    @Benchmark
    public void testMyLongAdderV20() {
        myLongAdderV20.increment();
    }

    @Benchmark
    public void testMyLongAdderV24() {
        myLongAdderV24.increment();
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
    public void testMyLongAdderV211() {
        myLongAdderV211.increment();
    }

    @Benchmark
    public void testMyLongAdderV30() {
        myLongAdderV30.increment();
    }

    @Benchmark
    public void testMyLongAdderV23() {
        myLongAdderV23.increment();
    }
}

