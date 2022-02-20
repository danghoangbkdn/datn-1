import { formatDate } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Entity } from 'src/app/shared/models/entity.model';
import { Product } from 'src/app/shared/models/product.model';
import { EntityService } from 'src/app/shared/services/entity.service';

@Component({
  selector: 'app-entity',
  templateUrl: './entity.component.html',
  styleUrls: ['./entity.component.scss']
})
export class EntityComponent implements OnInit {
  @Input() id: number;
  entityForm: FormGroup;
  entity: Entity;

  constructor(
    private formBuilder: FormBuilder,
    private entityService: EntityService
  ) {
    this.entity = new Entity();
  }

  ngOnInit(): void {
    this.getEntityByProductId(this.id);
  }

  public initForm(): void {
    this.entityForm = this.formBuilder.group({
      id: [this.entity.id],
      storageId: [this.entity.storageId],
      productId: [this.entity.productId],
      entryDay: [this.entity.entryDay === undefined ? this.entity.entryDay : Date.now()],
      totalQuantity: [this.entity.totalQuantity === undefined ? 0 : this.entity.totalQuantity, Validators.pattern('[0-9]*')],
      quantity: [{value: this.entity.quantity, disabled: true}],
    });
  }

  public getEntityByProductId(id: number): void {
    this.entityService.getEntityByProductId(id).subscribe((entity: Entity) => {
      console.log(entity);
      this.entity = entity;
      this.initForm();
    })
  }

  public save(): void {
    this.entity = new Entity(this.entityForm.value);
    console.log(this.entity);
    this.entity.entryDay = new Date(this.entity.entryDay);
    this.entity.totalQuantity = + this.entity.totalQuantity;
    console.log(this.entity);

    this.entityService.updateEntity(this.entity.id, this.entity).subscribe((entity: Entity) => {
      this.entity = entity;
      this.initForm();
    })
  }

}
