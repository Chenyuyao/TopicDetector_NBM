package NBM;
import java.util.Vector;


public class Tools {
	public Factor inference(Vector<Factor> flst, String queryVar, String[] olst, String[] elst, int[] vlst){
		if (elst!=null && vlst!=null) {
			if (elst.length != vlst.length) {
				System.out.print("Evidence lst does not match value lst\n");
	        	System.exit(0);
			}
			
			//Replace each factor f that mentions a variable(s) in E with its restriction e
			for (int i =0; i<elst.length; i++) {
				for (int j=0; j<flst.size(); j++) {
					if(flst.get(j)!=null&&flst.get(j).contain(elst[i])) {
						flst.set(j, flst.get(j).restrict(elst[i], vlst[i]));
					}
				}
			}
		}
		//For each variable tar -- in the order given -- eliminate tar
		for (int i = 0; i<olst.length; i++){
			String tar = olst[i];
			
			//Compute new factor nf = f1 x f2 x … x fk, where the fj are the factors in flst that include tar
			Factor nf = null;
			for (int j=0; j<flst.size(); j++) {
				if (flst.get(j) != null && flst.get(j).contain(tar)) {
					nf = flst.get(j).multiply(nf);				
					//Remove the factors fj
					flst.remove(j);
					j--;
				}
			}
			//sum nf with tar to eliminate tar
			nf = nf.sum(tar);
			nf.print();
			//add new factor nf to flst
			flst.add(nf);
		}
		//The remaining factors refer only to the query variable queryVar. Take their product and normalize to produce P(queryVar)
		Factor res = null;
		for (int i=0; i<flst.size(); i++) {
			if (flst.get(i) != null) {
				res = flst.get(i).multiply(res);
			}
		}
		res.norm();
		return res;
		
	}
}
