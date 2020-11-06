package jmh.test;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
public class Example3 {

    @Benchmark
    public void test() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(10);
    }

    @Benchmark
    @Warmup(iterations = 10)
    @Measurement(iterations = 5)
    public void testWithSetting() {

    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(Example3.class.getSimpleName())
                .forks(1)
                .warmupIterations(10)
                .measurementIterations(10)
                .build();
        new Runner(options).run();
    }
}
