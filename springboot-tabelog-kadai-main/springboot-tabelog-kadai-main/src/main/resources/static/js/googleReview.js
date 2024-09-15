document.addEventListener('DOMContentLoaded', (event) => {
    const googleReviewButton = document.getElementById('googleReviewButton');
    if (googleReviewButton) {
        googleReviewButton.addEventListener('click', () => {
            const shopName = document.getElementById('shop-name').innerText;
            const googleSearchUrl = `https://www.google.com/search?q=${encodeURIComponent(shopName)}+review`;
            window.open(googleSearchUrl, '_blank');
        });
    }
});