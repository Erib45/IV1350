package se.kth.iv1350.model;

import se.kth.iv1350.DTO.ItemDTO;

/**
 * Item contains an itemDTO describing the item and a quantity of the item.
 * This is used to store items in the sale.
 * @author Erik Eriksson
 * @author Vanshu Dutta
 * @author Rolf Dahlberg
 */
public class Item {
	private final ItemDTO itemDTO;
	private int quantity;
	/**
	 * Class constructor
	 * @param itemDTO Describes an item
	 * @param quantity Item quantity
	 */
	public Item(ItemDTO itemDTO, int quantity){
		this.itemDTO = itemDTO;
		this.quantity = quantity;
	}

	public ItemDTO getItemDTO() {
		return itemDTO;
	}

	public int getQuantity() {
		return quantity;
	}
	/**
	 * Increase item quantity
	 * @param quantity Item quantity 
	 */
	public void updateQuantity(int quantity){
		this.quantity += quantity;
	}

	@Override
	public boolean equals(Object anotherObject) {
		if(this == anotherObject)
			return true;
		if(!(anotherObject instanceof Item) && anotherObject == null)
			return false;
		Item item = (Item)anotherObject;
		return itemDTO.equals(item.getItemDTO());
	}
}
