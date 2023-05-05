<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20230505031620 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE fournisseur (id INT AUTO_INCREMENT NOT NULL, nomfournisseur VARCHAR(255) NOT NULL, addressfournisseur VARCHAR(255) NOT NULL, numfournisseur INT NOT NULL, mailfournisseur VARCHAR(255) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE vehicule (id VARCHAR(255) NOT NULL, fournisseurid INT DEFAULT NULL, idconducteur INT DEFAULT NULL, type_dy_vehicule VARCHAR(255) NOT NULL, marque VARCHAR(255) NOT NULL, etat INT NOT NULL, pdf_file INT NOT NULL, kilometrage INT NOT NULL, imagev VARCHAR(255) NOT NULL, INDEX IDX_292FFF1D29F82E11 (fournisseurid), INDEX IDX_292FFF1DEECE6C79 (idconducteur), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE vehicule ADD CONSTRAINT FK_292FFF1D29F82E11 FOREIGN KEY (fournisseurid) REFERENCES fournisseur (id)');
        $this->addSql('ALTER TABLE vehicule ADD CONSTRAINT FK_292FFF1DEECE6C79 FOREIGN KEY (idconducteur) REFERENCES conducteur (id_conducteur)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE vehicule DROP FOREIGN KEY FK_292FFF1D29F82E11');
        $this->addSql('ALTER TABLE vehicule DROP FOREIGN KEY FK_292FFF1DEECE6C79');
        $this->addSql('DROP TABLE fournisseur');
        $this->addSql('DROP TABLE vehicule');
    }
}
