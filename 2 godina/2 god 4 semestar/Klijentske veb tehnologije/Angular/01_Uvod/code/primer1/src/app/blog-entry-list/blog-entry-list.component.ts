import { Component, OnInit } from '@angular/core';
import { Input } from '@angular/core';
import { BlogEntry } from '../model/blogEntry';

@Component({
  selector: 'app-blog-entry-list',
  templateUrl: './blog-entry-list.component.html',
  styleUrls: ['./blog-entry-list.component.css']
})
export class BlogEntryListComponent implements OnInit {

  @Input()
  blogEntries:BlogEntry[];

  constructor() { }

  ngOnInit() {
  }

}
