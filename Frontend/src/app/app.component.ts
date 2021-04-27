import {HttpClient} from '@angular/common/http';
import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})

export class AppComponent {

  entries: string[] = [];

  constructor(private httpClient: HttpClient) {

  }

  getEntries(page: number) {
    this.entries = [];
    let url = "/api/entries/?numOfPage=" + page.toString() + "";
    this.httpClient.get(url).subscribe(
      response => {
        let data: any = response;
        for (let i = 0; i < data.length; i++) {
          let entryTitle = data[i].title;
          this.entries.push(entryTitle);
        }
      }
    )
  }
}
