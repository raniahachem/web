<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20230428231808 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
    
        $this->addSql('ALTER TABLE reclamation ADD id_client_id INT NOT NULL');
        $this->addSql('ALTER TABLE reclamation ADD CONSTRAINT FK_CE60640499DED506 FOREIGN KEY (id_client_id) REFERENCES client (id_client)');
        $this->addSql('CREATE INDEX IDX_CE60640499DED506 ON reclamation (id_client_id)');
    }
    
    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE reclamation DROP FOREIGN KEY FK_CE60640499DED506');
        $this->addSql('DROP INDEX IDX_CE60640499DED506 ON reclamation');
        $this->addSql('ALTER TABLE reclamation DROP id_client_id');
    }
        
}
