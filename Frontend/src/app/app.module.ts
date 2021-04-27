import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { SidenavComponent } from './components/sidenav/sidenav.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NewEntryComponent } from './components/new-entry/new-entry.component';
import { SettingsComponent } from './components/settings/settings.component';
import { CryptocurrencyComponent } from './components/cryptocurrency/cryptocurrency.component';
import { FooterComponent } from './components/footer/footer.component';
import { HeaderComponent } from './components/header/header.component';
import { CommentComponent } from './components/comment/comment.component';
import { EntryComponent } from './components/entry/entry.component';
import { LoginComponent } from './components/login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    EntryComponent,
    CommentComponent,
    HeaderComponent,
    FooterComponent,
    CryptocurrencyComponent,
    SettingsComponent,
    NewEntryComponent,
    SidenavComponent,
    LoginComponent
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
