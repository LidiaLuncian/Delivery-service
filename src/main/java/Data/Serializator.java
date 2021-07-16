package Data;

import Business.DeliveryService;

import java.io.*;

public class Serializator {

    public static void serialize(Serializable obj){
        try{
            FileOutputStream file = new FileOutputStream("delivery.txt");
            ObjectOutputStream objOut = new ObjectOutputStream(file);
            objOut.writeObject(obj);
            objOut.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DeliveryService deserialize(){

        DeliveryService delivery = null;
        try{
            FileInputStream file = new FileInputStream("delivery.txt");
            ObjectInputStream obj = new ObjectInputStream(file);
            delivery = (DeliveryService) obj.readObject();
            obj.close();
            file.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return delivery;
    }
}
