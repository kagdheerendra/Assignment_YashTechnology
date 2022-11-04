import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProtectedRoutingModule } from './protected-routing.module';
import { NavigationComponent } from './components/navigation/navigation.component';
import { HomeComponent } from './components/home/home.component';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatDialogModule} from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';
import {MatToolbarModule} from '@angular/material/toolbar';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { ProductDialogComponent } from './components/product-dialog/product-dialog.component';
import { OrderItemComponent } from './components/order-item/order-item.component';
import { CartItemComponent } from './components/cart-item/cart-item.component';
import { AddressDialogComponent } from './components/address-dialog/address-dialog.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptor } from '../auth-guard/AuthInterceptor';
import { BuynowComponent } from './components/buynow/buynow.component';


@NgModule({
  declarations: [
    NavigationComponent,
    HomeComponent,
    ProductDialogComponent,
    OrderItemComponent,
    CartItemComponent,
    AddressDialogComponent,
    BuynowComponent
  ],
  imports: [
    CommonModule,
    ProtectedRoutingModule,
    MatButtonModule,
    MatIconModule,
    MatTooltipModule,
    MatDialogModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    FormsModule,
    FlexLayoutModule,
    MatToolbarModule,
    MatCardModule,
    MatInputModule,
    MatProgressSpinnerModule
  ],
  providers:[{
    provide : HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi   : true,
  },],
  entryComponents: [ProductDialogComponent]
})
export class ProtectedModule { }
