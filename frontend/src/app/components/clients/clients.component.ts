import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { Client } from '../../models/models';
import { ClientService } from '../../services/client.service';

@Component({
  selector: 'app-clients',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './clients.component.html'
})
export class ClientsComponent implements OnInit {
  clients: Client[] = [];
  searchNom  = '';
  showForm   = false;
  editMode   = false;
  selected: Client = { nom: '', email: '' };
  message    = '';
  error      = '';

  constructor(private svc: ClientService) {}

  ngOnInit() { this.load(); }

  load() {
    this.svc.getAll().subscribe({ next: d => this.clients = d, error: () => this.error = 'Erreur de chargement' });
  }

  search() {
    if (!this.searchNom.trim()) { this.load(); return; }
    this.svc.search(this.searchNom).subscribe(d => this.clients = d);
  }

  openCreate() {
    this.selected = { nom: '', email: '' };
    this.editMode = false;
    this.showForm = true;
  }

  openEdit(c: Client) {
    this.selected = { ...c };
    this.editMode = true;
    this.showForm = true;
  }

  save() {
    const obs = this.editMode
      ? this.svc.update(this.selected.id!, this.selected)
      : this.svc.create(this.selected);
    obs.subscribe({
      next: () => { this.message = 'Client enregistré.'; this.showForm = false; this.load(); },
      error: () => this.error = 'Erreur lors de la sauvegarde.'
    });
  }

  delete(id: number) {
    if (!confirm('Supprimer ce client ?')) return;
    this.svc.delete(id).subscribe({ next: () => this.load(), error: () => this.error = 'Erreur suppression.' });
  }
}
