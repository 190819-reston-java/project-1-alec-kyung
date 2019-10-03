'use strict';

const GET_EMPLOYEES_URL = "/project-1-ERS/manager";

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
let selectEmpReimbButton = document.getElementById("select-request");
selectEmpReimbButton.addEventListener("click", (event) => {
    console.log("select reimbursement clicked");
});

//VIEW ALL PENDING REIMBURSMENTS (MANAGER)
let managerViewAllPendingButton = document.getElementById("get-all-pending-reimbs");
managerViewAllPendingButton.addEventListener("click", (event) => {
    console.log("pending reimbs clicked")
    fetch(GET_EMPLOYEES_URL, { method: "GET" })
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
    let linebreak = document.createElement("br");
    PRLi.innerText = `EMPLOYEEID: ${employee.empId} | EMPLOYEENAME: ${employee.firstName} ${employee.lastName} | EMAIL: ${employee.email} | PHONE: ${employee.phoneNumber} ${linebreak}`;

    pendingReimbsList.append(employeeLi);
};