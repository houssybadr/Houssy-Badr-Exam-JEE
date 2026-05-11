import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { Contrat, Client } from '../../models/models';
import { ContratService } from '../../services/contrat.service';
import { ClientService } from '../../services/client.service';

@Component({
  selector: 'app-contrats',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './contrats.component.html'
})
export class ContratsComponent implements OnInit {
  contrats: Contrat[]  = [];
  clients:  Client[]   = [];
  filterClientId: number | null = null;
  showForm  = false;
  selected: Partial<Contrat> = {};
  message = '';
  error   = '';

  constructor(
    private svc: ContratService,
    private clientSvc: ClientService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.clientSvc.getAll().subscribe(c => this.clients = c);
    this.route.queryParams.subscribe(p => {
      this.filterClientId = p['clientId'] ? +p['clientId'] : null;
      this.load();
    });
  }

  load() {
    const obs = this.filterClientId
      ? this.svc.getByClient(this.filterClientId)
      : this.svc.getAll();
    obs.subscribe({ next: d => this.contrats = d, error: () => this.error = 'Erreur de chargement' });
  }

  openCreate() {
    this.selected = { typeContrat: 'AUTO', dateSouscription: new Date().toISOString().split('T')[0],
                      dureeContrat: 12, tauxCouverture: 80, montantCotisation: 500 };
    this.showForm = true;
  }

  save() {
    this.svc.create(this.selected as Contrat).subscribe({
      next: () => { this.message = 'Contrat créé.'; this.showForm = false; this.load(); },
      error: () => this.error = 'Erreur création.'
    });
  }

  valider(id: number) {
    this.svc.valider(id).subscribe({ next: () => this.load(), error: () => this.error = 'Erreur validation.' });
  }

  resilier(id: number) {
    if (!confirm('Résilier ce contrat ?')) return;
    this.svc.resilier(id).subscribe({ next: () => this.load(), error: () => this.error = 'Erreur résiliation.' });
  }

  delete(id: number) {
    if (!confirm('Supprimer ce contrat ?')) return;
    this.svc.delete(id).subscribe({ next: () => this.load(), error: () => this.error = 'Erreur suppression.' });
  }

  badgeClass(statut: string) {
    return { EN_COURS: 'bg-warning text-dark', VALIDE: 'bg-success', RESILIE: 'bg-danger' }[statut] ?? 'bg-secondary';
  }

  iconContrat(type: string) {
    return { AUTO: 'bi-car-front', HABITATION: 'bi-house', SANTE: 'bi-heart-pulse' }[type] ?? 'bi-file';
  }
}
