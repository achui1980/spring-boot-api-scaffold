package com.achui.api.core;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ResultGeneratorTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void genSuccessResult() {
        ResultGenerator generator = new ResultGenerator();
        Result t = ResultGenerator.genSuccessResult();
        String expected = "{\"code\":200,\"message\":\"SUCCESS\"}";
        assertThat(JSON.toJSONString(t)).isEqualTo(expected);
    }

    @Test
    public void genSuccessResult1() {
        TestObj testObj = new TestObj();
        testObj.setF1("f1");
        testObj.setF2("f2");
        Result<TestObj> t = ResultGenerator.genSuccessResult(testObj);
        String expected = "{\"code\":200,\"data\":{\"f1\":\"f1\",\"f2\":\"f2\"},\"message\":\"SUCCESS\"}";
        assertThat(t.toString()).isEqualTo(expected);
    }

    @Test
    public void genFailResult() {
        Result t = ResultGenerator.genFailResult("Error");
        String expected = "{\"code\":400,\"message\":\"Error\"}";
        assertThat(JSON.toJSONString(t)).isEqualTo(expected);
    }

    @Data
    class TestObj {
        private String f1;
        private String f2;
    }
}