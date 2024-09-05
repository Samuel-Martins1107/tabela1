function validarNome(){
    var nome = document.getElementById("nome").value;
    var letras = /^[A-Za-z]+$/;
    if(nome.match(letras)){
document.getElementById("texto").innerHTML= "NOME OK!";
    } else{
document.getElementById("nome").value="";
document.getElementById("nome").focus();
document.getElementById("texto").innerHTML="Digite apenas letras!!"
    }
return false;
}
function calcularImc(){
    var peso = document.formulario.peso.value;
    var altura = document.formulario.altura.value;
    var numero = 
    if(peso== "" ||altura=="" ){
        document.getElementById("peso").focus();
        alert("FAVOR PREENCHER OS CAMPOS")
        return false;
    }
   
    var resultado = peso/(altura*altura);

    console.log("peso" + peso);
    console.log("altura" + altura);
    console.log("resultado" + resultado);
    alert("SEU IMC E: " +resultado.toFixed(2));
    return false;


}