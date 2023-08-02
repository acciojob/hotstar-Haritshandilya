package com.driver.services;

import com.driver.EntryDto.WebSeriesEntryDto;
import com.driver.model.ProductionHouse;
import com.driver.model.WebSeries;
import com.driver.repository.ProductionHouseRepository;
import com.driver.repository.WebSeriesRepository;
//import org.graalvm.compiler.nodes.calc.IntegerDivRemNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WebSeriesService {

    @Autowired
    WebSeriesRepository webSeriesRepository;

    @Autowired
    ProductionHouseRepository productionHouseRepository;

    public Integer addWebSeries(WebSeriesEntryDto webSeriesEntryDto)throws  Exception{

        //Add a webSeries to the database and update the ratings of the productionHouse
        //Incase the seriesName is already present in the Db throw Exception("Series is already present")
        //use function written in Repository Layer for the same
        //Dont forget to save the production and webseries Repo
        Optional<ProductionHouse> optionalProductionHouse = productionHouseRepository.findById(webSeriesEntryDto.getProductionHouseId());
        ProductionHouse productionHouse = optionalProductionHouse.get();
        if(webSeriesRepository.findBySeriesName(webSeriesEntryDto.getSeriesName())!=null) throw new Exception("Series is already present");
        WebSeries webSeries = new WebSeries(webSeriesEntryDto.getSeriesName(),webSeriesEntryDto.getAgeLimit(),webSeriesEntryDto.getRating(),webSeriesEntryDto.getSubscriptionType());
        WebSeries savedWebseries = webSeriesRepository.save(webSeries);
        List<WebSeries> wbs = productionHouse.getWebSeriesList();
        Double totalRatings = productionHouse.getRatings()*wbs.size()+webSeries.getRating();
        wbs.add(webSeries);
        totalRatings/=wbs.size();
        productionHouse.setRatings(totalRatings);
        productionHouse.setWebSeriesList(wbs);
        productionHouseRepository.save(productionHouse);
        return savedWebseries.getId();
    }

}
