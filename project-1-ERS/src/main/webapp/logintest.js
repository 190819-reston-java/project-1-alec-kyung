'use strict';


function validate() {

}
const LOGIN_URL = "/project-1-ERS/login";
let loginVerify = document.getElementById("testform");


loginVerify.addEventListener("submit", (event) => {
    //    event.preventDefault();
    let empEmail = loginVerify.userEmail.value;
    let empPwd = loginVerify.userPassword.value;
    console.log("login clicked");
    fetch(LOGIN_URL, { method: "POST" })
        .then((response) => {
            return response.json();
        });

})