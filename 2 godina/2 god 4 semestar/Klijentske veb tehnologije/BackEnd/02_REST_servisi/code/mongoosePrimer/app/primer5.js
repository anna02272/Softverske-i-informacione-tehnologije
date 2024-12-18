// izmena dokumenta

// koristimo mongoose
var mongoose = require('mongoose');
// postavljanje promise biblioeteke za mongoose
mongoose.Promise = require('bluebird');
// konektujemo se na lokalnu instancu mongodb
mongoose.connect('mongodb://localhost/primer1',{ useMongoClient: true });
// koristimo mongoose shemu koju smo kreirali u folderu model
var BlogEntry = require('../app/model/blogEntry');

// selektujemo dokument i izmenimo ga
BlogEntry.findOneAndUpdate({ title: 'Hello World!' }, { description: 'ponovo izmenjena vrednost' }, function(err, entry) {
  if (err) throw err;

  // objekat koji se menjao je dostupan u callback funckiji
  console.log(entry);
});
