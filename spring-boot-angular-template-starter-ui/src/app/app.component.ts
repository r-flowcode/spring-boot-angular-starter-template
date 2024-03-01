import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {


  openGithubRepo(): void{
    window.open("https://github.com/r-flowcode/spring-boot-angular-starter-template");
  }
}
