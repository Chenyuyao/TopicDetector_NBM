package NBM;

import java.util.Comparator;


public class WordComparator implements Comparator<SpecWord> {
	@Override
    public int compare(SpecWord n1,SpecWord n2) {
		if (n1.priority < n2.priority) return -1;
		else if (n1.priority > n2.priority) return 1;
		else return 0;
	}
}