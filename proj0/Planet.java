public class Planet{
	public double xxPos, yyPos, xxVel, yyVel, mass;
	public String imgFileName;
	public static final double G = 6.67e-11;
	public static double Relative_X_Position(Planet a, Planet b){
		double rp_x;
		rp_x = b.xxPos - a.xxPos;
		return rp_x;
	}

	public static double Relative_Y_Position(Planet a, Planet b){
		double rp_y;
		rp_y = b.yyPos - a.yyPos;
		return rp_y;
	}

	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p){
		double distance, distance_x, distance_y;
		distance_x = p.xxPos - xxPos;
		distance_y = p.yyPos - yyPos;
		distance = Math.sqrt(distance_x * distance_x + distance_y * distance_y);
		return distance;
	}

	public double calcForceExertedBy(Planet p){
		double force;
		force = G * mass * p.mass / (calcDistance(p) * calcDistance(p));
		return force; 
	}

	public double calcForceExertedByX(Planet p){
		double force_x;
		force_x = calcForceExertedBy(p) * Relative_X_Position(this, p) / calcDistance(p);
		return force_x;
	}

	public double calcForceExertedByY(Planet p){
		double force_y;
		force_y = calcForceExertedBy(p) * Relative_Y_Position(this, p) / calcDistance(p);
		return force_y;
	}

	public double calcNetForceExertedByX(Planet[] p_array){
		int i;
		double netforce_x = 0;
		for(i=0;i<p_array.length;i++){
			if (p_array[i].equals(this)){
				continue;
			}else{
				netforce_x += calcForceExertedByX(p_array[i]);
			}
		}
		return netforce_x;
	}

	public double calcNetForceExertedByY(Planet[] p_array){
		int i;
		double netforce_y = 0;
		for(i=0;i<p_array.length;i++){
			if (p_array[i].equals(this)){
				continue;
			}else{
				netforce_y += calcForceExertedByY(p_array[i]);
			}
		}
		return netforce_y;
	}

	public void update(double t, double fx, double fy){
		double a_x, a_y;
		a_x = fx / mass;
		a_y = fy / mass;
		xxVel += a_x * t;
		yyVel += a_y * t;
		xxPos += xxVel * t;
		yyPos += yyVel * t;
	}

	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}
}