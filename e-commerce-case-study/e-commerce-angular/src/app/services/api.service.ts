import { Injectable, Inject } from '@angular/core';
import { HttpClient, HttpHeaders, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
//import { Product } from '../Model/product';
//import { User } from '../Model/user';
import { SESSION_STORAGE, StorageService } from 'ngx-webstorage-service';
//import { Address } from '../Model/address';
import { environment } from 'src/environments/environment';
import { User } from '../Model/user';
import { Product } from '../Model/product';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  
  constructor(@Inject(SESSION_STORAGE) private storage: StorageService, private http: HttpClient) {

  }

    // Registering new users to the system
    register(user: any): Observable<any> {
      return this.http.post(environment.baseUrl+environment.signupUrl,
        JSON.stringify(user),
        {
          headers:
            { 'Content-Type': 'application/json' }
        });
    }

      // validating user credentials
  login(user: any): Observable<any> {
    return this.http.post(environment.baseUrl+environment.loginUrl,
      JSON.stringify(user),
      {
        headers:
          { 'Content-Type': 'application/json' }
      });
  }

     // Fetching all the products
     getProducts(): Observable<any> {
      return this.http.get<any>(environment.baseUrl+environment.productsUrl);
    }

    logout(){
      this.http.get<any>(environment.baseUrl+environment.logoutUrl);
    }

    // Add product in the system
    addProduct( desc: string,
      quan: string, price: string, prodname: string, image: File): Observable<any> {
      const formData: FormData = new FormData();
      formData.append("description", desc);
      formData.append("price", price);
      formData.append("productname", prodname);
      formData.append("quantity", quan);
      formData.append("file", image);
      return this.http.post<any>(environment.baseUrl+environment.addProductUrl, formData);
  
    }

      // Add Products to the Cart
  addToCart(product: Product): Observable<any> {
    return this.http.get<any>(environment.baseUrl+environment.addToCartUrl +"?productId="+product.productId);
  }

    // View Cart items
    getCartItems(): Observable<any> {
      return this.http.get<any>(environment.baseUrl+environment.viewCartUrl);
    }

      // update items quantity in the cart
  updateCartItem(prodid: number, quant: number): Observable<any> {
    var map = {
      "id":prodid,
      "quantity":quant
    }
    return this.http.put<any>(environment.baseUrl+environment.updateCartUrl, map);
  }

  // delete cart Item 
  deleteCartItem(bufdid: number): Observable<any> {
    return this.http.delete<any>(environment.baseUrl+environment.deleteCartUrl + "?bufcartid=" + bufdid);
  }

  // Authentication Methods 

  isAuthenticated(): boolean {
    return this.getToken() !== undefined;
  }

  storeToken(token: string, auth_type: string) {
    this.storage.set("auth_token", token);
    this.storage.set("auth_type", auth_type);
  }

  getAuthType(): string {
    if (this.storage.get("auth_type") !== null) {
      return this.storage.get("auth_type");
    }else {
      return "";
    }
  }

  getToken() {
    return this.storage.get("auth_token");
  }

  removeToken() {
    this.storage.remove("auth_type");
    return this.storage.remove("auth_token");
  }

}
