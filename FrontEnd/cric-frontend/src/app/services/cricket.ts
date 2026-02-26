import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CricketService {
  // Aapka Spring Boot API endpoint
  private apiUrl = 'http://localhost:8082/api/v1/score/all';

  constructor(private http: HttpClient) { }

  // Ye function backend se Live aur History data mangwayega
  getMatches(): Observable<any> {
    return this.http.get<any>(this.apiUrl).pipe(
    catchError((error: HttpErrorResponse) => {
      // English Exception: Handle specific HTTP status codes
      const msg = `Status: ${error.status}, Message: ${error.message}`;
      console.error("Failed to fetch history:", msg);
      return throwError(() => new Error("Internal Server Error while fetching match history."));
    })
  );
  }
}