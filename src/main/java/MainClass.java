import Business.DeliveryService;
import Data.Serializator;
import Presentation.LogIn;

import java.io.File;

public class MainClass {
    public static void main(String[] args){

        File file = new File("delivery.txt");

        DeliveryService delivery = null;
        if(!file.exists())
        {
            delivery = new DeliveryService();
            delivery.importProducts();
            delivery.createCompositeMenuItem("Breakfast");
        }
        else{
            delivery = Serializator.deserialize();
        }
        new LogIn(delivery);


      /*  for(BaseProduct bp: service.getMeniuCSV()) {
            System.out.println(bp.toString());
        }*/
    }
}
