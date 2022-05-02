package DTO;

public class ItemDTO {
    private final int ID, price;
    private final float tax;
    private final String name, description;
    
    /**
     * Class constructor
     * @param ID Item identifier.
     * @param price cost of the item
     * @param tax the percentage of tax on the item
     * @param name the name of the item
     * @param description describing the item
     */
    public ItemDTO(int ID, int price, float tax,
        String name, String description){
        this.ID = ID;
        this.price = price;
        this.tax = tax;
        this.name = name;
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public float getTax() {
        return tax;
    }

    public String getName() {
        return name;
    }
 
    public String getDescription() {
        return description;
    }
    
    public int getID() {
    	return ID;
    }

    @Override
    public boolean equals(Object anotherObject) {
    	if (anotherObject == this)
    		return true;
    	if (!(anotherObject instanceof ItemDTO)) 
    		return false;
    	ItemDTO itemDTO = (ItemDTO) anotherObject;
    	if(itemDTO.getID() == ID)
    		return true;
    	return false;
    }
}
