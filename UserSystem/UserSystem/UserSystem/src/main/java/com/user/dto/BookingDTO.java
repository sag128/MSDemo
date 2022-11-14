package com.user.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;


@Data
@ToString
public class BookingDTO {


    private long id;
    private long userId;
    private LocalDateTime startTime;
    private LocalDateTime actualStartTime;

    private LocalDateTime endTime;

    private LocalDateTime actualEndTime;
    private Rating rating;
    private float totalFees;
    private float actualTotalFees;
    private long doctorId;
    private String review;
    private String reasonForConsultation;
    private long referredBy;
    private RefundStatus refundStatus;
    private float refundAmount;
    private String reasonForRefund;


}
