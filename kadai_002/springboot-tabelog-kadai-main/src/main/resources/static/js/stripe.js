const stripe = Stripe('pk_test_51PiVQRGc1SCcyjfLyeRzX3XxMVUdHfAyoVUF1ldt1u9fMb4YE6xoDE4TVtbd6dZcxyzWbxf2NJ0I7v1nMjSQFQJa00SP9RVTUw');
const paymentButton = document.querySelector('#paymentButton');

paymentButton.addEventListener('click', () => {
  stripe.redirectToCheckout({
    sessionId: 'sessionId'
  })
});
