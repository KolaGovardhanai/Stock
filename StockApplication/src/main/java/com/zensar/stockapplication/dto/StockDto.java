package com.zensar.stockapplication.dto;

//import com.zensar.stockapplication.entity.StockResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDto {
	private long stockId;
	private String name;
	private String marketName;
	private double price;
}
