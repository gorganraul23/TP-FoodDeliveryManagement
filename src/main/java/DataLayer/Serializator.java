package DataLayer;

import BusinessLayer.DeliveryService;
import BusinessLayer.MenuItem;
import BusinessLayer.Order;
import BusinessLayer.User;

import java.io.*;
import java.util.List;
import java.util.Map;


public class Serializator {

    public void serialization(DeliveryService deliveryService) {
        try {
            FileOutputStream products = new FileOutputStream("products.txt");
            FileOutputStream orders = new FileOutputStream("orders.txt");
            FileOutputStream users = new FileOutputStream("users.txt");

            ObjectOutputStream productsOut = new ObjectOutputStream(products);
            ObjectOutputStream ordersOut = new ObjectOutputStream(orders);
            ObjectOutputStream usersOut = new ObjectOutputStream(users);

            productsOut.writeObject(deliveryService.getMenuItems());
            ordersOut.writeObject(deliveryService.getOrders());
            usersOut.writeObject(deliveryService.getUsers());

            products.close();
            orders.close();
            users.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void deserialization(DeliveryService deliveryService) {
        try {
            FileInputStream products = new FileInputStream("products.txt");
            FileInputStream orders = new FileInputStream("orders.txt");
            FileInputStream users = new FileInputStream("users.txt");

            ObjectInputStream productsIn = new ObjectInputStream(products);
            ObjectInputStream ordersIn = new ObjectInputStream(orders);
            ObjectInputStream usersIn = new ObjectInputStream(users);

            deliveryService.setProducts((List<MenuItem>) productsIn.readObject());
            deliveryService.setOrders((Map<Order, List<MenuItem>>) ordersIn.readObject());
            deliveryService.setUsers((List<User>) usersIn.readObject());

            productsIn.close();
            ordersIn.close();
            usersIn.close();
            products.close();
            orders.close();
            users.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
