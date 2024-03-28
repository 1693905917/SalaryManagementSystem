package com.example.salarymanage.controller;

import com.example.salarymanage.config.PageResult;
import com.example.salarymanage.config.Result;
import com.example.salarymanage.domain.*;
import com.example.salarymanage.service.IEmployeeService;
import com.example.salarymanage.service.IProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @BelongsProject: SalaryManage
 * @BelongsPackage: com.example.salarymanage.controller
 * @Author: ASUS
 * @CreateTime: 2024-03-13  15:13
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IProfessionService professionService;

    /*
     * @description:查询全部员工数据信息并且按照工资进行排序
     * @author:  HZP
     * @date: 2024/3/20 14:32
     * @param: []
     * @return: java.util.List<com.example.salarymanage.domain.EmployeeDTO>
     **/
    @GetMapping
    public List<EmployeeDTO> getAll(){
        return employeeService.getEmployeeList();
    }

    /*
     * @description:各销售经理的工资计算及最终按工资举行的排序
     * @author:  HZP
     * @date: 2024/3/20 17:02
     * @param:
     * @return:
     **/
    @GetMapping("/marketManager")
    public Result<PageResult> pageMarketManagersQuery(@RequestBody EmployeePageQueryDTO employeePageQueryDTO){
        PageResult pageResult=employeeService.pageQueryByMarketManagers(employeePageQueryDTO);
        return Result.success(pageResult);
    }


    /*
     * @description:员工分页查询
     * @author:  HZP
     * @date: 2024/3/20 15:11
     * @param:
     * @return:
     **/
    @GetMapping("/page")
    public Result<PageResult> page(@RequestBody EmployeePageQueryDTO employeePageQueryDTO){
        PageResult pageResult=employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResult);
    }

    /*
     * @description:用户NameOrPid进行分页查询
     * @author:  HZP
     * @date: 2024/3/20 16:17
     * @param:
     * @return:
     **/
    @GetMapping("/like")
    public Result<PageResult>  pageByNameOrPid(@RequestBody EmployeePageByNameOrPidDTO employeePageByNameOrPidDTO){
        PageResult pageResult=  employeeService.pageQueryByNameOrPid(employeePageByNameOrPidDTO);
        return Result.success(pageResult);
    }


    /*
     * @description:新增用户
     * @author:  HZP
     * @date: 2024/3/20 15:12
     * @param:
     * @return:
     **/
    @PostMapping
    public Result<String> saveEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
        return Result.success();
    }





//    @GetMapping
//    public void getEmployeeSalaryNow(){
//        employeeService.setEmployeeSalaryList();
//    }

}
