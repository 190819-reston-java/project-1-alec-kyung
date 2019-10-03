'use strict';

const INFO_URL = "/project-1-ERS/employee/viewInfo";
const UPDATE_URL = "/project-1-ERS/employee/updateInfo";
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
    employeeCardInfo.innerText = `EMPLOYEE ID: ${employee.empId} | EMPLOYEE NAME: ${employee.firstName} ${employee.lastName} | Email: ${employee.email} | Phone: ${employee.phoneNumber}`;
    // clearDisplay();
    infoCard.append(employeeCardInfo);
}

let updateButton = document.getElementById("get-employee-info");


//VIEW PENDING REIMBURSEMENTS BY EMPLOYEE ID

let pendingButton = document.getElementById("get-pending-reimbs");
let viewPRLi = document.getElementById("employee-pending-requests");
pendingButton.addEventListener("click", (event) => {
    console.log("pending button clicked");

    // let email = getSingleEmployeeInfo.email.value;
    fetch(PENDING_URL, { method: "GET" })
        .then((response) => {
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