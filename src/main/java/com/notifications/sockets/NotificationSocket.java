package com.notifications.sockets;

import io.vertx.core.impl.ConcurrentHashSet;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.Set;

//istanziata all'avvio di quarkus
@ApplicationScoped
@ServerEndpoint("/notifications")
public class NotificationSocket {
    Set<Session> sessionPool = new ConcurrentHashSet<>();
    //thread safe: può essere usato in regime di concorrenza, onOpen devo mettere la sessione nella sessionPool
    @OnMessage
    public void onMessageReceived(String message, Session session){
        System.out.println("the message is: " + message);
        //per inviare messaggi, di tipo testo allora sendText
        session.getAsyncRemote().sendText("I received a " + message);
        sessionPool.stream().forEach(session1 -> {
            session1.getAsyncRemote().sendText("Sending this " + message + "to everyone listening");
        });
    }

    @OnOpen
    //on connection open
    public void onOpen(Session session){
        System.out.println("Someone connected");
        sessionPool.add(session);
        session.getAsyncRemote().sendText("message sent");
    }
    @OnClose
    public void onClose(Session session){
        sessionPool.remove(session);
        System.out.println("Someone disconnected");
    }



    //quando mi connetto al websocket devo tenermi un elenco di sessioni cosi da poter mandare messaggi
}
