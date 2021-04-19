package com.practicaweb.practicadaw.Service;

import com.practicaweb.practicadaw.json.UrlJsonFetcher;
import com.practicaweb.practicadaw.model.Bitcoin;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BitcoinService {

    private static final String REQUEST_URL = "https://api.coinbase.com/v2/prices/BTC-USD/spot";

    private final UrlJsonFetcher jsonFetcher;

    public BitcoinService(){
        this.jsonFetcher = new UrlJsonFetcher();
    }

    public JSONArray fetchCoinPrices(String url) throws Exception {
        try {
            JSONObject json = jsonFetcher.fetchObjectFromUrl(url);
            JSONArray data = json.getJSONArray("prices");
            return data;
        } catch (Exception e){
            throw new Exception();
        }
    }

}
