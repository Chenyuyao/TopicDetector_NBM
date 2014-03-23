package NBM;
import java.lang.Math;

public class SpecWord {
	int word;
	int ct1,ct2,nc1,nc2;
	double priority;
	
	public SpecWord(int w, int ict1, int ict2, int inc1, int inc2) {
		word = w;
		ct1 = ict1;
		ct2 = ict2;
		nc1 = inc1;
		nc2 = inc2;
		
		priority = Math.log((double)ct1 / (double)(ct1+nc1) ) - Math.log((double)ct2 / (double)(ct2+nc2) );
	}
}