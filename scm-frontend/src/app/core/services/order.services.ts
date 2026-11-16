// src/app/core/services/order.service.ts
import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, of } from 'rxjs';

// providedIn root - singleton service
@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/orders';

  // switchMap, mergeMap, concatMap, and exhaustMap - RxJS operators
  createOrder(orderId: string, expedite: boolean): Observable<any> {
    return this.http.post(`${this.apiUrl}/${orderId}?expedite=${expedite}`, {}).pipe(
      catchError(this.handleError<any>('createOrder'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(`${operation} failed: ${error.message}`); // Angular Error Handling
      return of(result as T);
    };
  }
}