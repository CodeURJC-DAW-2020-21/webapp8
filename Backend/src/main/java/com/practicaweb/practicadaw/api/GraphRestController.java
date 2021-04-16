package com.practicaweb.practicadaw.api;

import com.practicaweb.practicadaw.Service.BitcoinService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/graphs")
public class GraphRestController {

    @Autowired
    BitcoinService bitcoinService;

    @GetMapping("/")
    public ResponseEntity<Collection<Double>> getGraph() throws Exception {
        List<Double> graphData = new ArrayList<>();
        JSONArray data = bitcoinService.fetchCoinPrices("https://api.coingecko.com/api/v3/coins/bitcoin/market_chart?vs_currency=usd&days=9&interval=daily");

        for (int i = 0; i < data.length(); i++) {
            JSONArray dataList = (JSONArray) data.get(i);
            Double d = (Double) dataList.get(1);
            graphData.add(d);
        }

        return ResponseEntity.ok(graphData);
    }
}