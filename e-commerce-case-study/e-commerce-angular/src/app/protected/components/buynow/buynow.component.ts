import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Product } from 'src/app/Model/product';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import { ApiService } from 'src/app/services/api.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-buynow',
  templateUrl: './buynow.component.html',
  styleUrls: ['./buynow.component.scss']
})
export class BuynowComponent implements OnInit {

  product: Product = {
    productId: 0,
    description: '',
    price: 0,
    productName: '',
    quantity: 0,
    productimage : new File([],"")
  };

  form!: FormGroup;
  private fileToUpload!: File;
  imageUrl: string = "/assets/img/noimage.png";
  mode:string;
  totalSum : number = 0;
  constructor(
    private router: Router,
    private snackbar: MatSnackBar,
    private api: ApiService,
    private dialogRef: MatDialogRef<BuynowComponent>, @Inject(MAT_DIALOG_DATA) public data: any
  ) { 
    this.mode = this.data.mode;
  }

  ngOnInit(): void {
    this.product = this.data.product;
    this.product.quantity = 1;
    this.fileToUpload = this.product.productimage;
    this.imageUrl = "data:image/png;base64,"+this.fileToUpload;
  }

  close() {
    this.dialogRef.close("close");
  }

  onQuantityChange(quan: any){
      this.product.quantity = quan;
      this.totalSum = quan * this.product.price;
  }

  buyNow(desc:any, quan:any, price:any, prodname:any) {
    //this.product.quantity = quan;
    this.api.buyNowProduct(this.product).subscribe(res => {
      if(res.status == 200){
        this.snackbar.open(`Product order placed successfully`, 'Close', {
          duration: 2000, horizontalPosition: 'right', verticalPosition: 'top'
        })
        this.dialogRef.close("save");
      }
    });
  }
}
