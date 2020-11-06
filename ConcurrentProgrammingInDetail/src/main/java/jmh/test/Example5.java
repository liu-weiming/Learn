package jmh.test;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * OutputTimeUnit
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)//在class上配置
@State(Scope.Thread)
@Warmup(iterations = 10)
@Measurement(iterations = 5)
public class Example5 {

    @OutputTimeUnit(TimeUnit.MILLISECONDS)//在method上配置
    @Benchmark
    public void test() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(1);
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(Example5.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(options).run();
    }

}
