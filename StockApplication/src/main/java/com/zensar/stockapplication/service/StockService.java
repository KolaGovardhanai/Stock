package com.zensar.stockapplication.service;

import java.util.List;

import com.zensar.stockapplication.dto.StockDto;
import com.zensar.stockapplication.exception.InvalidStockIdException;
//import com.zensar.stockapplication.entity.StockRequest;
//import com.zensar.stockapplication.entity.StockResponse;

public interface StockService {

List<StockDto> getAllStock(int pageNumber,int pageSize);
	
	StockDto getStock(long id) throws InvalidStockIdException;
	
	List<StockDto> getStockByItsName(String stockName);
	List<StockDto> getStockByItsNameAndPrice(String stockName, double price);
	
	StockDto createStock( StockDto stock,String token);
	
	String deleteStock( long stockId);
	
	StockDto updateStock( int stockId, StockDto stock) throws InvalidStockIdException;
	
	String deleteAllStocks();
}
