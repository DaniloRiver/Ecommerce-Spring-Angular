package com.danilodev.ecommerce.backend.infraestructure.rest;

import com.danilodev.ecommerce.backend.domain.model.DataPayment;
import com.danilodev.ecommerce.backend.domain.model.UrlPaypalResponse;
import com.danilodev.ecommerce.backend.infraestructure.service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/payment")
public class PaypalController {
    private final PaypalService paypalService;
    private final String SUCCESS_URL = "http://localhost:8085/api/v1/payment/success";
    private final String CANCEL_URL = "http://localhost:8085/api/v1/payment/cancel";




    @PostMapping
    public UrlPaypalResponse createPayment(@RequestBody DataPayment dataPayment){
        try {
            Payment payment = paypalService.createPayment(
                    Double.valueOf(dataPayment.getAmount()),
                    dataPayment.getCurrency(),
                    dataPayment.getMethod(),
                    "SALE",
                    dataPayment.getDescription(),
                    CANCEL_URL,
                    SUCCESS_URL
            );
            for(Links links:payment.getLinks()){
                if(links.getRel().equals("approval_url")){
                    return new UrlPaypalResponse( links.getHref());
                }
            }

        } catch (PayPalRESTException e) {
            throw new RuntimeException(e);
        }
        return new UrlPaypalResponse("http://localhost:4200");

    }

    @GetMapping("/success")
    public RedirectView paymentSuccess(
            @RequestParam("paymentId") String paymentId,
            @RequestParam("PayerID") String payerId
    ){
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if(payment.getState().equals("approved")){
                return new RedirectView("http://localhost:4200/payment/success");
//                return new RedirectView("http://localhost:4200");

            }
        } catch (PayPalRESTException e) {
            throw new RuntimeException(e);
        }
        return new RedirectView("http://localhost:4200");
    }

    @GetMapping("/cancel")
    public RedirectView paymentCancel(){
        return new RedirectView("http://localhost:4200");
    }























}
