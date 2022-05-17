package se.kth.iv1350.model;

import se.kth.iv1350.DTO.SaleDTO;

public interface TotalRevenueObserver {

    void newSale(SaleDTO saleDTO);
}
