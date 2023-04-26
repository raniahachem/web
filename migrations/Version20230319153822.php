<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20230319153822 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE avis DROP FOREIGN KEY fk_avis_client');
        $this->addSql('DROP TABLE admin');
        $this->addSql('DROP TABLE client');
        $this->addSql('DROP TABLE contrat');
        $this->addSql('DROP TABLE historique_reservation');
        $this->addSql('DROP TABLE livraison');
        $this->addSql('DROP TABLE message');
        $this->addSql('DROP TABLE offre');
        $this->addSql('DROP TABLE payement');
        $this->addSql('DROP TABLE reservation');
        $this->addSql('ALTER TABLE avis DROP FOREIGN KEY fk_avis_conducteur');
        $this->addSql('DROP INDEX fk_avis_client ON avis');
        $this->addSql('DROP INDEX fk_avis_conducteur ON avis');
        $this->addSql('ALTER TABLE conducteur ADD id INT AUTO_INCREMENT NOT NULL, CHANGE id_conducteur id_conducteur INT NOT NULL, CHANGE nom_conducteur nom_conducteur VARCHAR(255) NOT NULL, CHANGE prenom_conducteur prenom_conducteur VARCHAR(255) NOT NULL, CHANGE ville_conducteur ville_conducteur VARCHAR(255) NOT NULL, CHANGE email_conducteur email_conducteur VARCHAR(255) NOT NULL, CHANGE mdp_conducteur mdp_conducteur VARCHAR(255) NOT NULL, CHANGE type_de_permis type_de_permis VARCHAR(255) NOT NULL, CHANGE image_conducteur image_conducteur VARCHAR(255) NOT NULL, DROP PRIMARY KEY, ADD PRIMARY KEY (id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE admin (id_admin INT AUTO_INCREMENT NOT NULL, nom_admin VARCHAR(50) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, prenom_admin VARCHAR(50) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, email_admin VARCHAR(50) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, mdp_admin VARCHAR(50) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, PRIMARY KEY(id_admin)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE client (id_client INT AUTO_INCREMENT NOT NULL, cin_client INT NOT NULL, nom_client VARCHAR(30) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, prenom_client VARCHAR(30) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, ville_client VARCHAR(30) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, telephone_client INT NOT NULL, email_client VARCHAR(200) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, mdp_client VARCHAR(30) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, role VARCHAR(100) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, PRIMARY KEY(id_client)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE contrat (id_contrat INT AUTO_INCREMENT NOT NULL, date_debut DATE NOT NULL, date_fin DATE NOT NULL, prix INT NOT NULL, statut VARCHAR(50) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, id_admin INT DEFAULT NULL, id_conducteur INT DEFAULT NULL, qr_code VARCHAR(100) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, INDEX FK_contrat_admin (id_admin), INDEX FK_contrat_conducteur (id_conducteur), PRIMARY KEY(id_contrat)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE historique_reservation (id_historique_reservation INT AUTO_INCREMENT NOT NULL, id_reservation INT NOT NULL, id_conducteur INT NOT NULL, date DATE DEFAULT \'CURRENT_TIMESTAMP\' NOT NULL, date_depart_reelle DATE DEFAULT \'CURRENT_TIMESTAMP\' NOT NULL, date_arrive_reelle DATE DEFAULT \'CURRENT_TIMESTAMP\' NOT NULL, lieu_depart VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, lieu_destination VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, avis_client VARCHAR(500) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, status_reservation VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, id_client INT NOT NULL, PRIMARY KEY(id_historique_reservation)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE livraison (id_livraison INT AUTO_INCREMENT NOT NULL, addresse_liv VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, destinataire VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, poids DOUBLE PRECISION NOT NULL, contenu VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, prix_liv DOUBLE PRECISION NOT NULL, PRIMARY KEY(id_livraison)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE message (id_message INT AUTO_INCREMENT NOT NULL, id_conducteur INT NOT NULL, id_client INT NOT NULL, contenu VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, date_message DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL, INDEX id_client (id_client), INDEX id_conducteur (id_conducteur), PRIMARY KEY(id_message)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE offre (id_offre INT AUTO_INCREMENT NOT NULL, id_conducteur INT NOT NULL, Destination VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, pt_depart VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, Prix DOUBLE PRECISION NOT NULL, Type_vehicule VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, PRIMARY KEY(id_offre)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE payement (paymentId INT AUTO_INCREMENT NOT NULL, clientId INT NOT NULL, modePayment VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, prixCourse DOUBLE PRECISION NOT NULL, PRIMARY KEY(paymentId)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE reservation (id_reservation INT AUTO_INCREMENT NOT NULL, nb_place INT NOT NULL, date DATE DEFAULT \'CURRENT_TIMESTAMP\' NOT NULL, id_client INT NOT NULL, point_de_depart VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, point_arrive VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, id_conducteur INT NOT NULL, id_offre INT NOT NULL, PRIMARY KEY(id_reservation)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('ALTER TABLE avis ADD CONSTRAINT fk_avis_conducteur FOREIGN KEY (id_conducteur) REFERENCES conducteur (id_conducteur)');
        $this->addSql('ALTER TABLE avis ADD CONSTRAINT fk_avis_client FOREIGN KEY (id_client) REFERENCES client (id_client)');
        $this->addSql('CREATE INDEX fk_avis_client ON avis (id_client)');
        $this->addSql('CREATE INDEX fk_avis_conducteur ON avis (id_conducteur)');
        $this->addSql('ALTER TABLE conducteur MODIFY id INT NOT NULL');
        $this->addSql('DROP INDEX `PRIMARY` ON conducteur');
        $this->addSql('ALTER TABLE conducteur DROP id, CHANGE id_conducteur id_conducteur INT AUTO_INCREMENT NOT NULL, CHANGE nom_conducteur nom_conducteur VARCHAR(50) NOT NULL, CHANGE prenom_conducteur prenom_conducteur VARCHAR(50) NOT NULL, CHANGE ville_conducteur ville_conducteur VARCHAR(50) NOT NULL, CHANGE email_conducteur email_conducteur VARCHAR(200) NOT NULL, CHANGE mdp_conducteur mdp_conducteur VARCHAR(50) NOT NULL, CHANGE type_de_permis type_de_permis VARCHAR(11) DEFAULT NULL, CHANGE image_conducteur image_conducteur VARCHAR(255) DEFAULT NULL');
        $this->addSql('ALTER TABLE conducteur ADD PRIMARY KEY (id_conducteur)');
    }
}
