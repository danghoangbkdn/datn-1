import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Category } from 'src/app/shared/models/category.model';
import { Product } from 'src/app/shared/models/product.model';
import { CategoryService } from 'src/app/shared/services/category.service';
import { ProductService } from 'src/app/shared/services/product.service';

class Search {
  name: string;
  category: number;
}

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {
  categories: Category[];
  products: Product[];
  searchForm: FormGroup;
  searchObj = new Search();

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private categoryService: CategoryService,
    private productService: ProductService
  ) { }

  ngOnInit(): void {
    this.getAllCategory();
    this.getAllProduct();
    this.initSearchForm();
  }

  public initSearchForm(): void {
    this.searchForm = this.formBuilder.group({
      name: [this.searchObj.name],
      category: [this.searchObj.category],
    });
  }

  public getAllCategory(): void {
    this.categoryService.getAllCategory().subscribe((categories: Category[]) => {
      this.categories = categories;
    })
  }

  public getAllProduct(): void {
    this.productService.getAllProduct().subscribe((products: Product[]) => {
      this.products = products;
    })
  }

  public createProduct(): void {
    this.router.navigate(['/products/create']);
  }

  public updateProduct(id: number): void {
    this.router.navigate([`/products/update/${id}`]);
  }

}
