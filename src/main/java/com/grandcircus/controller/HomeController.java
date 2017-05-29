package com.grandcircus.controller;

import com.grandcircus.models.SelectionEntity;
import com.grandcircus.models.UsersEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.uber.sdk.rides.client.ServerTokenSession;
import com.uber.sdk.rides.client.SessionConfiguration;
import com.uber.sdk.rides.client.UberRidesApi;
import com.uber.sdk.rides.client.model.*;
import com.uber.sdk.rides.client.services.RidesService;
import com.lyft.networking.ApiConfig;
import com.lyft.networking.LyftApiFactory;
import com.lyft.networking.apiObjects.*;
import com.lyft.networking.apis.LyftPublicApi;
import retrofit2.Call;
import retrofit2.Response;

@Controller
public class HomeController {
    @Value("${GoogleAPI.key}")
    private String GoogleAPIKey;
    @Value("${UberClientId.key}")
    private String UberClientIdKey;
    @Value("${UberServerToken.key}")
    private String UberServerTokenKey;
    @Value("${LyftClientId.key}")
    private String LyftClientIdKey;
    @Value("${LyftClientToken.key}")
    private String LyftClientTokenKey;

    //@RequestMapping ("/")
    //public String displayForm() {
    //    return "userWelcome";}

    @RequestMapping ("/")
    public String displayForm(Model model) {
        model.addAttribute ( "GAPIKey", GoogleAPIKey);
        model.addAttribute ( "temp", "test");
        return "userWelcome";
    }

    @RequestMapping("listUsers")
    public ModelAndView listUsers() {
        Configuration cfg = new Configuration ().configure ("src/main/resources/hibernate.cfg.xml");
        SessionFactory sessionFact = cfg.buildSessionFactory ();
        Session usersSession = sessionFact.openSession ();
        usersSession.beginTransaction ();

        Criteria c = usersSession.createCriteria(UsersEntity.class);
        ArrayList<UsersEntity> usersArrayList = (ArrayList <UsersEntity>) c.list ();
        return new ModelAndView ( "welcome2", "cList", usersArrayList );
    }


    @RequestMapping(value = "/finishSelection", method=RequestMethod.POST)
    public String finishSelection (@RequestParam("price") String price, @RequestParam("time") int time){

        //ADDED TO TEST DATABASE
        Configuration cfg2 = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFact2 = cfg2.buildSessionFactory();
        Session session3 = sessionFact2.openSession();
        Transaction tx = session3.beginTransaction();
        SelectionEntity newSelection = new SelectionEntity();
        newSelection.setPrice(price);
        newSelection.setTimeDistance(time);
        session3.save(newSelection);
        tx.commit();
        session3.close();
        //ADDED TO TEST DATABASE

    return "finishSelection";
    }



