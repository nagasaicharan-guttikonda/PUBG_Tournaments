import { Component, OnInit } from '@angular/core';
import { Match } from '../../match';
import { ActivatedRoute, Router } from '@angular/router';
import { MatchService } from '../../match.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'pubg-match-details',
  templateUrl: './match-details.component.html',
  styleUrls: ['./match-details.component.css']
})
export class MatchDetailsComponent implements OnInit {
  mId:string;
  match:any;
  userName:any;

  constructor(private router:ActivatedRoute,private service:MatchService,private route:Router,private snackBar:MatSnackBar) {
    this.mId='';
   }

  ngOnInit() {
    this.mId=this.router.snapshot.params["mId"];
    this.service.getMatchById(this.mId).subscribe((match:any)=>{
      
        this.match=match;
      
      });
      this.userName=localStorage.getItem("userId");

  }

  addToFavouriteList(){
    let message=this.match['data']['attributes']['titleId']+" added to favouritelist";
    this.service.addMatchToFavouritelist(this.match).subscribe((match)=>{
      this.snackBar.open(message,'',{
        duration:1000
      });
    }) 


  }

}
