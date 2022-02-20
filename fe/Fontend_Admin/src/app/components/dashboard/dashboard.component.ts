import { element } from 'protractor';
import { IPostListData, IPostData } from './../../_models/post';
import { PostService } from './../../_services/post.service';
import { IUserListData, IUserData } from '../../_models/user';
import { Component, OnInit } from '@angular/core';
import * as Chartist from 'chartist';
import { UserService } from 'src/app/_services/user.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  totalUser = 0;
  totalPost = 0;

  constructor(
    private userService: UserService,
    private postService: PostService) {
  }

  startAnimationForLineChart(chart){
      let seq: any, delays: any, durations: any;
      seq = 0;
      delays = 80;
      durations = 500;

      chart.on('draw', function(data) {
        if(data.type === 'line' || data.type === 'area') {
          data.element.animate({
            d: {
              begin: 600,
              dur: 700,
              from: data.path.clone().scale(1, 0).translate(0, data.chartRect.height()).stringify(),
              to: data.path.clone().stringify(),
              easing: Chartist.Svg.Easing.easeOutQuint
            }
          });
        } else if(data.type === 'point') {
              seq ++;
              data.element.animate({
                opacity: {
                  begin: seq * delays,
                  dur: durations,
                  from: 0,
                  to: 1,
                  easing: 'ease'
                }
              });
          }
      });

      seq = 0;
  }
  startAnimationForBarChart(chart){
      let seq2: any, delays2: any, durations2: any;

      seq2 = 0;
      delays2 = 30;
      durations2 = 500;
      chart.on('draw', function(data) {
        if(data.type === 'bar'){
            seq2++;
            data.element.animate({
              opacity: {
                begin: seq2 * delays2,
                dur: durations2,
                from: 0,
                to: 1,
                easing: 'ease'
              }
            });
        }
      });

      seq2 = 0;
  }

  private getUsersMonthlyFigure() {
    let keys: string[] = [];
    let values: number[] = [];
    this.userService.getUsersMonthlyFigure().subscribe((response: IUserListData) => {
        response.Data.forEach((element: IUserData) => {
        keys.push(element.to);
        values.push(element.amount);
        this.totalUser += element.amount;});
        this.drawUserChart(keys.reverse(), values.reverse());
    });
  }

  private getPostsMonthlyFigure() {
    let keys2: string[] = [];
    let values2: number[] = [];
    this.postService.getPostsMonthlyFigure().subscribe((response: IPostListData) => {
        response.Data.forEach((element: IPostData) => {
          keys2.push(element.to);
          values2.push(element.amount);
          this.totalPost += element.amount;
        });
        this.drawPostChart(keys2.reverse(), values2.reverse());
    })
  }

  ngOnInit() {
    this.getUsersMonthlyFigure();
    this.getPostsMonthlyFigure();
  }

  private drawUserChart(keys: string[], values: number[]) {
      const dataMonthlyUserRegister: any = {
        // labels: trục x, series: giá trị theo cot y
          labels: keys,
          series: [
              values
          ]
      };

      const optionsMonthlyUserRegister: any = {
          lineSmooth: Chartist.Interpolation.cardinal({
              tension: 0
          }),
          low: 0,
          high: 8,
          chartPadding: { top: 0, right: 0, bottom: 0, left: 0},
      };

      const monthlyUserRegsisterChart = new Chartist.Line('#dailySalesChart', dataMonthlyUserRegister, optionsMonthlyUserRegister);

      this.startAnimationForLineChart(monthlyUserRegsisterChart);
  }

  private drawPostChart(keys: string[], values: number[]) {
    const datawebsiteViewsChart = {
      labels: keys,
      series: [
        values
      ]
    };

    const optionswebsiteViewsChart = {
        axisX: {
            showGrid: false
        },
        low: 0,
        high: 16,
        chartPadding: { top: 0, right: 0, bottom: 0, left: 0}
    };

    const responsiveOptions: any[] = [
      ['screen and (max-width: 640px)', {
        seriesBarDistance: 5,
        axisX: {
          labelInterpolationFnc: function (value) {
            return value[0];
          }
        }
      }]
    ];

    const websiteViewsChart = new Chartist.Bar('#websiteViewsChart', datawebsiteViewsChart, optionswebsiteViewsChart, responsiveOptions);

    //start animation for the Emails Subscription Chart
    this.startAnimationForBarChart(websiteViewsChart);
  }

}
