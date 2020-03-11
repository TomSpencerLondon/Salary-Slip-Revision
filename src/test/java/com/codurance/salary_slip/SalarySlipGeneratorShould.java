package com.codurance.salary_slip;

import org.junit.Assert;
import org.junit.Test;

public class SalarySlipGeneratorShould {

  @Test
  public void create_slip_for_employee_with_lowest_bracket() {
    int id = 12345;
    String name = "John J Doe";
    double annualGrossSalary = 5000;
    Employee employee = new Employee(id, name, annualGrossSalary);
    SalarySlipGenerator salarySlipGenerator = new SalarySlipGenerator();
    double monthlySalary = 416;

    SalarySlip result = salarySlipGenerator.generateFor(employee);
    Assert.assertEquals(monthlySalary, result.getMonthlySalary(), 0);
  }
}
