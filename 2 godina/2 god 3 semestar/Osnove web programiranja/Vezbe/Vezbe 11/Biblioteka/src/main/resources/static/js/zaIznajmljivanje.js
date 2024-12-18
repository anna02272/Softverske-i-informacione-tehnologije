fetch('knjige/zeljene', {
    method: 'get'
  }).then(response => {
	  return response.text()
  }).then((data) => {
	  console.log(data ? JSON.parse(data) : {})
	  listaKnjiga = JSON.parse(data)
	  
	  var table = document.getElementById("zeljene");
	  for (var i=0; i < listaKnjiga.length ; i++){
	       var tr = document.createElement('TR');
	       table.appendChild(tr);
	       
	       var tdNaziv = document.createElement('TD');
	       tdNaziv.appendChild(document.createTextNode(listaKnjiga[i].naziv));
	       tr.appendChild(tdNaziv);
	       
	       var tdRegistarskiBrojPrimerka = document.createElement('TD');
	       tdRegistarskiBrojPrimerka.appendChild(document.createTextNode(listaKnjiga[i].registarskiBrojPrimerka));
	       tr.appendChild(tdRegistarskiBrojPrimerka);
	       
	       var tdJezik = document.createElement('TD');
	       tdJezik.appendChild(document.createTextNode(listaKnjiga[i].jezik));
	       tr.appendChild(tdJezik);
	       
	       var tdBrojStranica = document.createElement('TD');
	       tdBrojStranica.appendChild(document.createTextNode(listaKnjiga[i].brojStranica));
	       tr.appendChild(tdBrojStranica);
	       
	       var tdForma = document.createElement('TD')
	       var form = document.createElement('FORM')
	       
	       form.setAttribute('method', "post")
	       form.setAttribute('action', "knjige/zeljene/ukloni")
	       
	       var inputHidden = document.createElement('INPUT')
	       
	       inputHidden.setAttribute('type', "hidden")
	       inputHidden.setAttribute('name', "idKnjige")
	       inputHidden.setAttribute('value', listaKnjiga[i].id)
	       
	       var inputSubmit = document.createElement('INPUT')
	       inputSubmit.setAttribute('type', "submit")
	       inputSubmit.setAttribute('value', "Ukloni iz zeljenih")
	       
	       form.appendChild(inputHidden)
	       form.appendChild(inputSubmit)
	       
	       tdForma.appendChild(form)
	       
	       tr.appendChild(tdForma);
	    }
	})
  .catch(function (error) {
    console.log('Request failed', error);
  });