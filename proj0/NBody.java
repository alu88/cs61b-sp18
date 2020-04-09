public class NBody {
	public static double readRadius(String file) {
		In in = new In(file);
		int numberPlanet = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Body[] readBodies(String file) {
		//List<Body> bodies = new ArrayList<Body>();
		In in = new In(file);
		int numberPlanet = in.readInt();
		double radius = in.readDouble();
		Body[] bodies = new Body[numberPlanet];


		for(int i = 0; i < numberPlanet; i++) {
			double xxP = in.readDouble();
			double yyP = in.readDouble();
			double xxV = in.readDouble();
			double yyV = in.readDouble();
			double mas = in.readDouble();
			String name = in.readString();
			//String planet = name.split(".")[0];
			// bodies.add(new Body(xxP, yyP, xxV, yyV, mas, planet));		
			bodies[i] = new Body(xxP, yyP, xxV, yyV, mas, name);
		}
		return bodies;
	}	

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Body[] planets = readBodies(filename);

		int N = planets.length;

		String imageToDraw = "images/starfield.jpg";

		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, imageToDraw);


		for (int i = 0; i < planets.length; i++) {
			planets[i].draw();
		}

		StdDraw.enableDoubleBuffering();

		for (double time = 0; time <= T; time = time + dt) {
			double xForce[] = new double[N];
			double yForce[] = new double[N];

			for (int j = 0; j < N; j++) {
				xForce[j] = planets[j].calcNetForceExertedByX(planets);
				yForce[j] = planets[j].calcNetForceExertedByY(planets);
			}

			for (int j = 0; j < N; j++) {
				planets[j].update(dt, xForce[j], yForce[j]);
			}

			StdDraw.setScale(-radius, radius);
			StdDraw.clear();
			StdDraw.picture(0, 0, imageToDraw);

			for (int i = 0; i < planets.length; i++) {
				planets[i].draw();
			}

			StdDraw.show();
			StdDraw.pause(10);
		}

		StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                  bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
		}
	}



}