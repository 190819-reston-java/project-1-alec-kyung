'use strict';

const GET_EMPLOYEES_URL = "/project-1-ERS/manager/get-employees";

//GET ALL EMPLOYEES
let getEmployeesButton = document.getElementById("get-employees");
let employeesList = document.getElementById("employeesList");

getEmployeesButton.addEventListener("click", (event) => {
    console.log("employees button clicked");
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
    let linebreak = document.createElement("br");
    employeeLi.innerText = `EMPLOYEEID: ${employee.empId} | EMPLOYEENAME: ${employee.firstName} ${employee.lastName} | EMAIL: ${employee.email} | PHONE: ${employee.phoneNumber} ${linebreak}`;

    employeesList.append(employeeLi);
};

//SEARCH REIMBURSMENT
const SEARCH_REIMB_URL = "/project-1-ERS/manager/select-reimb";
let selectEmpReimbButton = document.getElementById("select-request");
selectEmpReimbButton.addEventListener("click", (event) => {
    console.log("select reimbursement clicked");
    fetch(SEARCH_REIMB_URL, { method: "GET" })
        .then((response) => {
            // console.log(response.json());
            return response.json();
        })
        .then((selectReimbsJson) => {
            clearDisplay();
            for (let selectReimbs in selectReimbsJson) {
                console.log("PR JSON: " + selectReimbsJson);

                console.log("PR: " + selectReimbsJson[selectReimbs]);

                createPRLi(selectReimbsJson[selectReimbs]);
            }
        })
        .catch(console.log);
});


let createSRLi = (selectReimbs) => {
    let SRLi = document.createElement("li");
    SRLi.innerText = `REIMBURSEMENTID: ${selectReimbs.reimbId} | STATUS: ${selectReimbs.reimbAmt} ${selectReimbs.reimbStatus} | SUBMITTEDBY: ${selectReimbs.submittedBy} | RESOLVEDBY: ${selectReimbs.resolvedBy} | SUBMITTIME: ${selectReimbs.submitTime}`;

    selectReimbsList.append(SRLi);
};

//VIEW ALL PENDING REIMBURSMENTS (MANAGER)
const VIEW_ALL_PR_URL = "/project-1-ERS/manager/view-all-pending-reimbs";
let managerViewAllPendingButton = document.getElementById("get-all-pending-reimbs");
let viewPRLi = document.getElementById("pendingReimbsList");
managerViewAllPendingButton.addEventListener("click", (event) => {
    console.log("pending reimbs clicked")
    fetch(VIEW_ALL_PR_URL, { method: "GET" })
        .then((response) => {
            // console.log(response.json());
            return response.json();
        })
        .then((pendingReimbsJson) => {
            clearDisplay();
            for (let pendingReimbs in pendingReimbsJson) {
                console.log("PR JSON: " + pendingReimbsJson);

                console.log("PR: " + pendingReimbsJson[pendingReimbs]);

                createPRLi(pendingReimbsJson[pendingReimbs]);
            }
        })
        .catch(console.log);
});


let createPRLi = (pendingReimbs) => {
    let PRLi = document.createElement("li");
    PRLi.innerText = `REIMBURSEMENT ID: ${pendingReimbs.reimbId} | AMOUNT: ${pendingReimbs.reimbAmt} | REIMBURSEMENT STATUS: ${pendingReimbs.reimbStatus} | SUBMITTED BY: ${pendingReimbs.submittedBy} | RESOLVED BY: ${pendingReimbs.resolvedBy} | SUBMIT TIME: ${pendingReimbs.submitTime}`;

    viewPRLi.append(PRLi);
};

//View Resolved
const VIEW_RESOLVED_URL = "/project-1-ERS/manager/view-resolved-reimbs";
let managerViewResolvedButton = document.getElementById("get-resolved-reimbs");
let viewRRLi = document.getElementById("resolvedReimbsList");
managerViewResolvedButton.addEventListener("click", (event) => {
    console.log("resolved reimbs clicked")
    fetch(VIEW_RESOLVED_URL, { method: "GET" })
        .then((response) => {
            // console.log(response.json());
            return response.json();
        })
        .then((resolvedReimbsJson) => {
            clearDisplay();
            for (let resolvedReimbs in resolvedReimbsJson) {
                console.log("RR JSON: " + resolvedReimbsJson);

                console.log("RR: " + resolvedReimbsJson[resolvedReimbs]);

                createPRLi(resolvedReimbsJson[resolvedReimbs]);
            }
        })
        .catch(console.log);
});


let createRRLi = (resolvedReimbs) => {
    let RRLi = document.createElement("li");
    RRLi.innerText = `REIMBURSEMENT ID: ${resolvedReimbs.reimbId} | AMOUNT: ${resolvedReimbs.reimbAmt} | REIMBURSEMENT STATUS: ${resolvedReimbs.reimbStatus} | SUBMITTED BY: ${resolvedReimbs.submittedBy} | RESOLVED BY: ${resolvedReimbs.resolvedBy} | SUBMIT TIME: ${resolvedReimbs.submitTime}`;

    viewRRLi.append(RRLi);
};