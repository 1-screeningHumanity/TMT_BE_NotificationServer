package com.notificationserver.global.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import java.io.IOException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class FcmConfig {

	//로컬 환경
//	String fcmConfigUrl = "fcmStockProjectKey.json";
	//배포 환경
	String fcmConfigUrl = "app/resources/fcmStockProjectKey.json";

	@Bean
	FirebaseMessaging firebaseMessaging() throws IOException {
		GoogleCredentials googleCredentials = GoogleCredentials.
				fromStream(new ClassPathResource(fcmConfigUrl).getInputStream());

		FirebaseOptions firebaseOptions = FirebaseOptions.builder()
				.setCredentials(googleCredentials)
				.build();

		FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "stockProject");
		return FirebaseMessaging.getInstance(app);
	}
}
