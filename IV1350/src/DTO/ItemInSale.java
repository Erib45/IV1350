package DTO;

public class ItemInSale {
	private ItemDTO itemDTO;
	private int quantity;
	
	public ItemInSale(ItemDTO item, int quantity){
		this.itemDTO = item;
		this.quantity = quantity;
	}
	
	public ItemDTO getItem() {
		return itemDTO;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void updateQuantity(int amount){
		quantity += amount;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof ItemInSale))
			return false;
		ItemInSale item = (ItemInSale)o;
		return itemDTO.equals(item.getItem());
	}
}
