// src/app/app.routes.ts
import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'orders',
    // Lazy Loading - loading modules on demand
    loadComponent: () => import('./features/orders/order-dashboard/order-dashboard.component')
      .then(m => m.OrderDashboardComponent)
  },
  { path: '', redirectTo: '/orders', pathMatch: 'full' }
];