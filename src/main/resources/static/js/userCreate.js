


document.addEventListener("click",function(event){
let list =new Map();
if(event.target.value==="createUser"){
document.querySelector(".user_card_container").style.display="none";
document.querySelector("#signup_form_container").style.display="flex";
}
if(event.target.classList.contains("signup_form_close_button")){
document.querySelector(".user_card_container").style.display="flex";
document.querySelector("#signup_form_container").style.display="none";

}
if(event.target.classList.contains("user_delete_button")){
let url="http://localhost:9090/deleteUserById/";
const id=event.target.getAttribute("custom-value");
fetch(url+=id).then(res=>{ document.querySelector("#create_user_button").click();});
}
if(event.target.classList.contains("signup_form_submit_button")){
     event.preventDefault();
      const firstNameInput=document.querySelector("#form_firstname");
      const lastNameInput=document.querySelector("#form_lastname");
      const passwordInput=document.querySelector("#form_password");

      const confirmPasswordInput=document.querySelector("#form_confirm_password");
         const firstName=firstNameInput.value;
         const lastName=lastNameInput.value;
         const password=passwordInput.value;
         const confirmPassword=confirmPasswordInput.value;

      if(password===confirmPassword){

         list.set("firstname",firstName);
         list.set("lastName",lastName);
         list.set("password",password);
         const plainObject= Object.fromEntries(list.entries());

      fetch("http://localhost:9090/createUser",{
      method:"POST",
      body:JSON.stringify(plainObject),
      headers:{ "Content-Type":"application/json ; charset=UTF-8" }
      })
      .then(res=>{
      console.log(res);
      if(res.ok){
      document.querySelector("#create_user_button").click();
      }
}
      )
      }else{

      alert("password not matching");
      }
}
});

document.addEventListener("change",function(event){
if(event.target.classList.contains("search_assignment_by_userid")){
let url="http://localhost:9090/getAssignmentOfUser/";
fetch(url+=event.target.value)
.then(res=>res.text())
.then(res=>{
document.querySelector("#user_form_card_container").innerHTML=res;
document.querySelector("#assignment_manipulation_button").style.display="none";
})
}

})