package Business;


import Data.FileWriterTxt;
import Presentation.Employee;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @invariant isWellFormed()
 */
public class DeliveryService extends Observable implements IDeliveryServiceProcessing, Serializable {

    private List<BaseProduct> meniuCSV = new ArrayList<BaseProduct>();
    private Map<String, MenuItem> meniu;
    private List<Order> orders;
    private Map<Order, List<MenuItem>> ordersList;
    private int orderID = 0;
    private Observer employee;

    public DeliveryService() {
        this.meniu = new HashMap<>();
        this.orders = new ArrayList<>();
        this.ordersList = new HashMap<>();
        this.employee = new Employee();
    }



    @Override
    public void importProducts() {
        Pattern pattern = Pattern.compile(",");
        try(Stream<String> lines = Files.lines(Path.of("products.csv"))) {
            meniuCSV = lines.skip(1).map(line->{
                String[] array = pattern.split(line);
                return new BaseProduct(
                        array[0],
                        Float.parseFloat(array[1]),
                        Integer.parseInt(array[2]),
                        Integer.parseInt(array[3]),
                        Integer.parseInt(array[4]),
                        Integer.parseInt(array[5]),
                        Float.parseFloat(array[6])
                );
            }).distinct().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(BaseProduct bp: meniuCSV){
            meniu.put(bp.getTitle(), bp);
        }

    }


    @Override
    public void createBaseMenuItem(String title, float rating, int calories, int proteins, int fats, int sodium, float price) {
        assert !title.isEmpty() && Float.compare(price,0.0f)>0;
        meniu.put(title,new BaseProduct(title,rating,calories,proteins,fats,sodium,price));
    }

    @Override
    public void createCompositeMenuItem(String title) {
        assert !title.isEmpty();
        meniu.put(title,new CompositeProduct(title));
    }

    @Override
    public void addComponent(String product, String newComposite) {
        assert !product.isEmpty() && !newComposite.isEmpty();
        assert meniu.get(product) != null && meniu.get(newComposite) != null;
        MenuItem mi = meniu.get(product);
        ((CompositeProduct) mi).addComponents(meniu.get(newComposite));
        assert ((CompositeProduct) meniu.get(product)).containsProduct(meniu.get(newComposite));
    }

    @Override
    public void removeComponent(String product, String composite) {
        assert !product.isEmpty() && !composite.isEmpty();
        MenuItem mi = meniu.get(product);
        MenuItem mmi = meniu.get(composite);
        ((CompositeProduct) mi).deleteComponent(mmi);
        assert !((CompositeProduct) meniu.get(product)).containsProduct(meniu.get(composite));
    }

    @Override
    public void editBaseProduct(String product, String title, float rating, int calories, int proteins, int fats, int sodium, float price) {
        assert !product.isEmpty() && !title.isEmpty() && Float.compare(price, 0.0f)>0;
        MenuItem mi = meniu.get(product);
        ((BaseProduct)mi).setPrice(price);
        ((BaseProduct)mi).setCalories(calories);
        ((BaseProduct)mi).setProteins(proteins);
        ((BaseProduct)mi).setTitle(title);
        ((BaseProduct)mi).setRating(rating);
        ((BaseProduct)mi).setFats(fats);
        ((BaseProduct)mi).setSodium(sodium);
        assert isWellFormed();

    }

    @Override
    public void deleteBaseProduct(String product) {
        assert !product.isEmpty();
        MenuItem mi = meniu.remove(product);

        for(Map.Entry<String, MenuItem> bp:meniu.entrySet()){
            if(bp.getValue() instanceof CompositeProduct) {
                if(((CompositeProduct)bp.getValue()).containsProduct(mi)){
                    removeComponent(bp.getValue().getTitle(),mi.getTitle());
                }

            }
        }
        assert isWellFormed();
    }


    @Override
    public void createNewOrder(int clientId, Date date) {
        assert clientId >0  && date != null;
        Order newOrder = new Order(orderID, clientId, date);
        orders.add(newOrder);
        ordersList.put(newOrder, new ArrayList<>());
        orderID++;
    }

    @Override
    public void addOrderMenuItem(int orderId, String menuItem) {
        assert orderId >= 0 && orderId<=orders.size() && !menuItem.isEmpty();
        for(Order o:orders){
            if(o.getOrderId() == orderId){
                MenuItem m = meniu.get(menuItem);
                ordersList.get(o).add(m);
                notifyEmployee(m);

            }
        }
    }

    @Override
    public float totalOrderPrice(int orderId) {
        assert orderId >= 0 && orderId <=orders.size();
        float total = 0.0f;
        for(Order o:orders){
            if(o.getOrderId() == orderId){
                for(MenuItem m: ordersList.get(o)){
                    total = total + m.computePrice();
                }
            }
        }
        return total;
    }

    @Override
    public void generateBill(int orderId) throws IOException {
        Order order = orders.get(orderId);
        FileWriterTxt.generateBill(order,ordersList.get(order),totalOrderPrice(orderId));

    }

    /**
     * Method tests if a meniu structure remains well formed after adding and removing
     * components
     * @return true if the structure is well formed
     */
    private boolean isWellFormed(){
        for(String well: meniu.keySet()){
            if(!well.equals(meniu.get(well).getTitle()))return false;
        }
        return true;
    }

    public void notifyEmployee(MenuItem m){
        employee.update(this, m);
    }

    @Override
    public void generateRaport1(int start, int end) throws IOException {
        assert start <= end && start >=0 && start <=23 && end >=0 && end <=23;
        List<Order> o;

        o = orders.stream().filter(s->s.getHours() >= start && s.getHours() <= end).collect(Collectors.toList());

        FileWriter file = null;
        String fileTxt = "Raport1: " + "\n";
        for(Order oo: o){
            fileTxt = fileTxt + "clientul cu id-ul" + oo.getClientId()+ " a plasat comanda " + oo.getOrderId() + " la ora " + oo.getHours() + "\n";

        }
        file = new FileWriter("Raport 1.txt");
        file.write(fileTxt);
        file.close();


    }

    @Override
    public void generateRaport2(int no) throws IOException {
        assert no != 0;
        int count=0;
        List<String> mm = new ArrayList<>();
        for(Order o: ordersList.keySet()){
            for(MenuItem mi: ordersList.get(o)){
                if(!mm.contains(mi.getTitle()))
                    mm.add(mi.getTitle());
            }
        }
        int cont = mm.size();
        String fileTxt = "Raport2: " + "\n";

        for(int l=0; l<cont; l++){
            count=0;
            for(Order o: ordersList.keySet()){
                int finalL = l;
                count += ordersList.get(o).stream().filter(s->s.getTitle().equals(mm.get(finalL))).count();

            }
            if(count > no){
                fileTxt += mm.get(l) + "\n";
            }
        }
        FileWriter file;
        file = new FileWriter("Raport 2.txt");
        file.write(fileTxt);
        file.close();

    }

    @Override
    public void generateRaport3(int no, int price) throws IOException {
        assert no !=0 && price !=0 ;
        List<Integer> users = new ArrayList<>();
        for(Order o : ordersList.keySet()){
            users.add(o.getClientId());
        }
        int count;
        String fileTxt = "Raport3: " + "\n";
        List<Integer> uniqueUsers = new ArrayList<>(new HashSet<>(users));
        int k=uniqueUsers.size();
        for(int i =0;i<k;i++ ){
            count=0;
            int finalI = i;
            count+=ordersList.keySet().stream().filter(s->s.getClientId() == uniqueUsers.get(finalI) && totalOrderPrice(s.getOrderId())>price).count();
            if(count > no){
                fileTxt+=uniqueUsers.get(i) + "\n";
            }
        }
        FileWriter file = null;
        file = new FileWriter("Raport 3.txt");
        file.write(fileTxt);
        file.close();

    }

    @Override
    public void generateRaport4(int day) throws IOException {
        assert day != 0;
        Map<Order, List<MenuItem>> list;
        list = ordersList.entrySet().stream().filter(s->s.getKey().getDay() == day).collect(Collectors.toMap(s->s.getKey(), s->s.getValue()));

        List<String> items = new ArrayList<>();
        for(Order o: list.keySet()){
            for(MenuItem mi : ordersList.get(o)){
                items.add(mi.getTitle());
            }
        }
        int count=0;
        String fileTxt = "Raport4: " + "\n";
        List<String> uniqueProducts = new ArrayList<>(new HashSet<>(items));
        int k=uniqueProducts.size();
        int i=0;
        for(i=0;i<k;i++){
            count=0;
            for(Order oo: list.keySet()){
                int finalI = i;
                count+=list.get(oo).stream().filter(s->s.getTitle().equals(uniqueProducts.get(finalI))).count();

            }
            fileTxt += uniqueProducts.get(i) + count+ "\n";
        }
        FileWriter file = null;
        file = new FileWriter("Raport 4.txt");
        file.write(fileTxt);
        file.close();
    }

    public List<BaseProduct> getMeniuCSV() {
        return meniuCSV;
    }

    public void setMeniuCSV(List<BaseProduct> meniuCSV) {
        this.meniuCSV = meniuCSV;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Map<Order, List<MenuItem>> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(Map<Order, List<MenuItem>> ordersList) {
        this.ordersList = ordersList;
    }

    public Map<String, MenuItem> getMeniu() {
        return meniu;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setEmployee (Employee employee){
        this.employee = employee;
    }


}
