package PresentationLayer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clasa unde se construieste frame-ul pt client
 */
public class ClientView extends JFrame{

    private JButton btnOrder = new JButton("Place Order");
    private JButton btnMenu = new JButton("Menu");
    private JButton btnFilter = new JButton("Filter");

    private JLabel l = new JLabel("Filters:");

    private JLabel l_key = new JLabel("Keyword: ");
    private JTextField tf_key = new JTextField(15);
    private JLabel l_min_rating = new JLabel("Min rating: ");
    private JTextField tf_min_rating = new JTextField(15);
    private JLabel l_max_calories = new JLabel("Max calories: ");
    private JTextField tf_max_calories = new JTextField(15);
    private JLabel l_min_protein = new JLabel("Min proteins: ");
    private JTextField tf_min_protein = new JTextField(15);
    private JLabel l_max_fat = new JLabel("Max fats: ");
    private JTextField tf_max_fat = new JTextField(15);
    private JLabel l_min_sodium = new JLabel("Min sodium: ");
    private JTextField tf_min_sodium = new JTextField(15);
    private JLabel l_max_price = new JLabel("Max price: ");
    private JTextField tf_max_price = new JTextField(15);

    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;

    /**
     * Constructorul unde adaug componentele
     */
    public ClientView(){

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
        p1.add(l_key);
        p1.add(tf_key);
        p1.add(l_min_rating);
        p1.add(tf_min_rating);
        p1.add(l_max_calories);
        p1.add(tf_max_calories);
        p1.add(l_min_protein);
        p1.add(tf_min_protein);
        p1.add(l_max_fat);
        p1.add(tf_max_fat);
        p1.add(l_min_sodium);
        p1.add(tf_min_sodium);
        p1.add(l_max_price);
        p1.add(tf_max_price);
        p1.add(Box.createRigidArea(new Dimension(10, 20)));
        p1.add(Box.createRigidArea(new Dimension(10, 20)));

        JPanel p2 = new JPanel();
        p2.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS));
        p2.add(btnMenu);
        p2.add(btnFilter);
        p2.add(btnOrder);

        JPanel p3 = new JPanel();
        p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));
        p3.add(Box.createRigidArea(new Dimension(10, 20)));
        p3.add(p1);
        p3.add(Box.createRigidArea(new Dimension(10, 20)));
        p3.add(p2);
        p3.add(Box.createRigidArea(new Dimension(10, 20)));
        p3.add(scrollPane);
        p3.add(Box.createRigidArea(new Dimension(10, 20)));

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.X_AXIS));
        content.add(Box.createRigidArea(new Dimension(10, 20)));
        content.add(p3);
        content.add(Box.createRigidArea(new Dimension(10, 20)));

        this.setContentPane(content);
        this.pack();
        this.setTitle("Client");
        this.setSize(500, 400);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    public void addMenuListener(ActionListener al){
        btnMenu.addActionListener(al);
    }
    public void addOrderListener(ActionListener al){
        btnOrder.addActionListener(al);
    }
    public void addFilterListener(ActionListener al){
        btnFilter.addActionListener(al);
    }

    /**
     * Metoda prin care se adauga un rand in tabel
     * @param title titlul
     * @param rating ratingul
     * @param calories nr de calorii
     * @param protein nr de proteine
     * @param fat nr de grasimi
     * @param sodium cantitate de sodiu
     * @param price pretul produsului
     */
    public void addRow(String title, double rating, int calories, int protein, int fat, int sodium, int price){
        model.addRow(new Object[]{title, rating, calories, protein, fat, sodium, price});
    }

    /**
     * se sterg toate inreg din tabel
     */
    public void deleteAll(){
        model.setRowCount(0);
    }

    public String getTitle() {
        return tf_key.getText();
    }

    public String getRating() {
        return tf_min_rating.getText();
    }

    public String getCalories() {
        return tf_max_calories.getText();
    }

    public String getProtein() {
        return tf_min_protein.getText();
    }

    public String getFat() {
        return tf_max_fat.getText();
    }

    public String getSodium() {
        return tf_min_sodium.getText();
    }

    public String getPrice() {
        return tf_max_price.getText();
    }

    public JTable getTable(){
        return this.table;
    }

    void showMessage(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }
}
