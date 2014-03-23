package NBM;

public class Factor {
	public int degree;
	double[] oneDA;
	double[][] twoDA;
	double[][][] threeDA;
	double[][][][] fourDA;
	String[] vars;
	double sum;
	
	Factor(int d, String[] variable) {
		switch (d) {
        case 1:  
        	oneDA = new double[2];
            break;
 
        case 2:  
        	twoDA = new double[2][2];
            break;

        case 3:  
        	threeDA = new double[2][2][2];
            break;

        case 4:  
        	fourDA = new double[2][2][2][2];
            break;

        default: 
        	System.out.print("Degree out of design: bigger than 4.\n");
        	System.exit(0);
            break;			
		}
		degree = d;
		
		if (variable.length != d) {
			System.out.print("Cannot construct factor, expect degree "+d+",but get "+variable.length);
        	System.exit(0);
		}
		
		vars = variable;		
	}
	
	public Factor(Factor another) {
		this.degree = another.degree;
		this.sum = another.sum;
		vars = new String[degree];
		for (int i = 0; i<this.degree; i++) {
			vars[i] = another.vars[i];
		}
		
		switch(degree){
		case 1:
			oneDA = new double[2];
			for (int i1 =0; i1<2; i1++){
				oneDA[i1] = another.oneDA[i1];
			}
			break;
		case 2:
			twoDA = new double[2][2];
			for (int i1 =0; i1<2; i1++){
				for (int i2 =0; i2<2; i2++){
					twoDA[i1][i2] = another.twoDA[i1][i2];
				}
			}
			break;
		case 3:
			threeDA = new double[2][2][2];
			for (int i1 =0; i1<2; i1++){
				for (int i2 =0; i2<2; i2++){
					for (int i3 =0; i3<2; i3++){
						threeDA[i1][i2][i3] = another.threeDA[i1][i2][i3];
					}
				}
			}
			break;
		case 4:
			fourDA = new double[2][2][2][2];
			for (int i1 =0; i1<2; i1++){
				for (int i2 =0; i2<2; i2++){
					for (int i3 =0; i3<2; i3++){
						for (int i4 =0; i4<2; i4++){
							fourDA[i1][i2][i3][i4] = another.fourDA[i1][i2][i3][i4];
						}
					}
				}
			}
			break;
		default:
			System.out.print("Degree out of design\n");
        	System.exit(0);			
		}
	}
	
	public void set(double[] vals) {
		int i = 0;
		double s = 0;
		switch(vals.length) {
		case 2:
			for (int i1 =0; i1<2; i1++){
				oneDA[i1]=vals[i];
				s+=vals[i];
				i++;
			}
			break;
		case 4:
			for (int i1 =0; i1<2; i1++){
				for (int i2 =0; i2<2; i2++){
					twoDA[i1][i2] = vals[i];
					s+=vals[i];
					i++;
				}
			}
			break;
		case 8:
			for (int i1 =0; i1<2; i1++){
				for (int i2 =0; i2<2; i2++){
					for (int i3 =0; i3<2; i3++){
						threeDA[i1][i2][i3] = vals[i];
						s+=vals[i];
						i++;
					}
				}
			}
			break;
		case 16:
			for (int i1 =0; i1<2; i1++){
				for (int i2 =0; i2<2; i2++){
					for (int i3 =0; i3<2; i3++){
						for (int i4 =0; i4<2; i4++){
							fourDA[i1][i2][i3][i4] = vals[i];
							s+=vals[i];
							i++;
						}
					}
				}
			}
			break;
		default:
			System.out.print("Cannot input values\n");
        	System.exit(0);			
		}
		sum = s;
	}
	
	public double get(int[] p){
		double res = 0;
		switch(p.length) {
		case 1:
			res = oneDA[p[0]];
			break;
		case 2:
			res = twoDA[p[0]][p[1]];
			break;
		case 3:
			res = threeDA[p[0]][p[1]][p[2]];
			break;
		case 4:
			res = fourDA[p[0]][p[1]][p[2]][p[3]];
			break;
		default:
			System.out.print("Cannot input values\n");
        	System.exit(0);	
		}
		return res;
	}
	
	public boolean contain(String tar) {
		for (int i =0; i<vars.length; i++) {
			if (vars[i].equals(tar)) return true;
		}
		return false;
	}
	
