package com.songpeng.sparchetype.system.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.songpeng.sparchetype.common.BaseController;
import com.songpeng.sparchetype.common.Result;
import com.songpeng.sparchetype.system.entity.SysDict;
import com.songpeng.sparchetype.system.service.ISysDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 系统字典表 前端控制器
 * </p>
 *
 * @author SongPeng
 * @since 2019-08-26
 */
@Api(tags = "系统字典表")
@Controller("adminSysDictController")
@RequestMapping("/admin/sys/dict")
public class SysDictController extends BaseController {

    Logger log = LoggerFactory.getLogger(SysDictController.class);

    @Autowired
    private ISysDictService sysDictService;

    @ApiOperation("系统字典信息列表UI")
    @ApiImplicitParams({@ApiImplicitParam(name ="model", value = "模型", defaultValue = "模型")})
    @GetMapping("/list-ui")
    public String listUI(Model model) {
        return "admin/system/dict/list";
    }

    @PostMapping("/page")
    @ResponseBody
    public Result page(Page page) {
        IPage result = sysDictService.page(page);
        return Result.success(result);
    }

    @GetMapping("/add-or-update-ui")
    public String addOrUpdateUI(Model model, SysDict record) {
        if (StringUtils.isNotEmpty(record.getId())) {
            SysDict dict = sysDictService.getById(record.getId());
            model.addAttribute("dict", dict);
        }
        return "admin/system/dict/addOrUpdate";
    }

    @PostMapping("/add-or-update")
    @ResponseBody
    public Result addOrUpdate(SysDict record) {
        sysDictService.saveOrUpdate(record);
        return Result.success(record.getId());
    }
}
