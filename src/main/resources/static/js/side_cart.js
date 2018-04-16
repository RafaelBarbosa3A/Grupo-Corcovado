[... document.querySelectorAll(".comprarForm")].forEach((prodForm) => {
    let formData = new FormData();
    formData.append('produtoId',  prodForm.querySelector('input[name=produtoId]').value);
    formData.append('quantidade', prodForm.querySelector('input[name=quantidade]').value);
    
    prodForm.querySelector("a").addEventListener('click', (ev) => {
        console.log(prodForm.querySelector('input[name=quantidade]').value);
        
        fetch("/comercio/addToCart", {
            method: "POST",
            body: formData,
            credentials: "includes"
        }).then((response)=>{
            console.log("Status: " + response.status);
            console.log(response.text());
            
            
            
            
        }, (err)=>{
            console.error(err.message);
        });
    });
});
