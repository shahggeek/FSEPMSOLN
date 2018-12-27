import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'pmgui';

  loadedFeature : string;
  onNavigate(loadedFeature : string){
    this.loadedFeature = loadedFeature;
  }
}
