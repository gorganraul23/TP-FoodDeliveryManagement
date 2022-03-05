package DataLayer;

import BusinessLayer.MenuItem;
import BusinessLayer.Order;
import BusinessLayer.User;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Clasa unde se efectueaza rapoartele administratorului
 */
public class RaportCompute {

    /**
     * Metoda care face primul raport
     * @param list lista de comenzi
     * @param min ora de start
     * @param max ora de final
     */
    public static void computeRaport1(List<Order> list, int min, int max) {
        File file = new File("Rap1_" + LocalDateTime.now().getSecond() + ".txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(file.getAbsoluteFile());
        } catch (IOException e) {
        }
        BufferedWriter bw = new BufferedWriter(fw);
        try {
            bw.write("### Raport 1:  Orders between " + min + " and " + max + " ###\n\n");
            bw.write("Orders: \n\n");
            if(list.isEmpty())
                bw.write("No orders.\n");
            else {
                for (Order order : list) {
                    bw.write("Client: " + order.getClient().getUsername() + "\n" + "date: " + order.getDate() + "\n\n");
                }
            }
            bw.write("################################");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void computeRaport2(List<String> list, int nr) {
        File file = new File("Rap2_" + LocalDateTime.now().getSecond() + ".txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(file.getAbsoluteFile());
        } catch (IOException e) {
        }
        BufferedWriter bw = new BufferedWriter(fw);
        try {
            bw.write("### Raport 2:  Products ordered more than " + nr + " times " + "###\n\n");
            bw.write("Products: \n\n");
            if(list.isEmpty())
                bw.write("No products.\n");
            else {
                for (String s: list) {
                    bw.write("Product: " + s + "\n");
                }
            }
            bw.write("\n################################");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void computeRaport3(List<User> list, int times, int value) {
        File file = new File("Rap3_" + LocalDateTime.now().getSecond() + ".txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(file.getAbsoluteFile());
        } catch (IOException e) {
        }
        BufferedWriter bw = new BufferedWriter(fw);
        try {
            bw.write("### Raport 3:  Clients that ordered more than " + times + " times" +
                    "with a value > " + value + " ###\n\n");
            bw.write("Clients: \n\n");
            if(list.isEmpty())
                bw.write("No clients.\n");
            else {
                for (User user: list) {
                    bw.write("Username: " + user.getUsername() + "\n");
                }
            }
            bw.write("\n################################");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void computeRaport4(List<MenuItem> list, int day) {
        File file = new File("Rap4_" + LocalDateTime.now().getSecond() + ".txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(file.getAbsoluteFile());
        } catch (IOException e) {
        }
        BufferedWriter bw = new BufferedWriter(fw);
        try {
            bw.write("### Raport 4:  Products ordered in day " + day + " ###\n\n");
            bw.write("Products: \n\n");
            if(list.isEmpty())
                bw.write("No products.\n");
            else {
                for (MenuItem item: list) {
                    bw.write("Product: " + item.getTitle() + "\n");
                }
            }
            bw.write("\n################################");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
