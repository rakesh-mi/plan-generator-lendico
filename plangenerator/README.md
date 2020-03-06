# Plan Generator

### How to run locally and test

1. Clone the repository.
2. Using the command line, go inside the directory of the repository.
3. Execute `mvn clean spring-boot:run`. If port 8080 is in use, replace the port number in `application.yml`.
4. Test using any REST API testing tools, e.g. cURL or Postman, by POSTing to `localhost:8080/generate-plan` using the example payload below.
If the port number was replaced, use that instead.

### Background

In order to inform borrowers about the final repayment schedule, we need to have pre-calculated repayment plans throughout the lifetime of a loan.

To be able to calculate a repayment plan specific input parameters are necessary:
* duration (number of instalments in months)
* nominal interest rate
* total loan amount ("total principal amount")
* date of disbursement/payout

These four parameters need to be input parameters. The goal is to calculate a repayment plan for an annuity loan. Therefore the amount that
the borrower has to pay back every month, consisting of principal and interest repayments, does not change (the last instalment might be an exception).

The annuity amount has to be derived from three of the input parameters (duration, nominal interest rate, total loan amount) before starting
the plan calculation.

For simplicity, we will have the following assumptions:
* Each month has 30 days
* A year has 360 days

#### Formulas:
1. Initial Annuity (Borrower Payment Amount): http://financeformulas.net/Annuity_Payment_Formula.html
2. Interest = ( (Nominal-Rate * Days in Month * Initial Outstanding Principal) / Days in Year ) / 100
3. Principal = Annuity - Interest (If the calculated principal amount exceeds the initial outstanding principal amount,
take initial initial outstanding principal amount instead.)
4. Borrower Payment Amount (Annuity) = Principal + Interest


### Example payload / request body
```json
{
"loanAmount": "5000",
"nominalRate": "5.0",
"duration": 24,
"startDate": "2018-01-01T00:00:01Z"
}
```

### Example response
```json
{
[
{
"borrowerPaymentAmount": "219.36",
"date": "2018-01-01T00:00:00Z",
"initialOutstandingPrincipal": "5000.00",
"interest": "20.83",
"principal": "198.53",
"remainingOutstandingPrincipal": "4801.47",
},
{
"borrowerPaymentAmount": "219.36",
"date": "2018-02-01T00:00:00Z",
"initialOutstandingPrincipal": "4801.47",
"interest": "20.01",
"principal": "199.35",
"remainingOutstandingPrincipal": "4602.12",
},
...
{
"borrowerPaymentAmount": "219.28",
"date": "2019-12-01T00:00:00Z",
"initialOutstandingPrincipal": "218.37",
"interest": "0.91",
"principal": "218.37",
"remainingOutstandingPrincipal": "0",
}
]
}

```