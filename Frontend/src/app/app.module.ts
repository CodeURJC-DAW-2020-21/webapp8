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
import { MainForumComponent } from './components/main-forum/main-forum.component';
import { UserlistComponent } from './components/userlist/userlist.component';
import { RouterModule } from '@angular/router';
import { routing } from './app-routing.module';
import { ErrorComponent } from './components/error/error.component';
import { RegisterComponent } from './components/register/register.component';
import { FavCryptoComponent } from './components/fav-crypto/fav-crypto.component';
import { NewCommentComponent } from './components/new-comment/new-comment.component';
import { ForgotPassComponent } from './components/forgot-pass/forgot-pass.component';
import { SendEmailComponent } from './components/send-email/send-email.component';
import { FriendsComponent } from './components/friends/friends.component';

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
    LoginComponent,
    UserlistComponent,
    ErrorComponent,
    MainForumComponent,
    RegisterComponent,
    FavCryptoComponent,
    NewCommentComponent,
    ForgotPassComponent,
    SendEmailComponent,
    FriendsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    RouterModule,
    routing
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
