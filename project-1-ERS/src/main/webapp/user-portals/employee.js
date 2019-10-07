'use strict';

const INFO_URL = "/project-1-ERS/employee/viewInfo";
const SUBMIT_URL = "/project-1-ERS/employee/submitReimb";
const PENDING_URL = "/project-1-ERS/employee/pendingReimbs";
const RESOLVED_URL = "/project-1-ERS/employee/resolvedReimbs";

let unorderedList = document.getElementsByTagName("ul");
unorderedList.className = "list-group-item";


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
    employeeCardInfo.innerText = `EMPLOYEE ID: ${employee.empId} | EMPLOYEE NAME: ${employee.firstName} ${employee.lastName} | EMAIL: ${employee.email} | PHONE: ${employee.phoneNumber}`;
    // clearDisplay();
    infoCard.append(employeeCardInfo);
}

let updateButton = document.getElementById("get-employee-info");


//SUBMIT REIMBURSEMENT
// let submitForm = document.getElementById("submit-reimb");

// submitForm.addEventListener("submit", (event) => {
//     // event.preventDefault();
// })


//VIEW PENDING REIMBURSEMENTS BY EMPLOYEE ID

let pendingButton = document.getElementById("get-pending-reimbs");
let viewPRLi = document.getElementById("employee-pending-requests");
pendingButton.addEventListener("click", (event) => {
    console.log("pending button clicked");

    // let email = getSingleEmployeeInfo.email.value;
    fetch(PENDING_URL, { method: "GET" })
        .then((response) => {
            //console.log(response);
            return response.json();
        })
        .then((ePendingReimbsJson) => {
            console.log("EPR JSON: " + ePendingReimbsJson)
                // clearDisplay();
            for (let ePendingReimbs in ePendingReimbsJson) {


                createPRLi(ePendingReimbsJson[ePendingReimbs]);
            }
        })
        .catch((error) => {
            console.error(error);
        });
});

let createPRLi = (ePendingReimbs) => {
    let PRLi = document.createElement("li");
    // PRLi.className = "list-group-item";
    PRLi.innerText = `REIMBURSEMENT ID: ${ePendingReimbs.reimbId} | AMOUNT: ${ePendingReimbs.reimbAmt} | REIMBURSEMENT STATUS: ${ePendingReimbs.reimbStatus}`;
    viewPRLi.append(PRLi);
};

//VIEW RESOLVED REIMBURSEMENTS BY EMPLOYEE ID
let resolvedButton = document.getElementById("get-resolved-reimbs");
let viewRRLi = document.getElementById("resolved-requests");

resolvedButton.addEventListener("click", (event) => {
    console.log("resolved button clicked");

    fetch(RESOLVED_URL, { method: "GET" })
        .then((response) => {
            //console.log(response);
            return response.json();
        })
        .then((eResolvedReimbsJson) => {
            //clearDisplay
            for (let eResolvedReimbs in eResolvedReimbsJson) {
                createRRLi(eResolvedReimbsJson[eResolvedReimbs]);
            }
        })
        .catch((error) => {
            console.error(error);
        });

});
let createRRLi = (eResolvedReimbs) => {
    let RRLi = document.createElement("li");
    RRLi.innerText = `REIMBURSEMENT ID: ${eResolvedReimbs.reimbId} | AMOUNT: ${eResolvedReimbs.reimbAmt} | REIMBURSEMENT STATUS: ${eResolvedReimbs.reimbStatus} | RESOLVED BY: ${eResolvedReimbs.resolvedBy}`;

    viewRRLi.append(RRLi);
};

//UPDATE EMPOYEE
const UPDATE_URL = "/project-1-ERS/employee/updateInfo";
let updateEmployee = document.getElementById("update-info");

updateEmployee.addEventListener("submit", (event) => {
    console.log("Update Button");

    event.preventDefault();
    fetch(UPDATE_URL, { method: "PUT", body: JSON.stringify(updateFromForm(updateEmployee)) })
        .then((response) => {
            if (response.status >= 200 && response.status < 300) {
                alert("Employee updated");
            } else {
                alert("Failed to update");
            }

        })
});

let updateFromForm = (form) => {
    let employee = {};
    employee.firstName = form.firstName.value;
    employee.lastName = form.lastName.value;
    employee.phoneNumber = form.phoneNumber.value;
    employee.email = form.email.value;
    employee.password = form.password.value;
    return employee;
};
    
const LOGOUT_URL = "/project-1-ERS/employee/logout";

let logout = document.getElementById("logout");
logout.addEventListener("click", (event)=>{
    console.log("logout clicked")
    fetch(LOGOUT_URL, { method: "GET" })
        .then((response) => {
            return response.json();
        })
        .catch((error) => {
            console.error(error);
        });
});