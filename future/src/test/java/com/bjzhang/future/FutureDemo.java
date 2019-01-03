package com.bjzhang.future;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class FutureDemo {
    @Test
    public void test1() {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 123;
        }).thenApplyAsync(tem -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tem);
            return tem * 10;
        }).thenApplyAsync(tem -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tem);
            return tem + 1;
        }).thenAcceptAsync(tem -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tem);
        }).thenRunAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("over");
            return;
        });

        System.out.println("mid");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        CompletableFuture.supplyAsync(() -> {
            return 123;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            return 321;
        }), (data1, data2) -> {
            return data1 + data2;
        }).thenAccept(data -> {
            System.out.println(data);
        });
    }

    @Test
    public void test3() {
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4() {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 123;
        });
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test5() {
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).runAfterBoth(CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }), () -> {
        });
        sleep();
    }

    public void sleep() {
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试异常情况
     */
    @Test
    public void test6() {
        CompletableFuture.runAsync(() -> {
        }).runAfterBoth(CompletableFuture.runAsync(() -> {
        }), () -> {
            throw new RuntimeException("测试一下异常情况");
        }).handle((result, excep) -> {
            return 123;
        });
    }


    @Test
    public void test7() {
        CompletableFuture<String> dataStr = new CompletableFuture<>();
        dataStr.whenComplete((result, throwable) -> {

        });
    }


    public void testConsumer(Consumer<Integer> data) {
        data.accept(1231);
    }

    @Test
    public void test8() {
        getFuture().thenApply(data -> {
            assert data == 1;
            return data;
        }).exceptionally(ex -> {
            System.out.println(ex.toString());
            return 0;
        }).thenAccept(data -> {
        });
    }

    @Test
    public void test9() {
        CompletableFuture<String> future = CompletableFuture.completedFuture("123");
        future.thenApply(tem -> {
            return tem + "-";
        });
    }

    public CompletableFuture<Integer> getFuture() {
        return CompletableFuture.completedFuture(12);
    }
}
