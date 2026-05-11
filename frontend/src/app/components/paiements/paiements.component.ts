import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Paiement } from '../../models/models';
import { PaiementService } from '../../services/paiement.service';

@Component({
  selector: 'app-paiements',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './paiements.component.html'
})
export class PaiementsComponent implements OnInit {
  paiements: Paiement[] = [];
  filterContratId: number | null = null;
  total = 0;
  showForm = false;
  selected: Partial<Paiement> = {};
  message = '';
  error   = '';

  constructor(private svc: PaiementService, private route: ActivatedRoute) {}

  ngOnInit() {
    this.route.queryParams.subscribe(p => {
      this.filterContratId = p['contratId'] ? +p['contratId'] : null;
      this.load();
      if (this.filterContratId) this.loadTotal();
    });
  }

  load() {
    if (!this.filterContratId) { this.paiements = []; return; }
    this.svc.getByContrat(this.filterContratId).subscribe({ next: d => this.paiements = d });
  }

  loadTotal() {
    this.svc.getTotal(this.filterContratId!).subscribe(r => this.total = r.total);
  }

  openCreate() {
    this.selected = {
      datePaiement: new Date().toISOString().split('T')[0],
      type: 'MENSUALITE',
      contratId: this.filterContratId ?? undefined
    };
    this.showForm = true;
  }

  save() {
    this.svc.create(this.selected as Paiement).subscribe({
      next: () => { this.message = 'Paiement enregistré.'; this.showForm = false; this.load(); this.loadTotal(); },
      error: () => this.error = 'Erreur création.'
    });
  }

  delete(id: number) {
    if (!confirm('Supprimer ce paiement ?')) return;
    this.svc.delete(id).subscribe({ next: () => { this.load(); this.loadTotal(); } });
  }

  typeLabel(t: string) {
    return { MENSUALITE: 'Mensualité', PAIEMENT_ANNUEL: 'Annuel', PAIEMENT_EXCEPTIONNEL: 'Exceptionnel' }[t] ?? t;
  }

  typeBadge(t: string) {
    return { MENSUALITE: 'bg-primary', PAIEMENT_ANNUEL: 'bg-info', PAIEMENT_EXCEPTIONNEL: 'bg-warning text-dark' }[t] ?? 'bg-secondary';
  }
}
