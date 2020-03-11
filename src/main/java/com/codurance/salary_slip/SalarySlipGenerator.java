package com.codurance.salary_slip;

public class SalarySlipGenerator {
  public SalarySlip generateFor(Employee employee) {
    return new SalarySlip(employee);
  }
}
