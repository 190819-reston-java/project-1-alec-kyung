'use strict';

const INFO_URL = "/project-1-ERS/employee/viewInfo";
const UPDATE_URL = "/project-1-ERS/updateInfo";
const SUBMIT_URL = "/project-1-ERS/submitReimb";
const PENDING_URL = "/project-1-ERS/pendingReimbs";
const RESOLVED_URL = "/project-1-ERS/resolvedReimbs";


//GET EMPLOYEE INFO
let employeeInfoLi = document.getElementById("employeeInfo");
let infoButton = document.getElementById("get-employee-info");
let infoCard = document.getElementById("employeeInfoCard");
let infoCardHeader = document.getElementById("infoCardHeader")

infoCardHeader.addEventListener("click", (event) => {
    console.log("clicked");

    // let email = getSingleEmployeeInfo.email.value;
    fetch(INFO_URL, { method: "GET" })
        .then((response) => {
            return response.json();
        })
        .then((employeeJson) => {
            displayInfo(employeeJson);
            // clearDisplay();
        })
        .catch((error) => {
            console.error(error);
        });
});

let displayInfo = (employee) => {
    let employeeCardInfo = document.createElement("p");
    employeeCardInfo.innerText = `EMPLOYEE ID: ${employee.empId} | EMPLOYEE NAME: ${employee.firstName} ${employee.lastName} | Email: ${employee.email} | Phone: ${employee.phoneNumber}`;
    // clearDisplay();
    infoCard.append(employeeCardInfo); 
   
}

