import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { InteresRequest } from '../models/interes-request.model';
import { InteresResponse } from '../models/interes-response.model';

@Injectable({ providedIn: 'root' })
export class InteresService {
  constructor(private http: HttpClient) {}

  calcularInteres(req: InteresRequest): Observable<InteresResponse> {
    return this.http.post<InteresResponse>('/api/calcular-interes', req);
  }

  obtenerHistorial(): Observable<any[]> {
    return this.http.get<any[]>('/api/historial-calculos');
  }
}