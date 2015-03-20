package controller;

import java.util.TimerTask;

import view.OutletSimulator;
import model.Outlet;

public class ConsumptionUpdater extends TimerTask {

	private Outlet outlet;

	public ConsumptionUpdater(Outlet o) {
		this.outlet = o;
	}
	
	@Override
	public void run() {
		this.outlet.updatePowerConsumed();
		OutletSimulator.updateFrameCounters();
	}

}
