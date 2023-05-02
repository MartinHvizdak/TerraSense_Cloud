package sep4.terrasense_cloud.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Telegram {


    public Telegram() {
    }


    public static Reading getValues(JSONObject payload){

        try {
            String data = payload.getString("data");
            int hum = Integer.parseInt(data.substring(0,4), 16);
            double humidity= (double)hum/100;
            int temp = Integer.parseInt(data.substring(4,8), 16);
            double temperature=(double)temp/100;
            int co2 = Integer.parseInt(data.substring(8,12), 16);
            return new Reading(temperature,humidity,co2);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
}
