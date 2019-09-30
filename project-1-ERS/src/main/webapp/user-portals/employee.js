'use strict';

const EMPLOYEES_URL = "/project-1-ERS/manager";


//Get Employee Info
let employeeInfoCard = document.getElementById("employeeInformation");
let getSingleEmployeeInfo = document.getElementById("get-info");

getSingleEmployeeInfo.addEventListener("submit", (event) => {
    event.preventDefault();

    let email = getSingleEmployeeInfo.email.value;
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