package org.newboo.lock;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@State(Scope.Benchmark)
public class LockJmh {

    private Lock lock = new ReentrantLock();

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(LockJmh.class.getSimpleName())
                .forks(1)
                .threads(4)
                .warmupIterations(2)
                .measurementIterations(2)
                .mode(Mode.Throughput)
                .syncIterations(false)
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    public void testBlank(Blackhole blackhole) {
        blackhole.consume(1);
        //...
    }

    @Benchmark
    public void testTryLock(Blackhole blackhole) {
        if (lock.tryLock()) {
            blackhole.consume(1);
        }
    }


}
