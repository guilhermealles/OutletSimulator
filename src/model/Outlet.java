package model;

public class Outlet {
	private int id;
	private double power_consumed;
	private double power_consumption;
	
	public static final long REFRESH_INTERVAL = 2000;
	
	public Outlet(int id, int power_consumption) {
		this.id = id;
		this.power_consumption = power_consumption;
	}
	
	public void updatePowerConsumed() {
		this.power_consumed += power_consumption * (REFRESH_INTERVAL/1000);
	}
	
	public int getId() {
		return this.id;
	}
	
	public double getPowerConsumed() {
		return this.power_consumed;
	}
	
	public double getConsumption() {
		return this.power_consumption;
	}
	
	public double getAndResetPowerConsumed() {
		double consumed = this.power_consumed;
		resetPowerConsumed();
		return consumed;
	}
	
	public void resetPowerConsumed() {
		this.power_consumed = 0;
	}
	
	public void setConsumption(double new_value) {
		this.power_consumption = new_value;
	}
	
	
}
