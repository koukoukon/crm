package me.hibiki.crm.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.hibiki.crm.domain.SaleChance;
import me.hibiki.crm.service.SaleChanceService;
import me.hibiki.crm.util.ResultEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 高弘昆
 * @date 2020/7/17 21:59
 */
@RestController
@RequestMapping(path = "/admin/saleChance")
public class SaleChanceController {
    @Resource
    private SaleChanceService saleChanceService;

    @GetMapping
    public ResultEntity<SaleChance> listSaleChance(@RequestParam Integer page, @RequestParam Integer rows, SaleChance saleChance) {
        System.out.println(saleChance);
        Page<SaleChance> saleChancePage = new Page<>(page, rows, true);
        IPage<SaleChance> saleChanceIPage = saleChanceService.page(saleChancePage,new LambdaQueryWrapper<>(saleChance));
        return new ResultEntity<SaleChance>().rows(saleChanceIPage.getRecords()).total(saleChanceIPage.getTotal());
    }

    //删除
    @DeleteMapping
    public ResultEntity<Object> removeSaleChance(@RequestBody List<Integer> chanceIds) {
        if (saleChanceService.removeByIds(chanceIds)) {
            return new ResultEntity<>().code(1).message("删除销售机会信息成功");
        } else {
            return new ResultEntity<>().code(0).message("删除销售机会信息失败");
        }
    }

    //添加和修改
    @PatchMapping
    public ResultEntity<Object> saveOrEditSaleChance(@Validated SaleChance saleChance) {
        saleChance.setCreateTime(LocalDateTime.now());
        saleChance.setDevResult(0);
        if (saleChance.getAssignMan()!=null){
            saleChance.setAssignTime(LocalDateTime.now());
            saleChance.setState(1);
        }
        Integer saleChanceId = saleChance.getId();
        if (saleChanceService.saveOrUpdate(saleChance)) {
            if (saleChanceId == null) {
                return new ResultEntity<>().code(1).message("添加销售机会信息成功");
            } else {
                return new ResultEntity<>().code(1).message("修改销售机会信息成功");
            }
        } else {
            if (saleChanceId == null) {
                return new ResultEntity<>().code(0).message("添加销售机会信息失败");
            } else {
                return new ResultEntity<>().code(0).message("修改销售机会信息失败");
            }
        }
    }

}
