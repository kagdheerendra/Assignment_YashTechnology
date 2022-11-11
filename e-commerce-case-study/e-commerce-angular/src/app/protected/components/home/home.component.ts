import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/Model/product';
import { ApiService } from 'src/app/services/api.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { NavigationExtras, Router } from '@angular/router';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ProductDialogComponent } from '../product-dialog/product-dialog.component';
import { BuynowComponent } from '../buynow/buynow.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  loggedType: string;
  products: Product[] = [];
  public currentUrl: string;
  constructor(private api: ApiService, private snackbar: MatSnackBar, private route: Router, private dialog: MatDialog) {
    this.currentUrl = this.route.url;
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

  addToCart(product: Product) {
    this.api.getCartItems().subscribe(items => {
      let oblist = items.oblist;
      if (oblist.length > 0) {
        let isProductPresent = false;
        oblist.forEach((item: any) => {
          if (item.productId === product.productId) {
            this.snackbar.open(`Product already added to cart`, 'Close', {
              duration: 2000, horizontalPosition: 'right', verticalPosition: 'top'
            })
            isProductPresent = true;
          } else {
            isProductPresent = false;
          }
        });
        if(!isProductPresent)this.save(product);
      } else {
        this.save(product);
      }
    });
  }

  save(product: Product) {
    this.api.addToCart(product).subscribe(res => {
      if (res.status === "200") {
        this.snackbar.open(`Product added successfully`, 'Close', {
          duration: 2000, horizontalPosition: 'right', verticalPosition: 'top'
        })
      }
    })
  }

  delProd(prodid: any) {
    this.api.deleteProduct(prodid).subscribe(res => {
      if (res.status == 200) {
        this.products = res.oblist;
        this.snackbar.open(`Product deleted successfully`, 'Close', {
          duration: 2000, horizontalPosition: 'right', verticalPosition: 'top'
        })
        this.ngOnInit();
      }
    });
  }
  edit(prodid: any) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.panelClass = 'custom-dialog-container';
    dialogConfig.data = {
      'mode': 'edit',
      'productId': prodid
    }
    const dialogRef = this.dialog.open(ProductDialogComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(
      data => {
        if (data === "save") {
          this.route.navigateByUrl('/', { skipLocationChange: true }).then(() => {
            this.route.navigate([this.currentUrl]);
          });
        }
      }
    );
  }

  buyNow(product: Product) {

    this.api.checkOrderStatusByProductIdUrl(product.productId).subscribe(res => {
      if (res.status == 200) {
        this.snackbar.open(res.message, 'Close', {
          duration: 2000, horizontalPosition: 'right', verticalPosition: 'top'
        })
      } else {
        this.openDialogBox(product);
      }
    });

  }

  openDialogBox(product: Product) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.panelClass = 'custom-dialog-container';
    dialogConfig.data = {
      'product': product
    }
    const dialogRef = this.dialog.open(BuynowComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(
      data => {
        if (data === "save") {
          this.route.navigateByUrl('/', { skipLocationChange: true }).then(() => {
            this.route.navigate([this.currentUrl]);
          });
        }
      }
    );
  }

}
