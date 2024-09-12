const stripe = Stripe('pk_test_51OeDp6D9OrnhbmBlJEQ5JnKcILKRjZyM7UqsqNtfHwlypPiCsj3lzfzhQimv4D0nrI9ZL1ihdGcMYfMiCqMxmkfq00V1qqXsG5');
const paymentButton = document.querySelector('#paymentButton');

paymentButton.addEventListener('click', () => {
  stripe.redirectToCheckout({
    sessionId: sessionId
  })
});
