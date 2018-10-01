package hellocodingtests.service;

import hellocodingtests.StockData;
import hellocodingtests.model.Stock;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class StockService {

    public Map<Long, Stock> findAll(){
        return StockData.INSTANCE.getStocks();
    }

    public Optional<Stock> findStockById(Long id){
        return Optional.ofNullable(StockData.INSTANCE.getStocks().getOrDefault(id, null));
    }

    public Stock saveStock(Stock stock){
        StockData.INSTANCE.getStocks().put(stock.getId(), stock);
        return stock;
    }

}
