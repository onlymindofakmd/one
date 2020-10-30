package com.servicesilo.one.controller.workflow;

import com.servicesilo.one.util.CommonRet;
import com.servicesilo.one.util.RetUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/workflow")
public class WorkflowController {

    @PostMapping(value = "/addWorkflow")
    @ResponseBody
    public CommonRet addWorkflow() {
        return RetUtil.success();
    }

    @PostMapping(value = "/updateWorkflow")
    @ResponseBody
    public CommonRet updateWorkflow() {
        return RetUtil.success();
    }

    @PostMapping(value = "/deleteWorkflow")
    @ResponseBody
    public CommonRet deleteWorkflow() {
        return RetUtil.success();
    }

    @PostMapping(value = "/addNodes")
    @ResponseBody
    public CommonRet addNodes() {
        return RetUtil.success();
    }

    @PostMapping(value = "/configNodes")
    @ResponseBody
    public CommonRet configNode() {
        return RetUtil.success();
    }

    @PostMapping(value = "/updateNodes")
    @ResponseBody
    public CommonRet updateNodes() {
        return RetUtil.success();
    }

    @PostMapping(value = "/deleteNodes")
    @ResponseBody
    public CommonRet deleteNodes() {
        return RetUtil.success();
    }
}
