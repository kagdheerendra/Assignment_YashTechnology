import { Component, OnInit } from '@angular/core';
//import { ApiService } from 'src/app/Service/api.service';
//import { Cart } from 'src/app/Model/cart';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
import { AddressDialogComponent } from '../address-dialog/address-dialog.component';
import { Cart } from 'src/app/Model/cart';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-cart-item',
  templateUrl: './cart-item.component.html',
  styleUrls: ['./cart-item.component.css']
})
export class CartItemComponent implements OnInit {


  showLoader:boolean = true;
  cartlist: Cart[] = [];
  totalSum: number = 0;
  constructor(
    private api: ApiService, 
    private dialog: MatDialog,
    private route: Router) {

  }

  ngOnInit() {
    if(this.api.isAuthenticated() && this.api.getAuthType() === 'customer'){
      this.api.getCartItems().subscribe((res: { oblist: any[]; }) => {
        let oblist = res.oblist;
        oblist.forEach(item =>{
           if(item.orderId == 0 && item.accessByCart){
            this.cartlist.push(item);
           }
        });
       //this.cartlist = res.oblist;
       if(this.cartlist.length > 0){
        this.cartlist.forEach(value => {
          value['subTotal'] = (value.quantity * value.price);
          //this.totalSum = (value.quantity * value.price);
        });
       }
       this.getTotalCast();
       this.showLoader = false;
     });
    }else {
      this.route.navigate(['/public']);
    }
  }
  updateCart(id: any, quantity: any) {
    this.showLoader = true;
    this.cartlist = [];
    this.totalSum = 0;
    this.api.updateCartItem(id.value, quantity.value).subscribe((res: { oblist: any[]; }) => {
       //this.cartlist = res.oblist;
       let oblist = res.oblist;
       oblist.forEach(item =>{
          if(item.orderId == 0 && item.accessByCart){
           this.cartlist.push(item);
          }
       });
      this.cartlist.forEach(value => {
        value['subTotal'] = (value.quantity * value.price);
        //this.totalSum = (value.quantity * value.price);
      });
      this.getTotalCast();
      this.showLoader = false;
    });
  } 
  deleteItem(id: any) {
    this.showLoader = true;
    this.cartlist = [];
    this.totalSum = 0;
     this.api.deleteCartItem(id.value).subscribe((res: { oblist: any[]; }) => {
       //this.cartlist = res.oblist;
       let oblist = res.oblist;
       oblist.forEach(item =>{
          if(item.orderId == 0 && item.accessByCart){
           this.cartlist.push(item);
          }
       });
      this.cartlist.forEach(value => {
        value['subTotal'] = (value.quantity * value.price);
        //this.totalSum = (value.quantity * value.price);
      });
      this.getTotalCast();
      this.showLoader = false;
    });
  }

  placeOrder() {
    // this.api.placeOrder().subscribe((res: any) => {
    //   console.log(res);
    // });
    //this.route.navigate(['/home']);
    this.openAddessDialog();
  }

  openAddessDialog(){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.panelClass='custom-dialog-container';
    dialogConfig.width = '450px';
    dialogConfig.height = '510px';
    const dialogRef = this.dialog.open(AddressDialogComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(
      data => {
      if(data === 'save'){
        this.api.placeOrder().subscribe((res: any) => {
          console.log(res);
          this.route.navigate(['/protected/home']);
        });
      }
      }
  );
  }

  getTotalCast(){
    this.cartlist.forEach((cart)=>{
        this.totalSum = this.totalSum + cart.subTotal!;
    });
  }
}
