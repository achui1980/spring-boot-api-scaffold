package ${package.Controller};

import com.achui.api.core.Result;
import com.achui.api.core.ResultGenerator;
import ${package.Entity}.${table.entityName};
import ${package.Service}.${table.serviceName};
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>
     @Autowired
     private ${table.serviceName} service;

    @PostMapping("/add")
    public Result add(@RequestBody ${table.entityName} entity) {
        service.save(entity);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        service.removeById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping("/update")
    public Result update(@RequestBody ${table.entityName} entity) {
        service.saveOrUpdate(entity);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/detail/{id}")
    public Result detail(@PathVariable("id") Integer id) {
        SysMe entity = service.getById(id);
        return ResultGenerator.genSuccessResult(entity);
    }

    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "0") Integer pageSize) {
        Page<${table.entityName}> page = new Page<${table.entityName}>(pageNo, pageSize);
        return ResultGenerator.genSuccessResult(service.page(page));
    }
}
</#if>