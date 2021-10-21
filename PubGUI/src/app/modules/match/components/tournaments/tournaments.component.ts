import { Component, OnInit } from '@angular/core';
import { MatchService } from '../../match.service';
import { Tournament } from '../../tournament';
import { Observable } from 'rxjs';

@Component({
  selector: 'match-tournaments',
  templateUrl: './tournaments.component.html',
  styleUrls: ['./tournaments.component.css']
})
export class TournamentsComponent implements OnInit {
  tournaments: Array<Tournament>;
  loggedInState:Observable<Boolean>
userName:any;
  constructor(private service:MatchService) { 
    this.tournaments=[];
  }

  ngOnInit() {
    this.service.getTournaments().subscribe((res)=>{
      this.tournaments=res['data'];


    
        this.userName=localStorage.getItem("userId");
      
    })
  }

}

