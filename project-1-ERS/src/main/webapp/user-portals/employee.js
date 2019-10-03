'use strict';

const INFO_URL = "/project-1-ERS/employee/viewInfo";
const SUBMIT_URL = "/project-1-ERS/employee/submitReimb";
const PENDING_URL = "/project-1-ERS/employee/pendingReimbs";
const RESOLVED_URL = "/project-1-ERS/employee/resolvedReimbs";

//GET EMPLOYEE INFO
let employeeInfoLi = document.getElementById("employeeInfo");
let infoCard = document.getElementById("employeeInfoCard");
let infoCardHeader = document.getElementById("infoCardHeader")

window.addEventListener("load", (event) => {
    console.log("page loaded");

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
    employeeCardInfo.innerText = `EMPLOYEE ID: ${employee.empId} | EMPLOYEE NAME: ${employee.firstName} ${employee.lastName} | Email: ${employee.email} | Phone: ${employee.phoneNumber} | MANAGER: ${employee.manager}`;
    // clearDisplay();
    infoCard.append(employeeCardInfo);
}

let updateButton = document.getElementById("get-employee-info");


//VIEW PENDING REIMBURSEMENTS BY EMPLOYEE ID

let pendingButton = document.getElementById("select-request");
let viewPRLi = document.getElementById("employee-pending-requests");
pendingButton.addEventListener("submit", (event) => {
    console.log("pending button clicked");

    // let email = getSingleEmployeeInfo.email.value;
    fetch(PENDING_URL, { method: "GET" })
        .then((response) => {
            console.log(response);
            return response.json();
        })
        .then((ePendingReimbs) => {
            createPRLi(ePendingReimbs);
            // clearDisplay();
        })
        .catch((error) => {
            console.error(error);
        });
});

let createPRLi = (ePendingReimbs) => {
    let PRLi = document.createElement("li");
    PRLi.innerText = `REIMBURSEMENT ID: ${ePendingReimbs.reimbId} | AMOUNT: ${ePendingReimbs.reimbAmt} | REIMBURSEMENT STATUS: ${ePendingReimbs.reimbStatus} | SUBMITTED BY: ${ePendingReimbs.submittedBy} | RESOLVED BY: ${ePendingReimbs.resolvedBy} | SUBMIT TIME: ${ePendingReimbs.submitTime}`;

    viewPRLi.append(PRLi);
};

//VIEW RESOLVED REIMBURSEMENTS BY EMPLOYEE ID
let resolvedButton = document.getElementById("get-resolved-reimbs");

resolvedButton.addEventListener("click", (event) => {
    console.log("resolved button clicked");
});

//UPDATE EMPOYEE
const UPDATE_URL = "/project-1-ERS/employee/updateInfo";

let updateEmployee = document.getElementById("update-info");
updatePlayer.addEventListener("submit", (event) => {
    console.log("Update Button");

    event.preventDefault();
    fetch(UPDATE_URL, { method: "PUT", body: JSON.stringify(updateFromForm(updateEmployee)) })
        .then((response) => {
            if (response.status >= 200 && response.status < 300) {
                alert("Player updated");
            } else {
                alert("Failed to update");
            }

        })
});

let updateFromForm = (form) => {
    let employee = {};

}