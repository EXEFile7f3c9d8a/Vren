const tit = document.getElementById("Title");
const btn = document.getElementById("btn");

btn.addEventListener("click", () =>{
    if (tit.innerText === "Vren"){
        tit.innerText = "Still vren"
    }else if (tit.innerText === "Still vren"){
        tit.innerText = "Vren"
    }

});