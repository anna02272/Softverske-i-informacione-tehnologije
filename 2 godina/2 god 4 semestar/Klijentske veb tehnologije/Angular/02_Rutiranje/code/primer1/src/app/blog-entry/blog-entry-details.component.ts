import { Component, OnInit } from '@angular/core';
import { Input } from '@angular/core';
import { BlogEntry } from '../model/blogEntry';
import { BlogEntryComponent } from './blog-entry.component';
import { SharedBlogEntryService } from '../services/shared-blog-entry.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-blog-entry-details',
  templateUrl: './blog-entry.component.html',
  styleUrls: ['./blog-entry.component.css']
})
//posto su veoma slicne BlogEntry i BlogEntryDetails recikliraju isti templejt
//BlogEntryDetails nasledjuje BlogEntry i time preuzima i njene impute
export class BlogEntryDetailsComponent extends BlogEntryComponent {

  sub:any;

  index:number;

  blogEntry:BlogEntry;
  //kompomente BlogEntry i BlogEntryDetails komuniciraju kroz servis
  constructor(private sharedBlogEntryService:SharedBlogEntryService,
              private route: ActivatedRoute) {
    super();
   }

  ngOnInit() {
    this.blogEntry = this.sharedBlogEntryService.blogEntry;
    this.sub = this.route.params.subscribe(params => {
      this.index = +params['index']; 
   });
  }

}
