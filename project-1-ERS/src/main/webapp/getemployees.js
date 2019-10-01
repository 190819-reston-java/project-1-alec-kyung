'use strict';

const GET_EMPLOYEES_URL = "/project-1-ERS/manager";
let getEmployees = document.getElementById("get-employees");
let employeesList = document.getElementById("employeesList");
// let loginVerify = document.getElementById("test-login");

//getEmployees.addEventListener("click", console.log("clicked"));

getEmployees.addEventListener("click", (event) => {
    console.log("clicked");
    fetch(GET_EMPLOYEES_URL, { method: "GET" })
        .then((response) => {
            // console.log(response.json());
            return response.json();
        })
        .then((employeesJson) => {
            clearDisplay();
            for (let employee in employeesJson) {
                console.log("EMPLOYEES JSON: " + employeesJson);

                console.log("EMPLOYEE: " + employeesJson[employee]);

                createLi(employeesJson[employee]);
            }
        })
        .catch(console.log);
});


let clearDisplay = () => {
    employeesList.innerHTML = "";
};

let createLi = (employee) => {
    let employeeLi = document.createElement("li");
    employeeLi.innerText = `${employee.firstName} ${employee.lastName} : Email ${employee.email}, Phone ${employee.phoneNumber}`;

    employeesList.append(employeeLi);
};