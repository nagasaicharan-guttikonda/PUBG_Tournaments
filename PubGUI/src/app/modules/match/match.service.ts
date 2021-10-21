import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Tournament } from './tournament';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Match } from './match';
import { AuthenticationService } from '../authentication/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class MatchService {

  match: Match;
  pubgEndpoint: string;
  apiKey: string;
  springEndpoint: string;
  pubgApiHeader: HttpHeaders = new HttpHeaders({
    "Authorization": "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJiYzQzY2FkMC0zMWJhLTAxMzctNDFmOC0wMDU3NDUzNGQzNjMiLCJpc3MiOiJnYW1lbG9ja2VyIiwiaWF0IjoxNTUzNTgwMTgxLCJwdWIiOiJibHVlaG9sZSIsInRpdGxlIjoicHViZyIsImFwcCI6Ii03YTk3ZThjNi1jNTlhLTRkNTItOWNhNC01ZWZmN2U5NWU4NzUifQ.PWPsmAfb5rh7mwazVz-tMjgsXg8nHPsAUaGl2HOGung",
    "Accept": "application/vnd.api+json"
  });

  constructor(private http: HttpClient, private authService: AuthenticationService) {
    this.apiKey = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJiYzQzY2FkMC0zMWJhLTAxMzctNDFmOC0wMDU3NDUzNGQzNjMiLCJpc3MiOiJnYW1lbG9ja2VyIiwiaWF0IjoxNTUzNTgwMTgxLCJwdWIiOiJibHVlaG9sZSIsInRpdGxlIjoicHViZyIsImFwcCI6Ii03YTk3ZThjNi1jNTlhLTRkNTItOWNhNC01ZWZmN2U5NWU4NzUifQ.PWPsmAfb5rh7mwazVz-tMjgsXg8nHPsAUaGl2HOGung";
    this.pubgEndpoint = "https://api.pubg.com/";
    this.springEndpoint = "http://localhost:8089/matchservice";
    this.match = new Match();
  }


  getTournaments() {
    const url = this.pubgEndpoint + "tournaments";
    return this.http.get(url, { headers: this.pubgApiHeader })
      .pipe(map(this.pickTournaments));
  }

  pickTournaments(response) {
    return response["data"];
  }

  getTournamentById(tourid: string) {
    const headers = new HttpHeaders();
    const url = this.pubgEndpoint + "tournaments/" + tourid;
    return this.http.get(url, { headers: { 'Authorization': this.apiKey, 'accept': 'application/vnd.api+json' } })
  }

  getMatchById(matchId: string) {
    const headers = new HttpHeaders();
    const url = this.pubgEndpoint + "shards/tournament/matches/" + matchId;
    return this.http.get(url, { headers: { 'accept': 'application/vnd.api+json' } })
  }

  addMatchToFavouritelist(match) {
    this.match.createdAt = match['data']['attributes']['createdAt'];
    this.match.gameMode = match['data']['attributes']['gameMode'];
    this.match.id = match['data']['id'];
    this.match.mapName = match['data']['attributes']['mapName'];
    this.match.titleId = match['data']['attributes']['titleId'];

    return this.http.post(this.springEndpoint + "/match", this.match, { headers: { 'authorization': `Bearer ${this.authService.getToken()}` } });

  }

  getFavouriteMatches(): Observable<Array<Match>> {
    return this.http.get<Array<Match>>(this.springEndpoint + "/match", { headers: { 'authorization': `Bearer ${this.authService.getToken()}` } });
  }

  deleteFromFavouritelist(match: Match) {
    const url = this.springEndpoint+"/match" + "/" + match.id + "/" + match.userId;
    return this.http.delete(url, { responseType: 'text', headers: { 'authorization': `Bearer ${this.authService.getToken()}` } });
  }

}
