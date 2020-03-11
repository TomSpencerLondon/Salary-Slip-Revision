package com.codurance.salary_slip;

public class Employee {
  private int id;
  private String name;
  private double annualGrossSalary;

  public Employee(int id, String name, double annualGrossSalary) {
    this.id = id;
    this.name = name;
    this.annualGrossSalary = annualGrossSalary;
  }

  public String getName() {
    return this.name;
  }

  public double getGrossSalary() {
    return annualGrossSalary;
  }
}
