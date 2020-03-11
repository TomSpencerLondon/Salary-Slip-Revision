package com.codurance.salary_slip;

public class SalarySlip {

  private final String name;
  private final double salary;

  public SalarySlip(Employee employee) {
    this.name = employee.getName();
    this.salary = employee.getGrossSalary();
  }

  public double getMonthlySalary() {
    return Math.floor(salary / 12);
  }
}
