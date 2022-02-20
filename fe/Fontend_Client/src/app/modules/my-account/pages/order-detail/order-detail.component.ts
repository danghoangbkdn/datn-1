import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { StatusOrder } from 'src/app/shared/constants/StatusOrder';
import { Order } from 'src/app/shared/models/order.model';
import { OrderService } from 'src/app/shared/services/order.service';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmComponent } from 'src/app/shared/components/confirm/confirm.component';
import { AlertifyService } from 'src/app/shared/services/alertify.service';

@Component({
  selector: 'app-order-detail',
  templateUrl: './order-detail.component.html',
  styleUrls: ['./order-detail.component.scss']
})
export class OrderDetailComponent implements OnInit {
  processingState = StatusOrder.DANG_XU_LY;
  orderId: number;
  order: Order;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private orderService: OrderService,
    public dialog: MatDialog,
    public alertService: AlertifyService
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(
      (params: Params) => {
        this.orderId = params.id;
        console.log(this.orderId);
      }
    );
    this.getOrderById(this.orderId);
  }

  public getOrderById(id: number): void {
    this.orderService.getById(id).subscribe((order: Order) => {
      this.order = order;
      console.log(this.order);
    });
  }

  public cancelOrder(): void {
    const dialogRef = this.dialog.open(ConfirmComponent, {
      data: 'Bạn chắc chắn muốn hủy đơn hàng?'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined) {
        this.order.status = StatusOrder.HUY_DON;
        this.orderService.update(this.order).subscribe(
          (order: Order) => {
          this.order = order;
          this.alertService.success('Đã hủy đơn hàng!');
          },
          (_) => {
            this.alertService.error('Đơn hàng chưa được cập nhật!');
          }
        )
      }
    });
  }

}
