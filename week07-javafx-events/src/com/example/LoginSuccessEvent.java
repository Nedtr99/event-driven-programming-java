// ============================================================
// LoginSuccessEvent — Activity 4: Custom Event Class
// ============================================================
// Defines a custom event type for demonstrating decoupled
// event handling in JavaFX applications.
// ============================================================
package com.example;

import javafx.event.Event;
import javafx.event.EventType;

public class LoginSuccessEvent extends Event {

    public static final EventType<LoginSuccessEvent> LOGIN_SUCCESS =
            new EventType<>(Event.ANY, "LOGIN_SUCCESS");

    private final String username;

    public LoginSuccessEvent(String username) {
        super(LOGIN_SUCCESS);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
