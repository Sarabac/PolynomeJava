import poly.Poly;
public class hello {

	public static void main(String[] args) {
		double[] essai = {1,1,1};
		Poly test = new Poly(essai);
		System.out.println(test.derivee().derivee().integrale(3).integrale(0));
	}

}
