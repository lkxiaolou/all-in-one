//package org.newboo.benchmark;
//
//import org.newboo.longadder.LongAdderTest;
//import org.openjdk.jmh.annotations.*;
//import org.openjdk.jmh.infra.Blackhole;
//import org.openjdk.jmh.runner.Runner;
//import org.openjdk.jmh.runner.RunnerException;
//import org.openjdk.jmh.runner.options.Options;
//import org.openjdk.jmh.runner.options.OptionsBuilder;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//
//@State(Scope.Benchmark)
//public class UrlParseTest {
//
//    private static String urlStr = "http://127.0.0.1:20880/com.xiaoju.manhattan.fbi.rc.chaos.api.IdentityServiceApi?anyhost=true&application=strategic-manhattan-fbi-rc-fbi_rc_chaos&bean.name=com.xiaoju.manhattan.fbi.rc.chaos.api.IdentityServiceApi&default.service.filter=dubboProviderFilter&dubbo=2.0.2&environment=test&generic=false&interface=com.xiaoju.manhattan.fbi.rc.chaos.api.IdentityServiceApi&methods=queryMono,queryMulti&owner=cchenjunjie&register=true&revision=BCD-2.0-SNAPSHOT&side=provider&threads=50";
//
//    @Benchmark
//    public void testParseUrl(Blackhole blackhole) throws MalformedURLException {
//        URL urlObj = new URL(urlStr);
//        blackhole.consume(urlObj);
//    }
//
//    public static void main(String[] args) throws RunnerException {
//        Options opt = new OptionsBuilder()
//                .include(UrlParseTest.class.getSimpleName())
//                .forks(1)
//                .threads(4)
//                .jvmArgs("-XX:-RestrictContended")
//                .warmupIterations(2)
//                .measurementIterations(2)
//                .mode(Mode.Throughput)
//                .syncIterations(false)
//                .build();
//
//        new Runner(opt).run();
//    }
//}
//
