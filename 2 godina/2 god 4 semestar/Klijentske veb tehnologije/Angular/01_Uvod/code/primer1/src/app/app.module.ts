import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule }   from '@angular/forms';

import { AppComponent } from './app.component';
import { CommentComponent } from './comment/comment.component';
import { CommentListComponent } from './comment-list/comment-list.component';
import { BlogEntryComponent } from './blog-entry/blog-entry.component';
import { BlogEntryListComponent } from './blog-entry-list/blog-entry-list.component';
import { BlogEntryFormComponent } from './blog-entry-form/blog-entry-form.component';
import { SearchEntryComponent } from './search-entry/search-entry.component';
import { EmphasizeDirective } from './directives/emphasize/emphasize.directive';
import { TitlePipe } from './pipes/title.pipe';


@NgModule({
  declarations: [
    AppComponent,
    CommentComponent,
    CommentListComponent,
    BlogEntryComponent,
    BlogEntryListComponent,
    BlogEntryFormComponent,
    SearchEntryComponent,
    EmphasizeDirective,
    TitlePipe
  ],
  imports: [
    BrowserModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
