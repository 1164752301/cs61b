public class NBody{
	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dT = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] p_array = readPlanets(filename);
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "images/starfield.jpg");
		int i;
		for(i=0;i<p_array.length;i++){
			p_array[i].draw();
		}
		StdDraw.enableDoubleBuffering();
		int time;
		for(time=0;time<T;time+=dT){
			double[] xForces, yForces;
			xForces = new double[p_array.length];
			yForces = new double[p_array.length];
			for(i=0;i<p_array.length;i++){
				xForces[i] = p_array[i].calcNetForceExertedByX(p_array);
				yForces[i] = p_array[i].calcNetForceExertedByY(p_array);
			}
			for(i=0;i<p_array.length;i++){
				p_array[i].update(dT, xForces[i], yForces[i]);
			}
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for(i=0;i<p_array.length;i++){
			p_array[i].draw();
		    }
		    StdDraw.show();
		    StdDraw.pause(10);
		}
		StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                          planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
	}

	public static double readRadius(String file){
		In in = new In(file);
		int first = in.readInt();
		double second = in.readDouble();
		return second;
	}

	public static Planet[] readPlanets(String file){
		int i;
		In in = new In(file);
		int n = in.readInt();
		Planet[] p_array = new Planet[n];
		double useless_position = in.readDouble();
		for(i=0;i<n;i++){
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			p_array[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
		}
		return p_array;
	}
}