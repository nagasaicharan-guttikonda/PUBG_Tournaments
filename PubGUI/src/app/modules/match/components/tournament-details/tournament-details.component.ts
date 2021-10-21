import { Component, OnInit } from '@angular/core';
import { Match } from '../../match';
import { MatchService } from '../../match.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'pubg-tournament-details',
  templateUrl: './tournament-details.component.html',
  styleUrls: ['./tournament-details.component.css']
})
export class TournamentDetailsComponent implements OnInit {

  userName:any;
  matches:Array<Match>;
  tId:string;

  constructor(private service:MatchService,private router:ActivatedRoute,private route:Router) {
    this.matches=[];
    this.tId='';
   }

  ngOnInit() {
    this.tId=this.router.snapshot.params["tId"];
    this.service.getTournamentById(this.tId).subscribe((matches:any)=>{
      matches['included'].forEach(res => {
        this.matches.push(res);
      });
    })
    this.userName=localStorage.getItem("userId");

  }


}
