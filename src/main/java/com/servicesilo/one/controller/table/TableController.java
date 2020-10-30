package com.servicesilo.one.controller.table;

import com.servicesilo.one.model.ServiceTable;
import com.servicesilo.one.model.ServiceTableCol;
import com.servicesilo.one.util.CommonRet;
import com.servicesilo.one.util.RetUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/table")
public class TableController {

    @PostMapping(value = "/addTable")
    @ResponseBody
    public CommonRet addTable(@RequestBody ServiceTable table) {

        return RetUtil.success();
    }

    @PostMapping(value = "/updateTable")
    @ResponseBody
    public CommonRet updateTable(@RequestBody ServiceTable table) {

        return RetUtil.success();
    }

    @PostMapping(value = "/deleteTable")
    @ResponseBody
    public CommonRet deleteTable(@RequestParam String ids) {
        return RetUtil.success();
    }


    @PostMapping(value = "/addCol")
    @ResponseBody
    public CommonRet addCol(@RequestBody ServiceTableCol col) {
        return RetUtil.success();
    }

    @PostMapping(value = "/updateCol")
    @ResponseBody
    public CommonRet updateCol(@RequestBody ServiceTableCol col) {

        return RetUtil.success();
    }

    @PostMapping(value = "/deleteCols")
    @ResponseBody
    public CommonRet deleteCols(@RequestParam String ids) {
        return RetUtil.success();
    }
}
