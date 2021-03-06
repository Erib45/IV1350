package se.kth.iv1350.view;

import java.io.File;
import java.io.FileWriter;

import se.kth.iv1350.DTO.SaleDTO;
import se.kth.iv1350.model.TotalRevenueObserver;

/**
 *
 * @author Erik Eriksson
 * @author Vanshu Dutta
 * @author Rolf Dahlberg
 */
public class TotalRevenueFileOutput implements TotalRevenueObserver {

	private float totalRevenue;
	
	private void revenueFileOutput() {
		    try {
		      File outputFile = new File("outputFile.txt");
		      outputFile.delete();
		      outputFile.createNewFile();
		      FileWriter writer = new FileWriter("outputFile.txt");
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
