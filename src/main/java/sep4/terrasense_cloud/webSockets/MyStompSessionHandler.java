package sep4.terrasense_cloud.webSockets;

import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;

public class MyStompSessionHandler extends StompSessionHandlerAdapter {

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        session.subscribe("/topic/{deviceId}/up", new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return null;
             //   return LoRaWANDataPacket.class;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
              //  LoRaWANDataPacket dataPacket = (LoRaWANDataPacket) payload;
                // Process the data packet as needed
            }
        });
    }
}
