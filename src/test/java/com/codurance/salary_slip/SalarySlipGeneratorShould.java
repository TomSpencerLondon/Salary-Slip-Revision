package com.codurance.salary_slip;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

public class SalarySlipGeneratorShould {
  @Test
  public void create_slip_for_employee_with_lowest_bracket() {
    int id = 12345;
    String name = "John J Doe";
    double annualGrossSalary = 5000;
    Employee employee = new Employee(id, name, annualGrossSalary);
    SalarySlipGenerator salarySlipGenerator = new SalarySlipGenerator();
    double monthlySalary = 416.67;

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
    Assert.assertEquals(83.33, result.getTaxableMonthlyIncome(), 0.1);
    Assert.assertEquals(16.67, result.getMonthlyTaxPayable(), 0.1);
  }

  @Test
  public void create_slip_for_employee_with_gross_salary_of_45_000() {
    int id = 12345;
    String name = "John J Doe";
    double annualGrossSalary = 45000;
    Employee employee = new Employee(id, name, annualGrossSalary);
    SalarySlipGenerator salarySlipGenerator = new SalarySlipGenerator();
    SalarySlip result = salarySlipGenerator.generateFor(employee);

    Assert.assertEquals(3_750.00, result.getGrossMonthlySalary(), 0.1);
    Assert.assertEquals(2_833.33, result.getTaxableMonthlyIncome(), 0.1);
    Assert.assertEquals(600.00, result.getMonthlyTaxPayable(), 0.1);
    Assert.assertEquals(33.33, result.additionalTaxDueToExceedingHigherRateThreshold(), 0.1);
    Assert.assertEquals(352.73, result.getNationalInsuranceContribution(), 0.1);
  }

  @Test
  public void create_slip_for_employee_with_gross_salary_of_101_000() {
    int id = 12345;
    String name = "John J Doe";
    double annualGrossSalary = 101000.00;
    Employee employee = new Employee(id, name, annualGrossSalary);
    SalarySlipGenerator salarySlipGenerator = new SalarySlipGenerator();
    SalarySlip result = salarySlipGenerator.generateFor(employee);


//    Assert.assertEquals(8_416.67, result.getGrossMonthlySalary(), 0.1);
//    Assert.assertEquals(7541.67, result.getTaxableMonthlyIncome(), 0.1);
    Assert.assertEquals(2483.33, result.getMonthlyTaxPayable(), 0.1);
//    Assert.assertEquals(33.33, result.additionalTaxDueToExceedingHigherRateThreshold(), 0.1);
//    Assert.assertEquals(352.73, result.getNationalInsuranceContribution(), 0.1);
  }
}
