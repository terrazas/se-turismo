package se.negocio.estrategias;

import jess.Activation;
import jess.Strategy;

public final class EstrategiaPrueba implements Strategy{
	
	@Override
	public int compare(Activation arg0, Activation arg1) {
		System.out.println("Comparando regla \""+
				arg0.getRule().getName()+"\" con \""+
				arg1.getRule().getName()+"\"");
		if (arg0.getRule().getName().contains("2"))
			return 1;
		else
			return -1;
	}

	@Override
	public String getName() {
		return "EstrategiaPrueba";
	}

}
