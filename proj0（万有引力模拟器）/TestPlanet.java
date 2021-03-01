public class TestPlanet{
	/** p1: x = 5, y = 10, xv = 4, yv = 10, mass = 10,name...
	    p2: x = 6, y = 10, xv = 4, yv = 10, mass = 100, name... */
	public static void main(String[] args){
		Planet p1, p2;
		Planet[] ls;
		double result;
		p1 = new Planet(5, 10, 4, 10, 10, "p1");
	    p2 = new Planet(6, 10, 4, 10, 10, "p2");
	    ls = new Planet[2];
	    ls[0] = p1;
	    ls[1] = p2;
	    result = p1.calcNetForceExertedByX(ls);
	    System.out.print(result);
	}
	
}