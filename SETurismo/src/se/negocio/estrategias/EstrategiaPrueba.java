package se.negocio.estrategias;

import jess.Activation;
import jess.Fact;
import jess.JessException;
import jess.Strategy;

public final class EstrategiaPrueba implements Strategy{
	
	@Override
	public int compare(Activation arg0, Activation arg1) {
		Fact tour0=null;
		Fact tour1=null;
		
		for(int i=0;i<arg0.getToken().size();i++){
			if(arg0.getToken().fact(i).getName()=="Tour")
				tour0=arg0.getToken().fact(i);
		}
		
		for(int j=0;j<arg1.getToken().size();j++){
			if(arg1.getToken().fact(j).getName()=="Tour")
				tour1=arg1.getToken().fact(j);
		}
		
		if(tour0!=null && tour1!=null){
			System.out.println("comparando tours "+tour0.getFactId()+" "+tour1.getFactId());
			
			try {
				System.out.println("de valores "+tour0.getSlotValue("costo").toString()+" y "+tour1.getSlotValue("costo").toString());
			} catch (JessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
			
		return 0;
		
	}

	@Override
	public String getName() {
		return "EstrategiaPrueba";
	}

}
