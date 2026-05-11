import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Contrat } from '../models/models';

@Injectable({ providedIn: 'root' })
export class ContratService {
  private readonly API = 'http://localhost:8080/api/contrats';
  constructor(private http: HttpClient) {}

  getAll()                      { return this.http.get<Contrat[]>(this.API); }
  getById(id: number)           { return this.http.get<Contrat>(`${this.API}/${id}`); }
  getByClient(clientId: number) { return this.http.get<Contrat[]>(`${this.API}/client/${clientId}`); }
  create(c: Contrat)            { return this.http.post<Contrat>(this.API, c); }
  valider(id: number)           { return this.http.put<Contrat>(`${this.API}/${id}/valider`, {}); }
  resilier(id: number)          { return this.http.put<Contrat>(`${this.API}/${id}/resilier`, {}); }
  delete(id: number)            { return this.http.delete(`${this.API}/${id}`); }
}
