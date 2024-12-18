let adresa = { ulica: 'Fruskogorska', broj: 'bb',  pbroj: "22000", grad: "Novi Sad"};
let a = { 
  ime: "Ime", 
  prezime: "prezime", 
  adresa: adresa 
}

let b = {
  ime: 'ime2',
  prezime: "prezime2",
  ...adresa
}

let c = {
  ime: 'ime2',
  prezime: "prezime2",
  adresa: { ...adresa }
}



console.log(a);

a.adresa.pbroj = 21000;
console.log(a);
console.log(adresa);
console.log(b);
b.pbroj = 23000;
console.log(adresa);
console.log(c);
c.adresa.pbroj = 24000;
console.log(c.adresa);
console.log(adresa);


let godineStudija = ["2021/2022", "2022/2023", "2023/2024"];

let [prva, ...ostatak] = godineStudija;
console.log("prva", prva, "ostatak: ", ostatak);

let { ime: name, prezime: lastname } = c;

console.log(name, lastname);


const testFunc = ({ pbroj, grad })=>{
  console.log(pbroj);
  console.log(grad);
}

testFunc(adresa);
