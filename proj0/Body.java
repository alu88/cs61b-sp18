public class Body {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	final static double gInstance = 6.67e-11;
	public String imgFileName;

	public Body(double xP, double yP, double xV,double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Body(Body b) {
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Body c) {
		double rSquare = (this.xxPos - c.xxPos)*(this.xxPos - c.xxPos) + (this.yyPos - c.yyPos)*(this.yyPos - c.yyPos);
		return Math.sqrt(rSquare);
	}

	public double calcForceExertedBy(Body d) {
		double distance = this.calcDistance(d);
		return gInstance * this.mass * d.mass / (distance * distance);
	}

	public double calcForceExertedByX(Body a) {
		double distance = this.calcDistance(a);
		double force = this.calcForceExertedBy(a);
		return force * (a.xxPos - this.xxPos)/distance;
	}

	public double calcForceExertedByY(Body a) {
		double distance = this.calcDistance(a);
		double force = this.calcForceExertedBy(a);
		return force * (a.yyPos - this.yyPos)/distance;
	}

	public double calcNetForceExertedByX(Body[] a) {
		double netForceX = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i].equals(this)) {
				continue;
			}
			netForceX = netForceX + this.calcForceExertedByX(a[i]);
		}
		return netForceX;
	}

	public double calcNetForceExertedByY(Body[] a) {
		double netForceY = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i].equals(this)) {
				continue;
			}
			netForceY = netForceY + this.calcForceExertedByY(a[i]);
		}
		return netForceY;
	}

	public void update(double dt, double fX, double fY) {
		double aX = fX/this.mass;
		double aY = fY/this.mass;

		xxVel = xxVel + dt * aX;
		yyVel = yyVel + dt * aY;

		xxPos = xxPos + dt * xxVel;
		yyPos = yyPos + dt * yyVel;
		return;
	}

	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}
}