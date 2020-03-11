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
    Assert.assertEquals(monthlySalary, result.getGrossMonthlySalary(), 0);
  }

  @Test
  public void create_slip_for_employee_with_national_insurance() {
    int id = 12345;
    String name = "John J Doe";
    double annualGrossSalary = 9060;
    Employee employee = new Employee(id, name, annualGrossSalary);
    SalarySlipGenerator salarySlipGenerator = new SalarySlipGenerator();
    double nationalInsuranceContribution = 10;

    SalarySlip result = salarySlipGenerator.generateFor(employee);
    Assert.assertEquals(nationalInsuranceContribution, result.getNationalInsuranceContribution(), 0);
  }


  @Test
  public void create_slip_for_employee_with_taxable_income() {
    int id = 12345;
    String name = "John J Doe";
    double annualGrossSalary = 12000;
    Employee employee = new Employee(id, name, annualGrossSalary);
    SalarySlipGenerator salarySlipGenerator = new SalarySlipGenerator();

    double nationalInsuranceContribution = 10;

    SalarySlip result = salarySlipGenerator.generateFor(employee);


    Assert.assertEquals(916.67, result.getMonthlyTaxFreeAllowance(), 0.1);
    Assert.assertEquals(83.33, result.getTaxableIncome(), 0.1);
    Assert.assertEquals(16.67, result.getTaxPayable(), 0.1);
  }

  @Test
  public void create_slip_for_employee_with_gross_salary_of_45_000() {
    int id = 12345;
    String name = "John J Doe";
    double annualGrossSalary = 45000;
    Employee employee = new Employee(id, name, annualGrossSalary);
    SalarySlipGenerator salarySlipGenerator = new SalarySlipGenerator();
    SalarySlip result = salarySlipGenerator.generateFor(employee);


    Assert.assertEquals(166.67, result.getHigherRateMonthlyTaxableIncome(), 0.1);
  }
}