	public void print() {
		switch(degree){
		case 1:
			for (int i1 =0; i1<2; i1++){
				String m = ((i1==1)?"~":" ")+vars[0];
				System.out.print(m+":"+oneDA[i1]+"\n");
			}
			break;
		case 2:
			for (int i1 =0; i1<2; i1++){
				for (int i2 =0; i2<2; i2++){
					String m = ((i1==1)?"~":" ")+vars[0];
					m+= ((i2==1)?"~":" ")+vars[1];
					System.out.print(m+":"+twoDA[i1][i2]+"\n");
				}
			}
			break;
		case 3:
			for (int i1 =0; i1<2; i1++){
				for (int i2 =0; i2<2; i2++){
					for (int i3 =0; i3<2; i3++){
						String m = ((i1==1)?"~":" ")+vars[0];
						m+= ((i2==1)?"~":" ")+vars[1];
						m+= ((i3==1)?"~":" ")+vars[2];
						System.out.print(m+":"+threeDA[i1][i2][i3]+"\n");
					}
				}
			}
			break;
		case 4:
			for (int i1 =0; i1<2; i1++){
				for (int i2 =0; i2<2; i2++){
					for (int i3 =0; i3<2; i3++){
						for (int i4 =0; i4<2; i4++){
							String m = ((i1==1)?"~":" ")+vars[0];
							m+= ((i2==1)?"~":" ")+vars[1];
							m+= ((i3==1)?"~":" ")+vars[2];
							m+= ((i4==1)?"~":" ")+vars[3];
							System.out.print(m+":"+fourDA[i1][i2][i3][i4]+"\n");
						}
					}
				}
			}
			break;
		default:
			System.out.print("Degree out of design\n");
        	System.exit(0);			
		}
		System.out.print("\n");
	}
	
	public void norm() {
		switch(degree){
		case 1:
			for (int i1 =0; i1<2; i1++){
				oneDA[i1]/=sum;
			}
			break;
		case 2:
			for (int i1 =0; i1<2; i1++){
				for (int i2 =0; i2<2; i2++){
					twoDA[i1][i2]/=sum;
				}
			}
			break;
		case 3:
			for (int i1 =0; i1<2; i1++){
				for (int i2 =0; i2<2; i2++){
					for (int i3 =0; i3<2; i3++){
						threeDA[i1][i2][i3]/=sum;
					}
				}
			}
			break;
		case 4:
			for (int i1 =0; i1<2; i1++){
				for (int i2 =0; i2<2; i2++){
					for (int i3 =0; i3<2; i3++){
						for (int i4 =0; i4<2; i4++){
							fourDA[i1][i2][i3][i4]/=sum;
						}
					}
				}
			}
			break;
		default:
			System.out.print("Degree out of design\n");
        	System.exit(0);			
		}			
	}
	
	public Factor sum(String var) {
		if (degree == 1) {
			System.out.print("Cannot sum a Factor with only 1 variable\n");
        	System.exit(0);	
		}		
		int index=-1,j=0;
		String[] newVars = new String[degree-1];
		for(int i = 0; i < vars.length ; i++) {
			if (var.equals(vars[i]) ) index=i;
			else {
				newVars[j] = vars[i];
				j++;
			}
		}
		if (index == -1) {
			System.out.print("Cannot find given variable "+var +"\n");
        	System.exit(0);	
		}
		
		Factor res = new Factor(degree-1,newVars);
		int i = 0;
		double[] vals = null;
		switch (res.degree) {
		case 1:
			vals = new double[2];
			for (int i1 = 0; i1<2; i1++){
				double n = 0;
				switch(index){
				case 0:
					n = twoDA[0][i1]+twoDA[1][i1];					
					break;
				case 1:
					n = twoDA[i1][0]+twoDA[i1][1];
					break;
				}
				vals[i] = n; i++;
			}
			break;
			
		case 2:
			vals = new double[4];
			for (int i1 = 0; i1<2; i1++){
				for (int i2 = 0; i2<2; i2++){
					double n = 0;
					switch(index){
					case 0:
						n = threeDA[0][i1][i2]+threeDA[1][i1][i2];					
						break;
					case 1:
						n = threeDA[i1][0][i2]+threeDA[i1][1][i2];
						break;
					case 2:
						n = threeDA[i1][i2][0]+threeDA[i1][i2][1];
						break;
					}
					vals[i] = n; i++;
				}
			}
			break;
			
		case 3:
			vals = new double[8];
			for (int i1 = 0; i1<2; i1++){
				for (int i2 = 0; i2<2; i2++){
					for (int i3 = 0; i3<2; i3++){
						double n = 0;
						switch(index){
						case 0:
							n = fourDA[0][i1][i2][i3]+fourDA[1][i1][i2][i3];					
							break;
						case 1:
							n = fourDA[i1][0][i2][i3]+fourDA[i1][1][i2][i3];
							break;
						case 2:
							n = fourDA[i1][i2][0][i3]+fourDA[i1][i2][1][i3];
							break;
						case 3:
							n = fourDA[i1][i2][i3][0]+fourDA[i1][i2][i3][1];
							break;
						}
						vals[i] = n; i++;
					}
				}
			}
			break;			
		}		
		res.set(vals);
		return res;
	}
	
	
	public Factor restrict(String var,int tf) {
		if (degree == 1) {
			return null;
		}		
		if (tf !=0 && tf!=1){
			System.out.print("Value out of bound\n");
        	System.exit(0);	
		}
		int index=-1,j=0;
		String[] newVars = new String[degree-1];
		for(int i = 0; i < vars.length ; i++) {
			if (var.equals(vars[i])) index=i;
			else {
				newVars[j] = vars[i];
				j++;
			}
		}
		if (index == -1) {
			System.out.print("Cannot find given variable "+var +"\n");
        	System.exit(0);	
		}
		
		Factor res = new Factor(degree-1,newVars);
		int i = 0;
		double[] vals = null;
		switch (res.degree) {
		case 1:
			vals = new double[2];
			for (int i1 = 0; i1<2; i1++){
				double n = 0;
				switch(index){
				case 0:
					n = twoDA[tf][i1];					
					break;
				case 1:
					n = twoDA[i1][tf];
					break;
				}
				vals[i] = n; i++;
			}
			break;
			
		case 2:
			vals = new double[4];
			for (int i1 = 0; i1<2; i1++){
				for (int i2 = 0; i2<2; i2++){
					double n = 0;
					switch(index){
					case 0:
						n = threeDA[tf][i1][i2];					
						break;
					case 1:
						n = threeDA[i1][tf][i2];
						break;
					case 2:
						n = threeDA[i1][i2][tf];
						break;
					}
					vals[i] = n; i++;
				}
			}
			break;
			
		case 3:
			vals = new double[8];
			for (int i1 = 0; i1<2; i1++){
				for (int i2 = 0; i2<2; i2++){
					for (int i3 = 0; i3<2; i3++){
						double n = 0;
						switch(index){
						case 0:
							n = fourDA[tf][i1][i2][i3];					
							break;
						case 1:
							n = fourDA[i1][tf][i2][i3];
							break;
						case 2:
							n = fourDA[i1][i2][tf][i3];
							break;
						case 3:
							n = fourDA[i1][i2][i3][tf];
							break;
						}
						vals[i] = n; i++;
					}
				}
			}
			break;			
		}		
		res.set(vals);
		return res;
	}
	
