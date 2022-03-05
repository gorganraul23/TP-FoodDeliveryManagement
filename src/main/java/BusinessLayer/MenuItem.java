package BusinessLayer;

import java.io.Serializable;

/**
 * Clasa care inglobeaza produs de baza si produs compus
 */
public class MenuItem implements Serializable {

    private String title;
    private double rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int price;

    /**
     * Constructor fara parametri
     */
    public MenuItem(){

    }

    /**
     * Constructor cu parametri
     */
    public MenuItem(String title, double rating, int calories, int protein, int fat, int sodium, int price) {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getSodium() {
        return sodium;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "MenuItem[" +
                "title= " + title  +
                ", rating= " + rating +
                ", calories= " + calories +
                ", protein= " + protein +
                ", fat= " + fat +
                ", sodium= " + sodium +
                ", price= " + price +
                ']';
    }

    public int computePrice(){
        return this.getPrice();
    }
}
