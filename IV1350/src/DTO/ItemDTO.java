package DTO;

public class ItemDTO {
    private final int ID, price;
    private final float tax;
    private final String name, description;

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
    public boolean equals(Object o) {
    	if (o == this)
    		return true;
    	if (!(o instanceof ItemDTO)) 
    		return false;
    	ItemDTO itemDTO = (ItemDTO) o;
    	if(itemDTO.getID() == ID)
    		return true;
    	return false;
    }
}
