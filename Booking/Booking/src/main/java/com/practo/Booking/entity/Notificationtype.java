package com.practo.Booking.entity;

public enum Notificationtype {

	email("email"),
    message("message"),
    push("push");
    

    String value;

	Notificationtype(String value) {
        this.value =value;
    }
}
