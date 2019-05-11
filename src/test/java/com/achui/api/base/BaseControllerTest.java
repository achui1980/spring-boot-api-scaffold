package com.achui.api.base;

import com.achui.api.core.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BaseControllerTest {

    BaseController baseController;
    @Mock
    IService<Entity> service;

    Entity entity;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        entity = new Entity();
        baseController = new BaseController();
        ReflectionTestUtils.setField(baseController, "service", service);
    }

    @Test
    public void add() {
        when(service.save(any(Entity.class))).thenReturn(true);
        Result t = baseController.add(entity);
        String expected = "{\"code\":200,\"message\":\"SUCCESS\"}";
        assertThat(t.toString()).isEqualTo(expected);
    }

    @Test
    public void delete() {
        when(service.removeById(any(Integer.class))).thenReturn(true);
        Result t = baseController.delete(1);
        String expected = "{\"code\":200,\"message\":\"SUCCESS\"}";
        assertThat(t.toString()).isEqualTo(expected);
    }

    @Test
    public void update() {
        when(service.saveOrUpdate(any(Entity.class))).thenReturn(true);
        Result t = baseController.update(entity);
        String expected = "{\"code\":200,\"message\":\"SUCCESS\"}";
        assertThat(t.toString()).isEqualTo(expected);
    }

    @Test
    public void detail() {
        entity.setS1("0000");
        when(service.getById(any(Integer.class))).thenReturn(entity);
        Result<Entity> t = baseController.detail(1);
        assertThat(t.getData().getS1()).isEqualTo("0000");
    }

    @Test
    public void list() {
        Page<Entity> page = new Page();
        page.setTotal(100);
        when(service.page(any(Page.class))).thenReturn(page);
        Result<Page> t = baseController.list(100, 100);
        assertThat(t.getData().getTotal()).isEqualTo(100);

    }

    @Data
    class Entity {
        private String s1;
        private String s2;
    }
}