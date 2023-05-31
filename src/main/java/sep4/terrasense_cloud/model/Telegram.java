package sep4.terrasense_cloud.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.catalina.authenticator.Constants;
import org.json.JSONException;
import org.json.JSONObject;

public class Telegram {


    private String cmd;
    @JsonProperty("EUI")
    private String EUI;
    private int port;
    private boolean confirmed;
    private String data;

    public Telegram(String eui, int port, boolean confirmed, String data) {
        this.cmd = "tx";
        this.EUI = eui;
        this.port = port;
        this.confirmed = confirmed;
        this.data = data;
    }

    public String getCmd() {
        return cmd;
    }

    public String getEUI() {
        return EUI;
    }

    public void setEUI(String EUI) {
        this.EUI = EUI;
    }

    public int getPort() {
        return port;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public String getData() {
        return data;
    }

    public String getJson()

    {
        return "{" + "\"cmd\"=\"" + cmd + '\"' + ", \"EUI\"=\"" + EUI + '\"' + ", \"port\"=" + port
                + ", \"confirmed\"=" + confirmed + ", \"data\"=\"" + data + '\"' + '}';
    }
    @Override
    public String toString() {
        return "{" +
                "cmd='" + cmd + '\'' +
                ", EUI='" + EUI + '\'' +
                ", port=" + port +
                ", confirmed=" + confirmed +
                ", data='" + data + '\'' +
                '}';
    }
    public static Reading getValues(JSONObject payload){

        try {
            String data = payload.getString("data");
            int hum = Integer.parseInt(data.substring(0,4), 16);
            double humidity= (double)hum/100;
            int temp = Integer.parseInt(data.substring(4,8), 16);
            double temperature=(double)temp/10;
            int co2 = Integer.parseInt(data.substring(8,12), 16);
            return new Reading(temperature,humidity,co2);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
}
