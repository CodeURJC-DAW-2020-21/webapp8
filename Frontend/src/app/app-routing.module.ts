import { Routes, RouterModule } from '@angular/router';

import { NewEntryComponent } from './components/new-entry/new-entry.component';
import { SettingsComponent } from './components/settings/settings.component';
import { CryptocurrencyComponent } from './components/cryptocurrency/cryptocurrency.component';
import { EntryComponent } from './components/entry/entry.component';
import { MainForumComponent } from './components/main-forum/main-forum.component';


const routes: Routes = [
  { path: 'criptocurrencies', component: CryptocurrencyComponent },
  { path: 'newEntry', component: NewEntryComponent },
  { path: 'settings', component: SettingsComponent },
  { path: 'main', component: MainForumComponent },
  { path: '', redirectTo: 'main', pathMatch: 'full' },
  { path: '**', component:  EntryComponent} //Aqui deberia ir la pagina de error si no introducimos nada a lo que acceder
]

export const routing = RouterModule.forRoot(routes);