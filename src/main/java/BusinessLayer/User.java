package BusinessLayer;

import java.io.Serializable;

/**
 * Modeleaza un user al aplicatiei
 */
public class User implements Serializable {

    private String username;
    private String password;
    private String role;
    private int ordersPerformed;

    /**
     * Constructor
     * @param username username-ul utilizatorului
     * @param password parola pt cont
     * @param role rolul utilizatorului
     */
    public User(String username, String password, String role, int ordersPerformed) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.ordersPerformed = ordersPerformed;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getOrdersPerformed() {
        return ordersPerformed;
    }

    public void setOrdersPerformed(int ordersPerformed) {
        this.ordersPerformed = ordersPerformed;
    }

    /**
     * Afisare user
     * @return un String cu user-ul
     */
    public String toString(){
        return role + "-> username:" + username + " password:" + password;
    }
}
