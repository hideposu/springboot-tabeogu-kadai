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

    public String createStripeSession(HttpServletRequest httpServletRequest) {
        Stripe.apiKey = stripeApiKey;
        
        SessionCreateParams params = SessionCreateParams.builder()
            .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
            .addLineItem(
                SessionCreateParams.LineItem.builder()
                    .setPrice("price_1PxpwbGc1SCcyjfLgOqByA5x")
                    .setQuantity(1L)
                    .build()
            )
            .setSuccessUrl("https://your-success-url?session_id={CHECKOUT_SESSION_ID}")
            .setCancelUrl("https://your-cancel-url")
            .build();

        try {
            Session session = Session.create(params);
            System.out.println("Session ID: " + session.getId()); // セッションIDをログに出力
            return session.getId();
        } catch (StripeException e) {
            e.printStackTrace();
            return "";
        }
    }
}