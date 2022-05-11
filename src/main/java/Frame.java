import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Frame extends JFrame implements ActionListener {


    public static List<Angle> angles = new ArrayList<>();
    public static List<Rect> rectangles = new ArrayList<>();

    JButton r_BUTTON;
    JButton a_BUTTON;
    JButton solve_BUTTON;
    JButton clear_BUTTON;
    JButton save_BUTTON;

    JTextField x_FIELD;
    JTextField y_FIELD;

    public Frame(String title) {
        super(title);
        initializationForButtonAndFields();
        addButtonsAndFields();
        initialization();
    }

    private void initializationForButtonAndFields() {
        save_BUTTON = new JButton("save to new File");
        save_BUTTON.setBounds(1000, 210, 300, 50);
        save_BUTTON.addActionListener(this);
        save_BUTTON.setBackground(Color.ORANGE);

        solve_BUTTON = new JButton("solve");
        solve_BUTTON.setBounds(1000, 310, 300, 50);
        solve_BUTTON.addActionListener(this);
        solve_BUTTON.setBackground(Color.CYAN);

        clear_BUTTON = new JButton("clear Desk");
        clear_BUTTON.setBounds(1000, 410, 300, 50);
        clear_BUTTON.addActionListener(this);
        clear_BUTTON.setBackground(Color.PINK);

        r_BUTTON = new JButton("add rectangle");
        r_BUTTON.setBounds(1000, 10, 300, 50);
        r_BUTTON.addActionListener(this);
        r_BUTTON.setBackground(Color.GREEN);

        x_FIELD = new JTextField();
        x_FIELD.setBounds(900, 10, 90, 50);

        a_BUTTON = new JButton("add angle");
        a_BUTTON.setBounds(1000, 110, 300, 50);
        a_BUTTON.addActionListener(this);
        a_BUTTON.setBackground(Color.YELLOW);

        y_FIELD = new JTextField();
        y_FIELD.setBounds(900, 100, 90, 50);
    }

    private void initialization() {
        setLayout(null);
        setSize(1400, 900);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void addButtonsAndFields() {
        add(x_FIELD);
        add(y_FIELD);
        add(solve_BUTTON);
        add(a_BUTTON);
        add(save_BUTTON);
        add(clear_BUTTON);
        add(r_BUTTON);
    }

    private static void drawAngle(Graphics g, Angle angle) {
        g.setColor(Color.cyan);
        g.drawLine(angle.v.x, angle.v.y, angle.v1.x, angle.v1.y);
        g.drawLine(angle.v.x, angle.v.y, angle.v2.x, angle.v2.y);
    }


    private static void drawRectangle(Graphics g, Rect rect) {
        g.setColor(Color.orange);
        g.drawLine(rect.a.x, rect.a.y, rect.b.x, rect.b.y);
        g.drawLine(rect.a.x, rect.a.y, rect.d.x, rect.d.y);
        g.drawLine(rect.b.x, rect.b.y, rect.c.x, rect.c.y);
        g.drawLine(rect.c.x, rect.c.y, rect.d.x, rect.d.y);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);



        for (Angle angle : angles) {
            drawAngle(g, angle);
        }

        for (Rect rectangle : rectangles) {
            drawRectangle(g, rectangle);
        }

        for (Rect rectangle : rectangles) {
            for (Angle angle : angles) {
                Shape shape = new Shape(rectangle, angle);
                shapes.add(shape);
                if (shape.getSquare() >= maxSquare) {
                    maxSquare = shape.getSquare();
                    mainShape = shape;
                }
            }
        }

        if (flag) {
            Graphics2D g1 = (Graphics2D) g;
            BasicStroke pen1 = new BasicStroke(5);
            g1.setStroke(pen1);
            g1.setColor(Color.magenta);

            for (Point allPoint : mainShape.result) {
                g.drawOval(allPoint.x - 3, allPoint.y - 3, 10, 10);
                g.fillOval(allPoint.x - 3, allPoint.y - 3, 10, 10);
            }

            for (int i = 1; i < mainShape.result.size(); i++) {
                Point p1 = mainShape.result.get(i - 1);
                Point p2 = mainShape.result.get(i);
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
            }

            flag = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