	int[] tr(int[] tar, int[] far) {
		int [] res = new int[far.length];
		for(int i = 0; i < far.length; i++) {
			res[i] = tar[far[i]];
		}
		return res;
	}
	
	
	public Factor multiply(Factor f) {
		if (f == null) return this;
		int[] orgFor = new int[vars.length]; 
		int[] pinFor = new int[f.vars.length];
		
		String[] resVar = new String[vars.length];
		for (int i=0;i< vars.length;i++) {
			orgFor[i] = i;
			resVar[i] = vars[i];
		}
		for (int i = 0; i<f.vars.length ;i++) {
			boolean found = false;
			for (int j = 0; j<vars.length; j++) {
				if (f.vars[i].equals(vars[j])) {
					found = true;
				}
			}
			if (found == false) {
				String[] newArray = new String[resVar.length + 1];
			    System.arraycopy(resVar, 0, newArray, 0, resVar.length);
			    resVar = newArray;
				resVar[resVar.length-1] = f.vars[i];
			}
		}
		for(int i = 0; i<f.vars.length ;i++){
			for(int j = 0; j<resVar.length; j++) {
				if(f.vars[i].equals(resVar[j])) {
					pinFor[i] = j;
					//System.out.print("fvar "+f.vars[i]+" resVal "+ resVar[j] +" i "+i+" j "+j +"\n");
				}
			}
		}
		
		double[] resVals = null; int i=0;
		Factor res = new Factor(resVar.length, resVar);
		switch(res.degree) {
		case 1:
			resVals = new double[2];
			for(int i1=0; i1<2; i1++) {
				int[] tar = {i1};
				double d = this.get(tr(tar,orgFor)) * f.get(tr(tar,pinFor));
				resVals[i] = d;
				i++;
			}
			break;
		case 2:
			resVals = new double[4];
			for(int i1=0; i1<2; i1++) {
				for(int i2=0; i2<2; i2++){
					int[] tar = {i1,i2};
					double d = this.get(tr(tar,orgFor)) * f.get(tr(tar,pinFor));
					resVals[i] = d; i++;
				}
			}
			break;	
		case 3:
			resVals = new double[8];
			for(int i1=0; i1<2; i1++) {
				for(int i2=0; i2<2; i2++){
					for(int i3=0; i3<2; i3++){
						int[] tar = {i1,i2,i3};
						double d = this.get(tr(tar,orgFor)) * f.get(tr(tar,pinFor));
						resVals[i] = d; i++;
					}
				}
			}
			break;	
		case 4:
			resVals = new double[16];
			for(int i1=0; i1<2; i1++) {
				for(int i2=0; i2<2; i2++){
					for(int i3=0; i3<2; i3++){
						for(int i4=0; i4<2; i4++){
							int[] tar = {i1,i2,i3,i4};
							double d = this.get(tr(tar,orgFor)) * f.get(tr(tar,pinFor));
							resVals[i] = d; i++;
						}
					}
				}
			}
			break;				
		}
		res.set(resVals);
		return res;	
	}
}
