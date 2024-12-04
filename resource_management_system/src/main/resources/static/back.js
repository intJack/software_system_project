document.addEventListener('DOMContentLoaded', function () {
    document.body.addEventListener('click', function (event) {
        if (event.target.classList.contains('backButton')) {
            event.preventDefault();
            window.history.back();
        }
    });
});