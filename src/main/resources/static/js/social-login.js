document.addEventListener("DOMContentLoaded", function () {
    initSocialEvents(this);
});

function initSocialEvents(document) {
    document.querySelectorAll(".btn-social-login").forEach(
        element => {
            element.addEventListener("click", function () {
                const socialType = this.getAttribute("social-type");
                document.location = "/oauth2/authorization/" + socialType;
            });
        }
    );
}