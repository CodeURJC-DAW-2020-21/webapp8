package com.practicaweb.practicadaw.controller;

import com.practicaweb.practicadaw.Service.*;
import com.practicaweb.practicadaw.model.*;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.*;

@Controller
public class PostController {

    private final EntryService entryService;
    private final UserService userService;
    @Autowired
    CriptocurrencyService criptocurrencyService;

    @Autowired
    BitcoinService bitcoinService;

    @Autowired
    CommentService commentService;

    private RestTemplate restTemplate = new RestTemplate();

    public PostController(EntryService entryService, UserService userService, CriptocurrencyService criptocurrencyService) {
        this.entryService = entryService;
        this.userService = userService;
        this.criptocurrencyService = criptocurrencyService;
    }

    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

        if(principal != null) {

            model.addAttribute("logged", true);
            model.addAttribute("userName", principal.getName());
            model.addAttribute("isAdmin", request.isUserInRole("ADMIN"));

        } else {
            model.addAttribute("logged", false);
        }
    }



    @GetMapping("/password")
    public String passwordReset(Model model){
        return "password";
    }

    @GetMapping("/passwordEmail")
    public String passwordEmail(Model model){
        return "passwordEmail";
    }

    @GetMapping("/criptomonedas")
    public String criptomonedas(Model model, HttpServletRequest request) {
        List<Criptocurrency> criptocurrencies = criptocurrencyService.selectAll();
        model.addAttribute("criptocurrencies", criptocurrencies);
        for (int i = 0; i < criptocurrencies.toArray().length; i++){

        }

        Principal principal = request.getUserPrincipal();
        if(principal != null) {
            User user = userService.findByName(principal.getName()).orElseThrow();
            List<Criptocurrency> usercriptocurrencies = user.getCriptocurrencies();
            int i = 0; //counter size user's friends list
            List<User> friendListFinal = new ArrayList<User>();
            List<User> friendList = user.getFriends();

            while (i<friendList.size()){
                User friend = friendList.get(i);
                List<Criptocurrency> listFriendCripto = friend.getCriptocurrencies();
                int j = 0; //counter size friend's cryptocurrency list
                int cont = 0;
                while (j<usercriptocurrencies.size()){
                    for(int k = 0; k < listFriendCripto.size(); k++) {
                        if (usercriptocurrencies.get(j).equals(listFriendCripto.get(k))) {
                            listFriendCripto.remove(listFriendCripto.get(k));
                            cont++;
                        }
                    }
                j++;
                }
            i ++;
                if (cont>=2 && !listFriendCripto.isEmpty()){
                    model.addAttribute("recommendedCripto", listFriendCripto);
                    friendListFinal.add(friend);
                }
            }
            for (int w = 0; w < user.getCriptocurrencies().size(); w++){
                for (int z = 0; z < criptocurrencies.size(); z++){
                    Criptocurrency criptocurrency = user.getCriptocurrencies().get(w);
                    if (criptocurrency.equals(criptocurrencies.get(z))){
                        criptocurrencies.get(z).setImage("images/star.svg");
                    } else {
                        if(!criptocurrencies.get(z).getImage().equals("images/star.svg")){
                            criptocurrencies.get(z).setImage("images/starEmpty.svg");
                        }

                    }
                }
            }
            model.addAttribute("friends", friendListFinal);
        }
        return "criptomonedas";
    }

    @GetMapping("/favorite_cryptocurrencies")
    public String favorites(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if(principal != null) {
            User user = userService.findByName(principal.getName()).orElseThrow();
            List<Criptocurrency> criptocurrenciesFav = user.getCriptocurrencies();
            model.addAttribute("criptocurrenciesFav", criptocurrenciesFav);
        }
        return "cript_favoritas";
    }

    @GetMapping("/users")
    public String users(Model model, HttpServletRequest request){
        List<User> users = userService.selectAll();
        model.addAttribute("users", users);
        return "userList";
    }

    @GetMapping("/Bitcoin")
    public String bitcoin(Model model) throws Exception{
        List<Double> graphData = new ArrayList<>();
        JSONArray data = bitcoinService.fetchCoinPrices("https://api.coingecko.com/api/v3/coins/bitcoin/market_chart?vs_currency=usd&days=9&interval=daily");

        for(int i = 0; i < data.length(); i++){
            JSONArray dataList = (JSONArray) data.get(i);
            Double d = (Double) dataList.get(1);
            graphData.add(d);
        }
        String finalData = graphData.toString();
        model.addAttribute("chartData", finalData);
        return "Bitcoin";
    }

    @GetMapping("/Dogecoin")
    public String doge(Model model) {
        return "Dogecoin";
    }

    @GetMapping("/Badger")
    public String badger(Model model) {
        return "Badger";
    }
}

