<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20230504151804 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('DROP INDEX IDX_603499934FFF9576 ON contrat');
        $this->addSql('ALTER TABLE contrat DROP id_abonnement_id');
        $this->addSql('ALTER TABLE offre ADD CONSTRAINT FK_AF86866F4F479BA3 FOREIGN KEY (id_conducteur_id) REFERENCES conducteur (id_conducteur)');
        $this->addSql('ALTER TABLE reservation ADD CONSTRAINT FK_42C8495599DED506 FOREIGN KEY (id_client_id) REFERENCES client (id_client)');
        $this->addSql('ALTER TABLE reservation ADD CONSTRAINT FK_42C849554F479BA3 FOREIGN KEY (id_conducteur_id) REFERENCES conducteur (id_conducteur)');
        $this->addSql('ALTER TABLE reservation ADD CONSTRAINT FK_42C849551C13BCCF FOREIGN KEY (id_offre_id) REFERENCES offre (id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE contrat ADD id_abonnement_id INT DEFAULT NULL');
        $this->addSql('CREATE INDEX IDX_603499934FFF9576 ON contrat (id_abonnement_id)');
        $this->addSql('ALTER TABLE offre DROP FOREIGN KEY FK_AF86866F4F479BA3');
        $this->addSql('ALTER TABLE reservation DROP FOREIGN KEY FK_42C8495599DED506');
        $this->addSql('ALTER TABLE reservation DROP FOREIGN KEY FK_42C849554F479BA3');
        $this->addSql('ALTER TABLE reservation DROP FOREIGN KEY FK_42C849551C13BCCF');
    }
}
