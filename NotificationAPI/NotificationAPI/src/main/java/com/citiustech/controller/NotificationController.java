package com.citiustech.controller;

import com.citiustech.controller.dto.NotificationDTO;
import com.citiustech.controller.dto.NotificationResponseDTO;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.citiustech.entity.Notification;
import com.citiustech.service.NotificationService;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestController
@RefreshScope
public class NotificationController {
    
	@Autowired
	NotificationService notificationService;
	
	@PostMapping("/sendNotification")
	public ResponseEntity<NotificationResponseDTO> notify(@RequestBody NotificationDTO notificationDTO) {
		NotificationResponseDTO notificationResponseDTO = new NotificationResponseDTO();
		try {
			notificationService.sendNotification(notificationDTO);
			notificationResponseDTO.setStatus(HttpStatus.OK);
			notificationResponseDTO.setMessage("Notification Sent Successfully");
		}
		catch (Exception e) {
			notificationResponseDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			notificationResponseDTO.setMessage("Something went wrong, Please try again");
		}
		return new ResponseEntity<>(notificationResponseDTO, notificationResponseDTO.getStatus());
	}
}
