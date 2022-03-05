package BusinessLayer;

import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class DeliveryService extends Observable implements IDeliveryServiceProcessing {

    private Map<Order, List<MenuItem>> orders;
    private List<MenuItem> products;
    private List<User> users;
    private String connectedNow;

    public DeliveryService() {
        orders = new HashMap<>();
        products = new ArrayList<>();
        users = new ArrayList<>();
        addSomeUsers();
        connectedNow = "";

    }

    public String validLogin(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                if(user.getRole().equals("Client"))
                    connectedNow = username;
                return user.getRole();
            }
        }
        return "";
    }

    public boolean validUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username))
                return false;
        }
        return true;
    }

    public void addSomeUsers() {
        users.add(new User("beer", "123", "Client", 0));
        users.add(new User("boss", "boss", "Administrator", 0));
        users.add(new User("ehe", "boby", "Employee", 0));
    }

    /**
     * Importa meniul
     */
    @Override
    public void importMenu() {

        assert products.size() == 0 : "Assert import (pre)";

        Path pathToFile = Paths.get("C:\\My_Designs\\Intellij\\PT2021_30227_Gorgan_Raul_Assignment_4\\products.csv");
        Set<String> stringProducts = new HashSet<>();
        try {
            stringProducts = Files.lines(pathToFile)
                    .skip(1)
                    .collect(Collectors.toSet());
        } catch (Exception ex) {
            System.out.println("error");
        }

        for (String line : stringProducts) {
            String[] fields = line.split(",");
            BaseProduct product = new BaseProduct();
            product.setTitle(fields[0]);
            product.setRating(Double.parseDouble(fields[1]));
            product.setCalories(Integer.parseInt(fields[2]));
            product.setProtein(Integer.parseInt(fields[3]));
            product.setFat(Integer.parseInt(fields[4]));
            product.setSodium(Integer.parseInt(fields[5]));
            product.setPrice(Integer.parseInt(fields[6]));
            products.add(product);
        }

        assert products.size() != 0 : "Assert import (post)";
        assert wellFormed() : "Assert well formed";
    }

    /**
     * Adauga o linie
     */
    @Override
    public void addProduct(String title, double rating, int calories, int protein, int fat, int sodium, int price) {

        assert !title.equals("") && rating > 0 && rating < 5 && price > 0 : "Assert add (pre)";
        int preCount = products.size();

        products.add(new MenuItem(title, rating, calories, protein, fat, sodium, price));

        int postCount = products.size();
        assert postCount == preCount + 1 : "Assert add (post)";
        assert wellFormed() : "Assert well formed";
    }

    /**
     * Modifica o linie
     */
    @Override
    public void modifyProduct(MenuItem item) {
        assert item != null : "Assert modify (pre)";
        for(MenuItem menuItem : products){
            if(menuItem.getTitle().equals(item.getTitle())){
                menuItem.setRating(item.getRating());
                menuItem.setCalories(item.getCalories());
                menuItem.setProtein(item.getProtein());
                menuItem.setFat(item.getFat());
                menuItem.setSodium(item.getSodium());
                menuItem.setPrice(item.getPrice());
            }
        }
        assert wellFormed() : "Assert well formed";
    }

    /**
     * Sterge o linie
     */
    @Override
    public void deleteProduct(int index) {
        assert index >= 0 : "Assert delete (pre)";
        int preCount = products.size();

        products.remove(index);

        int postCount = products.size();
        assert postCount == preCount + 1 : "Assert delete (post)";
        assert wellFormed() : "Assert well formed";
    }

    public boolean wellFormed(){    //nu exista comenzi fara produse
        for(User user : users){
            if(user.getRole().equals("Administrator"))
                return true;
        }
        return false;
    }

    @Override
    public List<MenuItem> filter(String keyword, double minRating, int maxCalories, int minProtein, int maxFat, int minSodium, int maxPrice) {
        assert keyword!=null || minRating!=-1 || maxCalories!=10000 || minProtein!=-1 || maxFat!=10000 || minSodium!=-1 || maxPrice!=10000 : "Assert filter (pre)";

        List<MenuItem> newList;
        newList = products.stream()
                .filter(c -> c.getTitle().contains(keyword)).collect(Collectors.toList());
        newList = newList.stream().filter(item -> item.getRating() >= minRating &&
                                          item.getCalories() <= maxCalories &&
                                          item.getProtein() >= minProtein &&
                                          item.getFat() <= maxFat &&
                                          item.getSodium() >= minSodium &&
                                          item.getPrice() <= maxPrice)
                .collect(Collectors.toList());

        assert wellFormed() : "Assert well formed";
        assert newList != null : "Asert filter (post)";
        return newList;
    }

    /**
     * Adauga o comanda
     */
    @Override
    public void addOrder(Order order, List<MenuItem> list) {
        assert order != null : "Assert addOrder (pre)";

        orders.put(order, list);

        assert wellFormed() : "Assert well formed";
        assert orders.containsKey(order) : "Assert addOrder (post)";
    }

    @Override
    public List<Order> generateRaport1(int min, int max) {
        assert min < max : "Assert raport1 (pre)";

        List<Order> orderList = new ArrayList<>();
        for (Map.Entry<Order, List<MenuItem>> entry : orders.entrySet()) {
            orderList.add(entry.getKey());
        }
        orderList = orderList.stream()
                .filter(order -> order.getDate().getHour() >= min && order.getDate().getHour() <= max)
                .collect(Collectors.toList());

        assert orderList != null : "Assert raport1 (post)";
        assert wellFormed() : "Assert well formed";
        return orderList;
    }

    @Override
    public List<String> generateRaport2(int numar) {
        assert numar > 0 : "Assert raport2 (pre)";

        List<MenuItem> list = new ArrayList<>();
        for (Map.Entry<Order, List<MenuItem>> entry : orders.entrySet()) {
            list.addAll(entry.getValue());
        }
        List<String> string_list = new ArrayList<>();
        for(MenuItem item : list){
            string_list.add(item.getTitle());
        }
        Map<String, Integer> freq = new HashMap<>();
        for(String item : string_list){
            if(freq.get(item) == null){
                freq.put(item, 1);
            }
            else {
                int nr = freq.get(item) + 1;
                freq.put(item, nr);
            }
        }

        List<String> newList;
        newList = freq.entrySet().stream().filter(e -> e.getValue() > numar).map(Map.Entry::getKey)
                .collect(Collectors.toList());

        assert newList != null : "Assert raport2 (post)";
        assert wellFormed() : "Assert well formed";
        return newList;
    }


    @Override
    public List<User> generateRaport3(int times, int value) {
        assert times >0 : "Assert raport3";
        assert value >0 : "Assert raport3";
        List<User> list;
        list = orders.keySet().stream()
                .map(Order::getClient)
                .filter(client -> client.getOrdersPerformed() > times)
                .collect(Collectors.toList());

        assert list != null;
        return list;
    }

    @Override
    public List<MenuItem> generateRaport4(int day) {
        assert day > 0 && day <= 365 : "Assert raport4 (pre)";
        List<MenuItem> list = new ArrayList<>();
        /*list = orders.keySet().stream()
                .map(Order::getDate)
                .filter(client -> client.getDayOfYear() == day)
                .collect(Collectors.toList());*/
        assert list != null : "Assert raport4 (post)";
        return list;
    }

    public List<User> getUsers() {
        return this.users;
    }
    public List<MenuItem> getMenuItems() {
        return this.products;
    }
    public Map<Order, List<MenuItem>> getOrders(){
        return this.orders;
    }
    public void setUsers(List<User> list){
        this.users = list;
    }
    public void setProducts(List<MenuItem> list){
        this.products = list;
    }
    public void setOrders(Map<Order, List<MenuItem>> map){
        this.orders = map;
    }

    public String getConnectedNow() {
        return connectedNow;
    }

    public User userConnected(){
        for(User user : users){
            if(connectedNow.equals(user.getUsername()))
                return user;
        }
        return null;
    }
}
