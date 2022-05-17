package se.kth.iv1350.view;

import java.io.File;
import java.io.FileWriter;

import se.kth.iv1350.DTO.SaleDTO;
import se.kth.iv1350.model.TotalRevenueObserver;

public class TotalRevenueFileOutput implements TotalRevenueObserver {

	private float totalRevenue;
	
	public void revenueFileOutput() {
		    try {
		      File outputFile = new File("se.kth.iv1350.src\\se\\kth\\iv1350\\view\\outputFile.txt");
		      outputFile.delete();
		      outputFile.createNewFile();
		      FileWriter writer = new FileWriter("se.kth.iv1350.src\\se\\kth\\iv1350\\view\\outputFile.txt");
		      writer.write("Total revenue: " + totalRevenue);
		      writer.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (Exception e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}

	@Override
	public void newSale(SaleDTO saleDTO) {
		totalRevenue += saleDTO.getTotal();
		revenueFileOutput();
	}

}
