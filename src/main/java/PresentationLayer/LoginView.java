package PresentationLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {

    private JLabel l = new JLabel("Log in");
    private JLabel l_user = new JLabel("Username: ");
    private JTextField tf_user = new JTextField(15);
    private JLabel l_pass = new JLabel("Password: ");
    private JPasswordField tf_pass = new JPasswordField(15);
    private JButton btnContinue = new JButton("Continue");
    private JButton btnRegister = new JButton("Register");
    private JButton btnClose = new JButton("Close");

    public LoginView() {

        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(4, 2));
        p1.add(l_user);
        p1.add(tf_user);
        p1.add(l_pass);
        p1.add(tf_pass);
        p1.add(Box.createRigidArea(new Dimension(20, 20)));
        p1.add(Box.createRigidArea(new Dimension(20, 20)));
        p1.add(btnContinue);
        p1.add(btnRegister);

        JPanel p2 = new JPanel();
        p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
        p2.add(Box.createRigidArea(new Dimension(0, 20)));
        p2.add(l);
        l.setFont(new Font("Sheriff", Font.BOLD, 19));
        l.setAlignmentX(CENTER_ALIGNMENT);
        p2.add(Box.createRigidArea(new Dimension(0, 15)));
        p2.add(Box.createRigidArea(new Dimension(0, 15)));
        p2.add(p1);
        p2.add(Box.createRigidArea(new Dimension(0, 15)));
        p2.add(btnClose);
        btnClose.setAlignmentX(CENTER_ALIGNMENT);
        p2.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.X_AXIS));
        content.add(Box.createRigidArea(new Dimension(15, 15)));
        content.add(p2);
        content.add(Box.createRigidArea(new Dimension(15, 15)));

        this.setContentPane(content);
        this.pack();
        this.setTitle("Food management System");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void addContinueListener(ActionListener al) {
        btnContinue.addActionListener(al);
    }

    public void addRegisterListener(ActionListener al) {
        btnRegister.addActionListener(al);
    }

    public void addCloseListener(ActionListener al) {
        btnClose.addActionListener(al);
    }

    public String getUsername() {
        return tf_user.getText();
    }

    public String getPassword() {
        return String.valueOf(tf_pass.getPassword());
    }

    void showMessage(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }


}
