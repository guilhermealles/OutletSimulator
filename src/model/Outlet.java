package model;

import java.util.Timer;

public class Outlet {
	private int id;
	private double power_consumed;
	private int power_consumption_per_minute; // As a first implementation, the power consumption will be updated once every 10 seconds
	
	private static final long REFRESH_INTERVAL = 10000;
	
	public Outlet(int id, int power_consumption_per_minute) {
		this.id = id;
		this.power_consumption_per_minute = power_consumption_per_minute;
		
		// Create a new timer thread to update the power consumption
		ConsumptionUpdater power_consumption_updater = new ConsumptionUpdater(this);
		Timer timer = new Timer(true);
		timer.scheduleAtFixedRate(power_consumption_updater, REFRESH_INTERVAL, REFRESH_INTERVAL);	
	}
	
	protected void updatePowerConsumed() {
		double refreshes_per_minute = 60000/REFRESH_INTERVAL;
		this.power_consumed += (double)power_consumption_per_minute/refreshes_per_minute;
	}
	
	public double getPowerConsumed() {
		return this.power_consumed;
	}
	
	public double getAndResetPowerConsumed() {
		double consumed = this.power_consumed;
		resetPowerConsumed();
		return consumed;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void resetPowerConsumed() {
		this.power_consumed = 0;
	}
}
