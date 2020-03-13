package com.codurance.salary_slip;

public class SalarySlip {

  public static final int MINIMUM_SALARY_FOR_NATIONAL_INSURANCE = 8060;
  public static final int MONTHS_IN_YEAR = 12;
  public static final double NATIONAL_INSURANCE_PERCENTAGE = 0.12;
  public static final double BASIC_RATE = 0.20;
  public static final double GROSS_TAX_FREE_ALLOWANCE = 11000.0;
  public static final double ROUNDING_ADJUSTMENT = 100.0;
  public static final double MONTHLY_HIGHER_RATE_THRESHOLD = 3583.33;
  public static final double HIGHER_RATE_THRESHOLD = 43000.00;

  private final String name;
  private final double salary;

  public SalarySlip(Employee employee) {
    this.name = employee.getName();
    this.salary = employee.getGrossSalary();
  }

  public double getGrossMonthlySalary() {
    double result = (salary / 12);
    return Math.round(result * ROUNDING_ADJUSTMENT) / ROUNDING_ADJUSTMENT;
  }

  public double getNationalInsuranceContribution() {
    double yearlyContribution = salary - MINIMUM_SALARY_FOR_NATIONAL_INSURANCE;
    yearlyContribution = yearlyContribution * NATIONAL_INSURANCE_PERCENTAGE;
    yearlyContribution -= higherRateFilter();
    return Math.round((yearlyContribution / MONTHS_IN_YEAR) * ROUNDING_ADJUSTMENT) / ROUNDING_ADJUSTMENT;
  }

  private double higherRateFilter() {
    double tenPercent = 0;
    if (salary > HIGHER_RATE_THRESHOLD) {
      double nationalInsuranceExcessReduction = salary - 43000.00;
      tenPercent = nationalInsuranceExcessReduction * 0.10;
    }
    return tenPercent;
  }

  public double getMonthlyTaxFreeAllowance(){
    double monthlyTaxFeeAllowance = GROSS_TAX_FREE_ALLOWANCE / MONTHS_IN_YEAR;
    return Math.round(monthlyTaxFeeAllowance * ROUNDING_ADJUSTMENT) / ROUNDING_ADJUSTMENT;
  }

  public double getTaxableMonthlyIncome(){
    System.out.println(getGrossMonthlySalary());
    System.out.println(getMonthlyTaxFreeAllowance());
    return getGrossMonthlySalary() - getMonthlyTaxFreeAllowance();
  }

  public double getMonthlyTaxPayable(){
    double result = getTaxableMonthlyIncome() * BASIC_RATE;
    if (getHigherRateMonthlyTaxableIncome() > 0){
      result += additionalTaxDueToExceedingHigherRateThreshold();
    }
    return result;
  }

  public double additionalTaxDueToExceedingHigherRateThreshold(){
    return ((salary - HIGHER_RATE_THRESHOLD) / MONTHS_IN_YEAR) * BASIC_RATE;
  }

  public double getHigherRateMonthlyTaxableIncome(){
    return getGrossMonthlySalary() - MONTHLY_HIGHER_RATE_THRESHOLD;
  }
}
