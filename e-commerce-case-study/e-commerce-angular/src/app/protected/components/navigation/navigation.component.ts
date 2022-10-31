import { Component, OnInit } from '@angular/core';
//import { ApiService } from 'src/app/Service/api.service';
import { Router } from '@angular/router';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
import { ProductDialogComponent } from '../product-dialog/product-dialog.component';
import { ApiService } from 'src/app/services/api.service';
@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  public loggedType: string;
  public addShow:boolean = false;
  public currentUrl: string;
  constructor(
    //private auth: ApiService, 
    private dialog: MatDialog,
    private route: Router,
    private api: ApiService
  ) {
    this.currentUrl = this.route.url;
    if(this.currentUrl == "/protected/home"){
      this.addShow = true;
    }
    this.loggedType = "admin";
    if (this.api.getAuthType() == null) {
      this.loggedType = "home";
    } else {
      if (this.api.getAuthType() == "customer") {
        this.loggedType = "customer";
      } else if (this.api.getAuthType() == "admin") {
        this.loggedType = "admin";
      }
    }
  }

  ngOnInit() {
    //console.log(this.auth.getAuthType());

  }
  logout() {
    this.loggedType = "home";
    this.api.removeToken();
    this.api.logout();
    this.route.navigate(['/public']);
  }

  addProduct(){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.panelClass='custom-dialog-container';
    const dialogRef = this.dialog.open(ProductDialogComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(
      data => {
        if(data == "save"){
          this.route.navigateByUrl('/', {skipLocationChange: true}).then(() => {
              this.route.navigate([this.currentUrl]);
          });
        }
      }
  ); 
  }
}
