package DataLayer;

import BusinessLayer.MenuItem;
import BusinessLayer.Order;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Clasa care contine metoda care scrie factura pentru comanda
 */
public class BillCompute {

    /**
     * Metoda care scrie factura pt comanda
     * @param order comanda
     * @param list lista de produse
     */
    public static void computeBill(Order order, List<MenuItem> list) {
        File file = new File("order_bill_" + order.getClient().getUsername() + "_" + order.getDate().getSecond() + ".txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(file.getAbsoluteFile());
        } catch (IOException e) {
        }
        BufferedWriter bw = new BufferedWriter(fw);
        try {
            bw.write("### Bill for order " + order.getClient().getUsername() + "_" + order.getDate().getSecond() + " ###\n\n");
            bw.write("# Client details:\n Name: " + order.getClient().getUsername() + "\n");
            bw.write("# Products:\n");
            for (MenuItem menuItem : list) {
                bw.write(menuItem.getTitle() + ", price: " + menuItem.computePrice() + "\n");
            }
            int total = computePrice(list);
            bw.write("\nTOTAL: " + total + "\n\n" +
                    "################################");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }



    /**
     * Efectueaza pretul total
     * @param list lista de produse
     * @return pretul total
     */
    public static int computePrice(List<MenuItem> list) {
        int price = 0;
        for (MenuItem menuItem : list) {
            price += menuItem.getPrice();
        }
        return price;
    }
}
