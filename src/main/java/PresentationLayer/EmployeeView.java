package PresentationLayer;

import ObserverPattern.Observer;
import ObserverPattern.Subject;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class EmployeeView extends Observer {

    public JFrame frame;
    private JLabel label = new JLabel("Orders:");
    private JLabel l_orders = new JLabel("");

    public EmployeeView(Subject subject) {

        this.subject = subject;
        this.subject.attach(this);

        frame = new JFrame("Employee");

        JPanel p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
        p1.add(label);
        label.setFont(new Font("Sheriff", Font.PLAIN, 18));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        p1.add(Box.createRigidArea(new Dimension(10, 20)));
        p1.add(l_orders);
        l_orders.setAlignmentX(Component.LEFT_ALIGNMENT);

        frame.setContentPane(p1);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        frame.setVisible(true);
    }

    @Override
    public void update() {
        l_orders.setText(l_orders.getText() + "<HTML> New order! date: " + LocalDateTime.now() + "<BR/><HTML>");
    }

    public JLabel getLabel() {
        return this.label;
    }
}
