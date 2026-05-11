import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Client } from '../models/models';

@Injectable({ providedIn: 'root' })
export class ClientService {
  private readonly API = 'http://localhost:8080/api/clients';
  constructor(private http: HttpClient) {}

  getAll()                    { return this.http.get<Client[]>(this.API); }
  getById(id: number)         { return this.http.get<Client>(`${this.API}/${id}`); }
  search(nom: string)         { return this.http.get<Client[]>(`${this.API}/search?nom=${nom}`); }
  create(c: Client)           { return this.http.post<Client>(this.API, c); }
  update(id: number, c: Client) { return this.http.put<Client>(`${this.API}/${id}`, c); }
  delete(id: number)          { return this.http.delete(`${this.API}/${id}`); }
}
