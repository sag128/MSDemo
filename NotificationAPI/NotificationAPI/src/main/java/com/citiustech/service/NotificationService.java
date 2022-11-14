package com.citiustech.service;

import com.citiustech.controller.dto.NotificationDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citiustech.entity.Notification;
import com.citiustech.repository.GenericRepo;

@Service
public class NotificationService {

	@Autowired
	GenericRepo genericRepo;
	
	@Transactional
   public void sendNotification(NotificationDTO notificationDTO) {
		Notification notification = new Notification();
		BeanUtils.copyProperties(notificationDTO,notification);
		genericRepo.save(notification);
  }

}
