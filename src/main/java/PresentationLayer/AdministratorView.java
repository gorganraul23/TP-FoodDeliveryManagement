package PresentationLayer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Frame pentru administrator
 */
public class AdministratorView extends JFrame {

    private JButton btnImport = new JButton("Import menu");
    private JButton btnLoad = new JButton("Load");
    private JButton btnAdd = new JButton("Add");
    private JButton btnModify = new JButton("Modify");
    private JButton btnDelete = new JButton("Delete");

    private JLabel l_title = new JLabel("Title: ");
    private JTextField tf_title = new JTextField(15);
    private JLabel l_rating = new JLabel("Rating: ");
    private JTextField tf_rating = new JTextField(15);
    private JLabel l_calories = new JLabel("Calories: ");
    private JTextField tf_calories = new JTextField(15);
    private JLabel l_protein = new JLabel("Protein: ");
    private JTextField tf_protein = new JTextField(15);
    private JLabel l_fat = new JLabel("Fat: ");
    private JTextField tf_fat = new JTextField(15);
    private JLabel l_sodium = new JLabel("Sodium: ");
    private JTextField tf_sodium = new JTextField(15);
    private JLabel l_price = new JLabel("Price: ");
    private JTextField tf_price = new JTextField(15);

    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;

    private JLabel l_min_h = new JLabel("Min hour: ");
    private JTextField tf_min_h = new JTextField(15);
    private JLabel l_max_h = new JLabel("Max hour: ");
    private JTextField tf_max_h = new JTextField(15);
    private JLabel l_min_amount = new JLabel("Min amount: ");
    private JTextField tf_min_amount = new JTextField(15);
    private JLabel l_min_times = new JLabel("Min times: ");
    private JTextField tf_min_times = new JTextField(15);
    private JLabel l_min_price = new JLabel("Min price: ");
    private JTextField tf_min_price = new JTextField(15);
    private JLabel l_day = new JLabel("Day: ");
    private JTextField tf_day = new JTextField(15);
    private JButton btnRap1 = new JButton("Generate");
    private JButton btnRap2 = new JButton("Generate");
    private JButton btnRap3 = new JButton("Generate");
    private JButton btnRap4 = new JButton("Generate");

