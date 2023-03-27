<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20230327015103 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
                // this down() migration is auto-generated, please modify it to your needs
                $this->addSql('CREATE TABLE message (id_message INT NOT NULL, id_admin INT NOT NULL, id_client INT NOT NULL, id_rec INT NOT NULL, contenu VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, date_message DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL, INDEX id_conducteur (id_admin), INDEX id_rec (id_rec), INDEX id_admin (id_admin), INDEX IDX_B6BD307FE173B1B8 (id_client), PRIMARY KEY(id_message)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
                $this->addSql('CREATE TABLE reclamation (id_rec INT AUTO_INCREMENT NOT NULL, id_client INT DEFAULT NULL, type VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, date_r DATE NOT NULL, description VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_general_ci`, INDEX id_client (id_client), PRIMARY KEY(id_rec)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_general_ci` ENGINE = InnoDB COMMENT = \'\' ');
                $this->addSql('ALTER TABLE message ADD CONSTRAINT message_ibfk_4 FOREIGN KEY (id_admin) REFERENCES admin (id_admin) ON UPDATE CASCADE ON DELETE CASCADE');
                $this->addSql('ALTER TABLE message ADD CONSTRAINT message_ibfk_2 FOREIGN KEY (id_client) REFERENCES client (id_client) ON UPDATE CASCADE ON DELETE CASCADE');
                $this->addSql('ALTER TABLE message ADD CONSTRAINT message_ibfk_3 FOREIGN KEY (id_rec) REFERENCES reclamation (id_rec) ON UPDATE CASCADE ON DELETE CASCADE');
                $this->addSql('ALTER TABLE reclamation ADD CONSTRAINT reclamation_ibfk_1 FOREIGN KEY (id_client) REFERENCES client (id_client) ON UPDATE CASCADE ON DELETE CASCADE');
                $this->addSql('DROP TABLE messenger_messages');
                $this->addSql('ALTER TABLE avis DROP FOREIGN KEY FK_8F91ABF086EDF194');
                $this->addSql('ALTER TABLE avis CHANGE id_conducteur id_conducteur INT NOT NULL');
                $this->addSql('ALTER TABLE avis ADD CONSTRAINT avis_ibfk_1 FOREIGN KEY (id_conducteur) REFERENCES conducteur (id_conducteur) ON UPDATE CASCADE ON DELETE CASCADE');
                $this->addSql('ALTER TABLE contrat DROP FOREIGN KEY FK_60349993668B4C46');
                $this->addSql('ALTER TABLE contrat DROP FOREIGN KEY FK_6034999386EDF194');
                $this->addSql('ALTER TABLE contrat ADD CONSTRAINT contrat_ibfk_1 FOREIGN KEY (id_conducteur) REFERENCES conducteur (id_conducteur) ON UPDATE CASCADE ON DELETE CASCADE');
                $this->addSql('ALTER TABLE contrat ADD CONSTRAINT contrat_ibfk_2 FOREIGN KEY (id_admin) REFERENCES admin (id_admin) ON UPDATE CASCADE ON DELETE CASCADE');
                $this->addSql('ALTER TABLE historique_reservation DROP FOREIGN KEY FK_9B66473386EDF194');
                $this->addSql('ALTER TABLE historique_reservation DROP FOREIGN KEY FK_9B664733E173B1B8');
                $this->addSql('ALTER TABLE historique_reservation ADD CONSTRAINT historique_reservation_ibfk_2 FOREIGN KEY (id_client) REFERENCES client (id_client) ON UPDATE CASCADE ON DELETE CASCADE');
                $this->addSql('ALTER TABLE historique_reservation ADD CONSTRAINT historique_reservation_ibfk_1 FOREIGN KEY (id_conducteur) REFERENCES conducteur (id_conducteur) ON UPDATE CASCADE ON DELETE CASCADE');
                $this->addSql('ALTER TABLE offre DROP FOREIGN KEY FK_AF86866F86EDF194');
                $this->addSql('ALTER TABLE offre CHANGE id_offre id_offre INT NOT NULL, CHANGE id_conducteur id_conducteur INT NOT NULL');
                $this->addSql('ALTER TABLE offre ADD CONSTRAINT offre_ibfk_1 FOREIGN KEY (id_conducteur) REFERENCES conducteur (id_conducteur) ON UPDATE CASCADE ON DELETE CASCADE');
                $this->addSql('ALTER TABLE payement CHANGE paymentId paymentId INT NOT NULL');
                $this->addSql('ALTER TABLE reservation DROP FOREIGN KEY FK_42C8495586EDF194');
                $this->addSql('ALTER TABLE reservation DROP FOREIGN KEY FK_42C84955E173B1B8');
                $this->addSql('ALTER TABLE reservation CHANGE id_reservation id_reservation INT NOT NULL, CHANGE id_conducteur id_conducteur INT NOT NULL, CHANGE id_client id_client INT NOT NULL');
                $this->addSql('ALTER TABLE reservation ADD CONSTRAINT reservation_ibfk_1 FOREIGN KEY (id_client) REFERENCES client (id_client) ON UPDATE CASCADE ON DELETE CASCADE');
                $this->addSql('ALTER TABLE reservation ADD CONSTRAINT reservation_ibfk_2 FOREIGN KEY (id_conducteur) REFERENCES conducteur (id_conducteur) ON UPDATE CASCADE ON DELETE CASCADE');
        
    }

    public function down(Schema $schema): void
    {
            $this->addSql('CREATE TABLE messenger_messages (id BIGINT AUTO_INCREMENT NOT NULL, body LONGTEXT NOT NULL, headers LONGTEXT NOT NULL, queue_name VARCHAR(190) NOT NULL, created_at DATETIME NOT NULL, available_at DATETIME NOT NULL, delivered_at DATETIME DEFAULT NULL, INDEX IDX_75EA56E0FB7336F0 (queue_name), INDEX IDX_75EA56E0E3BD61CE (available_at), INDEX IDX_75EA56E016BA31DB (delivered_at), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE message DROP FOREIGN KEY message_ibfk_4');
        $this->addSql('ALTER TABLE message DROP FOREIGN KEY message_ibfk_2');
        $this->addSql('ALTER TABLE message DROP FOREIGN KEY message_ibfk_3');
        $this->addSql('ALTER TABLE reclamation DROP FOREIGN KEY reclamation_ibfk_1');
        $this->addSql('DROP TABLE message');
        $this->addSql('DROP TABLE reclamation');
        $this->addSql('ALTER TABLE avis DROP FOREIGN KEY avis_ibfk_1');
        $this->addSql('ALTER TABLE avis CHANGE id_conducteur id_conducteur INT DEFAULT NULL');
        $this->addSql('ALTER TABLE avis ADD CONSTRAINT FK_8F91ABF086EDF194 FOREIGN KEY (id_conducteur) REFERENCES conducteur (id_conducteur)');
        $this->addSql('ALTER TABLE contrat DROP FOREIGN KEY contrat_ibfk_1');
        $this->addSql('ALTER TABLE contrat DROP FOREIGN KEY contrat_ibfk_2');
        $this->addSql('ALTER TABLE contrat ADD CONSTRAINT FK_60349993668B4C46 FOREIGN KEY (id_admin) REFERENCES admin (id_admin)');
        $this->addSql('ALTER TABLE contrat ADD CONSTRAINT FK_6034999386EDF194 FOREIGN KEY (id_conducteur) REFERENCES conducteur (id_conducteur)');
        $this->addSql('ALTER TABLE historique_reservation DROP FOREIGN KEY historique_reservation_ibfk_2');
        $this->addSql('ALTER TABLE historique_reservation DROP FOREIGN KEY historique_reservation_ibfk_1');
        $this->addSql('ALTER TABLE historique_reservation ADD CONSTRAINT FK_9B66473386EDF194 FOREIGN KEY (id_conducteur) REFERENCES conducteur (id_conducteur)');
        $this->addSql('ALTER TABLE historique_reservation ADD CONSTRAINT FK_9B664733E173B1B8 FOREIGN KEY (id_client) REFERENCES client (id_client)');
        $this->addSql('ALTER TABLE offre DROP FOREIGN KEY offre_ibfk_1');
        $this->addSql('ALTER TABLE offre CHANGE id_offre id_offre INT AUTO_INCREMENT NOT NULL, CHANGE id_conducteur id_conducteur INT DEFAULT NULL');
        $this->addSql('ALTER TABLE offre ADD CONSTRAINT FK_AF86866F86EDF194 FOREIGN KEY (id_conducteur) REFERENCES conducteur (id_conducteur)');
        $this->addSql('ALTER TABLE payement CHANGE paymentId paymentId INT AUTO_INCREMENT NOT NULL');
        $this->addSql('ALTER TABLE reservation DROP FOREIGN KEY reservation_ibfk_1');
        $this->addSql('ALTER TABLE reservation DROP FOREIGN KEY reservation_ibfk_2');
        $this->addSql('ALTER TABLE reservation CHANGE id_reservation id_reservation INT AUTO_INCREMENT NOT NULL, CHANGE id_client id_client INT DEFAULT NULL, CHANGE id_conducteur id_conducteur INT DEFAULT NULL');
        $this->addSql('ALTER TABLE reservation ADD CONSTRAINT FK_42C8495586EDF194 FOREIGN KEY (id_conducteur) REFERENCES conducteur (id_conducteur)');
        $this->addSql('ALTER TABLE reservation ADD CONSTRAINT FK_42C84955E173B1B8 FOREIGN KEY (id_client) REFERENCES client (id_client)');
    }
}
