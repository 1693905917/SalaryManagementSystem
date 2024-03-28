package com.example.salarymanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.salarymanage.config.PageResult;
import com.example.salarymanage.domain.Employee;
import com.example.salarymanage.domain.EmployeeDTO;
import com.example.salarymanage.domain.EmployeePageByNameOrPidDTO;
import com.example.salarymanage.domain.EmployeePageQueryDTO;

import java.util.List;

public interface IEmployeeService extends IService<Employee> {


    /*
     * @description:设置全部员工的工资信息
     * @author:  HZP
     * @date: 2024/3/20 17:02
     * @param:
     * @return:
     **/
    void setEmployeeSalaryList();

    /*
     * @description:获取全部员工信息
     * @author:  HZP
     * @date: 2024/3/20 17:03
     * @param:
     * @return:
     **/
    List<EmployeeDTO> getEmployeeList();


    /*
     * @description:新增用户
     * @author:  HZP
     * @date: 2024/3/20 17:01
     * @param:
     * @return:
     **/
    void saveEmployee(Employee employee);

    /*
     * @description:员工分页查询
     * @author:  HZP
     * @date: 2024/3/20 17:03
     * @param:
     * @return:
     **/
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /*
     * @description:用户NameOrPid进行分页查询
     * @author:  HZP
     * @date: 2024/3/20 17:03
     * @param:
     * @return:
     **/
    PageResult pageQueryByNameOrPid(EmployeePageByNameOrPidDTO employeePageByNameOrPidDTO);

    /*
     * @description:各销售经理的工资计算及最终按工资举行的排序
     * @author:  HZP
     * @date: 2024/3/20 17:03
     * @param:
     * @return:
     **/
    PageResult pageQueryByMarketManagers(EmployeePageQueryDTO employeePageQueryDTO);

}