    @RequestMapping(value = "/ridecompare", method = RequestMethod.POST)
    public String ridecompare(Model model, @RequestParam("streetNum") String street,
                              @RequestParam("routee")String routeM,
                              @RequestParam("local")String loc,
                              @RequestParam("nope")String state,
                              @RequestParam("postal")String post,
                              @RequestParam("count")String count,
                              @RequestParam("strtN") String strt,
                              @RequestParam("rou")String rout,
                              @RequestParam("loca")String local,
                              @RequestParam("yep") String state1,
                              @RequestParam("posta")String postal,
                              @RequestParam("userCountry")String userCount,
                              @RequestParam("capSeat") String choice) {
        String fromAdd= street + " " + routeM + " " + loc + " " + state+ " " + post + " "+count;
        String toAdd = strt + " " + rout + " " + local + " " + state1 + " " + postal + " " + userCount;

        model.addAttribute("fromAdd", fromAdd);
        model.addAttribute("toAdd", toAdd);

        List <Product> results;
        List <PriceEstimate> prices;
        List <TimeEstimate> duration;
        String id = "";

        //ADDED TO TEST DATABASE
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFact = cfg.buildSessionFactory();
        Session session2 = sessionFact.openSession();
        Transaction tx = session2.beginTransaction();
        SelectionEntity newSelection = new SelectionEntity();
        newSelection.setFromAddress(fromAdd); 
        newSelection.setToAddress(toAdd);
        session2.save(newSelection);
        tx.commit();
        session2.close();
        //ADDED TO TEST DATABASE


        try {

            Coordinates results12 = GoogleGeocode.geocode(fromAdd);
            float googleLat = (float)results12.latitude;
            float googleLong = (float)results12.longitude;

            Coordinates results13 = GoogleGeocode.geocode(toAdd);
            float googleLat2 = (float)results13.latitude;
            float googleLong2 = (float)results13.longitude;

            //Uber AppConfig
            SessionConfiguration config = new SessionConfiguration.Builder ()
                    .setClientId (UberClientIdKey)
                    .setServerToken (UberServerTokenKey)
                    .build ();
            ServerTokenSession session = new ServerTokenSession ( config );

            UberRidesApi ride = UberRidesApi.with ( session ).build ();
            RidesService service = ride.createService ();

            //Lyft AppConfig
            ApiConfig apiConfig = new ApiConfig.Builder()
                    .setClientId(LyftClientIdKey)
                    .setClientToken(LyftClientTokenKey)
                    .build ();
            //Uber ProductType
            Response <ProductsResponse> response = service.getProducts ( googleLat, googleLong ).execute ();
            ProductsResponse products = response.body ();
            results = products.getProducts ();

            //Lyft ProductType
            //WILL ADDRESS WITH STEPHANIE AND REST OF GROUP

            //Uber Price
            Response <PriceEstimatesResponse> respond = service.getPriceEstimates ( googleLat, googleLong,
                    googleLat2, googleLong2 ).execute ();
            PriceEstimatesResponse priceTag = respond.body ();
            prices = priceTag.getPrices ();

            //Lyft Price (Standard and LyftPlus(4+ people)
            String lyftType = "";
            int numSeats = 0;
            String displayPriceMin = "";
            String displayPriceMax = "";

            if (choice.equals("1")) {
                try {
                    LyftPublicApi lyftPublicApi = new LyftApiFactory(apiConfig).getLyftPublicApi();
                    Call<CostEstimateResponse> costEstimateCall = lyftPublicApi.getCosts(results12.latitude, results12.longitude, "lyft", results13.latitude, results13.longitude);
                    Response<CostEstimateResponse> lyftResultsStandard = costEstimateCall.execute();
                    CostEstimateResponse body = lyftResultsStandard.body();
                    List<CostEstimate> pricesLyftStandard = body.cost_estimates;


                    for (CostEstimate costEstimate : body.cost_estimates) { //tried 'prices' rather than 'body' but didn't like....
                        displayPriceMin = ("$" + (String.valueOf(costEstimate.estimated_cost_cents_min / 100)));
                        displayPriceMax = (String.valueOf(costEstimate.estimated_cost_cents_max / 100));
                    }

                    lyftType = "LYFT Standard";
                    numSeats = 4;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            else if (choice.equals("2")) {
                try {
                    LyftPublicApi lyftPublicApi = new LyftApiFactory(apiConfig).getLyftPublicApi();
                    Call<CostEstimateResponse> costEstimateCall = lyftPublicApi.getCosts(results12.latitude, results12.longitude, "lyft_plus", results13.latitude, results13.longitude);
                    Response<CostEstimateResponse> lyftResultsPlus = costEstimateCall.execute();
                    CostEstimateResponse body = lyftResultsPlus.body();
                    List<CostEstimate> pricesLyftPlus = body.cost_estimates;

                    for (CostEstimate costEstimate : body.cost_estimates) { //tried 'prices' rather than 'body' but didn't like....
                        displayPriceMin = ("$" + (String.valueOf(costEstimate.estimated_cost_cents_min / 100)));
                        displayPriceMax = (String.valueOf(costEstimate.estimated_cost_cents_max / 100));
                    }

                    lyftType = "LYFT Plus";
                    numSeats = 6;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //"All Types" is selected, condition will return new jsp page compared to when one type of Lyft is picked
            else {
                String name = "";
                String lyftName = "";
                int numRide = 0;
                int numRides = 0;
                String displayPriceMinStand = "";
                String displayPriceMaxStand = "";
                String displayPriceMinPlus = "";
                String displayPriceMaxPlus = "";
                String lyftStandETA = "";
                String lyftPlusETA = "";
                String lyft1 = "lyft";
                String lyft2 = "lyft_plus";
                try {
                    LyftPublicApi lyftPublicApi = new LyftApiFactory(apiConfig).getLyftPublicApi();
                    Call<CostEstimateResponse> costEstimateCall = lyftPublicApi.getCosts(results12.latitude,
                            results12.longitude, lyft1, results13.latitude, results13.longitude);
                    Response<CostEstimateResponse> lyftResultsStandard = costEstimateCall.execute();
                    CostEstimateResponse body = lyftResultsStandard.body();
                    List<CostEstimate> pricesLyftStandard = body.cost_estimates;


                    for (CostEstimate costEstimate : body.cost_estimates) { //tried 'prices' rather than 'body' but didn't like....
                        displayPriceMinStand = ("$" + (String.valueOf(costEstimate.estimated_cost_cents_min / 100)));
                        displayPriceMaxStand = (String.valueOf(costEstimate.estimated_cost_cents_max / 100));
                    }

                    name = "LYFT Standard";
                    numRide = 4;


                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {

                    LyftPublicApi lyftPublicApi = new LyftApiFactory(apiConfig).getLyftPublicApi();
                    Call<EtaEstimateResponse> etaCall = lyftPublicApi.getEtas(results12.latitude,
                            results12.longitude, "lyft");

                    Response<EtaEstimateResponse> lyftDriverEta = etaCall.execute();
                    EtaEstimateResponse body = lyftDriverEta.body();
                    List<Eta> lyftTime = body.eta_estimates;

                    for (Eta eta : body.eta_estimates) {
                        lyftStandETA = (String.valueOf(eta.eta_seconds / 60));
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
                //LYFT Plus
                try {
                    LyftPublicApi lyftPublicApi = new LyftApiFactory(apiConfig).getLyftPublicApi();
                    Call<CostEstimateResponse> costEstimateCall = lyftPublicApi.getCosts(results12.latitude,
                            results12.longitude, lyft2, results13.latitude, results13.longitude);
                    Response<CostEstimateResponse> lyftResultsPlus = costEstimateCall.execute();
                    CostEstimateResponse body = lyftResultsPlus.body();
                    List<CostEstimate> pricesLyftPlus = body.cost_estimates;


                    for (CostEstimate costEstimate : body.cost_estimates) { //tried 'prices' rather than 'body' but didn't like....
                        displayPriceMinPlus = ("$" + (String.valueOf(costEstimate.estimated_cost_cents_min / 100)));
                        displayPriceMaxPlus = (String.valueOf(costEstimate.estimated_cost_cents_max / 100));
                    }

                    lyftName = "LYFT Plus";
                    numRides = 6;

                    try {

                        LyftPublicApi lyftPublicApi2 = new LyftApiFactory(apiConfig).getLyftPublicApi();
                        Call<EtaEstimateResponse> etaCall = lyftPublicApi2.getEtas(results12.latitude,
                                results12.longitude, "lyft_plus");

                        Response<EtaEstimateResponse> lyftDriverEta = etaCall.execute();
                        EtaEstimateResponse body2 = lyftDriverEta.body();
                        List<Eta> lyftTime = body2.eta_estimates;


                        for (Eta eta : body2.eta_estimates) {
                            lyftPlusETA = (String.valueOf(eta.eta_seconds / 60));
                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    model.addAttribute("displayStandard", name);
                    model.addAttribute("riders", numRide);
                    model.addAttribute("priceMinStand", displayPriceMinStand);
                    model.addAttribute("priceMaxStand", displayPriceMaxStand);
                    model.addAttribute("displayPlus", lyftName);
                    model.addAttribute("rideCap", numRides);
                    model.addAttribute("displayPriceMinPlus", displayPriceMinPlus);
                    model.addAttribute("displayPriceMaxPlus", displayPriceMaxPlus);
                    model.addAttribute("standardETA", lyftStandETA);
                    model.addAttribute("plusETA", lyftPlusETA);

                    return "allproducts";
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            model.addAttribute("typeOfLyft", lyftType);
            model.addAttribute("capacity", numSeats);
            model.addAttribute("displayPriceMin", displayPriceMin);
            model.addAttribute("displayPriceMax", displayPriceMax);



            //Uber Time
            Response <TimeEstimatesResponse> responseTime = service.getPickupTimeEstimate ( googleLat, googleLong,
                    id ).execute ();

            TimeEstimatesResponse time = responseTime.body ();
            duration = time.getTimes ();

            //Lyft Time
            try {
                String displayTime = "";

                LyftPublicApi lyftPublicApi = new LyftApiFactory(apiConfig).getLyftPublicApi();
                Call<EtaEstimateResponse> etaCall = lyftPublicApi.getEtas(results12.latitude, results12.longitude,
                        null);


                Response<EtaEstimateResponse> lyftDriverEta = etaCall.execute();
                EtaEstimateResponse body = lyftDriverEta.body();
                List<Eta> lyftTime = body.eta_estimates;


                for (Eta eta : body.eta_estimates) {
                    displayTime = (String.valueOf(eta.eta_seconds / 60));
                }

                model.addAttribute("driverETA", displayTime);


            } catch (IOException e) {
                e.printStackTrace();
            }


            //Read Uber Data
            int numRider = Integer.parseInt(choice);

            String displayName = "";
            String priceEst = "";
            int cap = 0;
            int seconds, eta = 0;
            for (int x = 0; x < results.size(); x++){
                if (results.get(x).getCapacity() == numRider){
                    displayName = results.get ( x ).getDisplayName ();;
                    cap = results.get ( x ).getCapacity ();
                    priceEst = prices.get ( x ).getEstimate ();
                    seconds = duration.get ( x ).getEstimate ();
                    eta = (seconds % 3600) / 60;
                }
                //FIXME
                else {
                    model.addAttribute("uber", results);
                    model.addAttribute("uber", prices);
                    model.addAttribute("uber", duration);
                    return "allproducts";
                }
            }



            model.addAttribute ( "product", displayName );
            model.addAttribute ( "cap", cap );
            model.addAttribute ( "price", priceEst );
            model.addAttribute ( "time", eta );

        } catch (IOException e) {
            e.printStackTrace ();
            //} catch (JSONException e) {
            //    e.printStackTrace();
        }
        return "ridecompare";
    }
}