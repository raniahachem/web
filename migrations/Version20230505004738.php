<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20230505004738 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE abonnement (idAb INT AUTO_INCREMENT NOT NULL, type_ab VARCHAR(255) NOT NULL, prix_ab DOUBLE PRECISION NOT NULL, mode_paiement_ab VARCHAR(255) NOT NULL, PRIMARY KEY(idAb)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE admin (id_admin INT AUTO_INCREMENT NOT NULL, email_ad VARCHAR(255) NOT NULL, mdp_ad VARCHAR(255) NOT NULL, PRIMARY KEY(id_admin)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE avis (id INT AUTO_INCREMENT NOT NULL, rating DOUBLE PRECISION NOT NULL, id_client INT NOT NULL, id_conducteur INT NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE client (id_client VARCHAR(255) NOT NULL, cin_client INT NOT NULL, nom_client VARCHAR(255) NOT NULL, prenom_client VARCHAR(255) NOT NULL, ville_client VARCHAR(255) NOT NULL, telephone_client INT NOT NULL, email_client VARCHAR(255) NOT NULL, mdp_client VARCHAR(255) NOT NULL, role VARCHAR(255) NOT NULL, PRIMARY KEY(id_client)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE commentaire (id INT AUTO_INCREMENT NOT NULL, contenu VARCHAR(255) NOT NULL, date DATETIME NOT NULL, id_conducteur INT NOT NULL, id_client INT NOT NULL, id_avis INT NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE conducteur (id_conducteur INT AUTO_INCREMENT NOT NULL, cin_conducteur INT NOT NULL, nom_conducteur VARCHAR(255) NOT NULL, prenom_conducteur VARCHAR(255) NOT NULL, ville_conducteur VARCHAR(255) NOT NULL, telephone_conducteur INT NOT NULL, email_conducteur VARCHAR(255) NOT NULL, mdp_conducteur VARCHAR(255) NOT NULL, type_de_permis VARCHAR(255) NOT NULL, image_conducteur VARCHAR(255) NOT NULL, PRIMARY KEY(id_conducteur)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE contrat (id_contrat INT AUTO_INCREMENT NOT NULL, id_conducteur INT NOT NULL, id_admin INT NOT NULL, date_debut DATE NOT NULL, date_fin DATE NOT NULL, prix DOUBLE PRECISION NOT NULL, statut VARCHAR(255) NOT NULL, qr_code VARCHAR(255) NOT NULL, PRIMARY KEY(id_contrat)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE message (id INT AUTO_INCREMENT NOT NULL, id_rec_id INT DEFAULT NULL, id_admin_id INT DEFAULT NULL, contenu VARCHAR(255) NOT NULL, date_message DATETIME NOT NULL COMMENT \'(DC2Type:datetime_immutable)\', INDEX IDX_B6BD307F305E0476 (id_rec_id), INDEX IDX_B6BD307F34F06E85 (id_admin_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE offre (id INT AUTO_INCREMENT NOT NULL, id_conducteur_id INT DEFAULT NULL, destination VARCHAR(255) NOT NULL, pt_depart VARCHAR(255) NOT NULL, prix DOUBLE PRECISION NOT NULL, type_vehicule VARCHAR(255) NOT NULL, INDEX IDX_AF86866F4F479BA3 (id_conducteur_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE payement (paymentId INT AUTO_INCREMENT NOT NULL, client INT NOT NULL, mode_payment VARCHAR(255) NOT NULL, prixCourse DOUBLE PRECISION NOT NULL, payment_date DATETIME NOT NULL, PRIMARY KEY(paymentId)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE reclamation (id INT AUTO_INCREMENT NOT NULL, id_client_id VARCHAR(255) DEFAULT NULL, type VARCHAR(255) NOT NULL, date_r DATE NOT NULL, description VARCHAR(255) NOT NULL, INDEX IDX_CE60640499DED506 (id_client_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE reservation (id INT AUTO_INCREMENT NOT NULL, id_client_id VARCHAR(255) DEFAULT NULL, id_conducteur_id INT DEFAULT NULL, id_offre_id INT NOT NULL, nb_place INT NOT NULL, date DATE NOT NULL, point_de_depart VARCHAR(255) NOT NULL, point_arrive VARCHAR(255) NOT NULL, INDEX IDX_42C8495599DED506 (id_client_id), INDEX IDX_42C849554F479BA3 (id_conducteur_id), UNIQUE INDEX UNIQ_42C849551C13BCCF (id_offre_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE messenger_messages (id BIGINT AUTO_INCREMENT NOT NULL, body LONGTEXT NOT NULL, headers LONGTEXT NOT NULL, queue_name VARCHAR(190) NOT NULL, created_at DATETIME NOT NULL, available_at DATETIME NOT NULL, delivered_at DATETIME DEFAULT NULL, INDEX IDX_75EA56E0FB7336F0 (queue_name), INDEX IDX_75EA56E0E3BD61CE (available_at), INDEX IDX_75EA56E016BA31DB (delivered_at), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE message ADD CONSTRAINT FK_B6BD307F305E0476 FOREIGN KEY (id_rec_id) REFERENCES reclamation (id)');
        $this->addSql('ALTER TABLE message ADD CONSTRAINT FK_B6BD307F34F06E85 FOREIGN KEY (id_admin_id) REFERENCES admin (id_admin)');
        $this->addSql('ALTER TABLE offre ADD CONSTRAINT FK_AF86866F4F479BA3 FOREIGN KEY (id_conducteur_id) REFERENCES conducteur (id_conducteur)');
        $this->addSql('ALTER TABLE reclamation ADD CONSTRAINT FK_CE60640499DED506 FOREIGN KEY (id_client_id) REFERENCES client (id_client)');
        $this->addSql('ALTER TABLE reservation ADD CONSTRAINT FK_42C8495599DED506 FOREIGN KEY (id_client_id) REFERENCES client (id_client)');
        $this->addSql('ALTER TABLE reservation ADD CONSTRAINT FK_42C849554F479BA3 FOREIGN KEY (id_conducteur_id) REFERENCES conducteur (id_conducteur)');
        $this->addSql('ALTER TABLE reservation ADD CONSTRAINT FK_42C849551C13BCCF FOREIGN KEY (id_offre_id) REFERENCES offre (id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE message DROP FOREIGN KEY FK_B6BD307F305E0476');
        $this->addSql('ALTER TABLE message DROP FOREIGN KEY FK_B6BD307F34F06E85');
        $this->addSql('ALTER TABLE offre DROP FOREIGN KEY FK_AF86866F4F479BA3');
        $this->addSql('ALTER TABLE reclamation DROP FOREIGN KEY FK_CE60640499DED506');
        $this->addSql('ALTER TABLE reservation DROP FOREIGN KEY FK_42C8495599DED506');
        $this->addSql('ALTER TABLE reservation DROP FOREIGN KEY FK_42C849554F479BA3');
        $this->addSql('ALTER TABLE reservation DROP FOREIGN KEY FK_42C849551C13BCCF');
        $this->addSql('DROP TABLE abonnement');
        $this->addSql('DROP TABLE admin');
        $this->addSql('DROP TABLE avis');
        $this->addSql('DROP TABLE client');
        $this->addSql('DROP TABLE commentaire');
        $this->addSql('DROP TABLE conducteur');
        $this->addSql('DROP TABLE contrat');
        $this->addSql('DROP TABLE message');
        $this->addSql('DROP TABLE offre');
        $this->addSql('DROP TABLE payement');
        $this->addSql('DROP TABLE reclamation');
        $this->addSql('DROP TABLE reservation');
        $this->addSql('DROP TABLE messenger_messages');
    }
}
