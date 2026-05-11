import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Paiement } from '../models/models';

@Injectable({ providedIn: 'root' })
export class PaiementService {
  private readonly API = 'http://localhost:8080/api/paiements';
  constructor(private http: HttpClient) {}

  getByContrat(contratId: number) { return this.http.get<Paiement[]>(`${this.API}/contrat/${contratId}`); }
  getByClient(clientId: number)   { return this.http.get<Paiement[]>(`${this.API}/client/${clientId}`); }
  getTotal(contratId: number)     { return this.http.get<{total: number}>(`${this.API}/contrat/${contratId}/total`); }
  create(p: Paiement)             { return this.http.post<Paiement>(this.API, p); }
  delete(id: number)              { return this.http.delete(`${this.API}/${id}`); }
}
