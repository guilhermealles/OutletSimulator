package controller;

import java.util.Timer;

import model.Outlet;

public class OutletController {
	
	private static Outlet outlet;
	private static ConsumptionUpdater power_consumption_updater;
	
	public OutletController() {	}
	
	public void initializeOutlet(int outlet_id, int power_consumption) {
		outlet = new Outlet(outlet_id, power_consumption);
		
		// Create a new timer thread to update the power consumption
		power_consumption_updater = new ConsumptionUpdater(outlet);
		Timer timer = new Timer(true);
		timer.scheduleAtFixedRate(power_consumption_updater, Outlet.REFRESH_INTERVAL, Outlet.REFRESH_INTERVAL);
	}
	
	public int getOutletId() {
		return outlet.getId();
	}
	
	public double getPowerConsumed() {
		return outlet.getPowerConsumed();
	}
	
	public double getConsumptionPerMinute() {
		return outlet.getConsumption();
	}
	
	public void setPowerConsumptionPerMinute(double new_value) {
		outlet.setConsumption(new_value);
	}
	
	//TODO .
	public Outlet getOutlet() {
		return outlet;
	}
}
