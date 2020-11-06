package jmh.test;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 四种BenchmarkMode类型
 */
@State(Scope.Thread)
public class Example4 {

    /**
     * 基准测试方法调用的耗时
     * @throws InterruptedException
     */
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 1)
    @Measurement(iterations = 10)
    public void testAverageTime() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(10);
    }

    /**
     * 方法吞吐量：单位时间内的调用次数
     * @throws InterruptedException
     */
    @BenchmarkMode(Mode.Throughput)
    @Benchmark
    public void testThroughput() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(10);
    }

    /**
     * 时间采样：采用一种采样的方式来统计基准测试方法的性能结果，收集所有的性能数据
     * @throws InterruptedException
     */
    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    public void testSimpleTime() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(10);
    }

    /**
     * 冷测试，不论是Warmup还是Measurement，在一个批次中基准测试方法只会执行一次，一般将Warmup设置为0
     * @throws InterruptedException
     */
    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    public void testSingleShotTime() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(10);
    }

    /**
     * 配置多个模式的基准测试
     * @throws InterruptedException
     */
    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    public void testMultiMode() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(1);
    }

    /**
     * 配置所有模式基准测试
     * @throws InterruptedException
     */
    @Benchmark
    @BenchmarkMode(Mode.All)
    public void testAllMode() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(1);
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(Example4.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(options).run();
    }

}
