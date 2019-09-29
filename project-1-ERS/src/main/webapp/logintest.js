'use strict';

let login = document.getElementById("testform");
const LOGIN_URL = "/project-1-ERS/login";

login.addEventListener("submit", (event) => {
    console.log("Login test");

    fetch(LOGIN_URL, { method: "POST" })
        .then((response) => {
            return response.json();
        })


});


// let email = testform.userEmail.value;
// let password = testform.userPassword.value;
// console.log(`User input received: ${email}, ${password}`);


// function loginVerify(email, password, onSuccess) {
//     let xhr = new XMLHttpRequest();
//     xhr.addEventListener("readystatechange", () => {
//         if (xhr.readyState === xhr.DONE) {
//             let response = xhr.response;
//             console.log(`Response received: ${response}`);

//             // check the status code:
//             if (xhr.status >= 200 && xhr.status < 300) {
//                 onSuccess(response);
//             } else {
//                 console.error(`Failure with status ${xhr.status}`);
//             }
//         }

//         xhr.open("POST", `localhost:8080/project-1-ERS/login`);

//         xhr.send();
//     })
// }