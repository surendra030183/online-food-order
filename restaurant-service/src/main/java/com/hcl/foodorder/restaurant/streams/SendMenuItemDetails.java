package com.hcl.foodorder.restaurant.streams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;

import com.hcl.foodorder.domain.events.MenuItemEvent;


@Configuration
public class SendMenuItemDetails {
	private static final Logger logger = LoggerFactory.getLogger(SendMenuItemDetails.class);
	
	@Autowired
	private MenuItemStream menuItemStream;

	public void sendMessage(final MenuItemEvent menuItemEvent) {
		logger.info("Invoked sendMessage()");
		MessageChannel messageChannel = menuItemStream.sendEmployeeMessage();
		messageChannel.send(MessageBuilder.withPayload(menuItemEvent)
				.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());
	}
}
