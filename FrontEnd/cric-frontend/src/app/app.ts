import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CricketService } from './services/cricket';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './app.html', // Aapki file ka naam app.html hai
  styleUrl: './app.css'
})
export class App implements OnInit {
  liveMatches: any[] = [];
  historyMatches: any[] = [];

  constructor(private cricketService: CricketService) {}

  ngOnInit() {
    this.cricketService.getMatches().subscribe({
      next: (res) => {
        this.liveMatches = res.live;
        this.historyMatches = res.history;
      },
      error: (err) => console.error("Data not found !!", err)
    });
  }
}