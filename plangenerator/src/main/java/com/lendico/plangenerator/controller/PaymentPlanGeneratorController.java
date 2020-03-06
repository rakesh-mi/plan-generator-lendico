package com.lendico.plangenerator.controller;


import com.lendico.plangenerator.model.Request;
import com.lendico.plangenerator.model.Response;
import com.lendico.plangenerator.service.PaymentPlanGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "generate-plan")
public class PaymentPlanGeneratorController {

    @Autowired
    private PaymentPlanGeneratorService paymentPlanGeneratorService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<Response> generatePaymentPlan(@RequestBody final Request request) {
        return paymentPlanGeneratorService.generatePaymentPlan(request);
    }
}