import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Category } from 'src/app/shared/models/category.model';
import { FileDTO } from 'src/app/shared/models/filedto.model';
import { Product } from 'src/app/shared/models/product.model';
import { CategoryService } from 'src/app/shared/services/category.service';
import { FileService } from 'src/app/shared/services/file.service';
import { ProductService } from 'src/app/shared/services/product.service';
import { AlertService } from 'src/app/_services';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.scss']
})
export class ProductFormComponent implements OnInit {
  productForm: FormGroup;
  product = new Product();
  images: string;
  allCategories: Category[];
  cateNum: number[];
  productCate = new FormControl();
  id = 0;

  constructor(
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private categoryService: CategoryService,
    private productService: ProductService,
    private fileService: FileService,
    private alertService: AlertService,
  ) {
    this.id = + this.route.snapshot.paramMap.get('id');
    this.images = '';
  }

  ngOnInit(): void {
    this.getAllCategory();
    this.initProductForm();
    this.id === 0 ? this.initProductForm() : this.getProduct(this.id);
  }

  public initProductForm(): void {
    this.cateNum = this.product.categories === undefined? [] : this.getCategoryIds(this.product.categories);
    this.productForm = this.formBuilder.group({
      id: [this.product.id === undefined? 0 : this.product.id],
      name: [this.product.name === undefined? '' : this.product.name],
      supplier: [this.product.supplier === undefined? '' : this.product.supplier],
      categories: [this.cateNum ],
      promotions: [{value: this.product.promotions === undefined? [] : this.product.promotions, disabled: true}],
      originPrice: [this.product.originPrice === undefined? 0 : this.product.originPrice],
      quantity: [this.product.quantity] === undefined? 0 : this.product.quantity,
      description: [this.product.description === undefined? '' : this.product.description],
      files: [this.product.files === undefined? [] : this.product.files],
      image: [this.product.image === undefined? [] : this.product.image],
    })
  }

  public getAllCategory(): void {
    this.categoryService.getAllCategory().subscribe((categories: Category[]) => {
      this.allCategories = categories;
    })
  }

  public getProduct(id: number): void {
    this.productService.getProduct(id).subscribe((product: Product) => {
      this.product = product;
      this.initProductForm();
    })
  }

  public getCategoryIds(categories: Category[]): number[] {
    return categories.map(category => category.id);
  }

  public getProductImg(images: string): string[] {
    return images.split(',');
  }

  public uploadFile(event) {
    const image = (event.target as HTMLInputElement).files[0];
    var file: any = new FormData();
    file.append('file', image);

    this.fileService.uploadFile(file).subscribe((file: FileDTO) => {
      this.images += ',' + file.path;
      this.alertService.success('Tải ảnh lên thành công');
    })
  }

  public findCategories(inputCategory: number[]): Category[] {
    let categories = [];
    inputCategory.forEach(cate => {
      categories.push(this.allCategories.find(x => x.id === cate));
    })

    return categories;
  }

  public save(): void {
    this.product = new Product(this.productForm.value);
    this.product.categories = this.findCategories(this.productForm.get('categories').value);
    this.product.image += this.product.image.length > 0 ? this.images : this.images.substring(1, this.images.length);

    const action = this.id === 0 ? this.productService.createProduct(this.product) : this.productService.updateProduct(this.id, this.product);
    action.subscribe((product: Product) => {
      this.product = product;
      this.initProductForm();
    })
  }
}
