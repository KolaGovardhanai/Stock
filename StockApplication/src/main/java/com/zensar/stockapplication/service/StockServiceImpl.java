package com.zensar.stockapplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.zensar.stockapplication.dto.StockDto;
import com.zensar.stockapplication.entity.Stock;
import com.zensar.stockapplication.exception.InvalidStockIdException;
//import com.zensar.stockapplication.entity.StockRequest;
//import com.zensar.stockapplication.entity.StockResponse;
import com.zensar.stockapplication.repository.StockRepository;
@Service
public class StockServiceImpl implements StockService {
	@Autowired
	private StockRepository stockRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<StockDto> getAllStock(int pageNumber,int pageSize) {
		
	Page<Stock> pageStocks= stockRepository.findAll(PageRequest.of(pageNumber, pageSize,Sort.by("marketName").descending()));	
		List<Stock> content = pageStocks.getContent();
		List<StockDto> stockDto=new ArrayList<>();
		for(Stock stock : content) {
			StockDto mapToDto = mapToDto(stock);
			stockDto.add(mapToDto);
		}
		
		return stockDto;
	}
  
	public List<StockDto> getStockByItsName(String stockName) {
	List<Stock> findStockByName= stockRepository.findStockByItsName(stockName);
	
	List<StockDto> stocks=new ArrayList<StockDto>();
	
	for(Stock st:findStockByName) {
		stocks.add(modelMapper.map(st, StockDto.class));
	}
	
	return stocks;
	}
	public List<StockDto> getStockByItsNameAndPrice(String stockName, double price) {
		List<Stock> findStockByNameAndPrice= stockRepository.findStockByItsNameAndPrice(stockName,price);
		
		List<StockDto> stocks=new ArrayList<StockDto>();
		
		for(Stock st:findStockByNameAndPrice) {
			stocks.add(modelMapper.map(st, StockDto.class));
		}
		
		return stocks;
		}
	
	@Override
	public StockDto getStock(long id) throws InvalidStockIdException {
		
		Optional<Stock>optionalStock= stockRepository.findById(id);
	/*	StockResponse stockResponse=new StockResponse();
		stockResponse.setStockId(stock.getStockId());
		stockResponse.setMarketName(stock.getMarketName());
		stockResponse.setName(stock.getName());
		stockResponse.setPrice(stock.getPrice()); */
		Stock stock=null;
		if(optionalStock.isPresent()) {
			stock=optionalStock.get();
			return	modelMapper.map(stock, StockDto.class);
		}else {
	    throw new InvalidStockIdException("Stock is not available with stock id"+ id);
		
	//	return stockResponse;
		}
	}

	@Override
	public StockDto createStock(StockDto stock, String token) {
	//	return stockRepository.save(stock);
	//	Stock newStock=mapToStock(stock);
	Stock newStock=	modelMapper.map(stock, Stock.class);
		if(token.equals("GK66612")) {
			Stock save= stockRepository.save(newStock);
			return modelMapper.map(save,StockDto.class );
			}else {
				return null ;
			} 
			
	}

	@Override
	public String deleteStock(long stockId) {
		
		stockRepository.deleteById(stockId);
		return " Stock deleted with stock id "+stockId;
	/*	for(Stock stock:stocks) {
			if(stock.getStockId()== stockId) {
				stocks.remove(stock);
				return " Stock deleted with stock id "+stockId;
			}
		}
		return "Sorry,stock id is not available"; */
	}

	@Override
	public StockDto updateStock(int stockId, StockDto stock) throws InvalidStockIdException {
		
		StockDto stockDto =getStock(stockId);
		//Stock stock2=mapToStock(stockDto);
		Stock stock2 = modelMapper.map(stockDto,Stock.class);
		if(stock2!=null) {
			stock2.setPrice(stock.getPrice());
			stock2.setMarketName(stock.getMarketName());
			stock2.setName(stock.getName());
			stock2.setStockId(stockId);
		Stock stock3=	stockRepository.save(stock2);
	return	modelMapper.map(stock3,StockDto.class);
		}
		
		return null;
	}

	@Override
	public String deleteAllStocks() {
		stockRepository.deleteAll();
		//stocks.removeAll(stocks);
		return ("All stocks deleted successfullyy");
	}

	/*private Stock mapToDto(StockDto stockDto) {
		Stock newStock= new Stock();
		newStock.setMarketName(stockDto.getMarketName());
		newStock.setName(stockDto.getName());
		newStock.setPrice(stockDto.getPrice());
		return newStock;
	}*/
	private Stock mapToStock(StockDto stockDto) {
		Stock newStock= new Stock();
		newStock.setMarketName(stockDto.getMarketName());
		newStock.setName(stockDto.getName());
		newStock.setPrice(stockDto.getPrice());
		return newStock;
	}
	private StockDto mapToDto(Stock stock) {
		
		StockDto stockDto=new StockDto();
		stockDto.setStockId(stock.getStockId());
		stockDto.setPrice(stock.getPrice());
		stockDto.setMarketName(stock.getMarketName());
		stockDto.setName(stock.getName());
		
		return stockDto;
	}

}
