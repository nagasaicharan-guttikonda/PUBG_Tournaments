import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TournamentsComponent } from './components/tournaments/tournaments.component';
import { TournamentDetailsComponent } from './components/tournament-details/tournament-details.component';
import { MatchDetailsComponent } from './components/match-details/match-details.component';
import { AuthGuardService } from '../../authGuard.service';
import { FavouritelistComponent } from './components/favouritelist/favouritelist.component';
const routes: Routes = [
  {
    path:'tournaments',
    children:[
        {
          path:'',
          redirectTo: 'all',
          pathMatch: 'full',
          canActivate:[AuthGuardService],
        },
        {
          path:'all',
          component:TournamentsComponent,
         canActivate:[AuthGuardService],

        },
         {
          path:'matches/:tId',
          component: TournamentDetailsComponent,
          canActivate:[AuthGuardService],

        },
           {
          path:'match/:mId',
          component: MatchDetailsComponent,
          canActivate:[AuthGuardService],

        },
        {
            path:'favouritelist',
            component:FavouritelistComponent,
            canActivate:[AuthGuardService]
        }
        
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MatchRoutingModule { }
