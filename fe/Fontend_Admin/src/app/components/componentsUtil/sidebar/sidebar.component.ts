import { Component, OnInit, OnDestroy, SystemJsNgModuleLoader } from '@angular/core';
import { User } from 'src/app/_models';

declare const $: any;
declare interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
}
export const ROUTES: RouteInfo[] = [
    { path: '/dashboard', title: 'Thống kê - Báo cáo',  icon: 'dashboard', class: '' },
    { path: '/user-profile', title: 'Tài khoản',  icon:'person', class: '' },
    { path: '/user-list', title: 'Người dùng',  icon:'content_paste', class: '' },
    { path: '/categories', title: 'Danh mục',  icon: 'content_paste', class: '' },
    { path: '/products', title: 'Sản phẩm',  icon: 'content_paste', class: '' },
    { path: '/orders', title: 'Đơn hàng',  icon:'content_paste', class: '' }
];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit, OnDestroy {
  menuItems: any[];
  currentUSer: User;

  constructor() { }

  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
  }

  ngOnDestroy() {
    //this.current
  }

}
