package model;

import DTO.ItemDTO;

public class Item {
	private ItemDTO itemDTO;
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

	public ItemDTO getItem() {
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
		if(!(anotherObject instanceof Item))
			return false;
		Item item = (Item)anotherObject;
		return itemDTO.equals(item.getItem());
	}
}
