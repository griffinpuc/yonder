package griffin.github.wayward;

public class Waypoint {

    String label;
    double pointX;
    double pointY;
    double pointZ;

    public Waypoint(String label, double pointX, double pointY, double pointZ){

        label = label;
        pointX = pointX;
        pointY = pointY;
        pointZ = pointZ;

    }

    public void setLabel(String label){
        label = label;
    }

}
