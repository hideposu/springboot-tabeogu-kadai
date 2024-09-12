package com.example.springboottabelogkadai.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class StripeService {
	 @Value("${stripe.api-key}")
     private String stripeApiKey;
	
	public String careateStripeSession(HttpServletRequest httpServletRequest) {
    Stripe.apiKey = stripeApiKey;
    String requestUrl = new String(httpServletRequest.getRequestURL());
        
    SessionCreateParams params =
    		  SessionCreateParams.builder()
    		    .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
    		    .addLineItem(
    		      SessionCreateParams.LineItem.builder()
    		        .setPrice("price_1PxpwbGc1SCcyjfLgOqByA5x")
    		        .setQuantity(1L)
    		        .build()
    		    )
    		    .setUiMode(SessionCreateParams.UiMode.EMBEDDED)
    		    .setReturnUrl("https://nagoyameshi12345678910-995116fc38d7.herokuapp.com/subscription/success?session_id={CHECKOUT_SESSION_ID}")
    		    .build();

    try {
        Session session = Session.create(params);
        return session.getId();
    } catch (StripeException e) {
        e.printStackTrace();
        return "";
    }
  }
}