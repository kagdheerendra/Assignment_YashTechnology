import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import { Address } from 'src/app/Model/address';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-address-dialog',
  templateUrl: './address-dialog.component.html',
  styleUrls: ['./address-dialog.component.scss']
})
export class AddressDialogComponent implements OnInit {

  isAddPresent: boolean = false;
  addressForm = new FormGroup({
    address: new FormControl(null, [Validators.required]),
    city: new FormControl(null, [Validators.required]),
    state: new FormControl(null, [Validators.required]),
    country: new FormControl(null, [Validators.required]),
    zipcode: new FormControl(null, [Validators.required, Validators.minLength(5), Validators.maxLength(7)]),
    phoneNumber: new FormControl(null, [Validators.required, Validators.pattern("^((\\+91-?)|0)?[0-9]{10}$")])
  })

  constructor(
    private api: ApiService,
    private snackbar: MatSnackBar,
    private dialogRef: MatDialogRef<AddressDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  ngOnInit(): void {
    this.api.getAddress().subscribe(res => {
      if (res.map != null) {
        this.addressForm.setValue({
          address: res.map.address,
          city: res.map.city,
          state: res.map.state,
          country: res.map.country,
          zipcode: res.map.zipcode,
          phoneNumber: res.map.phonenumber
        });
        this.isAddPresent = true;
      }
    }, err => {
      console.log(err);
    });
  }

  close() {
    this.dialogRef.close('close');
  }

  save() {
    if (!this.addressForm.valid) {
      return;
    }
    let address = {
      address : this.addressForm.value.address || '',
      city: this.addressForm.value.city|| '',
      state: this.addressForm.value.state || '',
      country: this.addressForm.value.country|| '',
      zipcode: this.addressForm.value.zipcode || '',
      phonenumber: this.addressForm.value.phoneNumber || '',
    }
    
    this.api.addOrUpdateAddress(address).subscribe(res => {
      if(res.status === '200'){
        console.log(res);
        if(this.isAddPresent){
          this.snackbar.open(`Address updeted successfully`, 'Close', {
            duration: 2000, horizontalPosition: 'right', verticalPosition: 'top'
          })
        }else {
          this.snackbar.open(`Address added successfully`, 'Close', {
            duration: 2000, horizontalPosition: 'right', verticalPosition: 'top'
          })
        }
        this.dialogRef.close('save');
      }
    });
  }
}
