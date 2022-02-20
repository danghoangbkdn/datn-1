import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { CartProduct } from 'src/app/shared/models/cart-product.model';
import { OrderProduct } from 'src/app/shared/models/order-product.model';
import { Product } from 'src/app/shared/models/product.model';
import { AlertifyService } from 'src/app/shared/services/alertify.service';
import { CartService } from 'src/app/shared/services/cart.service';
import { ProductService } from 'src/app/shared/services/product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {
  productId: number;
  product: Product;
  index = 0;
  quantityProduct = 1;

  constructor(
    private productService: ProductService,
    private router: Router,
    private route: ActivatedRoute,
    private cartService: CartService,
    private alertService: AlertifyService
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(
        (params: Params) => {
          this.productId = params.id;
          console.log(this.productId);
        }
      );
    this.getBookById(this.productId);
  }

  public getBookById(id: number) {
    this.productService.getProductById(id).subscribe((res: Product) => {
      this.product = res;
      console.log(this.product);
    })
  }

  public addToCart(product: Product): void {
    let cartProduct = new CartProduct({
      cartId: 0,
      productId: product.id,
      productName: product.name,
      productCover:  product.covers[0],
      productDescription: product.description,
      price: product.originPrice,
      quantity: this.quantityProduct,
      status: false
    });

    this.cartService.addToCart(cartProduct).subscribe(_ => {
      this.alertService.success('Đã thêm vào giỏ hàng!');
    })
  }


}
