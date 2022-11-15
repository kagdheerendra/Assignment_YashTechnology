import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from '../auth-guard/auth.guard';
import { CartItemComponent } from './components/cart-item/cart-item.component';
import { HomeComponent } from './components/home/home.component';
import { OrderItemComponent } from './components/order-item/order-item.component';

// Routes for child Module (protectedModule). Since public module is lazy loaded in in the 
// app-routing.module the full path is `/protected/home`
const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent,
    canActivate:[AuthGuard]
  },
  {
    path: 'home/order',
    component: OrderItemComponent,
    canActivate:[AuthGuard]
  },
  {
    path: 'home/cart',
    component: CartItemComponent,
    canActivate:[AuthGuard]
  },
  
  {
    path: '**',
    redirectTo: 'home',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProtectedRoutingModule { }
