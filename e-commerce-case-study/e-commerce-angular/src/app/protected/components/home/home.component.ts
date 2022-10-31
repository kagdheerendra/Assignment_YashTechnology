import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/Model/product';
import { ApiService } from 'src/app/services/api.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  
  loggedType:string;
  products: Product[] = [];
  constructor(private api: ApiService, private snackbar: MatSnackBar) { 
    this.loggedType = "customer";
      if (this.api.getAuthType() == "customer") {
        this.loggedType = "customer";
      } else if (this.api.getAuthType() == "admin") {
        this.loggedType = "admin";
      }
  }

  ngOnInit(): void {
    if (this.api.isAuthenticated()) {
      this.api.getProducts().subscribe(
        res => {
          this.products = res.oblist;
        }
      );
    }
  }

  addToCart(product : Product) {
    console.log(product);
    this.api.addToCart(product).subscribe(res => {
      if(res.status === "200"){
        this.snackbar.open(`Product added successfully`, 'Close', {
          duration: 2000, horizontalPosition: 'right', verticalPosition: 'top'
        })
      }
    })
  }
}
