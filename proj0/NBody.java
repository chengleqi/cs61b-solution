public class NBody {
    public static String backgroundPic = "images/starfield.jpg";
    
    public static double readRadius(String filePath) {
        In in = new In(filePath);
        int N = in.readInt();
		double Radius = in.readDouble();
        return Radius;
    }

    public static Planet[] readPlanets(String filePath) {
        In in = new In(filePath);
        int N = in.readInt();
        double Radius = in.readDouble();
        Planet[] planets = new Planet[N];
        for (int i = 0; i < N; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }
        return planets;
    }

    public static void drawBackground(double scale) {
		StdDraw.setScale(-scale, scale);
        StdDraw.picture(0, 0, backgroundPic);
	}

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        
        double[] xForces = new double[planets.length];
        double[] yForces = new double[planets.length];
        StdDraw.enableDoubleBuffering();
        for (double time = 0; time < T; time += dt) {
            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            drawBackground(radius);
            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
                planets[i].draw();
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
}