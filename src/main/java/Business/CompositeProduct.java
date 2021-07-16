package Business;

import java.io.Serializable;
import java.util.*;

public class CompositeProduct implements MenuItem, Serializable {

    private String title;
    private List<MenuItem> components;

    public CompositeProduct(String title){
        this.title = title;
        this.components = new ArrayList<>();
    }

    @Override
    public float computePrice() {
        float price=0.0f;
        for(MenuItem mi:components){
            price = price + mi.computePrice();
        }
        return price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = title;
    }

    public List<MenuItem> getComponents() {
        return components;
    }

    public void setComponents(List<MenuItem> components) {
        this.components = components;
    }

    public void addComponents(MenuItem newItem){
        components.add(newItem);
    }

    public void deleteComponent(MenuItem item){
        components.remove(item);
    }
    public boolean containsProduct(MenuItem item){
        for(MenuItem mi : components){
            if(mi instanceof BaseProduct){
                if(mi.equals(item))
                    return true;
            }
            else{
                if(mi instanceof CompositeProduct){
                    if(((CompositeProduct) mi).containsProduct(item))return true;
                }
            }
        }
        return false;
    }
}
