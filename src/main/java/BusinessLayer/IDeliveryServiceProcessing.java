package BusinessLayer;

import java.util.List;

public interface IDeliveryServiceProcessing {

    /**
     * @pre products.size = 0
     * @post products.size != 0
     * @inv wellformed
     */
    void importMenu();

    /**
     * Adauga o linie
     * @pre title != NULL, rating > 0, rating < 5, price > 0
     * @post products.size = products.size(@pre) + 1
     * @inv wellformed
     */
    void addProduct(String title, double rating, int calories, int protein, int fat, int sodium, int price);

    /**
     * Modifica o linie
     * @pre item != null
     * @post
     * @inv wellformed
     */
    void modifyProduct(MenuItem item);

    /**
     * Sterge o linie
     * @pre index >= 0
     * @post products.size = products.size(@pre) - 1
     * @inv wellformed
     */
    void deleteProduct(int index);

    /**
     * Filtreaza liniile tabelului
     * @pre keyword!=null || minRating!=-1 || maxCalories!=10000 || minProtein!=-1 || maxFat!=10000 || minSodium!=-1 || maxPrice!=10000
     * @post list != null
     * @inv wellformed
     */
    List<MenuItem> filter(String keyword, double minRating, int maxCalories, int minProtein, int maxFat, int minSodium, int maxPrice);

    /**
     * Adauga comanda
     * @pre order != null
     * @post list.contains(order)
     * @inv wellformed
     */
    void addOrder(Order order, List<MenuItem> list);

    /**
     * Genereaza raportul 1
     * @pre min < max
     * @post list != null
     * @inv wellformed
     */
    List<Order> generateRaport1(int min, int max);

    /**
     * Genereaza raportul 2
     * @pre numar > 0
     * @post list != null
     * @inv wellformed
     */
    List<String> generateRaport2(int numar);

    /**
     * @pre times > 0 && value >0
     * @post list != null
     * @inv wellformed
     */
    List<User> generateRaport3(int times, int value);

    /**
     * @pre
     * @pre
     * @inv wellformed
     */
    List<MenuItem> generateRaport4(int day);
}
