package main.java.storyArch.api;

/**
 * This is the API for the payment gateway
 * This is used for the payment feature.
 * This is used for the premium subscription feature.
 */
public class PaymentAPI {
    // Redirection to payment gateway
    public void redirectToPaymentGateway() {
        System.out.println("Redirecting to payment gateway");
        System.out.println("Payment successful");
        System.out.println("Thank you for your supporting us");
        System.out.println("*********************************");
        premiumSubscription();
    }

    private void premiumSubscription() {
        System.out.println("*********************************");
        System.out.println("You have successfully subscribed to premium subscription");
        System.out.println("You can now access all the premium features");
        System.out.println("""
                The premium subscriptions features are: +
                1. Add support for teams and unlimited Projects
                2. Remind users of upcoming subscription deadlines
                3. Automatic renewal of subscriptions""");
    }

}
