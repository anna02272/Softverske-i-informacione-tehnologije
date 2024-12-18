import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Comment } from './model/comment';
import { BlogEntry } from './model/blogEntry';
import dataBlogEntries from './util/dataBlogEntries';
import * as _ from 'lodash';
import searchEntries from './util/searchEntries'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  
  title = 'app';
  
  blogEntries: BlogEntry[];
  
  blogEntriesBckup: BlogEntry[];

  ngOnInit() {
    this.blogEntriesBckup = dataBlogEntries;
    this.blogEntries = _.cloneDeep(this.blogEntriesBckup);
  }
  
  saveBlogEntry(blogEntry: BlogEntry) {
    this.blogEntriesBckup.push(blogEntry);
    this.blogEntries = _.cloneDeep(this.blogEntriesBckup);
  }

  filter(text:string){
    // searchEntry ima signaturu (text:string, blogEntries:BlogEntries) => boolean
    // filter prima funkciju signature (entity:any) => boolean
    // zato radimo parcijalnu aplikaciju funkcije searchEntry 
    // i fiksiramo za prvi parametar tekst po kom se radi pretraga 
    let searchEntriesForText = searchEntries.bind(null,text);
    this.blogEntries=this.blogEntriesBckup.filter(searchEntriesForText);
  }
}
