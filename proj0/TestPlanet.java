public class TestPlanet {
    public static void main(String[] args) {
        Planet Samh = new Planet(1, 0, 0, 0, 10, "no");
        Planet Aegir = new Planet(3, 3, 0, 0, 5, "no");
        System.out.println(Samh.calcForceExertedBy(Aegir));
    }
}