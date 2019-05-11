package com.achui.api.core;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ServiceExceptionTest {

    @Test
    public void testServiceException1() {
        ServiceException exception = new ServiceException();
        System.out.println(exception.getMessage());
        assertThat(exception.getMessage()).isNull();
    }

    @Test
    public void testServiceException3() {
        ServiceException exception = new ServiceException("msg");
        System.out.println(exception.getMessage());
        assertThat(exception.getMessage()).isEqualTo("msg");
    }

    @Test
    public void testServiceException2() {
        ServiceException exception = new ServiceException("222", new Throwable("333"));
        assertThat(exception.getMessage()).isEqualTo("222");
    }

}