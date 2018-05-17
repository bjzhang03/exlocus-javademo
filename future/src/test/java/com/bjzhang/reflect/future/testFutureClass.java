package com.bjzhang.reflect.future;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class testFutureClass {
    @Test
    public void testCalculate() throws InterruptedException, ExecutionException {
        FutureClass futureClass = new FutureClass();
        System.out.println(futureClass.calculateAsync().get());
    }

    @Test
    public void testCalculate2() throws ExecutionException, InterruptedException {
        Future<String> completableFuture = CompletableFuture.completedFuture("Hello");

        String result = completableFuture.get();
        assertEquals("Hello", result);
    }

    @Test
    public void testCalculateWithCancellation() throws InterruptedException, ExecutionException {
        FutureClass futureClass = new FutureClass();
//        System.out.println(futureClass.calculateAsyncWithCancellation().get());
    }

    @Test
    public void testSupplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");
        System.out.println(future.get());

        assertEquals("Hello", future.get());
    }

    @Test
    public void testRunAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("hello");
        });
        System.out.println(future.get());
        assertThat(future.get()).isEqualTo(null);
    }

    @Test
    public void testThenAccept() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future = completableFuture.thenApply(s -> s + " World");
        System.out.println(future.get());
        assertEquals("Hello World", future.get());
    }

    @Test
    public void testThenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<Void> future = completableFuture.thenAccept(s -> System.out.println("Computation returned: " + s));

        System.out.println(future.get());
    }

    @Test
    public void testThenRun() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<Void> future = completableFuture.thenRun(() -> System.out.println("Computation finished."));

        System.out.println(future.get());
    }

    @Test
    public void testThenCompose() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture =
                CompletableFuture.supplyAsync(() -> "Hello")
                        .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));
        System.out.println(completableFuture.get());
        assertEquals("Hello World", completableFuture.get());
    }

    @Test
    public void testThenCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> "Hello")
                          .thenCombine(CompletableFuture.supplyAsync(() -> " World"), (s1, s2) -> s1 + s2);
        System.out.println(completableFuture.get());

        assertEquals("Hello World", completableFuture.get());
    }

    @Test
    public void testThenAcceptBoth() {
        CompletableFuture future = CompletableFuture.supplyAsync(() -> "Hello")
                                           .thenAcceptBoth(CompletableFuture.supplyAsync(() -> " World"),
                                                   (s1, s2) -> System.out.println(s1 + s2));

    }

    @Test
    public void testMultipleFuture() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2
                = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3
                = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<Void> combinedFuture
                = CompletableFuture.allOf(future1, future2, future3);

        System.out.println(combinedFuture.get());

        assertTrue(future1.isDone());
        assertTrue(future2.isDone());
        assertTrue(future3.isDone());
    }

    @Test
    public void testMultipleFuture2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2
                = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3
                = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<Void> combinedFuture
                = CompletableFuture.allOf(future1, future2, future3);

        String combined = Stream.of(future1, future2, future3)
                                  .map(CompletableFuture::join)
                                  .collect(Collectors.joining(" "));
        System.out.println(combined);

        assertEquals("Hello Beautiful World", combined);
    }

    @Test
    public void testHandleError() throws ExecutionException, InterruptedException {
        String name = null;

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            if (name == null) {
                throw new RuntimeException("Computation error!");
            }
            return "Hello, " + name;
        }).handle((s, t) -> s != null ? s : "Hello, Stranger!");
        System.out.println(completableFuture.get());

        assertEquals("Hello, Stranger!", completableFuture.get());
    }

    @Test
    public void testAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future = completableFuture.thenApplyAsync(s -> s + " World");

        System.out.println(future.get());
        assertEquals("Hello World", future.get());

    }

}
