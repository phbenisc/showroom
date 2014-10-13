package de.htwg_konstanz.ui.configuration.problemGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.uncommons.maths.random.CMWC4096RNG;
import org.uncommons.maths.random.CellularAutomatonRNG;
import org.uncommons.maths.random.JavaRNG;
import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.XORShiftRNG;

public class RNGManager {
	
	private interface Irng{
		public Random getInstance();
	}
	
	private Map<String, Irng> rngs = new HashMap<>();
	
	public RNGManager(){		
		rngs.put(JavaRNG.class.getSimpleName(), ()-> new JavaRNG());
		rngs.put(MersenneTwisterRNG.class.getSimpleName(), ()-> new MersenneTwisterRNG());
		rngs.put(CellularAutomatonRNG.class.getSimpleName(), ()-> new CellularAutomatonRNG());
		rngs.put(CMWC4096RNG.class.getSimpleName(), ()-> new CMWC4096RNG());
		rngs.put(XORShiftRNG.class.getSimpleName(), ()-> new XORShiftRNG());			
	}
	
	public Set<String> getAllRngNames(){
		return rngs.keySet();
	}
	public Random getRNG(String name){
		return rngs.get(name).getInstance();
	}
	
	

}
