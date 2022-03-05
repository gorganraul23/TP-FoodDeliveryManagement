package PresentationLayer;

import BusinessLayer.*;
import DataLayer.BillCompute;
import DataLayer.RaportCompute;
import DataLayer.Serializator;
import ObserverPattern.Subject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private LoginView loginView;
    private AdministratorView administratorView;
    private ClientView clientView;
    private EmployeeView employeeView;
    private DeliveryService deliveryService;
    private Subject subject;

    public Controller(LoginView loginView) {
        this.loginView = loginView;
        loginView.addContinueListener(new ContinueListener());
        loginView.addRegisterListener(new RegisterListener());
        loginView.addCloseListener(new CloseListener());

        deliveryService = new DeliveryService();

        administratorView = new AdministratorView();
        administratorView.addImportListener(new ImportListener());
        administratorView.addLoadListener(new LoadListener());
        administratorView.addAddListener(new AddListener());
        administratorView.addModifyListener(new ModifyListener());
        administratorView.addDeleteListener(new DeleteListener());
        administratorView.addRap1Listener(new Rap1Listener());
        administratorView.addRap2Listener(new Rap2Listener());
        administratorView.addRap3Listener(new Rap3Listener());
        administratorView.addRap4Listener(new Rap4Listener());

        clientView = new ClientView();
        clientView.addMenuListener(new MenuListener());
        clientView.addFilterListener(new FilterListener());
        clientView.addOrderListener(new OrderListener());

        subject = new Subject();
        //employeeView = new EmployeeView(subject);
    }

    class ContinueListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String role = deliveryService.validLogin(loginView.getUsername(), loginView.getPassword());
                if (role.equals(""))
                    loginView.showMessage("Incorrect username or password.\n Please register to continue.");
                else {
                    if (role.equals("Administrator")) {
                        administratorView.setVisible(true);
                    } else if (role.equals("Client")) {
                        clientView.setVisible(true);
                    } else
                        employeeView = new EmployeeView(subject);
                }
            } catch (Exception ex) {
                loginView.showMessage("An error has occurred");
            }
        }
    }

    class RegisterListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if (loginView.getUsername().equals("") || loginView.getPassword().equals(""))
                    loginView.showMessage("Incomplete fields.");
                else {
                    if (deliveryService.validUsername(loginView.getUsername())) {
                        deliveryService.getUsers().add(new User(loginView.getUsername(), loginView.getPassword(), "Client", 0));
                        loginView.showMessage("Account created successfully.");
                    } else
                        loginView.showMessage("Username already taken.");
                }
            } catch (Exception ex) {
                loginView.showMessage("An error has occurred");
            }
        }
    }

    class CloseListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                new Serializator().serialization(deliveryService);
                System.exit(0);
            } catch (Exception ex) {
                administratorView.showMessage("An error has occurred");
            }
        }
    }

    class ImportListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                deliveryService.importMenu();
                for (MenuItem item : deliveryService.getMenuItems()) {
                    administratorView.addRow(item.getTitle(), item.getRating(), item.getCalories(), item.getProtein(),
                            item.getFat(), item.getSodium(), item.getPrice());
                }
            } catch (Exception ex) {
                administratorView.showMessage("An error has occurred");
            }
        }
    }

    class LoadListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                new Serializator().deserialization(deliveryService);
                for (MenuItem item : deliveryService.getMenuItems()) {
                    administratorView.addRow(item.getTitle(), item.getRating(), item.getCalories(), item.getProtein(),
                            item.getFat(), item.getSodium(), item.getPrice());
                }
            } catch (Exception ex) {
                administratorView.showMessage("An error has occurred");
            }
        }
    }

    class AddListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String title;
                double rating;
                int calories, protein, fat, sodium, price;
                if (administratorView.getTable().getSelectedRowCount() < 2) {
                    title = administratorView.getTitle();
                    rating = Double.parseDouble(administratorView.getRating());
                    calories = Integer.parseInt(administratorView.getCalories());
                    protein = Integer.parseInt(administratorView.getProtein());
                    fat = Integer.parseInt(administratorView.getFat());
                    sodium = Integer.parseInt(administratorView.getSodium());
                    price = Integer.parseInt(administratorView.getPrice());
                    administratorView.addRow(title, rating, calories, protein, fat, sodium, price);
                    deliveryService.getMenuItems().add(new MenuItem(title, rating, calories, protein, fat, sodium, price));
                } else {
                    int[] rows = administratorView.getTable().getSelectedRows();
                    CompositeProduct prod = new CompositeProduct();
                    prod.setTitle(administratorView.getTitle());
                    for (int i = 0; i < administratorView.getTable().getSelectedRowCount(); i++) {
                        prod.setRating(prod.getRating() + deliveryService.getMenuItems().get(rows[i]).getRating());
                        prod.setCalories(prod.getCalories() + deliveryService.getMenuItems().get(rows[i]).getCalories());
                        prod.setProtein(prod.getProtein() + deliveryService.getMenuItems().get(rows[i]).getProtein());
                        prod.setFat(prod.getFat() + deliveryService.getMenuItems().get(rows[i]).getFat());
                        prod.setSodium(prod.getSodium() + deliveryService.getMenuItems().get(rows[i]).getSodium());
                        prod.setPrice(prod.getPrice() + deliveryService.getMenuItems().get(rows[i]).getPrice());

                        prod.getList().add((BaseProduct) deliveryService.getMenuItems().get(rows[i]));
                    }
                    prod.setRating(prod.getRating() / administratorView.getTable().getSelectedRowCount());
                    administratorView.addRow(prod.getTitle(), prod.getRating(), prod.getCalories(), prod.getProtein(), prod.getFat(), prod.getSodium(), prod.getPrice());
                    deliveryService.getMenuItems().add(new MenuItem(prod.getTitle(), prod.getRating(), prod.getCalories(), prod.getProtein(), prod.getFat(), prod.getSodium(), prod.getPrice()));
                }
            } catch (NumberFormatException nfex) {
                administratorView.showMessage("Wrong input");
            } catch (Exception ex) {
                administratorView.showMessage("An error has occurred");
            }
        }
    }

    class ModifyListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if (administratorView.getTable().getSelectedRowCount() == 0) {
                    administratorView.showMessage("No selected row to modify");
                } else if (administratorView.getTable().getSelectedRowCount() == 1) {
                    int row = administratorView.getTable().getSelectedRow();
                    MenuItem item = new MenuItem();
                    //if (!administratorView.getTitle().equals("")) {
                    item.setTitle(administratorView.getTable().getValueAt(row, 0).toString());
                    //} else {
                     //   item.setTitle(administratorView.getTitle());
                    //}
                    if (administratorView.getRating().equals("")) {
                        item.setRating(Double.parseDouble(administratorView.getTable().getValueAt(row, 1).toString()));
                    } else {
                        item.setRating(Double.parseDouble(administratorView.getRating()));
                    }
                    if (administratorView.getCalories().equals("")) {
                        item.setCalories(Integer.parseInt(administratorView.getTable().getValueAt(row, 2).toString()));
                    } else {
                        item.setCalories(Integer.parseInt(administratorView.getCalories()));
                    }
                    if (administratorView.getProtein().equals("")) {
                        item.setProtein(Integer.parseInt(administratorView.getTable().getValueAt(row, 3).toString()));
                    } else {
                        item.setProtein(Integer.parseInt(administratorView.getProtein()));
                    }
                    if (administratorView.getFat().equals("")) {
                        item.setFat(Integer.parseInt(administratorView.getTable().getValueAt(row, 4).toString()));
                    } else {
                        item.setFat(Integer.parseInt(administratorView.getFat()));
                    }
                    if (administratorView.getSodium().equals("")) {
                        item.setSodium(Integer.parseInt(administratorView.getTable().getValueAt(row, 5).toString()));
                    } else {
                        item.setSodium(Integer.parseInt(administratorView.getSodium()));
                    }
                    if (administratorView.getPrice().equals("")) {
                        item.setPrice(Integer.parseInt(administratorView.getTable().getValueAt(row, 6).toString()));
                    } else {
                        item.setPrice(Integer.parseInt(administratorView.getPrice()));
                    }
                    deliveryService.modifyProduct(item);
                    administratorView.modifyRow(row, item.getTitle(), item.getRating(), item.getCalories(), item.getProtein(), item.getFat(), item.getSodium(), item.getPrice());

                } else
                    administratorView.showMessage("To many rows to modify");
            } catch (Exception ex) {
                administratorView.showMessage("An error has occurred");
            }
        }
    }

    class DeleteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if (administratorView.getTable().getSelectedRowCount() > 0) {
                    int[] rows = administratorView.getTable().getSelectedRows();
                    for (int i = 0; i < administratorView.getTable().getSelectedRowCount(); i++) {
                        administratorView.deleteRow(rows[i]);
                        deliveryService.deleteProduct(rows[i]);
                    }
                } else
                    administratorView.showMessage("No selected row to delete");
            } catch (Exception ex) {
                administratorView.showMessage("An error has occurred");
            }
        }
    }

    class MenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                for (MenuItem item : deliveryService.getMenuItems()) {
                    clientView.addRow(item.getTitle(), item.getRating(), item.getCalories(), item.getProtein(),
                            item.getFat(), item.getSodium(), item.getPrice());
                }
            } catch (Exception ex) {
                administratorView.showMessage("An error has occurred");
            }
        }
    }

    class FilterListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String title;
                double rating = -1;
                int calories = 10000, protein = -1, fat = 10000, sodium = -1, price = 10000;
                title = clientView.getTitle();
                if (!clientView.getRating().equals(""))
                    rating = Integer.parseInt(clientView.getRating());
                if (!clientView.getCalories().equals(""))
                    calories = Integer.parseInt(clientView.getCalories());
                if (!clientView.getProtein().equals(""))
                    protein = Integer.parseInt(clientView.getProtein());
                if (!clientView.getFat().equals(""))
                    fat = Integer.parseInt(clientView.getFat());
                if (!clientView.getSodium().equals(""))
                    sodium = Integer.parseInt(clientView.getSodium());
                if (!clientView.getPrice().equals(""))
                    price = Integer.parseInt(clientView.getPrice());
                clientView.deleteAll();
                List<MenuItem> newList = deliveryService.filter(title, rating, calories, protein, fat, sodium, price);
                for (MenuItem item : newList) {
                    clientView.addRow(item.getTitle(), item.getRating(), item.getCalories(), item.getProtein(),
                            item.getFat(), item.getSodium(), item.getPrice());
                }
                //System.out.println(deliveryService.getConnectedNow());
            } catch (Exception ex) {
                clientView.showMessage("An error has occurred");
            }
        }
    }

    class OrderListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if (clientView.getTable().getSelectedRowCount() > 0) {
                    int[] rows = clientView.getTable().getSelectedRows();
                    Order order = new Order();
                    List<MenuItem> list = new ArrayList<>();
                    for (int index : rows) {
                        String title = (String) clientView.getTable().getValueAt(index, 0);
                        double rating = Double.parseDouble((clientView.getTable().getValueAt(index, 1)).toString());
                        int calories = Integer.parseInt((clientView.getTable().getValueAt(index, 2)).toString());
                        int proteins = Integer.parseInt((clientView.getTable().getValueAt(index, 3)).toString());
                        int fat = Integer.parseInt((clientView.getTable().getValueAt(index, 4)).toString());
                        int sodium = Integer.parseInt((clientView.getTable().getValueAt(index, 5)).toString());
                        int price = Integer.parseInt((clientView.getTable().getValueAt(index, 6)).toString());
                        MenuItem menuItem = new MenuItem(title, rating, calories, proteins, fat, sodium, price);
                        list.add(menuItem);
                    }
                    order.setClient(deliveryService.userConnected());
                    order.setDate(LocalDateTime.now());
                    deliveryService.addOrder(order, list);
                    employeeView.update();
                    BillCompute.computeBill(order, list);
                } else
                    clientView.showMessage("No products selected for the order.");
            } catch (Exception ex) {
                clientView.showMessage("An error has occurred");
                //ex.printStackTrace();
            }
        }
    }

    class Rap1Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int min = Integer.parseInt(administratorView.getMinHour());
                int max = Integer.parseInt(administratorView.getMaxHour());
                if (min > max)
                    administratorView.showMessage("Incorrect interval.");
                List<Order> list = deliveryService.generateRaport1(min, max);
                RaportCompute.computeRaport1(list, min, max);
            } catch (NumberFormatException nfex) {
                administratorView.showMessage("Incorrect number");
            } catch (Exception ex) {
                administratorView.showMessage("An error has occurred");
            }
        }
    }

    class Rap2Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int number = Integer.parseInt(administratorView.getMinAmount());
                List<String> list = deliveryService.generateRaport2(number);
                RaportCompute.computeRaport2(list, number);
            } catch (NumberFormatException nfex) {
                administratorView.showMessage("Incorrect number");
            } catch (Exception ex) {
                administratorView.showMessage("An error has occurred");
            }
        }
    }

    class Rap3Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int times = Integer.parseInt(administratorView.getMinTimes());
                int value = Integer.parseInt(administratorView.getMinPrice());
                List<User> list = deliveryService.generateRaport3(times, value);
                RaportCompute.computeRaport3(list, times, value);
            } catch (NumberFormatException nfex) {
                administratorView.showMessage("Incorrect number");
            }  catch (Exception ex) {
                administratorView.showMessage("An error has occurred");
            }
        }
    }

    class Rap4Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int day = Integer.parseInt(administratorView.getDay());
                List<MenuItem> list = deliveryService.generateRaport4(day);
                RaportCompute.computeRaport4(list, day);
            }catch (NumberFormatException nfex) {
                administratorView.showMessage("Incorrect number");
            }  catch (Exception ex) {
                administratorView.showMessage("An error has occurred");
            }
        }
    }
}
