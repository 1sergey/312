import java.util.ArrayList;
import java.util.List;

public class Shape {
    public Rect rectangle;
    public Angle angle;
    List<Point> rp = new ArrayList<>();
    public Shape(Rect r, Angle a) {
        this.rectangle = r;
        this.angle = a;
        getShape();
    }

}
