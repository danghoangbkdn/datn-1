import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { DeleteConfirmComponent } from 'src/app/shared/components/delete-confirm/delete-confirm.component';
import { Order } from 'src/app/shared/models/order.model';
import { OrderService } from 'src/app/shared/services/order.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit {
  orders: Order[];
  indexOrder: number;

  constructor(
    private router: Router,
    private orderService: OrderService,
    public dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.getAllOrder();
  }

  public goOrderDetail(id: number): void {
    this.router.navigate([`/orders/update/${id}`]);
  }

  private getAllOrder(): void {
    this.orderService.getAll().subscribe((orders: Order[]) => {
      this.orders = orders;
    })
  }

  public deleteOrder(id: number, order: Order) {
    const dialogRef = this.dialog.open(DeleteConfirmComponent, {
      data: {type: 'đơn hàng', name: `id: ${id}`}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined) {
        this.indexOrder = this.orders.indexOf(order);
        this.orderService.deteleById(id).subscribe(res => {
          this.orders.splice(this.indexOrder, 1);
        })
      }
    });
  }
}
