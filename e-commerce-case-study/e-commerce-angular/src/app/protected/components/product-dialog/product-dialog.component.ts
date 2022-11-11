import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import { ApiService } from 'src/app/services/api.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Product } from 'src/app/Model/product';
@Component({
  selector: 'app-product-dialog',
  templateUrl: './product-dialog.component.html',
  styleUrls: ['./product-dialog.component.scss']
})
export class ProductDialogComponent implements OnInit {

  product: Product = {
    productId: 0,
    description: "",
    price: 0,
    productName: '', 
    quantity: 0,
    productimage : new File([],"")
  };

  form!: FormGroup;
  private fileToUpload!: File;
  imageUrl: string = "/assets/img/noimage.png";
  mode:string;

  productForm = new FormGroup({
    productName: new FormControl("", [Validators.required,  Validators.maxLength(50)]),
    description: new FormControl("", [Validators.required,  Validators.maxLength(250)]),
    quantity: new FormControl(null, [Validators.required, Validators.pattern("^-?[0-9][^\.]*$")]),
    price: new FormControl(null, [Validators.required])
  })

  constructor(
    private router: Router,
    private snackbar: MatSnackBar,
    private api: ApiService,
    private dialogRef: MatDialogRef<ProductDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: any
  ) {
      this.mode = this.data.mode;
      
   }

  ngOnInit(): void {
    if(this.data.hasOwnProperty('productId')){
      this.api.getProductById(this.data.productId).subscribe(
        res => {
          res.oblist.forEach((item: Product) => {
            this.product = item;
            this.productForm.setValue({
              description: item.description ,
              price: item.price,
              productName: item.productName,
              quantity: item.quantity
            });
            this.fileToUpload = item.productimage;
            this.imageUrl = "data:image/png;base64,"+this.fileToUpload;
          });
        }
      );
    }
  }

  close() {
    this.dialogRef.close("close");
  }

  handleFileInput(event: any) {
    let file = event.target.files;
    this.fileToUpload = file.item(0) as File;
    var reader = new FileReader();
    reader.onload = (event: any) => {
      this.imageUrl = event.target.result;
    }
    reader.readAsDataURL(this.fileToUpload);
  }

  addProd() {
    let product : Product = {
      productId : this.data.productId,
      description: this.productForm.value.description!,
      price: this.productForm.value.price,
      productName: this.productForm.value.productName!,
      quantity: this.productForm.value.quantity,
      productimage : this.fileToUpload
    }
    this.api.addProduct(product).subscribe(res => {
      if(res.status == 200){
        this.snackbar.open(`Product add successfully`, 'Close', {
          duration: 2000, horizontalPosition: 'right', verticalPosition: 'top'
        })
        this.dialogRef.close("save");
      }
    });
  }

  updateProd() {
    let product : Product =  {
      productId : this.data.productId,
      description: this.productForm.value.description!,
      price: this.productForm.value.price,
      productName: this.productForm.value.productName!,
      quantity: this.productForm.value.quantity,
      productimage : this.fileToUpload
    }
    this.api.updateProduct(product).subscribe(res => {
      if(res.status == 200){
        this.snackbar.open(`Product updated successfully`, 'Close', {
          duration: 2000, horizontalPosition: 'right', verticalPosition: 'top'
        })
        this.dialogRef.close("save");
      }
    });
  }
}
