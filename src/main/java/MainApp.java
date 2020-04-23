import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

public class MainApp implements Runnable {


    private Scanner scanner;

    private void startApp() {
        scanner = new Scanner(System.in);
        System.out.println("Wybierz po czym chcesz znaleźć miejsce dla którego wyświetlisz pogodę \n0 - Zakończ działanie \n1 - Nazwa Miasta \n2 - Kod pocztowy");
        Integer name = scanner.nextInt();
        chooseTypeSearching(name);

    }

    private void chooseTypeSearching(Integer typeNumber) {
        switch (typeNumber) {
            case 0:
                break;
            case 1:
                connectByCityName();
                startApp();
                break;
            case 2:
                connectByZipCode();
                startApp();
                break;
        }
    }




    private void connectByCityName() {
        System.out.println("Podaj nazwe miasta:");
        scanner = new Scanner(System.in);
        String name = scanner.next();

        try {
            String response = new HttpService().connect(Config.APP_URL +"q="+ name+ Config.APP_ID + Config.APP_REGION);
            parseJson(response);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
        }


    private void connectByZipCode() {
        System.out.println("Podaj kod pocztowy miasta:");
        scanner = new Scanner(System.in);
        String name = scanner.next();

        try {
            String response = new HttpService().connect(Config.APP_URL +"zip="+ name+",pl"+ Config.APP_ID + Config.APP_REGION);
            parseJson(response);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }



    private void parseJson(String json) {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject jsonArrayEmployees = jsonObject.getJSONObject("main");
            List<Weather> weatherList = new ArrayList<>();
        System.out.println(jsonArrayEmployees.getDouble("temp"));
                Weather miasto = new Weather();
                miasto.setTemp(jsonArrayEmployees.getDouble("temp"));
                miasto.setTemp_max(jsonArrayEmployees.getDouble("temp_max"));
                miasto.setCisnienie(jsonArrayEmployees.getDouble("pressure"));
                miasto.setWidocznosc(jsonObject.getInt("visibility"));
                weatherList.add(miasto);
        JSONObject jsonArrayEmployees1 = jsonObject.getJSONObject("wind");
        miasto.setWiatr(jsonArrayEmployees1.getDouble("speed"));
        JSONArray jsonArrayEmployees2 = jsonObject.getJSONArray("weather");
        for (int i = 0; i < jsonArrayEmployees2.length(); i++) {
            JSONObject one = (JSONObject) jsonArrayEmployees2.get(i);
            miasto.setOpis(one.get("description").toString());
        }
        JSONObject jsonArrayEmployees3 = jsonObject.getJSONObject("clouds");
        miasto.setZachmurzenie(jsonArrayEmployees3.getDouble("all"));
            System.out.println(weatherList);
    }


    @Override
    public void run() {
        startApp();
    }
}
