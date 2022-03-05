package BusinessLayer;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Clasa care modeleaza o comanda
 */
public class Order implements Serializable {

    private User user;
    private LocalDateTime date;

    /**
     * Costructor fara parametri
     */
    public Order(){

    }

    /**
     * Constructorul cu parametri
     * @param user clientul care face comanda
     * @param date data si ora la care se face comanda
     */
    public Order(User user, LocalDateTime date) {
        this.user = user;
        this.date = date;
    }

    public User getClient() {
        return user;
    }

    public void setClient(User user) {
        this.user = user;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getDay(){
        return this.getDate().getDayOfYear();
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * Functia prin care se face un hash pe baza comenzii pentru a fi adaugata in map-ul deliveryService
     * @return un hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(user, date);
    }

    @Override
    public String toString() {
        return "Order[" +
                "client='" + user.getUsername() +
                ", date=" + date +
                ']';
    }
}
