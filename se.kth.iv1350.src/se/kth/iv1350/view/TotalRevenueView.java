package se.kth.iv1350.view;

import se.kth.iv1350.DTO.SaleDTO;
import se.kth.iv1350.model.TotalRevenueObserver;

public class TotalRevenueView implements TotalRevenueObserver {
    private float total;


    @Override
    public void newSale(SaleDTO saleDTO) {
        total += saleDTO.getTotal();
        System.out.println("Total revenue: " + total +"\n");
    }
}