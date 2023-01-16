

const dynamicLoader =document.querySelector("#dynamic_loader");

dynamicLoader.addEventListener("click",function(event){
let map=new Map();
if(event.target.classList.contains("assignment_button_delete")){
let url="http://localhost:9090/deleteAssignment/id="
fetch(url+=event.target.getAttribute("custom-value"),{
method:"DELETE"
}).then(res=> {
if(res.ok){
fetch("http://localhost:9090/allAssignments")
.then(res=> res.text())
.then(res=>{
dynamicLoader.innerHTML=res;
})

}
})


}
if(event.target.classList.contains("assignment_button_update") ||event.target.value=="createAssignment"){
const assignmentParaTag=document.querySelector(".assignment_execution_type");
document.getElementById("assignment_display_container").style.display="none";
document.getElementById("update_assignment_container").style.display="block";
if(event.target.classList.contains("assignment_button_update")){
assignmentParaTag.innerHTML="Update assignment"
const updateAssignmentId=event.target.getAttribute("custom-value");
const customValueSubmit=document.getElementById("update_assignment_submit");
customValueSubmit.setAttribute("custom-value",updateAssignmentId);
}
if(event.target.value=="createAssignment"){
assignmentParaTag.innerHTML="Create assignment";
document.querySelector("#create_assignment_userid_container").style.display="block";

const customValueSubmit=document.getElementById("update_assignment_submit");
customValueSubmit.removeAttribute("custom-value");
}
}
if(event.target.classList.contains("update_assignment_button_close")){
document.getElementById("assignment_display_container").style.display="flex";
document.getElementById("update_assignment_container").style.display="none";
}

if(event.target.classList.contains("assignment_button_adduser")){
const targetElement=event.target;
let customValue=targetElement.getAttribute("custom-value");
let userInput=document.querySelector("#add_userid_container[custom-value='"+customValue+"']");
console.log(targetElement.innerHTML);

if(targetElement.innerHTML==="Add user"){
console.log("inside add user");
userInput.style.display="block";
const input=userInput.getElementsByTagName("input")[0];
input.setAttribute("custom-value",customValue);
targetElement.style.backgroundColor ="#D11A2A";
targetElement.innerHTML="close";
targetElement.value="0";
}else if(targetElement.getAttribute("value")==0){
     console.log("inside close");
     targetElement.innerHTML="Add user"
     userInput.style.display="none";
     targetElement.value="1";
     targetElement.style.backgroundColor ="#00FF00";
     }

}
if(event.target.classList.contains("update_assignment_button_submit")){

event.preventDefault();

const assignmentName=document.querySelector("#update_assignment_name").value;
const assignmentStartDate=document.querySelector("#update_assignment_startdate").value;
const assignmentDueDate=document.querySelector("#update_assignment_duedate").value;
const assignmentType=document.querySelector("#update_assignment_type").value;

map.set("title",assignmentName);
map.set("startDate",assignmentStartDate);
map.set("dueDate",assignmentDueDate);
map.set("type",assignmentType);


if(event.target.hasAttribute("custom-value")){
const plainObject=Object.fromEntries(map.entries());
let url="http://localhost:9090/updateAssignment/";
fetch(url+=event.target.getAttribute("custom-value"),{
method:"PUT",
body:JSON.stringify(plainObject),
headers:{"content-type":"application/json  ; charset=UTF-8"}
})
.then(res=>{
document.querySelector("#fetch_assignment").click();})
}else{
const userId=document.querySelector("#create_assignment_user").value;
let UserMap =new Map();
UserMap.set("userId",userId);

const plainObject1=Object.fromEntries(UserMap.entries());
let userList=[plainObject1];

map.set("userList",userList);
const plainObject=Object.fromEntries(map.entries());

fetch("http://localhost:9090/createAssignment",{
method:"POST",
body:JSON.stringify(plainObject),
headers:{"content-type":"application/json  ; charset=UTF-8"}
})
.then(res=>{
document.querySelector("#fetch_assignment").click();})

}
}
})
dynamicLoader.addEventListener("change",function(event){
if(event.target.classList.contains("search_assignment_by_id")){
let url="http://localhost:9090/getAssignmentById/";
fetch(url+=event.target.value)
.then(res=>res.text())
.then(res=>{

dynamicLoader.innerHTML=res;
})
}
if(event.target.classList.contains("add_userid_input")){
let target=event.target;
let userId=event.target.value;
let assignId=event.target.getAttribute("custom-value");
let url=`http://localhost:9090/addUser/${userId}/ForAssignment/${assignId}`;

fetch(url)
.then(res=>{
target.parentNode.style.display="none";
})
}
})
