import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms'

import { AppComponent } from './app.component';
import { EntryComponent } from './entry/entry.component';
import { CommentComponent } from './comment/comment.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { CryptocurrencyComponent } from './cryptocurrency/cryptocurrency.component';
import { SettingsComponent } from './settings/settings.component';
import { NewEntryComponent } from './new-entry/new-entry.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [
    AppComponent,
    EntryComponent,
    CommentComponent,
    HeaderComponent,
    FooterComponent,
    CryptocurrencyComponent,
    SettingsComponent,
    NewEntryComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
