import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import { ApiService } from 'src/app/services/api.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
@Component({
  selector: 'app-product-dialog',
  templateUrl: './product-dialog.component.html',
  styleUrls: ['./product-dialog.component.scss']
})
export class ProductDialogComponent implements OnInit {

  form!: FormGroup;
  private fileToUpload!: File;
  imageUrl: string = "";
  constructor(
    private router: Router,
    private snackbar: MatSnackBar,
    private api: ApiService,
    private dialogRef: MatDialogRef<ProductDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: any
  ) {
   }

  ngOnInit(): void {
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

  addProd(desc:any, quan:any, price:any, prodname:any, image:any) {
    console.log(desc+"::"+quan+"::"+price+"::"+prodname);
    this.api.addProduct(desc.value, quan.value, price.value, prodname.value, this.fileToUpload).subscribe(res => {
      console.log(res);
      if(res.status == 200){
        this.snackbar.open(`Product add successfully`, 'Close', {
          duration: 2000, horizontalPosition: 'right', verticalPosition: 'top'
        })
        this.dialogRef.close("save");
      }
    });
  }
}
