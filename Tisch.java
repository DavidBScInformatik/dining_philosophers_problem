package ke6ea3b;

import java.util.ArrayList;

public class Tisch {
	
	ArrayList<Philosoph> arrPhilosophen = new ArrayList<Philosoph>();
	int[][] staebchen = new int[5][1];
	
	// Staebchen Array befüllen - Mehrdimensional - [] = Stäbchen 1-5 [] = 1 oder 0  True/False 
	Tisch() {
		for(int i=0;i<5;i++) {
			staebchen[i][0] = 1;
		}
	}
	
	public void sitDown(Philosoph p){
		this.arrPhilosophen.add(p);
	}
}
