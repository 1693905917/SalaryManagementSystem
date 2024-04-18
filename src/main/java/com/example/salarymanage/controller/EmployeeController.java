package com.example.salarymanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.salarymanage.config.PageResult;
import com.example.salarymanage.config.R;
import com.example.salarymanage.config.Result;
import com.example.salarymanage.domain.*;
import com.example.salarymanage.service.IEmployeeService;
import com.example.salarymanage.service.IProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
     * @description:员工分页查询优化版
     * @author:  HZP
     * @date: 2024/4/2 14:47
     * @param:
     * @return:
     **/
    @GetMapping("/{currentPage}/{pageSize}")
    public R getPage(@PathVariable("currentPage") int currentPage, @PathVariable("pageSize")  int pageSize, EmployeeVO employeeVo){
//        System.out.println("Employee:>>>>>>>"+employeeVo.toString());
        IPage<Employee> page = employeeService.getPage(currentPage, pageSize,employeeVo);
        //如果当前页码值大于了总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if( currentPage > page.getPages()){
            page = employeeService.getPage((int)page.getPages(), pageSize,employeeVo);
        }
//        System.out.println("page:>>>>>>>"+page.getRecords().toString());
        return new R(true, page);
    }
    /*
     * @description:通过ID获取对应员工信息
     * @author:  HZP
     * @date: 2024/4/2 22:35
     * @param:
     * @return:
     **/
    @GetMapping("/{id}")
    public R getById(@PathVariable("id") Integer id){
        return new R(true,employeeService.getById(id));
    }

    /*
     * @description:修改员工信息
     * @author:  HZP
     * @date: 2024/4/2 23:08
     * @param:
     * @return:
     **/
    @PutMapping
    public R update(@RequestBody Employee employee){
        return new R(employeeService.modify(employee));
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
     * @description:删除员工信息
     * @author:  HZP
     * @date: 2024/4/2 23:16
     * @param:
     * @return:
     **/
    @DeleteMapping("{id}")
    public R delete(@PathVariable("id") Integer id){
        Boolean delete = employeeService.delete(id);
        employeeService.SetMarketManagersSalary(id,false);
        return new R(delete);
    }

    /*
     * @description:新增员工信息
     * @author:  HZP
     * @date: 2024/4/2 23:23
     * @param:
     * @return:
     **/
    @PostMapping
    public R save(@RequestBody Employee employee) throws IOException {
        boolean flag = employeeService.save(employee);
        employeeService.SetMarketManagersSalary(employee.getPid(),true);
        if(employee.getName().equals("123")) throw new IOException();
        return new R(flag,flag ? "添加成功^_^" : "添加失败-_-!");
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
//    @PostMapping
//    public Result<String> saveEmployee(@RequestBody Employee employee){
//        employeeService.saveEmployee(employee);
//        return Result.success();
//    }





//    @GetMapping
//    public void getEmployeeSalaryNow(){
//        employeeService.setEmployeeSalaryList();
//    }

}
