package sep4.terrasense_cloud.webSockets;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import sep4.terrasense_cloud.model.Alert;
import sep4.terrasense_cloud.model.Reading;
import sep4.terrasense_cloud.model.Telegram;
import sep4.terrasense_cloud.model.Terrarium;
import sep4.terrasense_cloud.database.repository.TerrariumRepository;
import sep4.terrasense_cloud.database.repository.AlertRepository;
import sep4.terrasense_cloud.service.services.ReadingsService;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.CompletableFuture;

@Component
public class WebSocketClient implements WebSocket.Listener {
    private WebSocket server = null;
    private ReadingsService readingsService;
    @Autowired
    private TerrariumRepository terrariumRepository;

    @Autowired
    private AlertRepository alertRepository;

    // Send down-link message to device
    // Must be in JSON format according to https://github.com/ihavn/IoT_Semester_project/blob/master/LORA_NETWORK_SERVER.md
    public void sendDownLink(String jsonTelegram) {
        server.sendText(jsonTelegram, true);
    }

    // E.g. url: "wss://iotnet.teracom.dk/app?token=??????????????????????????????????????????????="
    // Substitute ????????????????? with the token you have been given
@Autowired
    public WebSocketClient(ReadingsService readingsService) {
        this.readingsService = readingsService;
        HttpClient client = HttpClient.newHttpClient();
        CompletableFuture<WebSocket> ws = client.newWebSocketBuilder()
                .buildAsync(URI.create("wss://iotnet.teracom.dk/app?token=vnoUhAAAABFpb3RuZXQudGVyYWNvbS5ka2v2Q_l1Fej_TK0VFKubjJQ="), this);

        server = ws.join();
    }

    // onOpen()
    public void onOpen(WebSocket webSocket) {
        // This WebSocket will invoke onText, onBinary, onPing, onPong or onClose methods on the associated listener (i.e. receive methods) up to n more times
        webSocket.request(1);
        System.out.println("WebSocket Listener has been opened for requests.");
    }

    // onError()
    public void onError(WebSocket webSocket, Throwable error) {
        System.out.println("A " + error.getCause() + " exception was thrown.");
        System.out.println("Message: " + error.getLocalizedMessage());
        webSocket.abort();
    }

    // onClose()
    public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
        System.out.println("WebSocket closed!");
        System.out.println("Status: " + statusCode + " Reason: " + reason);
        return CompletableFuture.completedFuture("onClose() completed.").thenAccept(System.out::println);
    }

    // onPing()
    public CompletionStage<?> onPing(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        System.out.println("Ping: Client ---> Server");
        System.out.println(message.asCharBuffer().toString());
        return CompletableFuture.completedFuture("Ping completed.").thenAccept(System.out::println);
    }

    // onPong()
    public CompletionStage<?> onPong(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        System.out.println("Pong: Client ---> Server");
        System.out.println(message.asCharBuffer().toString());
        return CompletableFuture.completedFuture("Pong completed.").thenAccept(System.out::println);
    }

    // onText()
    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
        String indented = null;
        try {
            indented = (new JSONObject(data.toString())).toString(4);
            readingsService.addReading(Telegram.getValues(new JSONObject(data.toString())));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        System.out.println(indented);

        webSocket.request(1);
        return CompletableFuture.completedFuture("onText() completed.").thenAccept(System.out::println);
    }

    private void checkAndSendAlerts(Reading reading) {
        boolean output = false;
        try {
            Terrarium terrarium = terrariumRepository.getReferenceById(1L);
            if (reading.getCO2() > terrarium.getMaxCO2()) {
                alertRepository.save(new Alert("CO2", LocalDateTime.now(), String.valueOf(reading.getCO2()), "high", terrarium));
                output = true;
            } else if (reading.getCO2() < terrarium.getMinCO2()) {
                alertRepository.save(new Alert("CO2", LocalDateTime.now(), String.valueOf(reading.getCO2()), "high", terrarium));
                output = true;
            }
            if (reading.getHumidity() > terrarium.getMaxHumidity()) {
                alertRepository.save(new Alert("Humidity", LocalDateTime.now(), String.valueOf(reading.getHumidity()), "high", terrarium));
                output = true;
            } else if (reading.getHumidity() < terrarium.getMinHumidity()) {
                alertRepository.save(new Alert("Humidity", LocalDateTime.now(), String.valueOf(reading.getHumidity()), "high", terrarium));
                output = true;
            }
            if (reading.getTemperature() > terrarium.getMaxTemperature()) {
                alertRepository.save(new Alert("Temperature", LocalDateTime.now(), String.valueOf(reading.getTemperature()), "high", terrarium));
                output = true;
            } else if (reading.getTemperature() < terrarium.getMaxTemperature()) {
                alertRepository.save(new Alert("Temperature", LocalDateTime.now(), String.valueOf(reading.getTemperature()), "high", terrarium));
                output = true;
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

        if (output) {
            MessageSendingOperations<Object> messagingTemplate = null;
            messagingTemplate.convertAndSend("/topic/alerts", "New alert triggered!");
        }


    }
}
