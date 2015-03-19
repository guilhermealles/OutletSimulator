package model;

public class Outlet {
	private int id;
	private int power_consumed;
	private int power_consumption_per_minute;
	
	public Outlet() {
		
	}
	
	public void updatePowerConsumed() {
		// TODO
	}
	
	public int getPowerConsumed() {
		return this.power_consumed;
	}
	
	public int getAndResetPowerConsumed() {
		int consumed = this.power_consumed;
		reset();
		return consumed;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void reset() {
		// TODO
	}
}
