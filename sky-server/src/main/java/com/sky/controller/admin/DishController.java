package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/dish")
@Api(tags = "dish relative")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;

    @PostMapping
    @ApiOperation("new dishes")
    public Result save(@RequestBody DishDTO dishDTO) {
        dishService.saveWithFlavor(dishDTO);

        return Result.success();

    }

    @GetMapping("/page")
    @ApiOperation("dish view")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {

        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);


        return Result.success(pageResult);
    }
    @DeleteMapping
    @ApiOperation("delete dishes")
    public Result delete(@RequestParam List<Long> ids) {

        dishService.deleteBatch(ids);

        return Result.success();
    }
    @GetMapping("/{id}")
    @ApiOperation("dish query")
    public Result<DishVO> getById(@PathVariable Long id) {
        DishVO dishVO = dishService.getByIdWithFlavor(id);
        return Result.success(dishVO);
    }
    @PutMapping
    @ApiOperation("update dish")
    public Result update(@RequestBody DishDTO dishDTO) {
        dishService.updateWithFlavor(dishDTO);


        return Result.success();
    }
    @GetMapping("/list")
    @ApiOperation("dish list")
    public Result<List<Dish>> list(Long categoryId) {

        List<Dish> list = dishService.list(categoryId);


        return Result.success(list);
    }
}
