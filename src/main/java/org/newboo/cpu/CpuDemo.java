package org.newboo.cpu;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@State(Scope.Benchmark)
public class CpuDemo {

    private volatile Integer value = 1;

    /**
     * get 和 getByLocal 哪个执行更快？为什么？
     */
    @Benchmark
    public void get(Blackhole blackhole) {
        if (value == null) {
            value = 2;
        }

        // blackhole 吞噬 value，防止被编译器优化
        blackhole.consume(value);
    }

    @Benchmark
    public void getByLocal(Blackhole blackhole) {
        Integer v1 = value;

        if (v1 == null) {
            v1 = 2;
        }

        blackhole.consume(v1);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(CpuDemo.class.getSimpleName())
                .forks(1)
                .threads(1)
                .jvmArgs("-XX:-RestrictContended")
                .warmupIterations(2)
                .measurementIterations(2)
                .mode(Mode.Throughput)
                .syncIterations(false)
                .build();

        new Runner(opt).run();
    }
}
