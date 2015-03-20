package model;

public class Outlet {
	private int id;
	private double power_consumed;
	private double power_consumption_per_minute; // As a first implementation, the power consumption will be updated once every 10 seconds
	
	public static final long REFRESH_INTERVAL = 2000;
	
	public Outlet(int id, int power_consumption_per_minute) {
		this.id = id;
		this.power_consumption_per_minute = power_consumption_per_minute;
	}
	
	public void updatePowerConsumed() {
		double refreshes_per_minute = 60000/REFRESH_INTERVAL;
		this.power_consumed += (double)power_consumption_per_minute/refreshes_per_minute;
	}
	
	public int getId() {
		return this.id;
	}
	
	public double getPowerConsumed() {
		return this.power_consumed;
	}
	
	public double getConsumptionPerMinute() {
		return this.power_consumption_per_minute;
	}
	
	public double getAndResetPowerConsumed() {
		double consumed = this.power_consumed;
		resetPowerConsumed();
		return consumed;
	}
	
	public void resetPowerConsumed() {
		this.power_consumed = 0;
	}
	
	public void setPowerConsumptionPerMinute(double new_value) {
		this.power_consumption_per_minute = new_value;
	}
	
	
}
