package ma.houssybadr.assurance;

import ma.houssybadr.assurance.entities.*;
import ma.houssybadr.assurance.enums.*;
import ma.houssybadr.assurance.repositories.*;
import ma.houssybadr.assurance.security.entities.AppRole;
import ma.houssybadr.assurance.security.entities.AppUser;
import ma.houssybadr.assurance.security.repositories.AppRoleRepository;
import ma.houssybadr.assurance.security.repositories.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

/**
 * Point d'entrée de l'application de gestion des contrats d'assurance.
 * Auteur : Houssy Badr — Examen JEE Architecture Distribuée
 */
@SpringBootApplication
public class AssuranceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssuranceApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(
            ClientRepository clientRepo,
            ContratAutoRepository autoRepo,
            ContratHabitationRepository habitationRepo,
            ContratSanteRepository santeRepo,
            PaiementRepository paiementRepo,
            AppUserRepository userRepo,
            AppRoleRepository roleRepo,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {

            // ── Rôles de sécurité ──────────────────────────────────────────
            AppRole roleAdmin   = roleRepo.save(AppRole.builder().roleName("ROLE_ADMIN").build());
            AppRole roleEmploye = roleRepo.save(AppRole.builder().roleName("ROLE_EMPLOYE").build());
            AppRole roleClient  = roleRepo.save(AppRole.builder().roleName("ROLE_CLIENT").build());

            // ── Utilisateurs de test ───────────────────────────────────────
            userRepo.save(AppUser.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .roles(List.of(roleAdmin, roleEmploye, roleClient))
                    .build());

            userRepo.save(AppUser.builder()
                    .username("employe1")
                    .password(passwordEncoder.encode("employe123"))
                    .roles(List.of(roleEmploye, roleClient))
                    .build());

            userRepo.save(AppUser.builder()
                    .username("client1")
                    .password(passwordEncoder.encode("client123"))
                    .roles(List.of(roleClient))
                    .build());

            System.out.println("✅ Utilisateurs créés : admin / employe1 / client1");

            // ── 5 clients métier ───────────────────────────────────────────
            Client c1 = clientRepo.save(Client.builder().nom("Houssy Badr").email("badr.houssy@gmail.com").build());
            Client c2 = clientRepo.save(Client.builder().nom("Alaoui Sara").email("sara.alaoui@gmail.com").build());
            Client c3 = clientRepo.save(Client.builder().nom("Benali Karim").email("karim.benali@gmail.com").build());
            Client c4 = clientRepo.save(Client.builder().nom("El Fassi Nadia").email("nadia.elfassi@gmail.com").build());
            Client c5 = clientRepo.save(Client.builder().nom("Tahiri Youssef").email("youssef.tahiri@gmail.com").build());

            // ── Contrats Auto ──────────────────────────────────────────────
            ContratAssuranceAuto auto1 = new ContratAssuranceAuto();
            auto1.setDateSouscription(LocalDate.of(2024, 1, 10));
            auto1.setStatut(StatutContrat.VALIDE);
            auto1.setDateValidation(LocalDate.of(2024, 1, 15));
            auto1.setMontantCotisation(1200.0);
            auto1.setDureeContrat(12);
            auto1.setTauxCouverture(80.0);
            auto1.setClient(c1);
            auto1.setNumImmatriculation("123-A-45");
            auto1.setMarqueVehicule("Renault");
            auto1.setModeleVehicule("Clio");
            autoRepo.save(auto1);

            ContratAssuranceAuto auto2 = new ContratAssuranceAuto();
            auto2.setDateSouscription(LocalDate.of(2024, 3, 5));
            auto2.setStatut(StatutContrat.EN_COURS);
            auto2.setMontantCotisation(1800.0);
            auto2.setDureeContrat(24);
            auto2.setTauxCouverture(90.0);
            auto2.setClient(c2);
            auto2.setNumImmatriculation("456-B-78");
            auto2.setMarqueVehicule("Dacia");
            auto2.setModeleVehicule("Logan");
            autoRepo.save(auto2);

            ContratAssuranceAuto auto3 = new ContratAssuranceAuto();
            auto3.setDateSouscription(LocalDate.of(2023, 11, 20));
            auto3.setStatut(StatutContrat.RESILIE);
            auto3.setMontantCotisation(900.0);
            auto3.setDureeContrat(12);
            auto3.setTauxCouverture(70.0);
            auto3.setClient(c3);
            auto3.setNumImmatriculation("789-C-12");
            auto3.setMarqueVehicule("Peugeot");
            auto3.setModeleVehicule("208");
            autoRepo.save(auto3);

            // ── Contrats Habitation ────────────────────────────────────────
            ContratAssuranceHabitation hab1 = new ContratAssuranceHabitation();
            hab1.setDateSouscription(LocalDate.of(2024, 2, 1));
            hab1.setStatut(StatutContrat.VALIDE);
            hab1.setDateValidation(LocalDate.of(2024, 2, 5));
            hab1.setMontantCotisation(600.0);
            hab1.setDureeContrat(12);
            hab1.setTauxCouverture(75.0);
            hab1.setClient(c1);
            hab1.setTypeLogement(TypeLogement.APPARTEMENT);
            hab1.setAdresseLogement("12 Rue Mohammed V, Casablanca");
            hab1.setSuperficie(85.0);
            habitationRepo.save(hab1);

            ContratAssuranceHabitation hab2 = new ContratAssuranceHabitation();
            hab2.setDateSouscription(LocalDate.of(2024, 4, 10));
            hab2.setStatut(StatutContrat.EN_COURS);
            hab2.setMontantCotisation(450.0);
            hab2.setDureeContrat(12);
            hab2.setTauxCouverture(60.0);
            hab2.setClient(c4);
            hab2.setTypeLogement(TypeLogement.MAISON);
            hab2.setAdresseLogement("8 Avenue Hassan II, Rabat");
            hab2.setSuperficie(150.0);
            habitationRepo.save(hab2);

            ContratAssuranceHabitation hab3 = new ContratAssuranceHabitation();
            hab3.setDateSouscription(LocalDate.of(2023, 6, 15));
            hab3.setStatut(StatutContrat.VALIDE);
            hab3.setDateValidation(LocalDate.of(2023, 6, 20));
            hab3.setMontantCotisation(800.0);
            hab3.setDureeContrat(24);
            hab3.setTauxCouverture(85.0);
            hab3.setClient(c5);
            hab3.setTypeLogement(TypeLogement.LOCAL_COMMERCIAL);
            hab3.setAdresseLogement("45 Boulevard Anfa, Casablanca");
            hab3.setSuperficie(200.0);
            habitationRepo.save(hab3);

            // ── Contrats Santé ─────────────────────────────────────────────
            ContratAssuranceSante sante1 = new ContratAssuranceSante();
            sante1.setDateSouscription(LocalDate.of(2024, 1, 20));
            sante1.setStatut(StatutContrat.VALIDE);
            sante1.setDateValidation(LocalDate.of(2024, 1, 25));
            sante1.setMontantCotisation(350.0);
            sante1.setDureeContrat(12);
            sante1.setTauxCouverture(70.0);
            sante1.setClient(c2);
            sante1.setNiveauCouverture(NiveauCouverture.BASIQUE);
            sante1.setNbPersonnesCouvertes(1);
            santeRepo.save(sante1);

            ContratAssuranceSante sante2 = new ContratAssuranceSante();
            sante2.setDateSouscription(LocalDate.of(2024, 3, 15));
            sante2.setStatut(StatutContrat.EN_COURS);
            sante2.setMontantCotisation(700.0);
            sante2.setDureeContrat(12);
            sante2.setTauxCouverture(85.0);
            sante2.setClient(c3);
            sante2.setNiveauCouverture(NiveauCouverture.INTERMEDIAIRE);
            sante2.setNbPersonnesCouvertes(3);
            santeRepo.save(sante2);

            ContratAssuranceSante sante3 = new ContratAssuranceSante();
            sante3.setDateSouscription(LocalDate.of(2023, 9, 1));
            sante3.setStatut(StatutContrat.VALIDE);
            sante3.setDateValidation(LocalDate.of(2023, 9, 5));
            sante3.setMontantCotisation(1100.0);
            sante3.setDureeContrat(24);
            sante3.setTauxCouverture(95.0);
            sante3.setClient(c5);
            sante3.setNiveauCouverture(NiveauCouverture.PREMIUM);
            sante3.setNbPersonnesCouvertes(5);
            santeRepo.save(sante3);

            // ── Paiements (23 au total) ────────────────────────────────────
            List<ContratAssurance> contrats = List.of(auto1, auto2, hab1, hab2, sante1, sante2, sante3);
            LocalDate base = LocalDate.of(2024, 1, 1);
            for (int i = 0; i < contrats.size(); i++) {
                ContratAssurance contrat = contrats.get(i);
                for (int m = 0; m < 3; m++) {
                    paiementRepo.save(Paiement.builder()
                            .datePaiement(base.plusMonths(i + m))
                            .montant(contrat.getMontantCotisation() / 12)
                            .type(TypePaiement.MENSUALITE)
                            .contrat(contrat)
                            .build());
                }
            }
            paiementRepo.save(Paiement.builder()
                    .datePaiement(LocalDate.of(2024, 6, 1))
                    .montant(500.0)
                    .type(TypePaiement.PAIEMENT_EXCEPTIONNEL)
                    .contrat(auto1)
                    .build());
            paiementRepo.save(Paiement.builder()
                    .datePaiement(LocalDate.of(2024, 1, 1))
                    .montant(1200.0)
                    .type(TypePaiement.PAIEMENT_ANNUEL)
                    .contrat(hab3)
                    .build());

            System.out.println("✅ DataSeeder : "
                    + clientRepo.count() + " clients, "
                    + autoRepo.count() + " autos, "
                    + habitationRepo.count() + " habitations, "
                    + santeRepo.count() + " santé, "
                    + paiementRepo.count() + " paiements.");
        };
    }
}
