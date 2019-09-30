'use strict';

const EMPLOYEES_URL = "/project-1-ERS/manager";


//Get Employee Info
let employeeInfoCard = document.getElementById("employeeInformation");
let employeeInfo = document.getElementById("get-info");

employeeInfo.addEventListener("submit", (event) => {
    // event.preventDefault();

    let email = employeeInfo.email.value;
    fetch(`${EMPLOYEES_URL}/${email}`)
        .then((response) => {
            return response.json();
        })
        .then((employeeJson) => {
            clearDisplay();
            createLi(employeeJson)
        })
        .catch((error) => {
            console.error(error);
            //alert(`Unable to find email`);
        });
});



let clearDisplay = () => {
    employeesList.innerHTML = "";
};

let createLi = (employee) => {
    let employeeLi = document.createElement("li");
    employeeLi.innerText = `${employee.firstName} ${employee.lastName} : Email ${employee.email}, Phone ${employee.phoneNumber}`;

    employeesInfo.append(employeeLi);
};