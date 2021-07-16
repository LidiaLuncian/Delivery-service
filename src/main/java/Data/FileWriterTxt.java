package Data;

import Business.MenuItem;
import Business.Order;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FileWriterTxt {
    private static final boolean append = false;

    public static void generateBill(Order order, List<MenuItem> mi, float price) throws IOException {
        File newBill = new File("BillNo " + order.getOrderId()+".txt");
        newBill.createNewFile();

        FileWriter fw = new FileWriter(newBill.getCanonicalPath(),append);
        PrintWriter pw = new PrintWriter(fw);
        pw.printf("%s%n", "Your bill!");
        pw.printf("%s%n","Id client: "+ order.getClientId() + " Id order: "+ order.getOrderId()+ " Date: " +order.getOrderDate() );
        for(MenuItem m:mi){
            pw.printf("%s%n",m.getTitle() + " " + m.computePrice());
        }
        pw.printf("%s%n","Total price: " + price);
        pw.printf("%s%n","Thank you for your order!Come again!");
        pw.close();

    }
}
