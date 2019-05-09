package com.achui.api.base;

import com.achui.api.core.Result;
import com.achui.api.core.ResultGenerator;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public class BaseController<M extends IService<T>, T> {
    @Autowired
    M service;

    @PostMapping("/add")
    public Result add(@RequestBody T entity) {
        service.save(entity);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        service.removeById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping("/update")
    public Result update(@RequestBody T entity) {
        service.saveOrUpdate(entity);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/detail/{id}")
    public Result detail(@PathVariable("id") Integer id) {
        T entity = service.getById(id);
        return ResultGenerator.genSuccessResult(entity);
    }

    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "0") Integer pageSize) {
        Page<T> page = new Page<T>(pageNo, pageSize);
        return ResultGenerator.genSuccessResult(service.page(page));
    }
}
