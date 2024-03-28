package com.example.salarymanage;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.salarymanage.dao.EmployeeDao;
import com.example.salarymanage.dao.ProfessionDao;
import com.example.salarymanage.domain.Employee;
import com.example.salarymanage.domain.EmployeeDTO;
import com.example.salarymanage.domain.Profession;
import com.example.salarymanage.service.IEmployeeService;
import com.example.salarymanage.service.IProfessionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@SpringBootTest
class SalaryManageApplicationTests {

    @Autowired
    private ProfessionDao professionDao;
    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IProfessionService professionService;


    @Test
    void delete(){
        employeeService.removeById(6);
    }
    @Test
    //分页功能
    void Page(){
        Page<Employee> employeePage = new Page<>(2, 5);
        Page<Employee> page = employeeService.page(employeePage);
    }

    @Test
    //数据录入：输入各种数据
    void save(){
        Employee employee = new Employee();
        employee.setName("郭铸");
        employee.setSex("1");
        employee.setAge(28);
        employee.setPid(3);
        employeeService.save(employee);
    }

    @Test
    void getAll(){
        //查询全部数据信息并且按照工资进行排序
        List<Employee> employeeList = employeeService.list();
        System.out.println(employeeList);
        List<Profession> professionList = professionService.list(new QueryWrapper<Profession>().orderByDesc("salary"));
        System.out.println(professionList);
    }


    @Test
    void contextLoads() {

    }

    @Test
    void testSelectById(){
        //根据ID查询
        Employee employee = employeeDao.selectById(1);
        System.out.println(employee);
        Profession profession = professionDao.selectById(1);
        System.out.println(profession);
    }

    @Test
    void testSelectByPid(){
        Employee employee = employeeDao.selectById(1);
        System.out.println(employee);
        Profession profession = professionDao.selectById(employee.getPid());
        EmployeeDTO employeeDTO = new EmployeeDTO(employee, profession);
        System.out.println(employeeDTO);
    }

    @Test
    void testSelectAll(){
        //查询全部
        List<Employee> employees = employeeDao.selectList(null);
        System.out.println(employees);
        List<Profession> professions = professionDao.selectList(null);
        System.out.println(professions);
    }
    @Test
    void testInsert(){
        //添加雇员
        Employee employee = new Employee();
        employee.setName("曹操");
        employee.setSex("1");
        employee.setAge(43);
        employee.setPid(4);
        int insert = employeeDao.insert(employee);
        System.out.println(insert);
    }

    @Test
    void testUpdate(){
        Employee employee = new Employee();
        employee.setId(2);
        employee.setName("小志");
        employee.setAge(22);
        int i = employeeDao.updateById(employee);
        System.out.println(i);
    }

    @Test //分页功能
    void testGetPage(){
        IPage page=new Page(2,5);
        employeeDao.selectPage(page, null);

    }

    //过滤查询
    @Test
    void testGetListByPid(){
        QueryWrapper<Employee> employeeQueryWrapper = new QueryWrapper<>();
        employeeQueryWrapper.select("sum(salary) as sum_salary").eq("pid",3);
//        List<Employee> employeeList = employeeDao.selectList(employeeQueryWrapper);
        Map<String, Object> map = employeeDao.selectMaps(employeeQueryWrapper).get(0);

        System.out.println(map.get("sum_salary"));
    }


    @Test
    void testLikeQuery(){
        Employee employee = new Employee();
        //employee.setName("小志");
        employee.setPid(3);
        IPage page=new Page(1,5);
        QueryWrapper<Employee> employeeQueryWrapper = new QueryWrapper<>();
        employeeQueryWrapper.like(employee.getName()!=null,"name",employee.getName());
        employeeQueryWrapper.like(employee.getPid()!=null,"pid",employee.getPid());
        employeeQueryWrapper.orderByDesc("salary");
        IPage page1 = employeeDao.selectPage(page, employeeQueryWrapper);
        System.out.println(page1.getRecords().toString());
    }
    //设置工作时间
    static final Integer WorkTimes=100;
    //设置销售额
    static final Integer Market=10000;

    @Test
     void setEmployeeSalaryList() {
        List<Employee> employeeList = employeeDao.selectList(null);
        for (Employee employee : employeeList) {
            //技术员工资
            if(employee.getPid()==2){
                employee.setSalary(WorkTimes*100);
                employeeDao.updateById(employee);
            }
            //销售员工资
            if(employee.getPid()==3){
                employee.setSalary((int)(Market*0.04));
                employeeDao.updateById(employee);
            }
        }
        //设置销售经理的工资
        QueryWrapper<Employee> employeeQueryWrapper = new QueryWrapper<>();
        employeeQueryWrapper.select("sum(salary) as sum_salary").eq("pid",3);
        Map<String, Object> map = employeeDao.selectMaps(employeeQueryWrapper).get(0);
        QueryWrapper<Employee> w1 = new QueryWrapper<>();
        w1.eq("pid",4);
        List<Employee> markerManager = employeeDao.selectList(w1);
        BigDecimal sum_salary = (BigDecimal) (map.get("sum_salary"));
        int sum=(int)(Integer.parseInt(sum_salary.toString())*0.005);
        for (Employee employee : markerManager) {
            employee.setSalary(sum+5000);
            employeeDao.updateById(employee);
        }
        //设置经理工资
        QueryWrapper<Employee> w2 = new QueryWrapper<>();
        w2.eq("pid",1);
        List<Employee> bossList = employeeDao.selectList(w2);
        for (Employee boss : bossList) {
            boss.setSalary(8000);
            employeeDao.updateById(boss);
        }
    }

}
