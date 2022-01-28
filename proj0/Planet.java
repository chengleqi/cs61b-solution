public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static final double G = 6.67e-11;

	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p) {
		return Math.sqrt((xxPos - p.xxPos)*(xxPos - p.xxPos) + (yyPos - p.yyPos)*(yyPos - p.yyPos));
	}

	public double calcForceExertedBy(Planet p) {
		if (!equals(p)) {
			return (G * mass * p.mass) / (calcDistance(p) * calcDistance(p));
		}
		return 0;
	}

	public double calcForceExertedByX(Planet p) {
		if (!equals(p)) {
			return calcForceExertedBy(p) * (p.xxPos - xxPos) / calcDistance(p);
		}
		return 0;
	}

	public double calcForceExertedByY(Planet p) {
		if (!equals(p)) {
			return calcForceExertedBy(p) * (p.yyPos - yyPos) / calcDistance(p);
		}
		return 0;
	}

	public boolean equals(Planet p) {
		return xxPos == p.xxPos && yyPos == p.yyPos && xxVel == p.xxVel && yyVel == p.yyVel && imgFileName == p.imgFileName;
	}

	public double calcNetForceExertedByX(Planet[] ps) {
		double NetForceX = 0;
		for (Planet p : ps) {
			NetForceX += calcForceExertedByX(p);
		}
		return NetForceX;
	}

	public double calcNetForceExertedByY(Planet[] ps) {
		double NetForceY = 0;
		for (Planet p : ps) {
			NetForceY += calcForceExertedByY(p);
		}
		return NetForceY;
	}

	public void update(double dt, double fX, double fY) {
		double aX = fX / mass;
		double aY = fY / mass;
		xxVel += dt * aX;
		yyVel += dt * aY;
		xxPos += dt * xxVel;
		yyPos += dt * yyVel;
	}

	public void draw() {
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}
}