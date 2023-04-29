<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20230428234204 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('DROP TABLE abonnement');
        $this->addSql('DROP TABLE avis');
        $this->addSql('DROP TABLE commentaire');
        $this->addSql('DROP TABLE conducteur');
        $this->addSql('DROP TABLE contrat');
        $this->addSql('DROP TABLE historique_reservation');
        $this->addSql('DROP TABLE livraison');
        $this->addSql('DROP TABLE offre');
        $this->addSql('DROP TABLE payement');
        $this->addSql('DROP TABLE reservation');
        $this->addSql('DROP TABLE vehicule');
        $this->addSql('ALTER TABLE message DROP FOREIGN KEY FK_B6BD307F305E0476');
        $this->addSql('DROP INDEX fk_b6bd307f305e0476 ON message');
        $this->addSql('CREATE INDEX IDX_B6BD307F305E0476 ON message (id_rec_id)');
        $this->addSql('ALTER TABLE message ADD CONSTRAINT FK_B6BD307F305E0476 FOREIGN KEY (id_rec_id) REFERENCES reclamation (id)');
        $this->addSql('ALTER TABLE reclamation ADD CONSTRAINT FK_CE60640499DED506 FOREIGN KEY (id_client_id) REFERENCES client (id_client)');
        $this->addSql('DROP INDEX fk_client_reclamation ON reclamation');
        $this->addSql('CREATE INDEX IDX_CE60640499DED506 ON reclamation (id_client_id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE abonnement (idAb INT NOT NULL, typeAb VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, prixAb DOUBLE PRECISION NOT NULL, modePaiementAb VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, UNIQUE INDEX id_ab (idAb), PRIMARY KEY(idAb)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE avis (id_avis INT NOT NULL, id_conducteur INT DEFAULT NULL, note INT NOT NULL) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE commentaire (id INT NOT NULL, contenu VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, date DATETIME NOT NULL, id_conducteur INT NOT NULL, id_client INT NOT NULL, id_avis INT NOT NULL, INDEX fk_commentaire_avis (id_avis), INDEX FK_client_commentaire (id_client), INDEX FK_conducteur_commentaire (id_conducteur), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE conducteur (id_conducteur INT AUTO_INCREMENT NOT NULL, cin_conducteur INT NOT NULL, nom_conducteur VARCHAR(50) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, prenom_conducteur VARCHAR(50) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, ville_conducteur VARCHAR(50) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, telephone_conducteur INT NOT NULL, email_conducteur VARCHAR(200) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, mdp_conducteur VARCHAR(50) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, type_de_permis VARCHAR(11) CHARACTER SET utf8mb4 DEFAULT NULL COLLATE `utf8mb4_general_ci`, image_conducteur VARCHAR(255) CHARACTER SET utf8mb4 DEFAULT NULL COLLATE `utf8mb4_general_ci`, PRIMARY KEY(id_conducteur)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE contrat (id_contrat INT NOT NULL, date_debut DATE NOT NULL, date_fin DATE NOT NULL, prix INT NOT NULL, statut VARCHAR(50) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, id_admin INT DEFAULT NULL, id_conducteur INT DEFAULT NULL, qr_code VARCHAR(100) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, INDEX FK_contrat_admin (id_admin), INDEX FK_contrat_conducteur (id_conducteur), PRIMARY KEY(id_contrat)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE historique_reservation (id_historique_reservation INT NOT NULL, id_reservation INT NOT NULL, id_conducteur INT NOT NULL, date DATE DEFAULT \'CURRENT_TIMESTAMP\' NOT NULL, date_depart_reelle DATE DEFAULT \'CURRENT_TIMESTAMP\' NOT NULL, date_arrive_reelle DATE DEFAULT \'CURRENT_TIMESTAMP\' NOT NULL, lieu_depart VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, lieu_destination VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, avis_client VARCHAR(500) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, status_reservation VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, id_client INT NOT NULL, PRIMARY KEY(id_historique_reservation)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE livraison (id_livraison INT NOT NULL, addresse_liv VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, destinataire VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, poids DOUBLE PRECISION NOT NULL, contenu VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, prix_liv DOUBLE PRECISION NOT NULL, PRIMARY KEY(id_livraison)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE offre (id_offre INT NOT NULL, id_conducteur INT NOT NULL, Destination VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, pt_depart VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, Prix DOUBLE PRECISION NOT NULL, Type_vehicule VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, PRIMARY KEY(id_offre)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE payement (paymentId INT NOT NULL, clientId INT NOT NULL, modePayment VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, prixCourse DOUBLE PRECISION NOT NULL, PRIMARY KEY(paymentId)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE reservation (id_reservation INT NOT NULL, nb_place INT NOT NULL, date DATE DEFAULT \'CURRENT_TIMESTAMP\' NOT NULL, id_client INT NOT NULL, point_de_depart VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, point_arrive VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, id_conducteur INT NOT NULL, id_offre INT NOT NULL, PRIMARY KEY(id_reservation)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE vehicule (immatriculation VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, type_du_vehicule VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, marque VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, cin_conducteur INT NOT NULL, etat INT NOT NULL, kilometrage INT NOT NULL, imageV VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('ALTER TABLE message DROP FOREIGN KEY FK_B6BD307F305E0476');
        $this->addSql('DROP INDEX idx_b6bd307f305e0476 ON message');
        $this->addSql('CREATE INDEX FK_B6BD307F305E0476 ON message (id_rec_id)');
        $this->addSql('ALTER TABLE message ADD CONSTRAINT FK_B6BD307F305E0476 FOREIGN KEY (id_rec_id) REFERENCES reclamation (id)');
        $this->addSql('ALTER TABLE reclamation DROP FOREIGN KEY FK_CE60640499DED506');
        $this->addSql('ALTER TABLE reclamation DROP FOREIGN KEY FK_CE60640499DED506');
        $this->addSql('DROP INDEX idx_ce60640499ded506 ON reclamation');
        $this->addSql('CREATE INDEX FK_Client_Reclamation ON reclamation (id_client_id)');
        $this->addSql('ALTER TABLE reclamation ADD CONSTRAINT FK_CE60640499DED506 FOREIGN KEY (id_client_id) REFERENCES client (id_client)');
    }
}
