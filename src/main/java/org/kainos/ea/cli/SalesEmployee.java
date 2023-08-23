package org.kainos.ea.cli;

public class SalesEmployee extends Employee {
    private double monthlySale;
    private float commissionRate;

    public SalesEmployee(int employeeId, String name, double salary, double monthlySale, float commissionRate) {
        super(employeeId, name, salary);
        this.monthlySale = monthlySale;
        this.commissionRate = commissionRate;
    }

    @Override
    public double calcPay(){ return getSalary()/12 + (monthlySale*commissionRate);}
    public double getMonthlySale() {
        return monthlySale;
    }

    public float getCommissionRate() {
        return commissionRate;
    }

    public void setMonthlySale(double monthlySale) {
        this.monthlySale = monthlySale;
    }

    public void setCommissionRate(float commissionRate) {
        this.commissionRate = commissionRate;
    }


}
