import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FavouritelistComponent } from './components/favouritelist/favouritelist.component';
import { MatchService } from './match.service';
import { HttpClientModule } from '@angular/common/http';
import { TournamentsComponent } from './components/tournaments/tournaments.component';
import { TournamentDetailsComponent } from './components/tournament-details/tournament-details.component';
import { MatchDetailsComponent } from './components/match-details/match-details.component';
import {MatCardModule, MatButtonModule, MatSnackBarModule, MatDialogModule, MatInputModule, MatDialogContent} from '@angular/material';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { MatchRoutingModule } from './match.routing';


@NgModule({
  declarations: [ FavouritelistComponent, TournamentsComponent,TournamentDetailsComponent, MatchDetailsComponent],
  imports: [
    CommonModule,
    HttpClientModule,
    RouterModule,
    MatButtonModule,
    MatSnackBarModule,
    MatCardModule,
    MatDialogModule,FormsModule,MatInputModule
  ],
  exports:[TournamentsComponent,TournamentDetailsComponent,MatchDetailsComponent,MatchRoutingModule],
  providers:[MatchService]
})
export class MatchModule { }
