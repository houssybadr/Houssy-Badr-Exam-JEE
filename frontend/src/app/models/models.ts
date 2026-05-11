export interface Client {
  id?: number;
  nom: string;
  email: string;
  nombreContrats?: number;
}

export type StatutContrat = 'EN_COURS' | 'VALIDE' | 'RESILIE';
export type TypePaiement  = 'MENSUALITE' | 'PAIEMENT_ANNUEL' | 'PAIEMENT_EXCEPTIONNEL';
export type TypeLogement  = 'APPARTEMENT' | 'MAISON' | 'LOCAL_COMMERCIAL';
export type NiveauCouverture = 'BASIQUE' | 'INTERMEDIAIRE' | 'PREMIUM';

export interface Contrat {
  id?: number;
  typeContrat: 'AUTO' | 'HABITATION' | 'SANTE';
  dateSouscription: string;
  statut: StatutContrat;
  dateValidation?: string;
  montantCotisation: number;
  dureeContrat: number;
  tauxCouverture: number;
  clientId: number;
  clientNom?: string;
  // AUTO
  numImmatriculation?: string;
  marqueVehicule?: string;
  modeleVehicule?: string;
  // HABITATION
  typeLogement?: TypeLogement;
  adresseLogement?: string;
  superficie?: number;
  // SANTE
  niveauCouverture?: NiveauCouverture;
  nbPersonnesCouvertes?: number;
}

export interface Paiement {
  id?: number;
  datePaiement: string;
  montant: number;
  type: TypePaiement;
  contratId: number;
  typeContrat?: string;
}

export interface LoginRequest  { username: string; password: string; }
export interface LoginResponse { accessToken: string; username: string; roles: string[]; }
