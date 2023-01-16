

const assignmentButton =document.getElementById("fetch_assignment");
assignmentButton.addEventListener("click",function(){

           fetch("http://localhost:9090/allAssignments")
                      .then(res=>res.text())
                      .then(res=>{
                      const content=document.querySelector("#dynamic_loader");
                      content.innerHTML=res;
           const scriptExist=document.querySelector("#all_assignment");
           if(scriptExist==null){

           const script =document.createElement("script");
           script.setAttribute("id","all_assignment");
           script.src="/js/allAssignment.js";
           document.body.appendChild(script);}

           })}

);
const userButton =document.getElementById("create_user_button");
  userButton.addEventListener("click",function(){

           fetch("http://localhost:9090/createUsers")
           .then(res=>res.text())
           .then(res=>{
           const content=document.querySelector("#dynamic_loader");
           content.innerHTML=res;
             const scriptExist=document.querySelector("#user_create_script");
             if(scriptExist==null){
           const script =document.createElement("script");
           script.setAttribute("id","user_create_script");
           script.src="/js/userCreate.js"
           document.body.appendChild(script);}
           })}
);

