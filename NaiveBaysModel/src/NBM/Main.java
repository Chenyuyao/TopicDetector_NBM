package NBM;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;


class Main {
	public static void main(String[] args) {
		Global g = new Global();		
		Vector<Factor> flst = new Vector<Factor>();
		
		//create factor list i.e. create Bayesian network
		//add root
		String[] rs = {"lab1"};
		Factor fr = new Factor(1, rs);
		double[] rval = {0.5,0.5};
		fr.set(rval);
		flst.add(fr);
		
		//find 10 max priority word feature
		WordComparator wc = new WordComparator();
		int qsize = 10;
		PriorityQueue<SpecWord> q = new PriorityQueue<SpecWord>(qsize, wc);
		int[] wlst = new int[qsize];
		
		for (int i=0; i < Global.TrainWord.size(); i++) {
			Integer word = Global.TrainWord.get(i);
			
			int ct1 = 1,ct2 = 1,nc1 = 1,nc2 = 1;
			ArrayList<Integer> examples = Global.TrainDoc;
			for (int j = 0; j < examples.size(); j++) {
				if (Global.TrainDocWord.get(examples.get(j)).contains((int)word)) {
					if (Global.TrainDocCat.get(examples.get(j)) == 1 ) {
						ct1++;
					}
					else {
						ct2++;
					}
				}
				else {
					if (Global.TrainDocCat.get(examples.get(j)) == 1 ) {
						nc1++;
					}
					else {
						nc2++;
					}
				}
			}		
			
			SpecWord sw = new SpecWord(word,ct1,ct2,nc1,nc2);
			if (q.size() < qsize) {
				q.add(sw);
			}
			else {
				SpecWord cw = q.poll();
				if (sw.priority > cw.priority) q.add(sw);
				else q.add(cw);
			}
		}
		
		for (int i =0; i<qsize; i++) {			
			//create string list as {word,"lab"}
			SpecWord word = q.poll();
			String ws = (int)word.word+"";
			System.out.print(ws+"\n");
			wlst[i] = word.word;
			String[] sl = {ws, "lab1"};
			Factor f = new Factor(2,sl);
			double lb1 = (double)word.ct1 + (double)word.nc1;
			double lb2 = (double)word.ct2 + (double)word.nc2;
			double[] val = {(double)word.ct1/lb1, (double)word.ct2/lb2, (double)word.nc1/lb1, (double)word.nc2/lb2}; 
			f.set(val);
	
			flst.add(f);
		}
		
		//start test
		//choose elimination order, this is empty for all docs since we restrict all word and want lab1
		String[] olst = {};
		
		//for each docs in Test, determine it's label
		Tools t = new Tools();
		int correct =0;
		
		for (int i = 0; i < Global.TestDoc.size(); i++) {
			int doc = Global.TestDoc.get(i);
			Vector<Factor> flst2 = new Vector<Factor>();
			for (int j =0; j<flst.size(); j++) {
				Factor f = new Factor(flst.get(j));
				flst2.add(f);
			}
			//create restrict list
			String[] elst = new String[wlst.length];
			int[] vlst	=	new int[wlst.length];
			
			for (int j=0; j < wlst.length; j++) {
				Integer word = wlst[j];
				String ws = (int)word +"";
				int val;
				if (Global.TestDocWord.get(doc).contains(word)) {
					val = 0;
				}
				else val=1;
				elst[j] = ws;
				vlst[j] = val;
			}
			Factor resf = t.inference(flst2, "lab1", olst, elst, vlst);
			double proLab1 = resf.oneDA[0];
			int res = (proLab1>0.5)?1:2;
			if (res == Global.TestDocCat.get(doc)) correct++;
		}
		System.out.print((double)correct/707 +"\n");
		
		
		int correct2 =0;
		
		for (int i = 0; i < Global.TrainDoc.size(); i++) {
			int doc = Global.TrainDoc.get(i);
			Vector<Factor> flst2 = new Vector<Factor>();
			for (int j =0; j<flst.size(); j++) {
				Factor f = new Factor(flst.get(j));
				flst2.add(f);
			}
			//create restrict list
			String[] elst = new String[wlst.length];
			int[] vlst	=	new int[wlst.length];
			
			for (int j=0; j < wlst.length; j++) {
				Integer word = wlst[j];
				String ws = (int)word +"";
				int val;
				if (Global.TrainDocWord.get(doc).contains(word)) {
					val = 0;
				}
				else val=1;
				elst[j] = ws;
				vlst[j] = val;
			}
			Factor resf = t.inference(flst2, "lab1", olst, elst, vlst);
			double proLab1 = resf.oneDA[0];
			int res = (proLab1>0.5)?1:2;
			if (res == Global.TrainDocCat.get(doc)) correct2++;
		}
		System.out.print((double)correct2/1061);
	}
	
}