    /**
     * Constructorul unde pun componentele pentru frame-ul administratorului
     */
    public AdministratorView() {

        table = new JTable();
        model = new DefaultTableModel();
        model.addColumn("Title");
        model.addColumn("Rating");
        model.addColumn("Calories");
        model.addColumn("Protein");
        model.addColumn("Fat");
        model.addColumn("Sodium");
        model.addColumn("Price");

        table = new JTable(model);
        scrollPane = new JScrollPane(table);

        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(4, 4));
        p1.add(l_title);
        p1.add(tf_title);
        p1.add(l_rating);
        p1.add(tf_rating);
        p1.add(l_calories);
        p1.add(tf_calories);
        p1.add(l_protein);
        p1.add(tf_protein);
        p1.add(l_fat);
        p1.add(tf_fat);
        p1.add(l_sodium);
        p1.add(tf_sodium);
        p1.add(l_price);
        p1.add(tf_price);

        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(1, 3));
        p2.add(btnAdd);
        p2.add(btnModify);
        p2.add(btnDelete);
        p2.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel p3 = new JPanel();
        p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));
        p3.add(btnImport);
        btnImport.setAlignmentX(LEFT_ALIGNMENT);
        p3.add(btnLoad);
        p3.add(Box.createRigidArea(new Dimension(0, 20)));
        p3.add(scrollPane);

        JPanel p5 = new JPanel();
        p5.setLayout(new BoxLayout(p5, BoxLayout.X_AXIS));
        p5.add(l_min_h);
        p5.add(tf_min_h);
        p5.add(l_max_h);
        p5.add(tf_max_h);
        p5.add(btnRap1);

        JPanel p6 = new JPanel();
        p6.setLayout(new BoxLayout(p6, BoxLayout.X_AXIS));
        p6.add(l_min_amount);
        p6.add(tf_min_amount);
        p6.add(btnRap2);

        JPanel p7 = new JPanel();
        p7.setLayout(new BoxLayout(p7, BoxLayout.X_AXIS));
        p7.add(l_min_times);
        p7.add(tf_min_times);
        p7.add(l_min_price);
        p7.add(tf_min_price);
        p7.add(btnRap3);

        JPanel p8 = new JPanel();
        p8.setLayout(new BoxLayout(p8, BoxLayout.X_AXIS));
        p8.add(l_day);
        p8.add(tf_day);
        p8.add(btnRap4);

        JPanel p4 = new JPanel();
        p4.setLayout(new BoxLayout(p4, BoxLayout.Y_AXIS));
        p4.add(Box.createRigidArea(new Dimension(0, 20)));
        p4.add(p1);
        p4.add(Box.createRigidArea(new Dimension(0, 20)));
        p4.add(p2);
        p4.add(Box.createRigidArea(new Dimension(0, 20)));
        p4.add(p3);
        p4.add(Box.createRigidArea(new Dimension(0, 20)));
        p4.add(p5);
        p4.add(Box.createRigidArea(new Dimension(0, 20)));
        p4.add(p6);
        p4.add(Box.createRigidArea(new Dimension(0, 20)));
        p4.add(p7);
        p4.add(Box.createRigidArea(new Dimension(0, 20)));
        p4.add(p8);
        p4.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.X_AXIS));
        content.add(Box.createRigidArea(new Dimension(20, 20)));
        content.add(p4);
        content.add(Box.createRigidArea(new Dimension(20, 20)));

        this.setContentPane(content);
        this.pack();
        this.setTitle("Administrator");
        this.setSize(500, 700);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    public void addImportListener(ActionListener al) {
        btnImport.addActionListener(al);
    }

    public void addLoadListener(ActionListener al) {
        btnLoad.addActionListener(al);
    }

    public void addAddListener(ActionListener al) {
        btnAdd.addActionListener(al);
    }

    public void addModifyListener(ActionListener al) {
        btnModify.addActionListener(al);
    }

    public void addDeleteListener(ActionListener al) {
        btnDelete.addActionListener(al);
    }

    public void addRap1Listener(ActionListener al) {
        btnRap1.addActionListener(al);
    }

    public void addRap2Listener(ActionListener al) {
        btnRap2.addActionListener(al);
    }

    public void addRap3Listener(ActionListener al) {
        btnRap3.addActionListener(al);
    }

    public void addRap4Listener(ActionListener al) {
        btnRap4.addActionListener(al);
    }

    /**
     * Metoda adauga un rand in tabel
     */
    public void addRow(String title, double rating, int calories, int protein, int fat, int sodium, int price) {
        model.addRow(new Object[]{title, rating, calories, protein, fat, sodium, price});
    }

    /**
     * Modifica o linie in tabel
     */
    public void modifyRow(int row, String title, double rating, int calories, int protein, int fat, int sodium, int price) {
        model.removeRow(row);
        model.insertRow(row, new Object[]{title, rating, calories, protein, fat, sodium, price});
    }

    /**
     * Metoda prin care sterg un rand in tabel
     *
     * @param index pozitia de unde se sterge
     */
    public void deleteRow(int index) {
        model.removeRow(index);
    }

    public JTable getTable() {
        return this.table;
    }

    public String getTitle() {
        return tf_title.getText();
    }

    public String getRating() {
        return tf_rating.getText();
    }

    public String getCalories() {
        return tf_calories.getText();
    }

    public String getProtein() {
        return tf_protein.getText();
    }

    public String getFat() {
        return tf_fat.getText();
    }

    public String getSodium() {
        return tf_sodium.getText();
    }

    public String getPrice() {
        return tf_price.getText();
    }

    public String getMinHour() {
        return tf_min_h.getText();
    }

    public String getMaxHour() {
        return tf_max_h.getText();
    }

    public String getMinAmount() {
        return tf_min_amount.getText();
    }

    public String getMinTimes() {
        return tf_min_times.getText();
    }

    public String getMinPrice() {
        return tf_min_price.getText();
    }

    public String getDay() {
        return tf_day.getText();
    }

    void showMessage(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }
}
