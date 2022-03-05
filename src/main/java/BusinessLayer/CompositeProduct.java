package BusinessLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Produs compus din mai multe produse simple
 */
public class CompositeProduct extends MenuItem implements Serializable {

    private List<BaseProduct> list;

    /**
     * Constructorul, unde se initializeaza o lista de produse simple
     */
    public CompositeProduct() {
        list = new ArrayList<>();
    }

    @Override
    public int computePrice() {
        int totalPrice = 0;
        for (BaseProduct baseProduct : list) {
            totalPrice += baseProduct.getPrice();
        }
        return totalPrice;
    }

    public List<BaseProduct> getList() {
        return list;
    }

}
