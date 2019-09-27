'use strict';

const GET_EMPLOYEES_URL = "/manager"
let getEmployees = document.getElementById("get-employees");
let loginVerify = document.getElementById("test-login");

//getEmployees.addEventListener("click", console.log("clicked"));

getEmployees.addEventListener("click", (event) => {
    console.log("clicked");
    fetch(GET_EMPLOYEES_URL, { method: "GET" })
        .then((response) => {
            console.log("Response: " + response);
            console.log(response.json());
            return response.json();
        })
        .then((employeesJson) => {
            clearDisplay();
            for (let employee in employeesJson) {
                console.log(employeesJson);

                console.log(employeesJson[employee]);

                createLi(employeesJson[employee]);
            }
        })
        .catch(console.log);
})

let employeesList = document.getElementById("employeesList");
let clearDisplay = () => {
    employeesList.innerHTML = "";
}

let createList = (employee) => {
    let li = document.createElement("li");
    li.innerText = `${employee.employeeFirstName} ${employee.employeeLastName} : Email ${employee.email}, Phone ${employee.phoneNumber}`;
}