import { Routes } from '@angular/router';
import { authGuard } from './guards/auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: 'clients', pathMatch: 'full' },
  {
    path: 'login',
    loadComponent: () => import('./components/login/login.component').then(m => m.LoginComponent)
  },
  {
    path: 'clients',
    canActivate: [authGuard],
    loadComponent: () => import('./components/clients/clients.component').then(m => m.ClientsComponent)
  },
  {
    path: 'contrats',
    canActivate: [authGuard],
    loadComponent: () => import('./components/contrats/contrats.component').then(m => m.ContratsComponent)
  },
  {
    path: 'paiements',
    canActivate: [authGuard],
    loadComponent: () => import('./components/paiements/paiements.component').then(m => m.PaiementsComponent)
  },
  { path: '**', redirectTo: 'clients' }
];
