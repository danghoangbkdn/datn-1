import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Category } from 'src/app/shared/models/category.model';
import { CategoryService } from 'src/app/shared/services/category.service';
import { MatDialog } from '@angular/material/dialog';
import { DeleteConfirmComponent } from 'src/app/shared/components/delete-confirm/delete-confirm.component';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.scss']
})
export class CategoryComponent implements OnInit {
  categories: Category[];
  rootCaterory: Category[];
  childCaterory: Category[];
  categoryForm: FormGroup;
  category = new Category();
  indexCate: number;

  constructor(
    private formBuilder: FormBuilder,
    private categoryService: CategoryService,
    public dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    console.log(this.category);

    this.intiCategoryForm();
    this.getAllCategory();
    this.getAllRootCategory();
  }

  public intiCategoryForm(): void {
    this.categoryForm = this.formBuilder.group({
      id: [this.category.id],
      name: [this.category.name, Validators.required],
      parentCategoryId: [this.category.parentCategoryId ]
    });
  }

  public getAllCategory(): void {
    this.categoryService.getAllCategory().subscribe((categories: Category[]) => {
      this.categories = categories;
      this.getCategory(categories[0].id, categories[0]);
    })
  }

  public getAllRootCategory(): void {
    this.categoryService.getAllRootCategory().subscribe((categories: Category[]) => {
      this.rootCaterory = categories;
    });
  }

  public getAllByParentCategory(parentId: number): void {
    this.categoryService.getAllByParentCategory(parentId).subscribe((categories: Category[]) => {
      this.childCaterory = categories;
    })
  }

  public getCategory(id: number, category: Category): void {
    this.getAllByParentCategory(id);
    this.category = category;
    this.intiCategoryForm();
    this.indexCate = this.categories.indexOf(category);
  }

  public addNew() {
    this.category = new Category();
    this.intiCategoryForm();
    this.indexCate = -1;
  }

  public save() {
    this.category = new Category(this.categoryForm.value);
    if (this.indexCate === -1) {
      this.categoryService.post(this.category).subscribe((cate: Category) => {
        this.categories.concat(cate);
        this.childCaterory = [];
      });
    } else {
      this.categoryService.put(this.category.id , this.category).subscribe((cate: Category) => {
        this.categories[this.indexCate] = cate;
        this.getCategory(cate.id, cate);
      });
    }
  }

  public delete(id: number, category: Category): void {
    const dialogRef = this.dialog.open(DeleteConfirmComponent, {
      data: {type: 'danh mục', name: category.name}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined) {
        this.indexCate = this.categories.indexOf(category);
        this.categoryService.delete(id).subscribe(res => {
          this.categories.splice(this.indexCate, 1);
        })
      }
    });
  }
}
