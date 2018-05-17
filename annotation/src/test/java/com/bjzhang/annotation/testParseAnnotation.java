package com.bjzhang.annotation;

import org.junit.Test;

public class testParseAnnotation {
    @Test
    public void testParseTypeAnnotation() throws ClassNotFoundException {
        ParseAnnotation.parseTypeAnnotation();
    }

    @Test
    public void testParseMethodAnnotation() throws ClassNotFoundException {
        ParseAnnotation.parseMethodAnnotation();
    }

    @Test
    public void testParseConstructAnnotation() throws ClassNotFoundException {
        ParseAnnotation.parseConstructAnnotation();
    }
}
