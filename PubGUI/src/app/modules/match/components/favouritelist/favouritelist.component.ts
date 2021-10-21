import { Component, OnInit } from '@angular/core';
import { Match } from '../../match';
import { MatchService } from '../../match.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'pubg-favouritelist',
  templateUrl: './favouritelist.component.html',
  styleUrls: ['./favouritelist.component.css']
})
export class FavouritelistComponent implements OnInit {

  userName:any;
  matches:Array<Match>
  constructor(private service:MatchService,private snackbar:MatSnackBar) {
    this.matches=[];
   }

  ngOnInit() {
    let message="favourites list is empty";
    this.service.getFavouriteMatches()
    .subscribe((matches)=>{
      if(matches.length==0)

   {

    this.snackbar.open(message,'', {

     duration:1000

    });

   }
      this.matches.push(...matches);
    })

    this.userName=localStorage.getItem("userId");

  }
 deleteFromFavouriteList(match){
    this.service. deleteFromFavouritelist(match).subscribe((result) => {
      let message = `${match.titleId} has been deleted`;
      this.snackbar.open(message, '', {
        duration: 1000
      });
      const index = this.matches.indexOf(match);
      this.matches.splice(index, 1);
    });

  }


}
