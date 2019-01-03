package com.bjzhang.proxy;

import org.junit.Test;

public class ProxyTest {
    @Test
    public void testProxy() {
        RealMovie realmovie = new RealMovie();
        Movie movie = new Cinema(realmovie);
        movie.play();
    }
}
