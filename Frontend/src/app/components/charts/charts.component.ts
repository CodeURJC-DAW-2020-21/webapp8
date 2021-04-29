
import { AfterViewInit, Component, ElementRef, ViewChild, OnInit } from '@angular/core';
import * as Chart from 'chart.js';
import { CryptocurrenciesService } from 'src/app/services/cryptocurrencies.service';

@Component({
  selector: 'app-charts',
  templateUrl: './charts.component.html',
  styleUrls: ['./charts.component.css']
})
export class ChartsComponent implements OnInit {

  @ViewChild('lineCanvas') lineCanvas: ElementRef;
  lineChart: any;

  data: string;

  constructor(public cryptocurrenciesService: CryptocurrenciesService) { }

  ngAfterViewInit(): void {
    this.lineChartMethod();
  }

  ngOnInit(): void {
    this.getChartData();
  }

  lineChartMethod() {
    this.lineChart = new Chart(this.lineCanvas.nativeElement, {
      type: 'line',
      data: {
        labels: ['1', '2', '3', '4', '5', '6', '7', '8', '9'],
        datasets: [
          {
            label: 'GRAFICA',
            fill: false,
            lineTension: 0.1,
            backgroundColor: 'rgba(75,192,192,0.4)',
            borderColor: 'rgba(75,192,192,1)',
            borderCapStyle: 'butt',
            borderDash: [],
            borderDashOffset: 0.0,
            borderJoinStyle: 'miter',
            pointBorderColor: 'rgba(75,192,192,1)',
            pointBackgroundColor: '#fff',
            pointBorderWidth: 1,
            pointHoverRadius: 5,
            pointHoverBackgroundColor: 'rgba(75,192,192,1)',
            pointHoverBorderColor: 'rgba(220,220,220,1)',
            pointHoverBorderWidth: 2,
            pointRadius: 1,
            pointHitRadius: 10,
            data: [this.data.toString()],
            spanGaps: false,
          }
        ]
      }
    });
  }

  getChartData(){
    this.cryptocurrenciesService.getChartData().subscribe(
      data => this.data = data,
      error => console.log(error)
    )
  }

}
