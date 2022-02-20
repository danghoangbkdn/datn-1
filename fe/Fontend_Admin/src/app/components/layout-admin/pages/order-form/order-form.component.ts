import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Order } from 'src/app/shared/models/order.model';
import { OrderService } from 'src/app/shared/services/order.service';
import { AlertService } from 'src/app/_services/alert.service';

class StatusOrder {
  name: string;
  value: string;
}

@Component({
  selector: 'app-order-form',
  templateUrl: './order-form.component.html',
  styleUrls: ['./order-form.component.scss']
})
export class OrderFormComponent implements OnInit {
  id: number;
  order: Order;
  statusOrder: StatusOrder[];
  orderForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private orderService: OrderService,
    private alertService: AlertService
  ) {
    this.id = + this.route.snapshot.paramMap.get('id');
  }

  ngOnInit(): void {
    this.getOrderById(this.id);
    this.initStatusOrder();
  }

  public initStatusOrder(): void {
    this.statusOrder = [
      {name: 'Đang xử lý', value: 'Đang xử lý'},
      {name: 'Đã xác nhận', value: 'Đã xác nhận'},
      {name: 'Đang giao hàng', value: 'Đang giao hàng'},
      {name: 'Đã giao hàng', value: 'Đã giao hàng'},
      {name: 'Đã hủy đơn', value: 'Đã hủy đơn'},
    ];
  }

  public initOrderForm() {
    this.orderForm = this.formBuilder.group({
      status: [this.order.status, Validators.required],
      shippingFee: [this.order.shippingFee || '15000', Validators.required],
      deliver: [this.order.deliver || '', Validators.required],
      deliveryDate: [this.order.deliveryDate || '', Validators.required]
    })
  }

  public getOrderById(id: number): void {
    this.orderService.getById(id).subscribe((order: Order) => {
      this.order = order;
      this.initOrderForm();
    })
  }

  public save(): void {
    if (!this.orderForm.valid) {
      this.alertService.error('Chưa nhập đầy đủ thông tin!');
      return;
    }

    this.order.status = this.orderForm.value.status;
    this.order.shippingFee = this.orderForm.value.shippingFee;
    this.order.deliver = this.orderForm.value.deliver;
    this.order.deliveryDate = this.orderForm.value.deliveryDate;
    console.log(this.order);

    this.orderService.updateById(this.id, this.order).subscribe(order => {
      this.order = order;
      this.initOrderForm();
    })
  }
}
