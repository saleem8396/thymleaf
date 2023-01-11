let userBoolean=false;

const assignmentButton =document.getElementById("fetch_assignment");
assignmentButton.addEventListener("click",function(){

           fetch("http://localhost:9090/allAssignments")
           .then(res=>res.text())
           .then(res=>{
           const content=document.querySelector("#dynamic_loader");
           content.innerHTML=res;
           })}
);
const userButton =document.getElementById("create_user_button");
  userButton.addEventListener("click",function(){


           fetch("http://localhost:9090/createUsers")
           .then(res=>res.text())
           .then(res=>{
           const content=document.querySelector("#dynamic_loader");
           content.innerHTML=res;
           })}
);

if(document.querySelector(".signup_form")!=null){
console.log("hello");
const form =document.querySelector(".signup_form");
form.addEventListener("submit",function(event){
     event.preventDefault();
      const firstNameInput=document.querySelector("#form_firstname");
      const firstName=firstNameInput.value;
      console.log(firstName);
      const lastNameInput=document.querySelector("#form_lastname");
      const lastName=lastNameInput.value;
      console.log(lastName);
      const passwordInput=document.querySelector("#form_password");
      const password=passwordInput.value;
      console.log(password);
      const confirmPasswordInput=document.querySelector("#form_confirm_password");
      const confirmPassword=confirmPasswordInput.value;
      console.log(confirmPassword);
});
}