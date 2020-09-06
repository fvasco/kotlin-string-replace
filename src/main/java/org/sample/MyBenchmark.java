package org.sample;

import kotlin.text.StringsKt;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@SuppressWarnings("unused")
@State(Scope.Benchmark)
@Fork(value = 1, warmups = 1)
public class MyBenchmark {

    private static final String base = StringsKt.repeat("ABC ABCDAB ABCDABCDABDE", 1);

    private static final String oldValue = "ABCDABD";

    private static final String newValue = "0123456";

    private static final boolean ignoreCase = true;

    @Benchmark
    public void replaceOriginal(Blackhole hole) {
        hole.consume(StringsKt.replace(base, "a", "1", true));
    }

    @Benchmark
    public void replaceNew(Blackhole hole) {
        hole.consume(MyBenchmarkKt.replace(base, "a", "1", true));
    }


    public static void main(String[] args) {
        System.out.println(StringsKt.replace("ABC AABABCDAB ABCDABCDABDE", "", "x",true));
        System.out.println(MyBenchmarkKt.replace( "ABC AABABCDAB ABCDABCDABDE", "","x", true));

        System.out.println(StringsKt.replace("ABC AABABCDAB ABCDABCDABDE", "A", "x",true));
        System.out.println(MyBenchmarkKt.replace( "ABC AABABCDAB ABCDABCDABDE", "A","x", true));

        System.out.println(StringsKt.replace("ABC AABABCDAB ABCDABCDABDE", "AB", "x",true));
        System.out.println(MyBenchmarkKt.replace( "ABC AABABCDAB ABCDABCDABDE", "AB","x", true));
    }
}
