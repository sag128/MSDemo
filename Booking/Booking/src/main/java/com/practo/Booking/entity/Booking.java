package com.practo.Booking.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.practo.Booking.enums.Rating;
import com.practo.Booking.enums.RefundStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "booking")
public class Booking {


    @Id
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // todo : actual user object from another app
    @Column(name = "user_id")
    private long userId;

    @Column(name = "start_time")
    private LocalDateTime startTime;



    @Column(name = "actual_start_time")
    private LocalDateTime actualStartTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;


    @Column(name = "actual_end_time")
    private LocalDateTime actualEndTime;


    private Rating rating;

    @Column(name = "total_fees",columnDefinition = "decimal(19,4)")
    private float totalFees;


    @Column(name = "actual_total_fees",columnDefinition = "decimal(19,4)")
    private float actualTotalFees;


    // todo : actual object from different MS
    @Column(name = "doctor_id")
    private long doctorId;

    private String review;

    @Column(name = "reason_for_consultation")
    private String reasonForConsultation;


    // todo : actual user id from another MS
    @Column(name = "referred_by")
    private long referredBy;

    @Column(name = "refund_status")
    private RefundStatus refundStatus;

    @Column(name = "refund_amount",columnDefinition = "decimal(19,4)")
    private float refundAmount;


    @Column(name = "reason_for_refund")
    private String reasonForRefund;


}
