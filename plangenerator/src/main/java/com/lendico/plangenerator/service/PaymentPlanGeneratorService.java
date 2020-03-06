package com.lendico.plangenerator.service;


import com.lendico.plangenerator.domain.PaymentPerMonth;
import com.lendico.plangenerator.model.Request;
import com.lendico.plangenerator.model.Response;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentPlanGeneratorService {

    public List<Response> generatePaymentPlan(final Request request) {
        request.validate();
        final List<PaymentPerMonth> paymentPerMonthList = generatePaymentPlanList(request);
        return generateResponseList(paymentPerMonthList);
    }

    private List<PaymentPerMonth> generatePaymentPlanList(final Request request) {

        final List<PaymentPerMonth> paymentPerMonthList = new ArrayList<>();
        paymentPerMonthList.add(firstMonth(request));
        paymentPerMonthList.addAll(remainingMonths(request, paymentPerMonthList.get(0)));
        return paymentPerMonthList;
    }

    private List<PaymentPerMonth> remainingMonths(final Request request, PaymentPerMonth previousPaymentPerMonth) {

        final List<PaymentPerMonth> paymentPerMonthList = new ArrayList<>();

        for (int counter = 1; counter < request.getDuration(); counter++) {
            previousPaymentPerMonth = new PaymentPerMonth(request, previousPaymentPerMonth, counter);
            paymentPerMonthList.add(previousPaymentPerMonth);
        }

        return paymentPerMonthList;
    }

    private PaymentPerMonth firstMonth(final Request request) {
        return new PaymentPerMonth(request);
    }

    private List<Response> generateResponseList(final List<PaymentPerMonth> paymentPerMonthList) {

        final List<Response> responseList = new ArrayList<>();

        for (final PaymentPerMonth paymentPerMonth : paymentPerMonthList) {
            final Response response = new Response(paymentPerMonth);
            responseList.add(response);
        }

        return responseList;
    }
}
