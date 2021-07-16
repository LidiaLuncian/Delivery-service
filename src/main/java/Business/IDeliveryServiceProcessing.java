package Business;

import java.io.IOException;
import java.util.Date;

public interface IDeliveryServiceProcessing {


    /**
     * Imports products from csv
     * @pre no
     * @post no
     * @throws IOException
     */
    void importProducts() throws IOException;

    /**
     * @pre !title.isEmpty(), price > 0.0f
     * @post none
     * @param title the name of the product
     * @param rating the product's rating
     * @param calories number of calories
     * @param proteins number of proteins
     * @param fats number of fats
     * @param sodium quantity of sodium
     * @param price the price
     */
    void createBaseMenuItem(String title, float rating, int calories, int proteins, int fats, int sodium, float price);

    /**
     * @pre !title.isEmpty()
     * @post none
     * @param title product's name
     */
    void createCompositeMenuItem(String title);

    /**
     * @pre !product.isEmpty(), !composite.isEmpty()
     * @post ((CompositeProduct) meniu.get(product)).containsProduct(meniu.get(composite))
     * @param product name of the product to be added
     * @param composite the name of the composite product
     */
    void addComponent(String product, String composite);

    /**
     * @pre !product.isEmpty() && !composite.isEmpty()
     * @post !((CompositeProduct) meniu.get(product)).containsProduct(meniu.get(composite));
     * @param product the name of the product to be removed
     * @param composite the composite product
     */
    void removeComponent(String product, String composite);

    /**
     * @pre !product.isEmpty() && !title.isEmpty() && Float.compare(price, 0.0f)>0;
     * @post isWellFormed()
     * @param product the name of the product
     * @param title the new name
     * @param rating the new rating
     * @param calories the new number calories
     * @param proteins the new number of proteins
     * @param fats the new number of fats
     * @param sodium the new quantity of sodium
     * @param price the new price
     */
    void editBaseProduct(String product, String title, float rating, int calories, int proteins, int fats, int sodium, float price);

    /**
     * @pre !product.isEmpty()
     * @post isWellFormed()
     *
     * @param product the name of the product
     */
    void deleteBaseProduct(String product);

    /**
     * @pre clientId >0  && date != null;
     * @post none
     * @param clientId the client's id
     * @param date the date
     */
    void createNewOrder(int clientId, Date date);

    /**
     * @pre orderId >= 0 && orderId<=orders.size() && !menuItem.isEmpty();
     * @post none
     * @param orderId the order's id
     * @param item the product that's bought
     */
    void addOrderMenuItem(int orderId, String item);

    /**
     * @pre orderId >= 0 && orderId <=orders.size();
     * @post none
     * @param orderId the order's id
     * @return the total price
     */
    float totalOrderPrice(int orderId);

    /**
     * @pre none
     * @post none
     *
     * @param orderId the order's id
     * @throws IOException
     */
    void generateBill(int orderId) throws IOException;

    /**
     * @pre none
     * @post none
     * @param m the menu item that is going to be prepared by the employee
     */
    void notifyEmployee(MenuItem m);

    /**
     * @pre start <= end && start >=0 && start <=23 && end >=0 && end <=23;
     * @post none
     * @param start the inferior limit
     * @param end the superior limit
     * @throws IOException
     */
    void generateRaport1(int start, int end) throws IOException;

    /**
     * @pre no != 0;
     * @post none
     * @param no number of times a product has been ordered
     * @throws IOException
     */
    void generateRaport2(int no) throws IOException;

    /**
     * @pre no !=0 && price !=0 ;
     * @post none
     * @param no a number of times a product has been ordered
     * @param price the minim price
     * @throws IOException
     */
    void generateRaport3(int no, int price) throws IOException;

    /**
     * @pre day != 0;
     * @post none
     * @param day day of the month
     * @throws IOException
     */
    void generateRaport4(int day) throws IOException;
}
