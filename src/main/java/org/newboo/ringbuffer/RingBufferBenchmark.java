package org.newboo.ringbuffer;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@State(Scope.Benchmark)
public class RingBufferBenchmark {

    private AtomicRangeInteger v0 = new AtomicRangeInteger(0, 999);
    private AtomicRangeIntegerV1 v1 = new AtomicRangeIntegerV1(0, 999);
    private AtomicRangeIntegerV2 v2 = new AtomicRangeIntegerV2(0, 999);

    @Benchmark
    public void testV0() {
        v0.incrementAndGet();
    }

    @Benchmark
    public void testV2() {
        v2.incrementAndGet();
    }

    @Benchmark
    public void testV1() {
        v1.incrementAndGet();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(RingBufferBenchmark.class.getSimpleName())
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

}
