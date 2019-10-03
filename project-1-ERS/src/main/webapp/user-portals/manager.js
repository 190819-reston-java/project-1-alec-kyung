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
managerViewAllPendingButton.addEventListener("click", (event) => {
    console.log("pending reimbs clicked")
    fetch(VIEW_ALL_PR_URL, { method: "GET" })
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


let createPRLi = (selectReimbs) => {
    let PRLi = document.createElement("li");
    let linebreak = document.createElement("br");
    PRLi.innerText = `REIMBURSEMENTID: ${selectReimbs.reimbId} | STATUS: ${selectReimbs.reimbAmt} ${selectReimbs.reimbStatus} | SUBMITTEDBY: ${selectReimbs.submittedBy} | RESOLVEDBY: ${selectReimbs.resolvedBy} | SUBMITTIME: ${selectReimbs.submitTime}`;

    selectReimbsList.append(PRLi);
};