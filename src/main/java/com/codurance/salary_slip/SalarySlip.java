package com.codurance.salary_slip;

public class SalarySlip {

  public static final int MINIMUM_SALARY_FOR_NATIONAL_INSURANCE = 8060;
  public static final int MONTHS_IN_YEAR = 12;
  public static final double NATIONAL_INSURANCE_PERCENTAGE = 0.12;
  public static final double TAXABLE_PERCENTAGE = 0.20;
  public static final double GROSS_TAX_FREE_ALLOWANCE = 11000.0;
  public static final double ROUNDING_ADJUSTMENT = 100.0;
  public static final double MONTHLY_HIGHER_RATE_THRESHOLD = 3583.33;
  private final String name;
  private final double salary;

  public SalarySlip(Employee employee) {
    this.name = employee.getName();
    this.salary = employee.getGrossSalary();
  }

  public double getGrossMonthlySalary() {
    return Math.floor(salary / 12);
  }

  public double getNationalInsuranceContribution() {
    double result = salary - MINIMUM_SALARY_FOR_NATIONAL_INSURANCE;
    result = result * NATIONAL_INSURANCE_PERCENTAGE;
    return Math.floor(result / MONTHS_IN_YEAR);
  }

  public double getMonthlyTaxFreeAllowance(){
    double monthlyTaxFeeAllowance = GROSS_TAX_FREE_ALLOWANCE / MONTHS_IN_YEAR;
    return Math.round(monthlyTaxFeeAllowance * ROUNDING_ADJUSTMENT) / ROUNDING_ADJUSTMENT;
  }

  public double getTaxableIncome(){
    return getGrossMonthlySalary() - getMonthlyTaxFreeAllowance();
  }

  public double getTaxPayable(){
    return getTaxableIncome() * TAXABLE_PERCENTAGE;
  }

  public double getHigherRateMonthlyTaxableIncome(){
    return getGrossMonthlySalary() - MONTHLY_HIGHER_RATE_THRESHOLD;
  }
}
