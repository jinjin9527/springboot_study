package com.sylinx.springbootstudy.restcontroller;

import com.sylinx.springbootstudy.model.Employee;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forthcontoller")
public class ForthBeanRestController {

    @PostMapping("/employees")
    Employee replaceEmployee(@RequestBody Employee newEmployee) {
        newEmployee.setId(newEmployee.getId() + "123");
        newEmployee.setName(newEmployee.getName() + "124");
        return newEmployee;
    }
}
