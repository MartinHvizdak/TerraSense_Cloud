package sep4.terrasense_cloud.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import sep4.terrasense_cloud.webSockets.MyStompSessionHandler;

import java.util.Collections;
import java.util.concurrent.ExecutionException;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${spring.websocket.url}")
    private String webSocketUrl;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket").setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Bean
    public WebSocketClient webSocketClient() {
        StandardWebSocketClient webSocketClient = new StandardWebSocketClient();
        return new SockJsClient(Collections.singletonList(new WebSocketTransport(webSocketClient)));
    }

    @Bean
    public WebSocketStompClient stompClient() {
        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient());
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        return stompClient;
    }

    @Bean
    public StompSessionHandler sessionHandler() {
        return new MyStompSessionHandler();
    }

    @Bean
    public StompSession stompSession() throws InterruptedException, ExecutionException {
        return stompClient().connect(webSocketUrl, sessionHandler()).get();
    }
}
