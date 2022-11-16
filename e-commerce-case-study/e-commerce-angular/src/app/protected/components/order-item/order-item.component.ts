import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/services/api.service';
import { MatSnackBar } from '@angular/material/snack-bar';
//import { ApiService } from 'src/app/Service/api.service';

@Component({
  selector: 'order-item',
  templateUrl: './order-item.component.html',
  styleUrls: ['./order-item.component.css']
})
export class OrderItemComponent implements OnInit {

  orderlist: any[] = [];
  constructor(private route: Router, 
    private api: ApiService,
    private snackbar: MatSnackBar,
    ) { }

  ngOnInit() {
    if (this.api.isAuthenticated() && this.api.getAuthType() === 'admin') {
      this.getOrderList();
    }else{
      this.route.navigate(['/public']);
    }
  }

  approve(orderid:any) {
    let order = {
      "orderId": orderid,
      "orderStatus": "APPROVED"
    }
    this.api.updateStatusForOrder( order).subscribe(res => {
      if(res.status === '200'){
        this.snackbar.open(`Approved successfully`, 'Close', {
          duration: 2000, horizontalPosition: 'right', verticalPosition: 'top'
        })
        this.getOrderList();
      }
      
    });
  }

  decline(orderid:any) {
    let order = {
      "orderId": orderid,
      "orderStatus": "DECLINED"
    }
    this.api.updateStatusForOrder(order).subscribe(res => {
      if(res.status === '200'){
        this.snackbar.open(`Approved successfully`, 'Close', {
          duration: 2000, horizontalPosition: 'right', verticalPosition: 'top'
        })
        this.getOrderList();
      }
    });
  }

  getOrderList() {
    this.api.getOrders().subscribe(res => {
      if(res.status === "200"){
        this.orderlist = res.orderlist;
      }
    });
  }

}
