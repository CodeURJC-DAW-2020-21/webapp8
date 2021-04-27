import { Routes, RouterModule } from '@angular/router';

import { NewEntryComponent } from './components/new-entry/new-entry.component';
import { SettingsComponent } from './components/settings/settings.component';
import { CryptocurrencyComponent } from './components/cryptocurrency/cryptocurrency.component';
import { MainForumComponent } from './components/main-forum/main-forum.component';
import { UserlistComponent } from './components/userlist/userlist.component';
import { ErrorComponent} from './components/error/error.component';

const routes: Routes = [
  { path: 'cryptocurrencies', component: CryptocurrencyComponent },
  { path: 'userslist', component: UserlistComponent },
  { path: 'userslist2', redirectTo: 'userslist', pathMatch: 'full' },
  { path: 'newEntry', component: NewEntryComponent },
  { path: 'settings', component: SettingsComponent },
  { path: 'main', component: MainForumComponent },
  { path: '', redirectTo: 'main', pathMatch: 'full' },
  { path: '**', component:  ErrorComponent}
]

export const routing = RouterModule.forRoot(routes);
