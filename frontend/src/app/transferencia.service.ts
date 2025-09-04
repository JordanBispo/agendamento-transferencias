import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';
import { Observable } from 'rxjs';

export interface Transferencia {
  id?: number;
  contaOrigem: string;
  contaDestino: string;
  valor: number;
  taxa?: number;
  dataTransferencia: string;
  dataAgendamento?: string;
}

@Injectable({ providedIn: 'root' })
export class TransferenciaService {
  private base = `${environment.apiUrl}/transferencias`;
  constructor(private http: HttpClient) {}

  listar(): Observable<Transferencia[]> {
    return this.http.get<Transferencia[]>(this.base);
  }

  agendar(t: Transferencia): Observable<Transferencia> {
    return this.http.post<Transferencia>(this.base, t);
  }
}