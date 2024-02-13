package com.service.operationService;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import org.springframework.stereotype.Controller;



@Controller
public class WebSocketController {

    @MessageMapping("/sendMessage")
    @SendTo("/operations")
    public String sendMessage(String message) {
        return "Mensaje recibido en el servidor: " + message;
    }

}
